package org.xiyoulinux.recruitment.dao;

import org.xiyoulinux.recruitment.model.po.Process;

public interface ProcessDAO {
    int deleteByPrimaryKey(Integer id);

    int insert(Process record);

    int insertSelective(Process record);

    Process selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Process record);

    int updateByPrimaryKey(Process record);
}