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


import com.dao.MessagessDao;
import com.entity.MessagessEntity;
import com.service.MessagessService;
import com.entity.vo.MessagessVO;
import com.entity.view.MessagessView;

@Service("messagessService")
public class MessagessServiceImpl extends ServiceImpl<MessagessDao, MessagessEntity> implements MessagessService {


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<MessagessEntity> page = this.selectPage(
                new Query<MessagessEntity>(params).getPage(),
                new EntityWrapper<MessagessEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Wrapper<MessagessEntity> wrapper) {
        Page<MessagessView> page = new Query<MessagessView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page, wrapper));
        PageUtils pageUtil = new PageUtils(page);
        return pageUtil;
    }

    @Override
    public List<MessagessVO> selectListVO(Wrapper<MessagessEntity> wrapper) {
        return baseMapper.selectListVO(wrapper);
    }

    @Override
    public MessagessVO selectVO(Wrapper<MessagessEntity> wrapper) {
        return baseMapper.selectVO(wrapper);
    }

    @Override
    public List<MessagessView> selectListView(Wrapper<MessagessEntity> wrapper) {
        return baseMapper.selectListView(wrapper);
    }

    @Override
    public MessagessView selectView(Wrapper<MessagessEntity> wrapper) {
        return baseMapper.selectView(wrapper);
    }

}
