package com.xxx.pms.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxx.pms.entity.Iterative;
import com.xxx.pms.mapper.IterativeMapper;
import com.xxx.pms.po.RequestParamPage;
import com.xxx.pms.response.Response;
import com.xxx.pms.service.IterativeService;
import com.xxx.pms.util.CommonUtils;
import com.xxx.pms.util.ResponseUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;


@Service
public class IterativeServiceImpl implements IterativeService {

    @Resource
    IterativeMapper IterativeMapper;

    @Override
    public Response addIterative(Iterative Iterative) {
        IterativeMapper.insertSelective(Iterative);
        return ResponseUtils.success();
    }

    @Override
    public Response updateIterative(Iterative Iterative) {
        IterativeMapper.updateByPrimaryKeySelective(Iterative);
        return ResponseUtils.success();
    }

    @Override
    public Response getIterativeById(int id) {
        Iterative Iterative = IterativeMapper.selectByPrimaryKey(id);
        return ResponseUtils.successData(Iterative);
    }

    @Override
    public Response deleteIterativeById(int id) {
        IterativeMapper.deleteByPrimaryKey(id);
        return ResponseUtils.success();
    }

    @Override
    public Response getIterativeListByPage(RequestParamPage<Iterative> form) {
        PageHelper.startPage(form.getPage(), form.getPageSize());

        Iterative Iterative = form.getParam();
        Example example = new Example(Iterative.class);
        Example.Criteria criteria = example.createCriteria();
        if(CommonUtils.isNotEmpty(Iterative.getProjectId())){
            criteria.andEqualTo("projectId",Iterative.getProjectId());
        }
        if(CommonUtils.isNotEmpty(Iterative.getName())){
            criteria.andLike("name","%"+Iterative.getName()+"%");
        }
        example.setOrderByClause("gmt_create desc");

        List<Iterative> IterativeList = IterativeMapper.selectByExample(example);
        PageInfo<Iterative> pageInfo = new PageInfo<>(IterativeList);
        return ResponseUtils.successData(pageInfo);
    }

}
