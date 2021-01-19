package com.xxx.pms.service;

import com.xxx.pms.entity.Project;
import com.xxx.pms.entity.TaskUser;
import com.xxx.pms.po.RequestParamPage;
import com.xxx.pms.response.Response;

import java.util.List;

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
     * 通过项目id获取项目名称
     * @param projectId 项目id
     * @return 项目名称
     */
    String getProjectNameById(Integer projectId);

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

    /**
     * 通过用户id获取用户参与的项目
     * @param userId 用户id
     * @return 参与的项目集合
     */
    List<Project> getUserJoinProjects(Integer userId);

    /**
     * 通过项目id获取项目下所有参与人的任务
     * @param projectId 项目id
     * @return 项目参与人任务集合
     */
    List<TaskUser> getProjectTaskUser(Integer projectId);

    /**
     * 获取公司下的项目
     * @param companyId 公司id
     * @return 公司项目集合
     */
    Response getProjectList(int companyId);

    /**
     * 用户切换项目
     * @param userId 用户id
     * @param projectId 项目id
     * @return response
     */
    Response changeProject(Integer userId, Integer projectId);
}
