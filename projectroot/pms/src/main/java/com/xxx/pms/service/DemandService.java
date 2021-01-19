package com.xxx.pms.service;

import com.xxx.pms.entity.Demand;
import com.xxx.pms.po.RequestParamPage;
import com.xxx.pms.response.Response;

import java.util.List;

public interface DemandService {
    /**
     * 增加需求
     * @param demand
     * @return
     */
    Response addDemand(Demand demand);


    /**
     * 修改需求
     * @param demand
     * @return
     */
    Response updateDemand(Demand demand);

    /**
     * 查询需求
     * @param id
     * @return
     */
    Response getDemandById(int id);

    /**
     * 删除需求
     * @param id
     * @return
     */
    Response deleteDemandById(int id);

    /**
     * 分页条件查询需求列表
     * @param form
     * @return
     */
    Response getDemandListByPage(RequestParamPage<Demand> form);


    Response saveDemandList(List<Demand> demandList, int page, int pageSize);
}
