package com.map.mapserver.localcache;

import com.map.mapserver.service.MapDao;
import com.map.mapserver.pojo.GeometryModel;

import jakarta.annotation.PostConstruct;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GeometryCache implements InitializingBean {
    Map<String, List<GeometryModel>> historyGis = new HashMap<>();
    @Autowired
    private MapDao mapDao;

    @Override
    public void afterPropertiesSet() {
        if (mapDao != null) {
            List<GeometryModel> cntyGeometry = new ArrayList<>();
            int i = 1;
            GeometryModel cntyPoint = mapDao.getCntyPoint(i);
            while (cntyPoint != null) {
                cntyGeometry.add(cntyPoint);
                i++;
                cntyPoint = mapDao.getCntyPoint(i);
            }
            historyGis.put("cntypts", cntyGeometry);

            List<GeometryModel> prefPtsGeometry = new ArrayList<>();
            i = 1;
            GeometryModel prefPoint = mapDao.getPrefPoint(i);
            while (prefPoint != null) {
                prefPtsGeometry.add(prefPoint);
                i++;
                prefPoint = mapDao.getPrefPoint(i);
            }
            historyGis.put("prefpts", prefPtsGeometry);

            List<GeometryModel> prefPgnGeometry = new ArrayList<>();
            i = 1;
            GeometryModel prefPolygon = mapDao.getPrefPolygon(i);
            while (prefPolygon != null) {
                prefPgnGeometry.add(prefPolygon);
                i++;
                prefPolygon = mapDao.getPrefPolygon(i);
            }
            historyGis.put("prefpgn", prefPgnGeometry);
        } else {
            // 处理 mapDao 为空的情况，例如抛出异常或者记录日志
        }
    }

    public List<GeometryModel> getDynastyGeometry(String category, Integer start, Integer end) {
        List<GeometryModel> result = new ArrayList<>();
        List<GeometryModel> geometryModels = historyGis.get(category);
        if (geometryModels != null) {
            for (GeometryModel g : geometryModels) {
                if ((g.getBegYr() >= start && g.getBegYr() <= end) || (g.getEndYr() >= start && g.getEndYr() <= end)) {
                    result.add(g);
                }
            }
        }
        return result;
    }
}
