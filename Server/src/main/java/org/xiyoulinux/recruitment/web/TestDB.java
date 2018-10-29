package org.xiyoulinux.recruitment.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xiyoulinux.recruitment.dao.ActivityDAO;
import org.xiyoulinux.recruitment.dao.JoinDAO;
import org.xiyoulinux.recruitment.model.po.Activity;
import org.xiyoulinux.recruitment.model.po.Join;

@Controller
public class TestDB {
    @Autowired
    private JoinDAO activityDAO;
    @ResponseBody
    @RequestMapping(value = "/testDB")
    public String test(){
        return activityDAO.insert(new Join())+"";
    }
}
