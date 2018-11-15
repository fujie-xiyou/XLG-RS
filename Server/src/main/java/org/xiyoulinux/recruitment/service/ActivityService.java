package org.xiyoulinux.recruitment.service;

import org.xiyoulinux.recruitment.model.po.Activity;

import java.util.List;

public interface ActivityService {
    public int add(Activity activity);
    public boolean delete(Activity activity);
    public boolean modify(Activity activity);
    public Activity getActivityByID(int id);
    public List<Activity> fetchAll();
}
