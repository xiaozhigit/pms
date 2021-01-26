package com.xxx.pms.response;

import com.xxx.pms.entity.Menu;
import lombok.Data;
import java.util.List;

@Data
public class MenuDto {
    /**
     * 当前菜单
     */
  private Menu menu;
    /**
     * 菜单的子菜单集合
     */
  private   List<MenuDto> children;
}
