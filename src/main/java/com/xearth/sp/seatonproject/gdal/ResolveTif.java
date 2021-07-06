package com.xearth.sp.seatonproject.gdal;


import java.util.HashMap;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.xearth.sp.seatonproject.dao.WeatherDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 解析Tif图为GeoJSON
 */
@Component
public class ResolveTif {

    @Autowired
    WeatherDao weatherDao;

    // 文件数量
    private static final Integer fileNum = 24;
    // 文件名
    private String[] nameArray = null;
    // 源文件（tif）
    private String[] sourceUrlArray = null;
    // 输出文件（GeoJSON）
    private String[] outUrlArray = null;
    // key企业编码 value企业坐标
    private HashMap<String, String> pointMap = null;


    @PostConstruct
    public void initData() {
        nameArray = new String[fileNum];
        sourceUrlArray = new String[fileNum];
        outUrlArray = new String[fileNum];
        int num = 3;
        for (int i = 0; i < fileNum; i++) {
            String name = "CHINA_" + num;
            String sourceUrl = "D:/issue/CHINA_PRE_202106070800/" + name + ".tif";
            String outUrl = "D:/issue/CHINA_PRE_202106070800/" + name + ".geojson";
            nameArray[i] = name;
            sourceUrlArray[i] = sourceUrl;
            outUrlArray[i] = outUrl;
            num += 3;
        }
        pointMap = new HashMap<>();
        pointMap.put("3900100145", "POINT (117.422 38.831)");
        pointMap.put("1400100201", "POINT (121.056878 30.591937)");
        pointMap.put("1700101341", "POINT (118.923724 32.163427)");
    }

    @Scheduled(cron = "0 0 5 */3 * ?")
    public void resolve() {
        Long startTime = System.currentTimeMillis();
        System.out.println("===========开始Tif解析：");
/*        // 注册
        gdal.AllRegister();
        ogr.RegisterAll();
        sourceUrlArray = gdal.GeneralCmdLineProcessor(sourceUrlArray);

        Dataset dataset = null;
        DataSource dataSource = null;
        for (int i = 0, len = sourceUrlArray.length; i < len; i++) {
            // 读取影像数据
            dataset = gdal.Open(sourceUrlArray[i], gdalconstConstants.GA_ReadOnly);
            if (dataset == null) {
                System.err.println("GDALOpen failed - " + gdal.GetLastErrorNo());
                System.err.println(gdal.GetLastErrorMsg());
                System.exit(2);
            }
            Band band = dataset.GetRasterBand(1);
            if (band == null) {
                System.err.println("Band does not exist on dataset");
                System.err.println("GDALOpen failed - " + gdal.GetLastErrorNo());
                System.err.println(gdal.GetLastErrorMsg());
                System.exit(3);
            }

            Driver driver = ogr.GetDriverByName("GeoJSON");
            dataSource = driver.CreateDataSource(outUrlArray[i]);
            Layer outputLayer = dataSource.CreateLayer(nameArray[i]);
            FieldDefn field_def = new FieldDefn("DN", ogr.OFSTJSON);
            outputLayer.CreateField(field_def);
            gdal.Polygonize(band, null, outputLayer, 0);
            System.out.println(outUrlArray[i]);
        }
        // 销毁
        dataSource.delete();
        dataset.delete();*/

        System.out.println("===========开始GeoJSON解析：");
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(4);
        ResolveGeoJson resolveGeoJson = null;
        for (String filepath : outUrlArray) {
            resolveGeoJson = new ResolveGeoJson(filepath, pointMap);
            CompletableFuture.supplyAsync(resolveGeoJson, fixedThreadPool).whenComplete((result, exception) -> {
                weatherDao.saveAll(result);
                Long endTime = System.currentTimeMillis();
                Long time = endTime - startTime;
                System.out.println("===========累计耗时：" + time/1000 + "秒");
            }).exceptionally((exception) -> {
                exception.printStackTrace();
                return null;
            });
        }
    }
}
