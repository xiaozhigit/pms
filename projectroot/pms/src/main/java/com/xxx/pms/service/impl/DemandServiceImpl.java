package com.xxx.pms.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxx.pms.entity.Demand;
import com.xxx.pms.mapper.DemandMapper;
import com.xxx.pms.po.RequestParamPage;
import com.xxx.pms.response.Response;
import com.xxx.pms.service.DemandService;
import com.xxx.pms.util.CommonUtils;
import com.xxx.pms.util.ResponseUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@Service
public class DemandServiceImpl implements DemandService {

    @Resource
    DemandMapper DemandMapper;

    @Override
    public Response addDemand(Demand demand) {
        DemandMapper.insertSelective(demand);
        return ResponseUtils.success();
    }

    @Override
    public Response updateDemand(Demand demand) {
        DemandMapper.updateByPrimaryKeySelective(demand);
        return ResponseUtils.success();
    }

    @Override
    public Response getDemandById(int id) {
        Demand Demand = DemandMapper.selectByPrimaryKey(id);
        return ResponseUtils.successData(Demand);
    }

    @Override
    public Response deleteDemandById(int id) {
        DemandMapper.deleteByPrimaryKey(id);
        return ResponseUtils.success();
    }

    @Override
    public Response getDemandListByPage(RequestParamPage<Demand> form) {
        PageHelper.startPage(form.getPage(), form.getPageSize());

        Demand Demand = form.getParam();
        Example example = new Example(Demand.class);
        Example.Criteria criteria = example.createCriteria();
        if(CommonUtils.isNotEmpty(Demand.getProjectId())){
            criteria.andEqualTo("projectId",Demand.getProjectId());
        }
        if(CommonUtils.isNotEmpty(Demand.getPromoter())){
            criteria.andLike("promoter","%"+Demand.getPromoter()+"%");
        }
        if(CommonUtils.isNotEmpty(Demand.getContext())){
            criteria.andLike("context","%"+Demand.getContext()+"%");
        }
        if(CommonUtils.isNotEmpty(Demand.getState())){
            criteria.andEqualTo("state",Demand.getState());
            if(Demand.getState().equals(0)){
                example.setOrderByClause("sort asc");
            }else {
                example.setOrderByClause("gmt_create desc");
            }
        }
        if(CommonUtils.isEmpty(Demand.getState())){
            example.setOrderByClause("gmt_create desc");
        }
        List<Demand> DemandList = DemandMapper.selectByExample(example);
        PageInfo<Demand> pageInfo = new PageInfo<>(DemandList);
        return ResponseUtils.successData(pageInfo);
    }

    @Override
    public Response saveDemandList(List<Demand> demandList, int page, int pageSize) {
        //计算排序号
        int i = page * pageSize+1;
        List<Demand> demandLists = new ArrayList<>();
        for(Demand demand : demandList){
            demand.setSort(i++);
            demandLists.add(demand);
        }
        DemandMapper.updateByPrimarykeyBatch(demandLists);
        return ResponseUtils.success();
    }
}
