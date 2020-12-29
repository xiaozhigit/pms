package com.xxx.pms.controller;

import com.xxx.pms.entity.Menu;
import com.xxx.pms.entity.UserFavoriteMenu;
import com.xxx.pms.po.MenuDto;
import com.xxx.pms.response.Response;
import com.xxx.pms.service.MenuService;
import com.xxx.pms.util.ResponseUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(tags={"菜单管理"})
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Resource
    private MenuService menuService;

    @ApiOperation(value = "新增菜单", notes="新增菜单接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name="menu",value="菜单实体类",dataType="Menu",required=true)
    })
    @ApiImplicitParam(value="鉴权token", name="Authorization", paramType="header", dataType="string", required=true)
    @PostMapping("addMenu")
    public Response addMenu(@RequestBody Menu menu){
        int  result= menuService.add(menu);
        return result>0?ResponseUtils.success(): ResponseUtils.error();
    }

    @ApiOperation(value = "通过菜单id删除菜单", notes="删除菜单接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="菜单实体类id",dataType="Integer",required=true)
    })
    @ApiImplicitParam(value="鉴权token", name="Authorization", paramType="header", dataType="string", required=true)
    @PostMapping("deleteMenuById")
    public Response deleteMenuById(Integer id){
        int  result= menuService.deleteById(id);
        return result>0?ResponseUtils.success(): ResponseUtils.error();
    }

    @ApiOperation(value = "通过菜单id更新菜单", notes="更新菜单接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name="menu",value="菜单实体类",dataType="Menu",required=true)
    })
    @ApiImplicitParam(value="鉴权token", name="Authorization", paramType="header", dataType="string", required=true)
    @PostMapping("updateMenuById")
    public Response updateMenuById(@RequestBody Menu menu){
        int  result= menuService.updateById(menu);
        return result>0?ResponseUtils.success(): ResponseUtils.error();
    }

    @ApiOperation(value = "查询所有菜单", notes="查询所有菜单接口")
    @ApiImplicitParam(value="鉴权token", name="Authorization", paramType="header", dataType="String", required=true)
    @PostMapping("getAllMenus")
    public Response getAllMenus(){
        List<Menu> result= menuService.selectAll();
        return  ResponseUtils.successData(result);
    }

    @ApiOperation(value = "查询所有菜单-菜单中显示所属公司", notes="查询所有菜单接口")
    @ApiImplicitParam(value="鉴权token", name="Authorization", paramType="header", dataType="String", required=true)
    @PostMapping("getAllMenusContainCompanyName")
    public Response getAllMenusContainCompanyName(){
        List<Menu> result= menuService.getAllMenusContainCompanyName();
        return  ResponseUtils.successData(result);
    }

    @ApiOperation(value = "根据用户id查询用户所有菜单", notes="根据用户id查询用户所有菜单接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userId",value="用户id",dataType="Integer",required=true)
    })
    @ApiImplicitParam(value="鉴权token", name="Authorization", paramType="header", dataType="String", required=true)
    @PostMapping("getMenusByUserId")
    public Response getMenusByUserId(Integer userId){
        List<MenuDto> result= menuService.selectMenusByUserId(userId);
        return  ResponseUtils.successData(result);
    }

    @ApiOperation(value = "根据用户id查询用户收藏的所有菜单", notes="根据用户id查询用户收藏的所有菜单接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userId",value="用户id",dataType="Integer",required=true)
    })
    @ApiImplicitParam(value="鉴权token", name="Authorization", paramType="header", dataType="String", required=true)
    @PostMapping("getFavoriteMenusByUserId")
    public Response getFavoriteMenusByUserId(Integer userId){
        List<Menu> result= menuService.selectFavoriteMenusByUserId(userId);
        return  ResponseUtils.successData(result);
    }

    @ApiOperation(value = "用户收藏菜单", notes="用户收藏菜单接口")
    @ApiImplicitParam(value="鉴权token", name="Authorization", paramType="header", dataType="String", required=true)
    @PostMapping("userFavoriteMenu")
    public Response userFavoriteMenu(@RequestBody UserFavoriteMenu userFavoriteMenu){
        int result= menuService.userFavoriteMenu(userFavoriteMenu);
        return result>0?ResponseUtils.success():ResponseUtils.error();
    }

}
