package com.xxx.pms.service;

import com.xxx.pms.entity.Iterative;
import com.xxx.pms.po.RequestParamPage;
import com.xxx.pms.response.Response;

public interface IterativeService {
    /**
     * 增加迭代数据
     * @param iterative
     * @return
     */
    Response addIterative(Iterative iterative);


    /**
     * 修改迭代数据
     * @param iterative
     * @return
     */
    Response updateIterative(Iterative iterative);

    /**
     * 查询迭代数据
     * @param id
     * @return
     */
    Response getIterativeById(int id);

    /**
     * 删除迭代数据
     * @param id
     * @return
     */
    Response deleteIterativeById(int id);

    /**
     * 分页条件查询迭代数据列表
     * @param form
     * @return
     */
    Response getIterativeListByPage(RequestParamPage<Iterative> form);


    /**
     * 根据项目id查询出未完成的最新迭代的一条数据
     * @param projectId
     * @return
     */
    Response getIterativeByProjectId(int projectId);

}
