package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.service.StoreupService;
import com.utils.UserBasedCollaborativeFiltering;
import java.util.*;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.utils.PageUtils;
import com.utils.Query;


import com.dao.ShangpinguanliDao;
import com.entity.ShangpinguanliEntity;
import com.service.ShangpinguanliService;
import com.entity.vo.ShangpinguanliVO;
import com.entity.view.ShangpinguanliView;

@Service("shangpinguanliService")
public class ShangpinguanliServiceImpl extends ServiceImpl<ShangpinguanliDao, ShangpinguanliEntity> implements ShangpinguanliService {

    @Autowired
    private StoreupService storeupService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ShangpinguanliEntity> page = this.selectPage(
                new Query<ShangpinguanliEntity>(params).getPage(),
                new EntityWrapper<ShangpinguanliEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Wrapper<ShangpinguanliEntity> wrapper) {
        Page<ShangpinguanliView> page = new Query<ShangpinguanliView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page, wrapper));
        PageUtils pageUtil = new PageUtils(page);
        return pageUtil;
    }

    @Override
    public List<ShangpinguanliVO> selectListVO(Wrapper<ShangpinguanliEntity> wrapper) {
        return baseMapper.selectListVO(wrapper);
    }

    @Override
    public ShangpinguanliVO selectVO(Wrapper<ShangpinguanliEntity> wrapper) {
        return baseMapper.selectVO(wrapper);
    }

    @Override
    public List<ShangpinguanliView> selectListView(Wrapper<ShangpinguanliEntity> wrapper) {
        return baseMapper.selectListView(wrapper);
    }

    @Override
    public ShangpinguanliView selectView(Wrapper<ShangpinguanliEntity> wrapper) {
        return baseMapper.selectView(wrapper);
    }

    @Override
    public Map<String, Double> recommend(Long userId, Integer num) {
        // 创建用户-物品收藏矩阵
        Map<String, Set<String>> userItemCollections = new HashMap<>();
        List<Map<String, String>> maps = storeupService.getCollected();
        for (Map<String, String> map : maps) {
            userItemCollections.put(String.valueOf(map.get("userid")), new HashSet<>(Arrays.asList(map.get("items").split(","))));
        }
        UserBasedCollaborativeFiltering cf = new UserBasedCollaborativeFiltering(userItemCollections);
        // 为目标用户推荐物品
        Map<String, Double> recommendations = cf.recommendItems(String.valueOf(userId), num);
        // 输出推荐结果
        System.err.println("基于用户收藏的推荐 用户ID:" + userId);
        for (Map.Entry<String, Double> entry : recommendations.entrySet()) {
            System.err.println("商品ID: " + entry.getKey() + ", 分数: " + entry.getValue());
        }
        return recommendations;
    }
}
