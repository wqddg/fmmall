package com.wqddg.dao;

import com.wqddg.entity.Category;
import com.wqddg.entity.CategoryVo;
import com.wqddg.general.GeneralDAO;

import java.util.List;

public interface CategoryMapper extends GeneralDAO<Category> {
    //这里我们使用连接查询
    List<CategoryVo> selectAllCategories();
    //我们这里使用子查询的方式   根据parent_id 查询菜单
    List<CategoryVo> selectAllCategories_id(int parentId);

    //查询一级类别
    List<CategoryVo> selectFirstLevelCategories();

}