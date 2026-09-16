package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.ShangpinguanliEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.ShangpinguanliVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.ShangpinguanliView;


/**
 * 商品管理
 */
public interface ShangpinguanliService extends IService<ShangpinguanliEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<ShangpinguanliVO> selectListVO(Wrapper<ShangpinguanliEntity> wrapper);

    ShangpinguanliVO selectVO(@Param("ew") Wrapper<ShangpinguanliEntity> wrapper);

    List<ShangpinguanliView> selectListView(Wrapper<ShangpinguanliEntity> wrapper);

    ShangpinguanliView selectView(@Param("ew") Wrapper<ShangpinguanliEntity> wrapper);

    PageUtils queryPage(Map<String, Object> params,Wrapper<ShangpinguanliEntity> wrapper);


    Map<String, Double> recommend(Long userId, Integer num);
}

