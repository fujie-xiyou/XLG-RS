package org.xiyoulinux.recruitment.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xiyoulinux.recruitment.dao.ActivityDAO;
import org.xiyoulinux.recruitment.model.po.Activity;
import org.xiyoulinux.recruitment.service.ActivityService;

import java.util.List;
@Service("ActivityService")
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    private ActivityDAO activityDAO;
    @Override
    public int add(Activity activity) {
        return activityDAO.insertSelective(activity);
    }

    @Override
    public boolean delete(Activity activity) {
        return false;
    }

    @Override
    public boolean modify(Activity activity) {
        return false;
    }

    @Override
    public List<Activity> fetchAll() {
        return null;
    }
}
