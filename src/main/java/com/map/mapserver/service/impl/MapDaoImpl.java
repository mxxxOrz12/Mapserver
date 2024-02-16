package com.map.mapserver.service.impl;

import com.map.mapserver.service.MapDao;
import com.map.mapserver.mapper.GeometryMapper;
import com.map.mapserver.pojo.GeometryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MapDaoImpl implements MapDao {

    @Autowired
    private GeometryMapper geometryMapper;

    @Override
    public GeometryModel getCntyPoint(Integer gId) {
        return geometryMapper.getCntyPoint(gId);
    }

    @Override
    public GeometryModel getPrefPoint(Integer gId) {
        return geometryMapper.getPrefPoint(gId);
    }

    @Override
    public GeometryModel getPrefPolygon(Integer gId) {
        return geometryMapper.getPrefPolygon(gId);
    }
}
