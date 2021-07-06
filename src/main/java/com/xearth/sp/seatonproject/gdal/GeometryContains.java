package com.xearth.sp.seatonproject.gdal;

import java.io.*;
import java.util.HashMap;

import com.alibaba.fastjson.JSONArray;
import org.geotools.geojson.GeoJSONUtil;
import org.geotools.geojson.geom.GeometryJSON;
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.locationtech.jts.geom.*;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;

public class GeometryContains {

    public void geometryContain(
            HashMap<String, JSONArray> polygonMap,
            HashMap<String, String> pointMap) throws IOException, ParseException {
        System.out.println("===========开始Geometry计算：0");
        Long startTime = System.currentTimeMillis();

        WKTReader wktReader = new WKTReader(JTSFactoryFinder.getGeometryFactory(null));
        GeometryJSON geoJson = new GeometryJSON();
        for (String code : pointMap.keySet()) {
            for (String key : polygonMap.keySet()) {
                for (int i = 0, len = polygonMap.get(key).size(); i < len; i++) {
                    String str = polygonMap.get(key).get(i).toString();
                    String DN = polygonMap.get(key).getJSONObject(i).getString("properties");
                    // json转换wkt
                    Reader reader = GeoJSONUtil.toReader(str);
                    Geometry geometry = geoJson.read(reader);
                    Point point = (Point) wktReader.read(pointMap.get(code));
                    // 使用jts的两线相交方法解决线的自相交问题
                    Polygon polygon = (Polygon) wktReader.read(getPolygonByInteriorPoint((Polygon) geometry).toString());
                    try {
                        // 判断点是否在面内
                        if (polygon.contains(point)) {
                            System.out.println(code + "：" + " row：" + i);
                            break;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        continue;
                    }
                }
            }
        }

        Long endTime = System.currentTimeMillis();
        Long time = endTime - startTime;
        System.out.println("===========计算Geometry耗时：" + time/1000 + "秒");
    }

    /**
     * 使用jts的两线相交方法解决线的自相交问题
     * @param geometry
     * @return
     */
    private static Polygon getPolygonByInteriorPoint(Polygon geometry) {
        LineString exteriorRing = geometry.getExteriorRing();
        Coordinate[] coordinates = exteriorRing.union(exteriorRing).getCoordinates();
        return geometry.getFactory().createPolygon(coordinates);
    }
}
