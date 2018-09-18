package org.xiyoulinux.recruitment.dao;

import org.xiyoulinux.recruitment.model.po.Score;

public interface ScoreDAO {
    int deleteByPrimaryKey(Integer id);

    int insert(Score record);

    int insertSelective(Score record);

    Score selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Score record);

    int updateByPrimaryKey(Score record);
}