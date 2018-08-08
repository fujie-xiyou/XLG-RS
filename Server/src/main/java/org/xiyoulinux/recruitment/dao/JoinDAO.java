package org.xiyoulinux.recruitment.dao;

import org.springframework.stereotype.Repository;
import org.xiyoulinux.recruitment.model.Join;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectKey;
@Mapper
@Repository
public interface JoinDAO {
    @Insert("INSERT INTO `join`(`student_no`, `process_id`, `is_vaild`, `cn_name`, `passwd`, `sex`, `birthday`, `province`, `college`, `dept`, `admin_class`, `mobile`)" +
            " VALUES(#{student_no},#{process_id},#{is_vaild},#{cn_name},#{passwd},#{sex},#{birthday},#{province},#{college},#{dept},#{admin_class},#{mobile})")
    //@SelectKey(statement = "call identity()",keyProperty = "id",before = false,resultType = int.class)
    public int insert(Join join);
}
