package org.xiyoulinux.recruitment.dao;

import org.xiyoulinux.recruitment.model.po.Join;

public interface JoinDAO {
    int deleteByPrimaryKey(Integer id);

    int insert(Join record);

    int insertSelective(Join record);

    Join selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Join record);

    int updateByPrimaryKey(Join record);
}
