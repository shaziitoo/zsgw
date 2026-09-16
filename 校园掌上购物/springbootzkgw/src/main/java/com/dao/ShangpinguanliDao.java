package com.dao;

import com.entity.ShangpinguanliEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.ShangpinguanliVO;
import com.entity.view.ShangpinguanliView;


/**
 * 商品管理
 *
 * @author
 * @email
 * @date 2021-03-02 11:45:35
 */
public interface ShangpinguanliDao extends BaseMapper<ShangpinguanliEntity> {

    List<ShangpinguanliVO> selectListVO(@Param("ew") Wrapper<ShangpinguanliEntity> wrapper);

        ShangpinguanliVO selectVO(@Param("ew") Wrapper<ShangpinguanliEntity> wrapper);

    List<ShangpinguanliView> selectListView(@Param("ew") Wrapper<ShangpinguanliEntity> wrapper);

    List<ShangpinguanliView> selectListView(Pagination page, @Param("ew") Wrapper<ShangpinguanliEntity> wrapper);

        ShangpinguanliView selectView(@Param("ew") Wrapper<ShangpinguanliEntity> wrapper);
}
