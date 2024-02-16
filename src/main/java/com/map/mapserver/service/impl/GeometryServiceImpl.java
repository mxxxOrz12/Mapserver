package com.map.mapserver.service.impl;


import com.map.mapserver.localcache.GeometryCache;
import com.map.mapserver.pojo.GeometryModel;
import com.map.mapserver.service.GeometryService;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeometryServiceImpl implements GeometryService {
    @Autowired
    private GeometryCache geometryCache;


    @PostConstruct
    public void init() {
        if (geometryCache != null) {
            System.out.println("GeometryCache 注入成功！");
        } else {
            System.out.println("GeometryCache 注入失败！");
        }
    }

    @Override
    public List<GeometryModel> getDynastyGeom(String category, Integer start, Integer end) {
        return geometryCache.getDynastyGeometry(category,start,end);
    }
}
