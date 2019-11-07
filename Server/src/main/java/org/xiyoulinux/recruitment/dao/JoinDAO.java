package org.xiyoulinux.recruitment.dao;

import org.apache.ibatis.annotations.Param;
import org.xiyoulinux.recruitment.model.po.Join;

import java.util.List;

public interface JoinDAO {
    int deleteByPrimaryKey(Integer id);

    int insert(Join record);

    int insertSelective(Join record);

    Join selectByPrimaryKey(Integer id);

    Join selectRand();

    Join selectByNo(String no);


    List<Join> selectByIdsAndProcess(@Param("ids") List<Integer> ids, @Param("process_id") int process_id);


    int maxId();

    int updateMobileByNo(@Param("mobile") String mobile, @Param("no") String no);

    int updateByPrimaryKeySelective(Join record);

    int updateByPrimaryKey(Join record);

    int updateIs_vaild(Boolean is_vaild);
}
