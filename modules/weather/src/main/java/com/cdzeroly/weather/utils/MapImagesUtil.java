package com.cdzeroly.weather.utils;

import cn.hutool.core.bean.BeanUtil;
import com.cdzeroly.common.core.exception.ServiceException;
import com.cdzeroly.weather.domain.vo.StationDataVo1;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.meteoinfo.common.Extent;
import org.meteoinfo.data.GridData;
import org.meteoinfo.data.GridDataSetting;
import org.meteoinfo.data.StationData;
import org.meteoinfo.geo.analysis.InterpolationMethods;
import org.meteoinfo.geo.analysis.InterpolationSetting;
import org.meteoinfo.geo.layer.VectorLayer;
import org.meteoinfo.geo.layout.MapLayout;
import org.meteoinfo.geo.mapdata.MapDataManage;
import org.meteoinfo.geo.mapview.MapView;
import org.meteoinfo.geo.util.GeoMathUtil;
import org.meteoinfo.geometry.legend.LegendScheme;
import org.meteoinfo.geometry.legend.PolygonBreak;

import java.awt.*;
import java.util.List;
import java.util.Map;

/**
 * @author : MGARY
 * @description :
 * @createDate : 2025/3/17 14:18
 */
public class MapImagesUtil {
    /**
     * 创建视图
     * @param view  地图视图
     * @return
     */
     public static   MapLayout getMapLayout(MapView view){

         MapLayout layout  = new MapLayout();
         //去除图形边框
         layout.getActiveMapFrame().setDrawNeatLine(false);
         //区域边界
         Extent extent = view.getExtent();
         //设置矩形的宽和高
         Rectangle bounds = new Rectangle(800, (int) (800 * 1D / extent.getWidth() * extent.getHeight()));
         //设置地图边框
         layout.setPageBounds(new Rectangle(0, 0, bounds.width, bounds.height));
         //设置页面边框
         layout.getActiveMapFrame().setLayoutBounds(new Rectangle(0, 0, bounds.width, bounds.height));
         layout.getActiveMapFrame().setMapView(view);

         return layout;
     }
    /**
     * 获取矢量图层
     */
    public static VectorLayer getVectorLayer(String  shapeFilepath) throws Exception {
        if (shapeFilepath == null){
            throw ServiceException.build ("请输入矢量图层路径");
        }
        //读取地图A
        VectorLayer scmap = MapDataManage.readMapFile_ShapeFile(shapeFilepath);
        //描述地图边界线
        PolygonBreak pb = (PolygonBreak) scmap.getLegendScheme().getLegendBreak(0);
        //是否设置填充
        pb.setDrawFill(false);
        //设置轮廓大小
        pb.setOutlineSize(2f);
        //设置轮廓颜色
        pb.setOutlineColor(Color.black);
        return scmap;
    }

    /**
     * 获取色标对象
     * @param path
     * @return
     * @throws Exception
     */
    public static LegendScheme readFromLgs(String path) throws Exception {
        LegendScheme scheme = new LegendScheme();
        scheme.importFromXMLFile(path, false);
        return scheme;
    }

    /**
     *   生成站点数据
     * @param list
     * @param name
     * @return
     */
    public static StationData getStationData(List<StationDataVo1> list, String name) {
        //创建站点格点
        StationData stationData = new StationData();
        //循环数据将值塞入格点中
        for (StationDataVo1 stationData1 : list) {
            Map<String, Object> map = BeanUtil.beanToMap(stationData1);
            Object value = map.get(name);
            double value1;
            if (value instanceof Long) {
                value1 = Double.longBitsToDouble((Long) value);
            } else if (value instanceof Double) {
                value1 = (Double) value;
            } else {
                value1 = Double.parseDouble(value.toString());
            }


            stationData.addData(stationData1.getStationName(), stationData1.getLongitude(), stationData1.getLatitude(), value1);
        }
        return stationData;
    }


    @Nullable
    public static GridData getGridData(List<StationDataVo1> list, VectorLayer altMap, StationData stationData) {
        //创建网格设置参数
        GridDataSetting gridDataSetting = new GridDataSetting();
        //设定数据区域
        gridDataSetting.dataExtent = altMap.getExtent();
        //设定格点数
        gridDataSetting.xNum = list.size();
        gridDataSetting.yNum = list.size();


        //创建插值设置
        InterpolationSetting interpolationSetting = new InterpolationSetting();
        //设定格点配置
        interpolationSetting.setGridDataSetting(gridDataSetting);
        //设定插值方法
        interpolationSetting.setInterpolationMethod(InterpolationMethods.KRIGING);
        //设定搜索半径
        interpolationSetting.setRadius(10);
        //设置最小点数
        interpolationSetting.setMinPointNum(1);
        interpolationSetting.setBeta(10);
        //插值到格点
        return GeoMathUtil.interpolateData(stationData, interpolationSetting);
    }

}
