package com.xxx.pms.service;

import com.xxx.pms.entity.Project;
import com.xxx.pms.po.RequestParamPage;
import com.xxx.pms.response.Response;

public interface ProjectService {
    /**
     * 增加项目
     * @param project
     * @return
     */
    Response addProject(Project project);


    /**
     * 修改项目
     * @param project
     * @return
     */
    Response updateProject(Project project);

    /**
     * 查询项目
     * @param id
     * @return
     */
    Response getProjectById(int id);

    /**
     * 删除项目
     * @param id
     * @return
     */
    Response deleteProjectById(int id);

    /**
     * 分页条件查询项目列表
     * @param form
     * @return
     */
    Response getProjectListByPage(RequestParamPage<Project> form);


}
