package com.oreo.website.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    long countByExample(websiteExample example);

    int deleteByExample(websiteExample example);

    int deleteByPrimaryKey(String user);

    int insert(websiteWithBLOBs record);

    int insertSelective(websiteWithBLOBs record);

    List<websiteWithBLOBs> selectByExampleWithBLOBs(websiteExample example);

    List<website> selectByExample(websiteExample example);

    websiteWithBLOBs selectByPrimaryKey(String user);

    int updateByExampleSelective(@Param("record") websiteWithBLOBs record, @Param("example") websiteExample example);

    int updateByExampleWithBLOBs(@Param("record") websiteWithBLOBs record, @Param("example") websiteExample example);

    int updateByExample(@Param("record") website record, @Param("example") websiteExample example);

    int updateByPrimaryKeySelective(websiteWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(websiteWithBLOBs record);

    int updateByPrimaryKey(website record);
}