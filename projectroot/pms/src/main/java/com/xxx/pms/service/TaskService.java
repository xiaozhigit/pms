package com.xxx.pms.service;

import com.xxx.pms.entity.TaskUser;
import java.util.List;

public interface TaskService {

    int add(TaskUser taskUser);

    int deleteById(Integer id);

    int updateById(TaskUser taskUser);

    TaskUser selectById(Integer id);

    List<TaskUser> selectUserTaskByUserId(Integer userId);
}
