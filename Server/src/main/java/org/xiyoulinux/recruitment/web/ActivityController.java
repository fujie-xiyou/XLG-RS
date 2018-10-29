package org.xiyoulinux.recruitment.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xiyoulinux.recruitment.model.po.Activity;
import org.xiyoulinux.recruitment.service.ActivityService;
@Controller
@RequestMapping("/activity")
@ResponseBody
public class ActivityController {
    @Autowired
    private ActivityService activityService;
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public int add(@RequestBody Activity activity){
        return activityService.add(activity);
    }

}
