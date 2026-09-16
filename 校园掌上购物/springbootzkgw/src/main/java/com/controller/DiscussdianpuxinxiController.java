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
import com.entity.DiscussdianpuxinxiEntity;
import com.entity.view.DiscussdianpuxinxiView;
import com.service.DiscussdianpuxinxiService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.CommonUtil;
/**
 * 店铺信息评论Controller
 *后端接口
 */
@RestController
@RequestMapping("/discussdianpuxinxi")
public class DiscussdianpuxinxiController {

    @Autowired
    private DiscussdianpuxinxiService discussdianpuxinxiService;



    
    
            /**
         * 后端列表
         */
                @RequestMapping("/page")
        public R page(@RequestParam Map<String, Object> params, DiscussdianpuxinxiEntity discussdianpuxinxi, HttpServletRequest request) {
                                    EntityWrapper<DiscussdianpuxinxiEntity> ew = new EntityWrapper<DiscussdianpuxinxiEntity>();
            PageUtils page = discussdianpuxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussdianpuxinxi), params), params));
            request.setAttribute("data", page);
            return R.ok().put("data", page);
        }

        /**
         * 前端列表
         */
                @RequestMapping("/list")
        public R list(@RequestParam Map<String, Object> params, DiscussdianpuxinxiEntity discussdianpuxinxi, HttpServletRequest request) {

         

                                    EntityWrapper<DiscussdianpuxinxiEntity> ew = new EntityWrapper<DiscussdianpuxinxiEntity>();
            PageUtils page = discussdianpuxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussdianpuxinxi), params), params));
            request.setAttribute("data", page);
            return R.ok().put("data", page);
        }

        /**
         * 列表
         */
                @RequestMapping("/lists")
        public R list( DiscussdianpuxinxiEntity discussdianpuxinxi) {
            EntityWrapper<DiscussdianpuxinxiEntity> ew = new EntityWrapper<DiscussdianpuxinxiEntity>();
            ew.allEq(MPUtil.allEQMapPre( discussdianpuxinxi, "discussdianpuxinxi"));
            return R.ok().put("data", discussdianpuxinxiService.selectListView(ew));
        }

        /**
         * 查询
         */
                @RequestMapping("/query")
        public R query(DiscussdianpuxinxiEntity discussdianpuxinxi) {
            EntityWrapper< DiscussdianpuxinxiEntity> ew = new EntityWrapper< DiscussdianpuxinxiEntity>();
            ew.allEq(MPUtil.allEQMapPre( discussdianpuxinxi, "Discussdianpuxinxi"));
            DiscussdianpuxinxiView discussdianpuxinxiView = discussdianpuxinxiService.selectView(ew);
            return R.ok("店铺信息评论").put("data", discussdianpuxinxiView);
        }

        /**
             * 后端详情
             */
                @RequestMapping("/info/{id}")
        public R info(@PathVariable("id") Long id) {
            DiscussdianpuxinxiEntity discussdianpuxinxi = discussdianpuxinxiService.selectById(id);
            return R.ok().put("data", discussdianpuxinxi);
        }

        /**
            * 前端详情
            */
                @RequestMapping("/detail/{id}")
        public R detail(@PathVariable("id") Long id) {
            DiscussdianpuxinxiEntity discussdianpuxinxi = discussdianpuxinxiService.selectById(id);
                        return R.ok().put("data", discussdianpuxinxi);
        }

        /**
        * 后端保存
        */
                @RequestMapping("/save")
        public R save(@RequestBody DiscussdianpuxinxiEntity discussdianpuxinxi, HttpServletRequest request) {
            
        
            discussdianpuxinxi.setId(new Date().getTime() + new Double(Math.floor(Math.random() * 1000)).longValue());
            //ValidatorUtils.validateEntity(address);
                            discussdianpuxinxiService.insert(discussdianpuxinxi);
            return R.ok();
        }

        /**
            * 前端保存
            */
                @RequestMapping("/add")
        public R add(@RequestBody DiscussdianpuxinxiEntity discussdianpuxinxi, HttpServletRequest request) {
                                discussdianpuxinxi.setId(new Date().getTime() + new Double(Math.floor(Math.random() * 1000)).longValue());
            //ValidatorUtils.validateEntity(yiyuanjianjie);

                discussdianpuxinxiService.insert(discussdianpuxinxi);
            return R.ok();
        }

        /**
          * 修改
          */
                @RequestMapping("/update")
        public R update(@RequestBody DiscussdianpuxinxiEntity discussdianpuxinxi, HttpServletRequest request) {
            //ValidatorUtils.validateEntity(zaixianbaoming);
                discussdianpuxinxiService.updateById(discussdianpuxinxi);//全部更新
            return R.ok();
        }

        /**
         * 删除
         */
                @RequestMapping("/delete")
        public R delete(@RequestBody Long[] ids) {
            discussdianpuxinxiService.deleteBatchIds(Arrays.asList(ids));
            return R.ok();
        }
    

    
    





    
}
