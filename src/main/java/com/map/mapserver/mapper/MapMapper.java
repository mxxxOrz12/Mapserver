package com.map.mapserver.mapper;


import com.map.mapserver.mybatis.GeometryTypeHandler;
import com.map.mapserver.pojo.Point;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;


import java.util.List;

@Mapper
public interface MapMapper {
    @Select("SELECT id, name_ch, geom FROM public.v6_time_cnty_pts_gbk_wgs84 WHERE id=#{gId}")
    @Results(id="pointModelResult", value={
            @Result(property = "gId", column = "id", jdbcType = JdbcType.BIGINT),
            @Result(property = "nameCh", column = "name_ch", jdbcType = JdbcType.VARCHAR),
            @Result(property = "geometry", column = "geom", typeHandler = GeometryTypeHandler.class)
    })
    Point getCntyPointByGid(@Param("gId") Integer gId);

    @Select("SELECT id,name_ch,geom from public.v6_time_cnty_pts_gbk_wgs84")
    @Results(id="pointsResult", value={
            @Result(property = "gId", column = "id", jdbcType = JdbcType.BIGINT),
            @Result(property = "nameCh", column = "name_ch", jdbcType = JdbcType.VARCHAR),
            @Result(property = "geometry", column = "geom", typeHandler = GeometryTypeHandler.class)
    })
    List<Point> getAllCntyPoints();
}
