package com.map.mapserver.mapper;

import com.map.mapserver.mybatis.GeometryTypeHandler;
import com.map.mapserver.pojo.GeometryModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface GeometryMapper {
    @Select("SELECT id, name_ch, pres_loc, type_ch, lev_rank, " +
            "beg_yr, beg_rule, end_yr, end_rule, geo_src,  " +
            "end_date, beg_chg_ty, end_chg_ty,geom  " +
            "FROM v6_time_cnty_pts_gbk_wgs84 " +
            "WHERE id = #{gId} LIMIT 1")
    @Results({
            @Result(property = "id", column = "gId"),
            @Result(property = "nameCh", column = "name_ch"),
            @Result(property = "presLoc", column = "pres_loc"),
            @Result(property = "typeCh", column = "type_ch"),
            @Result(property = "levRank", column = "lev_rank"),
            @Result(property = "begYr", column = "beg_yr"),
            @Result(property = "begRule", column = "beg_rule"),
            @Result(property = "endYr", column = "end_yr"),
            @Result(property = "endRule", column = "end_rule"),
            @Result(property = "geoSrc", column = "geo_src"),
            @Result(property = "entDate", column = "ent_date"),
            @Result(property = "begChgTy", column = "beg_chg_ty"),
            @Result(property = "endChgTy", column = "end_chg_ty"),
            @Result(property = "geometry", column = "geom", typeHandler = GeometryTypeHandler.class) // 假设有一个 GeometryTypeHandler 类来处理 Geometry 类型
    })
    GeometryModel getCntyPoint(Integer gId);

    @Select("SELECT id, name_ch, pres_loc, type_py, type_ch, lev_rank, " +
            "beg_yr, beg_rule, end_yr, end_rule, geo_src,   " +
            "end_date, beg_chg_ty, end_chg_ty, geom " +
            "FROM v6_time_pref_pts_gbk_wgs84 " +
            "WHERE id = #{gId} LIMIT 1")
    @Results({
            @Result(property = "id", column = "gId"),
            @Result(property = "nameCh", column = "name_ch"),
            @Result(property = "presLoc", column = "pres_loc"),
            @Result(property = "typeCh", column = "type_ch"),
            @Result(property = "levRank", column = "lev_rank"),
            @Result(property = "begYr", column = "beg_yr"),
            @Result(property = "begRule", column = "beg_rule"),
            @Result(property = "endYr", column = "end_yr"),
            @Result(property = "endRule", column = "end_rule"),
            @Result(property = "geoSrc", column = "geo_src"),
            @Result(property = "endDate", column = "end_date"),
            @Result(property = "begChgTy", column = "beg_chg_ty"),
            @Result(property = "endChgTy", column = "end_chg_ty"),
            @Result(property = "geometry", column = "geom", typeHandler = GeometryTypeHandler.class)
            // 继续映射其他属性
    })
    GeometryModel getPrefPoint(Integer gId);

    @Select("SELECT id, name_ch, pres_loc ,type_py, type_ch, lev_rank, " +
            "beg_yr, beg_rule, end_yr, end_rule, geo_src, " +
            "end_date, beg_chg_ty, end_chg_ty, geom " +
            "FROM v6_time_pref_pgn_gbk_wgs84 " +
            "WHERE id = #{gId} LIMIT 1")
    @Results({
            @Result(property = "id", column = "gId"),
            @Result(property = "nameCh", column = "name_ch"),
            @Result(property = "presLoc", column = "pres_loc"),
            @Result(property = "typeCh", column = "type_ch"),
            @Result(property = "levRank", column = "lev_rank"),
            @Result(property = "begYr", column = "beg_yr"),
            @Result(property = "begRule", column = "beg_rule"),
            @Result(property = "endYr", column = "end_yr"),
            @Result(property = "endRule", column = "end_rule"),
            @Result(property = "geoSrc", column = "geo_src"),
            @Result(property = "endDate", column = "end_date"),
            @Result(property = "begChgTy", column = "beg_chg_ty"),
            @Result(property = "endChgTy", column = "end_chg_ty"),
            @Result(property = "geometry", column = "geom", typeHandler = GeometryTypeHandler.class)
            // 继续映射其他属性
    })
    GeometryModel getPrefPolygon(Integer gId);
}
