package com.controller;

import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.entity.UserEntity;
import com.utils.ValidatorUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
 import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.annotation.IgnoreAuth;
import com.entity.MessagessEntity;
import com.entity.view.MessagessView;
import com.service.MessagessService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.CommonUtil;
/**
 * 在线留言Controller
 *后端接口
 */
@RestController
@RequestMapping("/messagess")
public class MessagessController {

    @Autowired
    private MessagessService messagessService;



    
    
            /**
         * 后端列表
         */
                @RequestMapping("/page")
        public R page(@RequestParam Map<String, Object> params, MessagessEntity messagess, HttpServletRequest request) {
                                    EntityWrapper<MessagessEntity> ew = new EntityWrapper<MessagessEntity>();
            PageUtils page = messagessService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, messagess), params), params));
            request.setAttribute("data", page);
            return R.ok().put("data", page);
        }

        /**
         * 前端列表
         */
                @RequestMapping("/list")
        public R list(@RequestParam Map<String, Object> params, MessagessEntity messagess, HttpServletRequest request) {

         

                                    EntityWrapper<MessagessEntity> ew = new EntityWrapper<MessagessEntity>();
            PageUtils page = messagessService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, messagess), params), params));
            request.setAttribute("data", page);
            return R.ok().put("data", page);
        }

        /**
         * 列表
         */
                @RequestMapping("/lists")
        public R list( MessagessEntity messagess) {
            EntityWrapper<MessagessEntity> ew = new EntityWrapper<MessagessEntity>();
            ew.allEq(MPUtil.allEQMapPre( messagess, "messagess"));
            return R.ok().put("data", messagessService.selectListView(ew));
        }

        /**
         * 查询
         */
                @RequestMapping("/query")
        public R query(MessagessEntity messagess) {
            EntityWrapper< MessagessEntity> ew = new EntityWrapper< MessagessEntity>();
            ew.allEq(MPUtil.allEQMapPre( messagess, "Messagess"));
            MessagessView messagessView = messagessService.selectView(ew);
            return R.ok("在线留言").put("data", messagessView);
        }

        /**
             * 后端详情
             */
                @RequestMapping("/info/{id}")
        public R info(@PathVariable("id") Long id) {
            MessagessEntity messagess = messagessService.selectById(id);
            return R.ok().put("data", messagess);
        }

        /**
            * 前端详情
            */
                @RequestMapping("/detail/{id}")
        public R detail(@PathVariable("id") Long id) {
            MessagessEntity messagess = messagessService.selectById(id);
                        return R.ok().put("data", messagess);
        }

        /**
        * 后端保存
        */
                @RequestMapping("/save")
        public R save(@RequestBody MessagessEntity messagess, HttpServletRequest request) {
            
        
            messagess.setId(new Date().getTime() + new Double(Math.floor(Math.random() * 1000)).longValue());
            //ValidatorUtils.validateEntity(address);
                            messagessService.insert(messagess);
            return R.ok();
        }

        /**
            * 前端保存
            */
                @RequestMapping("/add")
        public R add(@RequestBody MessagessEntity messagess, HttpServletRequest request) {
                                messagess.setId(new Date().getTime() + new Double(Math.floor(Math.random() * 1000)).longValue());
            //ValidatorUtils.validateEntity(yiyuanjianjie);

                messagessService.insert(messagess);
            return R.ok();
        }

        /**
          * 修改
          */
                @RequestMapping("/update")
        public R update(@RequestBody MessagessEntity messagess, HttpServletRequest request) {
            //ValidatorUtils.validateEntity(zaixianbaoming);
                messagessService.updateById(messagess);//全部更新
            return R.ok();
        }

        /**
         * 删除
         */
                @RequestMapping("/delete")
        public R delete(@RequestBody Long[] ids) {
            messagessService.deleteBatchIds(Arrays.asList(ids));
            return R.ok();
        }
    

    
    





    
}
