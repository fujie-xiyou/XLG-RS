package org.xiyoulinux.recruitment.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.stereotype.Repository;
import org.xiyoulinux.recruitment.model.Activity;

@Mapper
@Repository
public interface ActivityDAO {
    @Insert("INSERT INTO activity(Aname,Atime,processID) VALUES(#{Aname},#{Atime},#{processID}")
    @SelectKey(statement = "call identity()",keyProperty = "Aid",before = false,resultType = int.class)
    int insert(Activity activity);
}
