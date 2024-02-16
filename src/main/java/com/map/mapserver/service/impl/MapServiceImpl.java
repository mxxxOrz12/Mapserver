package com.map.mapserver.service.impl;

import com.map.mapserver.mapper.MapMapper;
import com.map.mapserver.pojo.Point;
import com.map.mapserver.service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MapServiceImpl implements MapService {

    @Autowired
    private MapMapper mapMapper;

    @Override
    public Point getCntyPointByGid(Integer gId) {
        return mapMapper.getCntyPointByGid(gId);
    }

    @Override
    public List<Point> getAllCntyPoints() {
        return mapMapper.getAllCntyPoints();
    }
}
