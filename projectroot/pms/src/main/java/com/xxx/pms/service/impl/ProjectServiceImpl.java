package com.xxx.pms.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxx.pms.entity.Project;
import com.xxx.pms.entity.ProjectUser;
import com.xxx.pms.entity.User;
import com.xxx.pms.entity.TaskUser;
import com.xxx.pms.mapper.ProjectMapper;
import com.xxx.pms.mapper.ProjectUserMapper;
import com.xxx.pms.mapper.TaskUserMapper;
import com.xxx.pms.po.RequestParamPage;
import com.xxx.pms.response.Response;
import com.xxx.pms.service.ProjectService;
import com.xxx.pms.service.UserService;
import com.xxx.pms.util.CommonUtils;
import com.xxx.pms.util.PinYinUtils;
import com.xxx.pms.util.ResponseUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@Service
public class ProjectServiceImpl implements ProjectService {

    @Resource
    ProjectMapper projectMapper;
    @Resource
    ProjectUserMapper projectUserMapper;
    @Resource
    private TaskUserMapper taskUserMapper;
    @Resource
    private UserService userService;

    @Override
    public Response addProject(Project project) {
        String pinYinHeadChar = PinYinUtils.getPinYinHeadChar(project.getName());
        project.setInitials(pinYinHeadChar);
        project.setState(0);
        projectMapper.insertSelective(project);
        List<ProjectUser> projectUserList = project.getProjectUserList();
        if(projectUserList != null){
            for(ProjectUser projectUser : projectUserList){
                projectUser.setProjectId(project.getId());
                projectUserMapper.insertSelective(projectUser);
            }
        }
        return ResponseUtils.success();
    }

    @Override
    public Response updateProject(Project project) {
        String pinYinHeadChar = PinYinUtils.getPinYinHeadChar(project.getName());
        project.setInitials(pinYinHeadChar);
        projectMapper.updateByPrimaryKeySelective(project);
        //删除之前绑定的项目人员
        Example example = new Example(ProjectUser.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("projectId",project.getId());
        projectUserMapper.deleteByExample(example);
        //重新添加修改后的项目人员
        List<ProjectUser> projectUserList = project.getProjectUserList();
        if(projectUserList != null){
            for(ProjectUser projectUser : projectUserList){
                projectUser.setProjectId(project.getId());
                projectUserMapper.insertSelective(projectUser);
            }
        }
        return ResponseUtils.success();
    }

    @Override
    public Response getProjectById(int id) {
        Project project = projectMapper.selectByPrimaryKey(id);
        //查询绑定的项目人员
        Example example = new Example(ProjectUser.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("projectId",project.getId());
        List<ProjectUser> projectUserList = projectUserMapper.selectByExample(example);
        project.setProjectUserList(projectUserList);
        return ResponseUtils.successData(project);
    }

    @Override
    public Response deleteProjectById(int id) {
        Project project = projectMapper.selectByPrimaryKey(id);
        project.setDelFlag(true);
        projectMapper.updateByPrimaryKeySelective(project);
        return ResponseUtils.success();
    }

    @Override
    public Response getProjectListByPage(RequestParamPage<Project> form) {
        PageHelper.startPage(form.getPage(), form.getPageSize());

        Project project = form.getParam();
        Example example = new Example(Project.class);
        Example.Criteria criteria = example.createCriteria();
        if(CommonUtils.isNotEmpty(project.getCompanyId())){
            criteria.andEqualTo("companyId",project.getCompanyId());
        }
        if(CommonUtils.isNotEmpty(project.getName())){
            criteria.andLike("name","%"+project.getName()+"%")
                    .orLike("initials","%"+project.getName()+"%");
        }
        criteria.andNotEqualTo("delFlag",true);

        example.setOrderByClause("gmt_create desc");
        List<Project> projects = projectMapper.selectByExample(example);
        PageInfo<Project> pageInfo = new PageInfo<>(projects);
        List<Project> projectList = new ArrayList<>();
        for(Project p : projects){
            //查询绑定的项目人员
            Example projectuserExample = new Example(ProjectUser.class);
            Example.Criteria projectuserCriteria = projectuserExample.createCriteria();
            projectuserCriteria.andEqualTo("projectId",p.getId());
            List<ProjectUser> projectUserList = projectUserMapper.selectByExample(projectuserExample);
            p.setProjectUserList(projectUserList);
            projectList.add(p);
        }

        pageInfo.setList(projectList);
        return ResponseUtils.successData(pageInfo);
    }

    @Override
    public List<Project> getUserJoinProjects(Integer userId) {
        return projectMapper.selectProjectByUserId(userId);
    }

    @Override
    public List<TaskUser> getProjectTaskUser(Integer projectId) {
        return taskUserMapper.selectByProject(projectId);
    }

    @Override
    public Response getProjectList(int companyId) {
        Example example = new Example(Project.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("companyId",companyId);
        criteria.andNotEqualTo("delFlag",1);
        List<Project> projectList = projectMapper.selectByExample(example);
        return ResponseUtils.successData(projectList);
    }

    @Override
    public Response changeProject(Integer userId, Integer projectId) {
        User user=new User();
        user.setId(userId);
        user.setProjectId(projectId);
        int result= userService.updateByPrimaryKeySelective(user);
        return result>0?ResponseUtils.success():ResponseUtils.error();
    }

    @Override
    public String getProjectNameById(Integer projectId) {
        Project project = projectMapper.selectByPrimaryKey(projectId);
        if (project != null) return project.getName();
        return null;
    }
}
