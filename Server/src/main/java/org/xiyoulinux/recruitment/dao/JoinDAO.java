package org.xiyoulinux.recruitment.dao;

import org.springframework.stereotype.Repository;
import org.xiyoulinux.recruitment.model.Join;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectKey;
@Mapper
@Repository
public interface JoinDAO {
    @Insert("INSERT INTO join(Sno,processID,is_vaild,Sname,Spasswd,Ssex,Sbirthday,Sprovince,Scoll,Sdept,Sclass,Sphone)" +
            "VALUES(#{Sno},#{processID},#{is_vaild},#{Sname},#{Ssex}),#{Sbirthday},#{Sprovince},#{Scoll},#{Sdept},#{Sclass},#{Sphone}")
    @SelectKey(statement = "call identity()",keyProperty = "joinID",before = false,resultType = int.class)
    public int insert(Join join);
}
