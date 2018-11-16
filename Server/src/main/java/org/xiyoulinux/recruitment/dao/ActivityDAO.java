package org.xiyoulinux.recruitment.dao;

import org.xiyoulinux.recruitment.model.po.Activity;

public interface ActivityDAO {
    int[] selectGradesByActivityID();

    int deleteByPrimaryKey(Integer id);

    int insert(Activity record);

    int insertSelective(Activity record);

    Activity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Activity record);

    int updateByPrimaryKey(Activity record);
}
