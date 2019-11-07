package org.xiyoulinux.recruitment.service;

import org.xiyoulinux.recruitment.model.vo.SmsJoin;
import org.xiyoulinux.recruitment.untils.ResponseResult;

import java.util.List;

public interface SmsService {
    public List<SmsJoin> sms(int smsId, int userNum, int process_id);
}
