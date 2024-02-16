package com.map.mapserver.pojo;


import com.vividsolutions.jts.geom.Geometry;
import lombok.Data;


@Data
public class Point {
    private Integer gId;
    private String nameCh;

    private Geometry geometry;


}