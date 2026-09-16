package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.DiscussshangpinguanliEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.DiscussshangpinguanliVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.DiscussshangpinguanliView;


/**
 * 商品管理评论
 */
public interface DiscussshangpinguanliService extends IService<DiscussshangpinguanliEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<DiscussshangpinguanliVO> selectListVO(Wrapper<DiscussshangpinguanliEntity> wrapper);

    DiscussshangpinguanliVO selectVO(@Param("ew") Wrapper<DiscussshangpinguanliEntity> wrapper);

    List<DiscussshangpinguanliView> selectListView(Wrapper<DiscussshangpinguanliEntity> wrapper);

    DiscussshangpinguanliView selectView(@Param("ew") Wrapper<DiscussshangpinguanliEntity> wrapper);

    PageUtils queryPage(Map<String, Object> params,Wrapper<DiscussshangpinguanliEntity> wrapper);


}

