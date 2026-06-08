package com.interviewtracker.dao;

import com.interviewtracker.model.Activity;
import java.util.List;

public interface ActivityDAO {
    boolean addActivity(Activity activity);
    List<Activity> getRecentActivitiesByUser(int userId, int limit);
}
