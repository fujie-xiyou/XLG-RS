package org.xiyoulinux.recruitment.untils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.xiyoulinux.recruitment.config.spring.RootConfig;
import org.xiyoulinux.recruitment.dao.JoinDAO;
import org.xiyoulinux.recruitment.model.Join;
public class test {
    @Autowired
    public JoinDAO joinDAO;
    public static void main(String[] args){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(RootConfig.class);
        JoinDAO joinDAO = context.getBean(JoinDAO.class);
        joinDAO.insert(new Join());
    }
}
