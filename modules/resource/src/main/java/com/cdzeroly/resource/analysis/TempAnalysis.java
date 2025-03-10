package com.cdzeroly.resource.analysis;

import com.cdzeroly.common.core.exception.ServiceException;
import org.meteoinfo.common.Extent;
import org.meteoinfo.data.GridData;
import org.meteoinfo.data.meteodata.MeteoDataInfo;
import org.meteoinfo.geo.layer.VectorLayer;
import org.meteoinfo.geo.layout.MapLayout;
import org.meteoinfo.geo.mapdata.MapDataManage;
import org.meteoinfo.geo.mapview.MapView;
import org.meteoinfo.geo.meteodata.DrawMeteoData;
import org.meteoinfo.geometry.legend.LegendScheme;
import org.meteoinfo.geometry.legend.PolygonBreak;

import javax.imageio.ImageIO;
import javax.print.PrintException;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TempAnalysis {

    /**
     * 主程序入口
     * 本程序主要用于处理气象数据，并将其可视化为地图图像
     * 具体步骤包括：读取NetCDF气象数据文件，创建栅格数据图层，设置地图视图，以及导出可视化图像
     */
    public static void alysis(String ncPath) throws Exception {
        // 指定NetCDF文件路径
//        String path = "C:\\Users\\Administrator\\Downloads\\Z_NAFP_C_BABJ_20250303002848_P_CLDAS_RT_ASI_0P0625_DAY-TMP-2025030200.nc";
        String fn = "C:\\Users\\Administrator\\Downloads\\四川省1\\四川省.shp";
        //读取色阶
        LegendScheme als = readFromLgs("C:\\Users\\Administrator\\Downloads\\色阶\\TEM.lgs");
        // 创建MeteoDataInfo对象以处理气象数据
        MeteoDataInfo meteoDataInfo = new MeteoDataInfo();

        // 打开NetCDF数据文件
        meteoDataInfo.openNetCDFData(ncPath);
        List<String> excludedItems = Arrays.asList( "LAT","LON");
        // 创建矢量图层
        VectorLayer vectorLayer = getVectorLayer(fn);
        meteoDataInfo.getDataInfo().getVariables().stream().filter(variable -> !excludedItems.contains(variable.getName())).forEach(variable -> {
            //绘制图层
            try {

                drawLayers(meteoDataInfo, variable.getName(), vectorLayer,als);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    private static void drawLayers(MeteoDataInfo meteo, String variableName, VectorLayer scmap, LegendScheme als) throws PrintException,IOException {

        // 创建栅格数据图层
        GridData gridData = meteo.getGridData();
        // 设置变量名称
        meteo.setVariableName(variableName);


//        VectorLayer layer = DrawMeteoData.createShadedLayer(gridData,als,"","",true);
        VectorLayer layer = DrawMeteoData.createShadedLayer(gridData,"","",true);
        layer = layer.clip(scmap);
        // 初始化地图视图
        MapView view = new MapView();
        // 将栅格数据图层添加到地图视图中
        view.addLayer(layer);
        view.addLayer(scmap);
        /*
         * 以下为通用方法,可以抽成工具类
         * 这部分代码用于设置地图布局和导出地图图像
         */
        MapLayout layout  = new MapLayout();
        //去除图形边框
        layout.getActiveMapFrame().setDrawNeatLine(false);
        //区域边界
        Extent extent = view.getExtent();
        //设置矩形的宽和高
        Rectangle bounds = new Rectangle(800, (int) (800 * 1D / extent.getWidth() * extent.getHeight()));
        //设置地图边框(分辨率)
        layout.setPageBounds(new Rectangle(0, 0, bounds.width, bounds.height));
        //设置页面边框(控制页面平铺)
        layout.getActiveMapFrame().setLayoutBounds(new Rectangle(0, 0, bounds.width, bounds.height));
        //将地图视图设置到活动地图框架中()
        layout.getActiveMapFrame().setMapView(view);
        String name =  variableName +","+ gridData.getBorderYMax() + "-"+  gridData.getBorderXMax() + "," +  gridData.getBorderYMin()+"-"+  gridData.getBorderXMin();
        //指定导出图像的路径
        String imagePath = "C:\\Users\\Administrator\\Desktop\\" + name+".png";

        //将地图布局导出为图像文件
        layout.exportToPicture(imagePath);
        //透明处理
        transparentProcessing(imagePath);
    }

    /**
     * 获取矢量图层
     * @return
     * @throws Exception
     */
    private static VectorLayer getVectorLayer(String  shapeFilepath) throws Exception {
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

    private static void transparentProcessing(String imagePath) throws IOException {
        //读取图片
        BufferedImage bi = ImageIO.read(new File(imagePath));
        //类型转换
        BufferedImage img = new BufferedImage(bi.getWidth(), bi.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = (Graphics2D) img.getGraphics();
        g.drawImage(bi, null, 0, 0);
        //透明处理
        int alpha = 0;
        for(int i=img.getMinY(); i<img.getHeight(); i++){
            for(int j=img.getMinX(); j<img.getWidth(); j++){
                int rgb = img.getRGB(j, i);
                //透明部分不需要处理
                if(rgb < 0){
                    int R = (rgb & 0xff0000) >> 16;
                    int G = (rgb & 0xff00) >> 8;
                    int B = (rgb & 0xff);
                    //将白色剔除
                    Color color = Color.white;
                    if(color.getRed() == R && color.getGreen() == G && color.getBlue() == B){
                        alpha = 0;
                    }
                    else {
                        alpha = 255;
                    }
                    rgb = (alpha << 24) | (rgb & 0x00ffffff);
                    img.setRGB(j, i, rgb);
                }
            }
        }
        //释放资源
        g.dispose();
        ImageIO.write(img, "png", new File(imagePath));
    }

    public static LegendScheme readFromLgs(String path) throws Exception {
        LegendScheme scheme = new LegendScheme();
        scheme.importFromXMLFile(path, false);
        return scheme;
    }

    // 辅助方法：添加站点数据到列表
    private static void addStationData(List<Map<String, Object>> list, String stationName, double lon, double lat, double tem) {
        Map<String, Object> item = new HashMap<>();
        item.put("Station_Name", stationName);
        item.put("Lon", lon);
        item.put("Lat", lat);
        item.put("TEM", tem);
        list.add(item);
    }
}
