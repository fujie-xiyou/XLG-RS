package org.xiyoulinux.recruitment.dao;

import org.xiyoulinux.recruitment.model.po.Join;

import java.util.List;

public interface JoinDAO {
    int deleteByPrimaryKey(Integer id);

    int insert(Join record);

    int insertSelective(Join record);

    Join selectByPrimaryKey(Integer id);

    Join selectRand();

    Integer selectByNo(String no);

    int updateByPrimaryKeySelective(Join record);

    int updateByPrimaryKey(Join record);

    int updateIs_vaild(Boolean is_vaild);

    List selectAllNo();
}
