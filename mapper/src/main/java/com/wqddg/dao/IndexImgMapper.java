package com.wqddg.dao;

import com.wqddg.entity.IndexImg;
import com.wqddg.general.GeneralDAO;

import java.util.List;

public interface IndexImgMapper extends GeneralDAO<IndexImg> {


    //1.查询轮播图信息 查询statis=1且按照seq进行排序
    public List<IndexImg> listIndexImags();

}