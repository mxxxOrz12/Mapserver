package com.map.mapserver.controller;


import com.map.mapserver.pojo.Point;
import com.map.mapserver.pojo.Result;
import com.map.mapserver.service.MapService;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.io.WKTWriter;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/history")
public class MapController {

    @Autowired
    private MapService mapService;

    @ResponseBody
    @GetMapping("/point")

    public Result<JSONObject> getPoint(@RequestParam("gId") Integer gId) {
        Point pointModel = mapService.getCntyPointByGid(gId); // 假设有一个方法可以获取一个点的信息

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("gid", pointModel.getGId());
        jsonObject.put("namech", pointModel.getNameCh());

        JSONObject geometry = new JSONObject();
        geometry.put("type", pointModel.getGeometry().getGeometryType());

        JSONArray coordinateArray = new JSONArray();
        Coordinate[] coordinates = pointModel.getGeometry().getCoordinates();
        JSONObject coor = new JSONObject();
        coor.put("longitude", coordinates[0].x);
        coor.put("latitude", coordinates[0].y);
        coordinateArray.add(coor);
        geometry.put("coordinates", coordinateArray); // Fixed key name to match GeoJSON standards
        jsonObject.put("geometry", geometry);
        return Result.success(jsonObject);
    }


    @ResponseBody
    @GetMapping("/points")
    public Result<JSONArray> getPoints() {
        List<Point> points = mapService.getAllCntyPoints();
        JSONArray jsonArray = new JSONArray();

        for (Point pointModel : points) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("gid", pointModel.getGId());
            jsonObject.put("namech", pointModel.getNameCh());

            JSONObject geometry = new JSONObject();
            geometry.put("type", pointModel.getGeometry().getGeometryType());

            JSONArray coordinateArray = new JSONArray();
            Coordinate[] coordinates = pointModel.getGeometry().getCoordinates();
            JSONObject coor = new JSONObject();
            coor.put("longitude", coordinates[0].x);
            coor.put("latitude", coordinates[0].y);
            coordinateArray.add(coor);
            geometry.put("coordinates", coordinateArray);

            jsonObject.put("geometry", geometry);

            jsonArray.add(jsonObject);
        }

        return Result.success(jsonArray);
    }

}
