package com.xearth.sp.seatonproject.gdal;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xearth.sp.seatonproject.pojo.Weather;
import org.geotools.geojson.GeoJSONUtil;
import org.geotools.geojson.geom.GeometryJSON;
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.locationtech.jts.geom.*;
import org.locationtech.jts.io.WKTReader;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.function.Supplier;

/**
 * 解析GeoJSON文件并判断点面相交入库
 */
public class ResolveGeoJson implements Supplier<List<Weather>> {

    String filepath;
    HashMap<String, String> pointMap;

    public ResolveGeoJson(){

    }

    public ResolveGeoJson(String filepath, HashMap<String, String> pointMap){
        this.filepath = filepath;
        this.pointMap = pointMap;
    }

    public List<Weather> get() {
        System.out.println("线程：" + Thread.currentThread().getName() + " 文件：" + filepath);
        FileReader fr = null;
        BufferedReader br = null;
        try {
            File file = new File(filepath);
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            String s = null;
            String ps = "";
            while ((s = br.readLine()) != null) {
                ps += s;
            }
            JSONObject jsonObject = JSONObject.parseObject(ps);
            JSONArray features = jsonObject.getJSONArray("features");
            String name = jsonObject.getString("name");
            Reader reader = null;
            Geometry geometry = null;
            Point point = null;
            Polygon polygon = null;
            String featureStr = "", DN = "";
            WKTReader wktReader = new WKTReader(JTSFactoryFinder.getGeometryFactory(null));
            GeometryJSON geoJson = new GeometryJSON();
            List<Weather> list = new ArrayList<>();
            for (String code : pointMap.keySet()) {
                for (int i = 0, len = features.size(); i < len; i++) {
                    featureStr = features.get(i).toString();
                    DN = features.getJSONObject(i).getJSONObject("properties").getString("DN");
                    // json转换wkt
                    reader = GeoJSONUtil.toReader(featureStr);
                    geometry = geoJson.read(reader);
                    point = (Point) wktReader.read(pointMap.get(code));
                    polygon = (Polygon) wktReader.read(getPolygonByInteriorPoint((Polygon) geometry).toString());
                    if (polygon.contains(point)) {
                        Weather weather = new Weather();
                        weather.setCode(code);
                        weather.setRainfall(DN);
                        weather.setTimeslot(name);
                        weather.setCreatime(new Date());
                        list.add(weather);
                        break;
                    }
                }
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                fr.close();
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 使用jts的两线相交方法解决线的自相交问题
     * @param geometry
     * @return
     */
    private Polygon getPolygonByInteriorPoint(Polygon geometry) {
        LineString exteriorRing = geometry.getExteriorRing();
        Coordinate[] coordinates = exteriorRing.union(exteriorRing).getCoordinates();
        return geometry.getFactory().createPolygon(coordinates);
    }
}
