package com.map.mapserver.service;

import com.map.mapserver.pojo.GeometryModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GeometryService {

    List<GeometryModel> getDynastyGeom(String category, Integer start, Integer end);

}
