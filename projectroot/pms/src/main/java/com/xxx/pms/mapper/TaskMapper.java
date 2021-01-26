package com.xxx.pms.mapper;

import com.xxx.pms.entity.Task;
import com.xxx.pms.response.ProjectProgressTask;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface TaskMapper extends Mapper<Task> {

    List<ProjectProgressTask> getProjectUserProgressTask(Integer projectId);

}
