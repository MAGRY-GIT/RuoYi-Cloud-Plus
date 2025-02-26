package com.cdzeroly.wvp.i1.bean;

import com.cdzeroly.common.json.utils.JsonUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : MGARY
 * @description : 图像分析参数
 * @createDate : 2025/2/17 16:36
 */
@Data
public class ImageAnalysisParamsDto {

    private byte channelNo;
    // 预置位号，从1开始，无预置位传FFH
    private byte presettingNo;


    // 智能分析启用标志。0：关闭；1：开启
    private byte analysisEnableFlag;

    // 告警类型编码列表，告警类型编码参考
    private AlarmTypeInfo alarmTypeInfo;

    // 告警区域信息
    private AlarmRegion alarmRegion;


    public void setAlarmTypeInfo(ByteBuf data, byte size) {
        alarmTypeInfo = new AlarmTypeInfo(data, size);
    }


    public void setAlarmRegion(ByteBuf data, byte size) {
        alarmRegion = new AlarmRegion(data, size);
    }

    public byte[] toBytes() {
        ByteBuf buffer = Unpooled.buffer();
        buffer.writeByte(channelNo);
        buffer.writeByte(presettingNo);
        buffer.writeByte(analysisEnableFlag);

        if (alarmTypeInfo != null) {
            byte[] alarmTypeBytes = alarmTypeInfo.toByte();
            buffer.writeBytes(alarmTypeBytes);
        }

        if (alarmRegion != null) {
            byte[] alarmRegionBytes = alarmRegion.toByte();
            buffer.writeBytes(alarmRegionBytes);
        }

        byte[] result = new byte[buffer.readableBytes()];
        buffer.readBytes(result);
        buffer.release();
        return result;
    }


    // 告警类型编码列表，告警类型编码参考
    @Data
    public static class AlarmTypeInfo {

        // 告警类型编码列表
        private List<IdentifyType> alarmTypes;
        // 告警阈值列表，取值范围[1-100]
        private List<Byte> alarmThresholds;

        public AlarmTypeInfo(List<Byte> alarmThresholds, List<IdentifyType> alarmTypes) {
            this.alarmThresholds = alarmThresholds;
            this.alarmTypes = alarmTypes;
        }

        public AlarmTypeInfo(ByteBuf data, byte size) {
            this.alarmTypes = new ArrayList<>(size);
            this.alarmThresholds = new ArrayList<>(size);

            for (int i = 0; i < size; i++) {
                alarmTypes.add(IdentifyType.getById(data.readByte()).orElse(IdentifyType.OTHER));
                alarmThresholds.add(data.readByte());
            }
            data.release();
        }

        public byte[] toByte() {
            byte[] bytes = new byte[alarmTypes.size() * 2];
            int index = 0;
            for (int i = 0; i < alarmTypes.size(); i++) {
                bytes[index++] = alarmTypes.get(i).getCode();
                bytes[index++] = alarmThresholds.get(i);
            }
            return bytes;
        }


    }

    public String alarmRegionToJson() {
        return JsonUtils.toJsonString(alarmRegion);
    }
    public String alarmTypeInfoToJson() {
        return JsonUtils.toJsonString(alarmTypeInfo);
    }


    // 告警区域信息
    @Data
    public static class AlarmRegion {

        private List<RegionalInfo> regionalInfos;

        public AlarmRegion(List<RegionalInfo> regionalInfos) {
            this.regionalInfos = regionalInfos;
        }

        public AlarmRegion(ByteBuf data, byte size) {
            this.regionalInfos = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                byte regionEnableFlags = data.readByte();
                //数据包数
                byte regionPointCounts = data.readByte();
                //去除全部
                ByteBuf buffer = Unpooled.buffer(regionPointCounts * 2);
                buffer.readBytes(buffer);
                RegionalInfo regionalInfo = new RegionalInfo(buffer, regionPointCounts);

                regionalInfos.add(regionalInfo);

            }
            data.release();
        }

        public byte[] toByte() {
            ByteBuf buffer = Unpooled.buffer();
            for (RegionalInfo regionalInfo : regionalInfos) {
                buffer.writeBytes(regionalInfo.toBytes());
            }
            byte[] result = new byte[buffer.readableBytes()];
            buffer.readBytes(result);
            buffer.release();
            return result;
        }

        @Data
        public static class RegionalInfo {

            // 区域作用标志列表，0：对划定区域内的隐患不分析；1：对划定区域内的隐患分析、告警
            private Byte regionEnableFlags;

            // 区域坐标点数目列表，取值范围[3-8]
            private Byte regionPointCounts;
            // 区域坐标点列表，每个区域的坐标点数目由regionPointCounts决定
            private List<Point> regionPoints;

            public RegionalInfo() {
            }

            public RegionalInfo(ByteBuf data, byte size) {

                List<Point> points = new ArrayList<>(size);
                for (int j = 0; j < size; j++) {
                    points.add(new Point(data.readByte(), data.readByte()));
                }
                this.regionPointCounts = size;
                this.regionPoints = points;

                data.release();
            }

            public byte[] toBytes() {
                byte[] bytes = new byte[regionPoints.size() * 2 + 2];
                bytes[0] = regionEnableFlags;
                bytes[1] = regionPointCounts;
                for (int i = 0; i < regionPoints.size(); i++) {
                    Point point = regionPoints.get(i);
                    bytes[i * 2 + 2] = point.getX();
                    bytes[i * 2 + 3] = point.getY();
                }

                return bytes;
            }

            @Data
            public static class Point {
                private Byte x;
                private Byte y;

                public Point(Byte x, Byte y) {
                    this.x = x;
                    this.y = y;
                }
            }
        }
    }

}
