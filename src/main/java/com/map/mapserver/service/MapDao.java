package com.map.mapserver.service;

import com.map.mapserver.pojo.GeometryModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;


public interface MapDao {
    GeometryModel getCntyPoint(@Param("gId") Integer gId);
    GeometryModel getPrefPoint(@Param("gId") Integer gId);
    GeometryModel getPrefPolygon(@Param("gId") Integer gId);
}
