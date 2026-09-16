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
import com.entity.DiscussshangpinguanliEntity;
import com.entity.view.DiscussshangpinguanliView;
import com.service.DiscussshangpinguanliService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.CommonUtil;
/**
 * 商品管理评论Controller
 *后端接口
 */
@RestController
@RequestMapping("/discussshangpinguanli")
public class DiscussshangpinguanliController {

    @Autowired
    private DiscussshangpinguanliService discussshangpinguanliService;



    
    
            /**
         * 后端列表
         */
                @RequestMapping("/page")
        public R page(@RequestParam Map<String, Object> params, DiscussshangpinguanliEntity discussshangpinguanli, HttpServletRequest request) {
                                    EntityWrapper<DiscussshangpinguanliEntity> ew = new EntityWrapper<DiscussshangpinguanliEntity>();
            PageUtils page = discussshangpinguanliService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussshangpinguanli), params), params));
            request.setAttribute("data", page);
            return R.ok().put("data", page);
        }

        /**
         * 前端列表
         */
                @RequestMapping("/list")
        public R list(@RequestParam Map<String, Object> params, DiscussshangpinguanliEntity discussshangpinguanli, HttpServletRequest request) {

         

                                    EntityWrapper<DiscussshangpinguanliEntity> ew = new EntityWrapper<DiscussshangpinguanliEntity>();
            PageUtils page = discussshangpinguanliService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussshangpinguanli), params), params));
            request.setAttribute("data", page);
            return R.ok().put("data", page);
        }

        /**
         * 列表
         */
                @RequestMapping("/lists")
        public R list( DiscussshangpinguanliEntity discussshangpinguanli) {
            EntityWrapper<DiscussshangpinguanliEntity> ew = new EntityWrapper<DiscussshangpinguanliEntity>();
            ew.allEq(MPUtil.allEQMapPre( discussshangpinguanli, "discussshangpinguanli"));
            return R.ok().put("data", discussshangpinguanliService.selectListView(ew));
        }

        /**
         * 查询
         */
                @RequestMapping("/query")
        public R query(DiscussshangpinguanliEntity discussshangpinguanli) {
            EntityWrapper< DiscussshangpinguanliEntity> ew = new EntityWrapper< DiscussshangpinguanliEntity>();
            ew.allEq(MPUtil.allEQMapPre( discussshangpinguanli, "Discussshangpinguanli"));
            DiscussshangpinguanliView discussshangpinguanliView = discussshangpinguanliService.selectView(ew);
            return R.ok("商品管理评论").put("data", discussshangpinguanliView);
        }

        /**
             * 后端详情
             */
                @RequestMapping("/info/{id}")
        public R info(@PathVariable("id") Long id) {
            DiscussshangpinguanliEntity discussshangpinguanli = discussshangpinguanliService.selectById(id);
            return R.ok().put("data", discussshangpinguanli);
        }

        /**
            * 前端详情
            */
                @RequestMapping("/detail/{id}")
        public R detail(@PathVariable("id") Long id) {
            DiscussshangpinguanliEntity discussshangpinguanli = discussshangpinguanliService.selectById(id);
                        return R.ok().put("data", discussshangpinguanli);
        }

        /**
        * 后端保存
        */
                @RequestMapping("/save")
        public R save(@RequestBody DiscussshangpinguanliEntity discussshangpinguanli, HttpServletRequest request) {
            
        
            discussshangpinguanli.setId(new Date().getTime() + new Double(Math.floor(Math.random() * 1000)).longValue());
            //ValidatorUtils.validateEntity(address);
                            discussshangpinguanliService.insert(discussshangpinguanli);
            return R.ok();
        }

        /**
            * 前端保存
            */
                @RequestMapping("/add")
        public R add(@RequestBody DiscussshangpinguanliEntity discussshangpinguanli, HttpServletRequest request) {
                                discussshangpinguanli.setId(new Date().getTime() + new Double(Math.floor(Math.random() * 1000)).longValue());
            //ValidatorUtils.validateEntity(yiyuanjianjie);

                discussshangpinguanliService.insert(discussshangpinguanli);
            return R.ok();
        }

        /**
          * 修改
          */
                @RequestMapping("/update")
        public R update(@RequestBody DiscussshangpinguanliEntity discussshangpinguanli, HttpServletRequest request) {
            //ValidatorUtils.validateEntity(zaixianbaoming);
                discussshangpinguanliService.updateById(discussshangpinguanli);//全部更新
            return R.ok();
        }

        /**
         * 删除
         */
                @RequestMapping("/delete")
        public R delete(@RequestBody Long[] ids) {
            discussshangpinguanliService.deleteBatchIds(Arrays.asList(ids));
            return R.ok();
        }
    

    
    





    
}
