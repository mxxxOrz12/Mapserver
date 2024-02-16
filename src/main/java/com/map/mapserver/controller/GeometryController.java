package com.map.mapserver.controller;

import com.map.mapserver.pojo.GeometryModel;
import com.map.mapserver.service.GeometryService;
import com.map.mapserver.service.MapDao;
import com.vividsolutions.jts.geom.*;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.geotools.geojson.geom.GeometryJSON;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/map")
public class GeometryController {

    @Autowired
    private GeometryService geometryService;

    @Autowired
    private MapDao mapDao;


    @ResponseBody
    @CrossOrigin
    @GetMapping("/geometry")
    public JSONObject getPoint1(@RequestParam("category") String category, @RequestParam("start") Integer start, @RequestParam("end") Integer end) {
        List<GeometryModel> result = geometryService.getDynastyGeom(category, start, end);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "FeatureCollection");
        JSONArray featuresArray = new JSONArray();
        for (GeometryModel g : result) {
            JSONObject feature = new JSONObject();
            feature.put("type", "Feature");
            JSONObject properties = new JSONObject();
            properties.put("gid", g.getGId());
            properties.put("namech", g.getNameCh());
            properties.put("presloc", g.getPresLoc());
            properties.put("typech", g.getTypeCh());
            properties.put("levrank", g.getLevRank());
            properties.put("begyr", g.getBegYr());
            properties.put("begrule", g.getBegRule());
            properties.put("endyr", g.getEndYr());
            properties.put("endrule", g.getEndRule());
            properties.put("geosrc", g.getGeoSrc());
            properties.put("entdate", g.getEndDate());
            properties.put("begchgty", g.getBegChgTy());
            properties.put("endchgty", g.getEndChgTy());
            feature.put("properties", properties);
            JSONObject geometry = geometryToJson(g.getGeometry());
            feature.put("geometry", geometry);
            featuresArray.add(feature);
        }
        jsonObject.put("features", featuresArray);
        return jsonObject;
    }

    private JSONObject geometryToJson(Geometry geometry) {
        Map<String, Object> jsonMap = new LinkedHashMap<>();
        jsonMap.put("type", geometry.getGeometryType());
        JSONArray coordinates = new JSONArray();

        if (geometry instanceof MultiPolygon) {
            MultiPolygon multiPolygon = (MultiPolygon) geometry;
            for (int i = 0; i < multiPolygon.getNumGeometries(); i++) {
                Polygon polygon = (Polygon) multiPolygon.getGeometryN(i);
                JSONArray polygonCoordinates = processPolygonCoordinates(polygon);
                coordinates.add(polygonCoordinates);
            }
        } else if (geometry instanceof Point) {
            Point point = (Point) geometry;
            Coordinate coordinate = point.getCoordinate();
            JSONArray coordinateArray = new JSONArray();
            coordinateArray.add(coordinate.x);
            coordinateArray.add(coordinate.y);
            coordinates.add(coordinateArray);
        } else {
            coordinates.addAll(processCoordinates(geometry.getCoordinates()));
        }

        if (coordinates.size() == 1) {
            jsonMap.put("coordinates", coordinates.get(0));
        } else {
            jsonMap.put("coordinates", coordinates);
        }

        return new JSONObject(jsonMap);
    }

    private JSONArray processPolygonCoordinates(Polygon polygon) {
        JSONArray polygonCoordinates = new JSONArray();
        polygonCoordinates.add(processCoordinates(polygon.getExteriorRing().getCoordinates()));

        for (int k = 0; k < polygon.getNumInteriorRing(); k++) {
            Coordinate[] interiorCoordinates = polygon.getInteriorRingN(k).getCoordinates();
            polygonCoordinates.add(processCoordinates(interiorCoordinates));
        }

        return polygonCoordinates;
    }

    private JSONArray processCoordinates(Coordinate[] coordinates) {
        JSONArray coordinateList = new JSONArray();
        for (Coordinate coordinate : coordinates) {
            JSONArray coordinateArray = new JSONArray();
            coordinateArray.add(coordinate.x);
            coordinateArray.add(coordinate.y);
            coordinateList.add(coordinateArray);
        }
        return coordinateList;
    }
}