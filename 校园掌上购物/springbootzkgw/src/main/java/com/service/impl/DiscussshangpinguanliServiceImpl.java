package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.utils.PageUtils;
import com.utils.Query;


import com.dao.DiscussshangpinguanliDao;
import com.entity.DiscussshangpinguanliEntity;
import com.service.DiscussshangpinguanliService;
import com.entity.vo.DiscussshangpinguanliVO;
import com.entity.view.DiscussshangpinguanliView;

@Service("discussshangpinguanliService")
public class DiscussshangpinguanliServiceImpl extends ServiceImpl<DiscussshangpinguanliDao, DiscussshangpinguanliEntity> implements DiscussshangpinguanliService {


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<DiscussshangpinguanliEntity> page = this.selectPage(
                new Query<DiscussshangpinguanliEntity>(params).getPage(),
                new EntityWrapper<DiscussshangpinguanliEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Wrapper<DiscussshangpinguanliEntity> wrapper) {
        Page<DiscussshangpinguanliView> page = new Query<DiscussshangpinguanliView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page, wrapper));
        PageUtils pageUtil = new PageUtils(page);
        return pageUtil;
    }

    @Override
    public List<DiscussshangpinguanliVO> selectListVO(Wrapper<DiscussshangpinguanliEntity> wrapper) {
        return baseMapper.selectListVO(wrapper);
    }

    @Override
    public DiscussshangpinguanliVO selectVO(Wrapper<DiscussshangpinguanliEntity> wrapper) {
        return baseMapper.selectVO(wrapper);
    }

    @Override
    public List<DiscussshangpinguanliView> selectListView(Wrapper<DiscussshangpinguanliEntity> wrapper) {
        return baseMapper.selectListView(wrapper);
    }

    @Override
    public DiscussshangpinguanliView selectView(Wrapper<DiscussshangpinguanliEntity> wrapper) {
        return baseMapper.selectView(wrapper);
    }

}
