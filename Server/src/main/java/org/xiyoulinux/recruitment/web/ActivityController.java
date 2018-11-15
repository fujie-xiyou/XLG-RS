package org.xiyoulinux.recruitment.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.xiyoulinux.recruitment.model.po.Activity;
import org.xiyoulinux.recruitment.service.ActivityService;
import org.xiyoulinux.recruitment.untils.ResponseResult;

@Controller
@RequestMapping("/activity")
@ResponseBody
public class ActivityController {
    @Autowired
    private ActivityService activityService;
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public ResponseResult add(@RequestBody Activity activity){
        if (activityService.add(activity) > 0){
            return new ResponseResult();
        }
        return new ResponseResult<String>(0,"数据库异常");
    }
    @RequestMapping(value = "getActivity", method = RequestMethod.GET)
    public ResponseResult getActivity(@RequestParam("id") int id){
        return new ResponseResult<Activity>(activityService.getActivityByID(id));
    }

}
