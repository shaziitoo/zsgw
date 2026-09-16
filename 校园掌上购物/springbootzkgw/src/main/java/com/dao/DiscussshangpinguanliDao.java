package com.dao;

import com.entity.DiscussshangpinguanliEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.DiscussshangpinguanliVO;
import com.entity.view.DiscussshangpinguanliView;


/**
 * 商品管理评论
 *
 * @author
 * @email
 * @date 2021-03-02 11:45:35
 */
public interface DiscussshangpinguanliDao extends BaseMapper<DiscussshangpinguanliEntity> {

    List<DiscussshangpinguanliVO> selectListVO(@Param("ew") Wrapper<DiscussshangpinguanliEntity> wrapper);

        DiscussshangpinguanliVO selectVO(@Param("ew") Wrapper<DiscussshangpinguanliEntity> wrapper);

    List<DiscussshangpinguanliView> selectListView(@Param("ew") Wrapper<DiscussshangpinguanliEntity> wrapper);

    List<DiscussshangpinguanliView> selectListView(Pagination page, @Param("ew") Wrapper<DiscussshangpinguanliEntity> wrapper);

        DiscussshangpinguanliView selectView(@Param("ew") Wrapper<DiscussshangpinguanliEntity> wrapper);
}
