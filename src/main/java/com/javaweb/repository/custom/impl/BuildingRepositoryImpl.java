package com.javaweb.repository.custom.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.repository.custom.BuildingRepositoryCustom;
import com.javaweb.entity.BuildingEntity;

@Transactional
@Repository

public class BuildingRepositoryImpl implements BuildingRepositoryCustom{
    @Autowired
    private ModelMapper modelMapper;


    @PersistenceContext
    private EntityManager entityManager;




    public static void joinTable(BuildingSearchBuilder buildingSearchBuilder, StringBuilder sql) {
        Long staffid =  buildingSearchBuilder.getStaffId();
        if (staffid != null) {
            sql.append(" INNER JOIN assignmentbuilding ON assignmentbuilding.buildingid = b.id ");
        }
    }
    public void queryNormal(BuildingSearchBuilder buildingSearchBuilder, StringBuilder where) {
        try {
            Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
            for (Field item : fields) {
                item.setAccessible(true);
                String fieldname = item.getName().toLowerCase();
                if (!fieldname.equals("staffid") && !fieldname.equals("typecode") && !fieldname.startsWith("area") && !fieldname.startsWith("rentprice")) {
                    Object value = item.get(buildingSearchBuilder);
                    if (value != null && !value.toString().trim().equals("")) {
                        if (item.getType().getName().equals("java.lang.Integer") || item.getType().getName().equals("java.lang.Long")) {
                            where.append(" AND b." + fieldname + " = " + value + " ");
                        }
                        else {
                            where.append(" AND b." + fieldname + " like '%" + value + "%' ");
                        }
                    }
                }
            }

        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void querySpecial(BuildingSearchBuilder buildingSearchBuilder, StringBuilder where) {
        Long staffid =  buildingSearchBuilder.getStaffId();
        if (staffid != null) {
            where.append(" AND assignmentbuilding.staffid = " + staffid );
        }
        Long areafrom =buildingSearchBuilder.getAreaFrom();

        Long areato = buildingSearchBuilder.getAreaTo();

        if (areato != null || areafrom != null) {
            where.append(" AND EXISTS (Select * from rentarea WHERE b.id = rentarea.buildingid ");
            if(areato != null ) {
                where.append(" AND rentarea.value <= " + areato);
            }
            if(areafrom != null) {
                where.append(" AND rentarea.value >= " + areafrom);
            }
            where.append(") ");
        }
        Long rentpricefrom = buildingSearchBuilder.getRentPriceFrom();
        Long rentpriceto = buildingSearchBuilder.getRentPriceTo();
        if (rentpriceto != null|| rentpricefrom != null) {
            if(rentpriceto != null) {
                where.append(" AND b.rentprice <= " + rentpriceto);
            }
            if(rentpricefrom != null) {
                where.append(" AND b.rentprice >= " + rentpricefrom);
            }
        }
        List<String> typecode = buildingSearchBuilder.getTypeCode();
        if (typecode != null && typecode.size() != 0) {
            where.append(" AND( ");
            String sql = typecode.stream().map(it -> " b.type like '%" + it + "%' ").collect(Collectors.joining(" OR "));
            where.append(sql);
            where.append(" ) ");
        }


    }

    @Override
    public List<BuildingEntity> find(BuildingSearchBuilder buildingSearchBuilder) {
        // TODO Auto-generated method stub
        StringBuilder sql = new StringBuilder("SELECT b.* ");
        sql.append(" FROM building b ");
        StringBuilder where = new StringBuilder("WHERE 1 = 1 ");
        joinTable(buildingSearchBuilder, sql);
        queryNormal(buildingSearchBuilder, where);
        System.out.println(where);
        querySpecial(buildingSearchBuilder, where);
        where.append(" GROUP BY b.id ");
        sql.append(where);

        Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);
        return query.getResultList();
    }

    @Override
    public Page<BuildingEntity> find(BuildingSearchBuilder buildingSearchBuilder, Pageable pageable) {
        // Build FROM + JOIN
        StringBuilder from = new StringBuilder(" FROM building b ");
        joinTable(buildingSearchBuilder, from);

        // Build WHERE
        StringBuilder where = new StringBuilder("WHERE 1 = 1 ");
        queryNormal(buildingSearchBuilder, where);
        querySpecial(buildingSearchBuilder, where);
        where.append(" GROUP BY b.id ");

        // Query lấy data
        String dataSql = "SELECT b.* " + from.toString() + where.toString()
                + " LIMIT " + pageable.getPageSize() + " OFFSET " + pageable.getOffset();
        Query query = entityManager.createNativeQuery(dataSql, BuildingEntity.class);
        List<BuildingEntity> results = query.getResultList();

        // Query đếm tổng — dùng chung from và where nên JOIN luôn có
        String countSql = "SELECT COUNT(*) FROM ("
                + "SELECT b.* " + from.toString() + where.toString()
                + ") AS tmp";
        Query countQuery = entityManager.createNativeQuery(countSql);
        long total = ((Number) countQuery.getSingleResult()).longValue();

        return new PageImpl<>(results, pageable, total);
    }

    @Override
    public void deleteBuildingorListBuilding(List<Long> ids) {
        for (Long id : ids){
            BuildingEntity buildingEntity = entityManager.find(BuildingEntity.class, id);
             entityManager.remove(buildingEntity);
        }
    }

}

