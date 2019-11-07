package org.xiyoulinux.recruitment.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xiyoulinux.recruitment.dao.JoinDAO;
import org.xiyoulinux.recruitment.model.po.Join;
import org.xiyoulinux.recruitment.model.vo.SmsJoin;
import org.xiyoulinux.recruitment.service.SmsService;
import org.xiyoulinux.recruitment.untils.ResponseResult;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service("smsService")
public class SmsServiceImpl implements SmsService {
    @Autowired
    JoinDAO joinDAO;
    String[] time_segments =
            {
                    "11月09号 08:00", "11月09号 08:30",
                    "11月09号 09:00", "11月09号 09:30",
                    "11月09号 10:00", "11月09号 10:30",
                    "11月09号 11:00", "11月09号 11:30",

                    "11月09号 13:00", "11月09号 13:30",
                    "11月09号 14:00", "11月09号 14:30",
                    "11月09号 15:00", "11月09号 15:30",
                    "11月09号 16:00", "11月09号 16:30",
                    "11月09号 17:00", "11月09号 17:30",

                    "11月09号 19:00", "11月09号 19:30",
                    "11月09号 20:00", "11月09号 20:30",
                    "11月09号 21:00", "11月09号 21:30",


                    "11月10号 08:00", "11月10号 08:30",
                    "11月10号 09:00",
                    "11月10号 10:30",
                    "11月10号 11:00", "11月10号 11:30",

                    "11月10号 13:00", "11月10号 13:30",
                    "11月10号 14:00", "11月10号 14:30",
                    "11月10号 15:00", "11月10号 15:30",
                    "11月10号 16:00", "11月10号 16:30",
                    "11月10号 17:00", "11月10号 17:30",

                    "11月10号 19:00", "11月10号 19:30",
                    "11月10号 20:00", "11月10号 20:30",
                    "11月10号 21:00", "11月10号 21:30",
            };

    @Override
    public List<SmsJoin> sms(int smsId, int userNum, int process_id) {
        int maxId = joinDAO.maxId();
        List<Integer> ids = new ArrayList<>();
        for (int i = 1; i <= maxId; i++) {
            if (i % userNum == smsId) {
                ids.add(i);
            }
        }
        List<Join> joins = joinDAO.selectByIdsAndProcess(ids, process_id);
        List<SmsJoin> smsJoins = new ArrayList<>(joins.size());
        for (Join join : joins) {
            SmsJoin smsJoin = new SmsJoin();
            smsJoin.setId(join.getId());
            smsJoin.setAdmin_class(join.getAdmin_class());
            smsJoin.setCn_name(join.getCn_name());
            smsJoin.setMobile(join.getMobile());
            smsJoin.setProcess_id(join.getProcess_id());
            smsJoin.setStudent_no(join.getStudent_no());
            smsJoin.setTime_segment(time_segments[join.getId() % time_segments.length]);
            smsJoins.add(smsJoin);

        }
        smsJoins.sort(Comparator.comparing(SmsJoin::getTime_segment));
        return smsJoins;

    }
}
