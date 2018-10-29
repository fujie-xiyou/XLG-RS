package org.xiyoulinux.recruitment.service;

import org.xiyoulinux.recruitment.model.po.Activity;

import java.util.List;

public interface ActivityService {
    public int add(Activity activity);
    public boolean delete(Activity activity);
    public boolean modify(Activity activity);
    public List<Activity> fetchAll();
}
