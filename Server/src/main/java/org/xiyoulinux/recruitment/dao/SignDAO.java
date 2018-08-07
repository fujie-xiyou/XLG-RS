package org.xiyoulinux.recruitment.dao;

import org.xiyoulinux.recruitment.model.po.Sign;

public interface SignDAO {
    int deleteByPrimaryKey(Integer id);

    int insert(Sign record);

    int insertSelective(Sign record);

    Sign selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Sign record);

    int updateByPrimaryKey(Sign record);
}