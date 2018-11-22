package org.xiyoulinux.recruitment.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xiyoulinux.recruitment.dao.JoinDAO;
import org.xiyoulinux.recruitment.model.po.Join;

@Controller
@ResponseBody
/**
 * @author fujie
 * @date
 */
public class LuckDrawController {
    @Autowired
   private JoinDAO joinDAO;
    class Stu {
        String sno;
        String name;

        public String getSno() {
            return sno;
        }

        public void setSno(String sno) {
            this.sno = sno;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
    @RequestMapping(value = "/luckDraw", method = RequestMethod.GET)
    public Stu luckDraw(){
        Join join = joinDAO.selectRand();
        System.out.println("name = " + join.getCn_name());
        join.setIs_vaild(false);
        joinDAO.updateByPrimaryKey(join);
        Stu stu = new Stu();
        stu.setSno(join.getStudent_no());
        stu.setName(join.getCn_name());
        return stu;
    }
    @RequestMapping(value = "/luckInit", method = RequestMethod.GET)
    public String luckInit(){
        joinDAO.updateIs_vaild(true);
        return "初始化成功";
    }
}
