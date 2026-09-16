package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.MessagessEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.MessagessVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.MessagessView;


/**
 * 在线留言
 */
public interface MessagessService extends IService<MessagessEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<MessagessVO> selectListVO(Wrapper<MessagessEntity> wrapper);

    MessagessVO selectVO(@Param("ew") Wrapper<MessagessEntity> wrapper);

    List<MessagessView> selectListView(Wrapper<MessagessEntity> wrapper);

    MessagessView selectView(@Param("ew") Wrapper<MessagessEntity> wrapper);

    PageUtils queryPage(Map<String, Object> params,Wrapper<MessagessEntity> wrapper);


}

