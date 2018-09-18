package org.xiyoulinux.recruitment.dao;

import org.xiyoulinux.recruitment.model.po.Evaluation;

public interface EvaluationDAO {
    int deleteByPrimaryKey(Integer id);

    int insert(Evaluation record);

    int insertSelective(Evaluation record);

    Evaluation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Evaluation record);

    int updateByPrimaryKey(Evaluation record);
}