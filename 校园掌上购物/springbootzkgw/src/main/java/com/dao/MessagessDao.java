package com.dao;

import com.entity.MessagessEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.MessagessVO;
import com.entity.view.MessagessView;


/**
 * 在线留言
 *
 * @author
 * @email
 * @date 2021-03-02 11:45:35
 */
public interface MessagessDao extends BaseMapper<MessagessEntity> {

    List<MessagessVO> selectListVO(@Param("ew") Wrapper<MessagessEntity> wrapper);

        MessagessVO selectVO(@Param("ew") Wrapper<MessagessEntity> wrapper);

    List<MessagessView> selectListView(@Param("ew") Wrapper<MessagessEntity> wrapper);

    List<MessagessView> selectListView(Pagination page, @Param("ew") Wrapper<MessagessEntity> wrapper);

        MessagessView selectView(@Param("ew") Wrapper<MessagessEntity> wrapper);
}
