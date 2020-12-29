package com.xxx.pms.controller;

import com.github.pagehelper.PageInfo;
import com.xxx.pms.entity.Company;
import com.xxx.pms.entity.CompanyMenu;
import com.xxx.pms.entity.Menu;
import com.xxx.pms.po.RequestParamPage;
import com.xxx.pms.response.Response;
import com.xxx.pms.service.CompanyService;
import com.xxx.pms.util.ResponseUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Api(tags={"公司管理"})
@RestController
@RequestMapping("/company")
public class CompanyController {

    @Resource
    private CompanyService companyService;

    @ApiOperation(value = "新增公司", notes="新增公司接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name="company",value="公司实体类",dataType="Company",required=true)
    })
    @ApiImplicitParam(value="鉴权token", name="Authorization", paramType="header", dataType="string", required=true)
    @PostMapping("addCompany")
    public Response addCompany(@RequestBody Company company){
        Map result= companyService.add(company);
        return (int)result.get("code")==200? ResponseUtils.success():
                ResponseUtils.fillState(new String[]{"400", (String) result.get("msg")});
    }

    @ApiOperation(value = "通过公司id删除公司", notes="删除公司接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="公司实体类id",dataType="Integer",required=true)
    })
    @ApiImplicitParam(value="鉴权token", name="Authorization", paramType="header", dataType="string", required=true)
    @PostMapping("deleteCompanyById")
    public Response deleteCompanyById(Integer id){
        int  result= companyService.deleteById(id);
        return result>0?ResponseUtils.success(): ResponseUtils.error();
    }

    @ApiOperation(value = "通过公司id更新公司", notes="更新公司接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name="company",value="公司实体类",dataType="Company",required=true)
    })
    @ApiImplicitParam(value="鉴权token", name="Authorization", paramType="header", dataType="string", required=true)
    @PostMapping("updateCompanyById")
    public Response updateCompanyById(@RequestBody Company company){
        int  result= companyService.updateById(company);
        return result>0?ResponseUtils.success(): ResponseUtils.error();
    }

    @ApiOperation(value = "分页条件查询公司", notes="分页条件查询公司接口")
    @ApiImplicitParam(value="鉴权token", name="Authorization", paramType="header", dataType="String", required=true)
    @PostMapping("getCompanysByConditionPage")
    public Response getCompanysByConditionPage(@RequestBody RequestParamPage<Company> paramPage){
        PageInfo<Company> result= companyService.selectByPageCondition(paramPage);
        return  ResponseUtils.successData(result);
    }


    @ApiOperation(value = "公司分配菜单", notes="公司分配菜单接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name="companyId",value="公司id",dataType="Integer",required=true),
    })
    @ApiImplicitParam(value="鉴权token", name="Authorization", paramType="header", dataType="String", required=true)
    @PostMapping("addMenu")
    public Response addMenu(Integer companyId,@RequestBody Integer []  menuIds){
        int  result= companyService.addCompanyMenus(companyId, Arrays.asList(menuIds));
        return result>0?ResponseUtils.success(): ResponseUtils.error();
    }

    @ApiOperation(value = "公司删除菜单", notes="公司删除菜单接口")
    @ApiImplicitParam(value="鉴权token", name="Authorization", paramType="header", dataType="String", required=true)
    @PostMapping("deleteMenu")
    public Response deleteMenu(@RequestBody CompanyMenu companyMenu){
        int result= companyService.deleteMenu(companyMenu);
        return  result>0?ResponseUtils.success(): ResponseUtils.error();
    }

    @ApiOperation(value = "通过公司id查询公司(包含公司菜单)", notes="查询公司(包含公司菜单)接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name="companyId",value="公司Id",dataType="Integer",required=true)
    })
    @ApiImplicitParam(value="鉴权token", name="Authorization", paramType="header", dataType="String", required=true)
    @PostMapping("getCompanyContainMenus")
    public Response getCompanyMenus(Integer companyId){
        Company result= companyService.getCompanyContainMenus(companyId);
        return  ResponseUtils.successData(result);
    }

}
