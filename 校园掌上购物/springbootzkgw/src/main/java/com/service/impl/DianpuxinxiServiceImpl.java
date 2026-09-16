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


import com.dao.DianpuxinxiDao;
import com.entity.DianpuxinxiEntity;
import com.service.DianpuxinxiService;
import com.entity.vo.DianpuxinxiVO;
import com.entity.view.DianpuxinxiView;

@Service("dianpuxinxiService")
public class DianpuxinxiServiceImpl extends ServiceImpl<DianpuxinxiDao, DianpuxinxiEntity> implements DianpuxinxiService {

    @Autowired
    private StoreupService storeupService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<DianpuxinxiEntity> page = this.selectPage(
                new Query<DianpuxinxiEntity>(params).getPage(),
                new EntityWrapper<DianpuxinxiEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Wrapper<DianpuxinxiEntity> wrapper) {
        Page<DianpuxinxiView> page = new Query<DianpuxinxiView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page, wrapper));
        PageUtils pageUtil = new PageUtils(page);
        return pageUtil;
    }

    @Override
    public List<DianpuxinxiVO> selectListVO(Wrapper<DianpuxinxiEntity> wrapper) {
        return baseMapper.selectListVO(wrapper);
    }

    @Override
    public DianpuxinxiVO selectVO(Wrapper<DianpuxinxiEntity> wrapper) {
        return baseMapper.selectVO(wrapper);
    }

    @Override
    public List<DianpuxinxiView> selectListView(Wrapper<DianpuxinxiEntity> wrapper) {
        return baseMapper.selectListView(wrapper);
    }

    @Override
    public DianpuxinxiView selectView(Wrapper<DianpuxinxiEntity> wrapper) {
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
