package com.cdzeroly.weather;

import com.cdzeroly.common.core.exception.ServiceException;
import com.cdzeroly.weather.utils.MapImagesUtil;
import com.cdzeroly.weather.utils.WeatherUtils;
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

import javax.print.PrintException;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

/**
 * @author MAGRY
 */
public class TempAnalysis {

    /**
     * 主程序入口
     * 本程序主要用于处理气象数据，并将其可视化为地图图像
     * 具体步骤包括：读取NetCDF气象数据文件，创建栅格数据图层，设置地图视图，以及导出可视化图像
     */
    public static   List<File> alysis(String ncPath) throws Exception {
        // 指定NetCDF文件路径
        String fn = "C:\\Users\\Administrator\\Downloads\\四川省1\\四川省.shp";
        //读取色阶
        LegendScheme als = MapImagesUtil.readFromLgs("C:\\Users\\Administrator\\Downloads\\色阶\\TEM.lgs");
        // 创建MeteoDataInfo对象以处理气象数据
        MeteoDataInfo meteoDataInfo = new MeteoDataInfo();

        // 打开NetCDF数据文件
        meteoDataInfo.openNetCDFData(ncPath);
        List<String> excludedItems = Arrays.asList( "LAT","LON");
        // 创建矢量图层
        VectorLayer vectorLayer = getVectorLayer(fn);
        List<File> files = new ArrayList<>();
        meteoDataInfo.getDataInfo().getVariables().stream().filter(variable -> !excludedItems.contains(variable.getName())).forEach(variable -> {
            //绘制图层
            try {

                File file = drawLayers(meteoDataInfo, variable.getName(), vectorLayer, als);
                files.add(file);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return files;
    }

    private static File drawLayers(MeteoDataInfo meteo, String variableName, VectorLayer scmap, LegendScheme als) throws PrintException,IOException {
        // 设置变量名称
        meteo.setVariableName(variableName);
        // 创建栅格数据图层
        GridData gridData = meteo.getGridData();

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
        String name =  variableName +","+ gridData.getBorderYMax() + "-"+  gridData.getBorderXMax() + "," +  gridData.getBorderYMin()+"-"+  gridData.getBorderXMin()+"-";
        // 创建临时文件
        File tempFile = File.createTempFile(name, ".png");

        String imagePath = "C:\\Users\\Administrator\\Desktop\\" + name+".png";

        //将地图布局导出为图像文件
        layout.exportToPicture(tempFile.getAbsolutePath());

        WeatherUtils.transparentProcessing(tempFile.getAbsolutePath());
        return tempFile;
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




}
