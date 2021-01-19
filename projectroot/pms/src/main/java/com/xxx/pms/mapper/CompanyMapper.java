package com.xxx.pms.mapper;

import com.xxx.pms.entity.Company;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface CompanyMapper extends Mapper<Company> {
    /**
     * 条件查询公司
     * @param param 公司实体类
     * @return 符合条件集合
     */
    List<Company> selectByCondition(Company param);


    @Select("select count(*) from sys_company where name =#{name} and id!=#{id}")
    int  companyNameIsRepeat(Company company);
}
