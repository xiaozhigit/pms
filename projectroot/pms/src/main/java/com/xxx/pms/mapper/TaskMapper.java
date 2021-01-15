package com.xxx.pms.mapper;

import com.xxx.pms.entity.Task;
import com.xxx.pms.entity.TaskUser;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface TaskMapper extends Mapper<Task> {
    // 这种继承Mapper的方式 可以不用mapper.xml文件

}
