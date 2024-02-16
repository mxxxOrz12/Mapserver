package com.map.mapserver.pojo;

import com.vividsolutions.jts.geom.Geometry;
import lombok.Data;

@Data

public class GeometryModel {
    private Integer gId;
    private String nameCh;
    private String presLoc;
    private String typeCh;
    private String levRank;
    private Integer begYr;
    private String begRule;
    private Integer endYr;
    private String endRule;
    private String geoSrc;
    private String endDate;
    private String begChgTy;
    private String endChgTy;
    private Geometry geometry;
}
