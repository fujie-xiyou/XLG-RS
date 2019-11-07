package org.xiyoulinux.recruitment.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.xiyoulinux.recruitment.model.vo.SmsJoin;
import org.xiyoulinux.recruitment.service.SmsService;

import java.util.*;

@Controller
public class SmsController {
    @Autowired
    SmsService smsService;
    @RequestMapping(value = "/sms", method = RequestMethod.GET)
    @ResponseBody
    public List<SmsJoin> sms(@RequestParam("sms_id") int smsId, @RequestParam("user_num") int userNum, @RequestParam("process_id") int process_id) {
        return smsService.sms(smsId, userNum, process_id);
    }

}
