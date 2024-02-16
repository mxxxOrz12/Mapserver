package com.map.mapserver.service;

import com.map.mapserver.pojo.Point;
import org.springframework.stereotype.Service;

import java.util.List;


public interface MapService {

    Point getCntyPointByGid(Integer gId);

    List<Point> getAllCntyPoints();


}
