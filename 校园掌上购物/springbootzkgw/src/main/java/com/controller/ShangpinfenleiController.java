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
import com.entity.ShangpinfenleiEntity;
import com.entity.view.ShangpinfenleiView;
import com.service.ShangpinfenleiService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.CommonUtil;
/**
 * 商品分类Controller
 *后端接口
 */
@RestController
@RequestMapping("/shangpinfenlei")
public class ShangpinfenleiController {

    @Autowired
    private ShangpinfenleiService shangpinfenleiService;



    
    
            /**
         * 后端列表
         */
                @RequestMapping("/page")
        public R page(@RequestParam Map<String, Object> params, ShangpinfenleiEntity shangpinfenlei, HttpServletRequest request) {
                                    EntityWrapper<ShangpinfenleiEntity> ew = new EntityWrapper<ShangpinfenleiEntity>();
            PageUtils page = shangpinfenleiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, shangpinfenlei), params), params));
            request.setAttribute("data", page);
            return R.ok().put("data", page);
        }

        /**
         * 前端列表
         */
                @IgnoreAuth
                @RequestMapping("/list")
        public R list(@RequestParam Map<String, Object> params, ShangpinfenleiEntity shangpinfenlei, HttpServletRequest request) {

         

                                    EntityWrapper<ShangpinfenleiEntity> ew = new EntityWrapper<ShangpinfenleiEntity>();
            PageUtils page = shangpinfenleiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, shangpinfenlei), params), params));
            request.setAttribute("data", page);
            return R.ok().put("data", page);
        }

        /**
         * 列表
         */
                @RequestMapping("/lists")
        public R list( ShangpinfenleiEntity shangpinfenlei) {
            EntityWrapper<ShangpinfenleiEntity> ew = new EntityWrapper<ShangpinfenleiEntity>();
            ew.allEq(MPUtil.allEQMapPre( shangpinfenlei, "shangpinfenlei"));
            return R.ok().put("data", shangpinfenleiService.selectListView(ew));
        }

        /**
         * 查询
         */
                @RequestMapping("/query")
        public R query(ShangpinfenleiEntity shangpinfenlei) {
            EntityWrapper< ShangpinfenleiEntity> ew = new EntityWrapper< ShangpinfenleiEntity>();
            ew.allEq(MPUtil.allEQMapPre( shangpinfenlei, "Shangpinfenlei"));
            ShangpinfenleiView shangpinfenleiView = shangpinfenleiService.selectView(ew);
            return R.ok("商品分类").put("data", shangpinfenleiView);
        }

        /**
             * 后端详情
             */
                @IgnoreAuth
                @RequestMapping("/info/{id}")
        public R info(@PathVariable("id") Long id) {
            ShangpinfenleiEntity shangpinfenlei = shangpinfenleiService.selectById(id);
            return R.ok().put("data", shangpinfenlei);
        }

        /**
            * 前端详情
            */
                @IgnoreAuth
                @RequestMapping("/detail/{id}")
        public R detail(@PathVariable("id") Long id) {
            ShangpinfenleiEntity shangpinfenlei = shangpinfenleiService.selectById(id);
                        return R.ok().put("data", shangpinfenlei);
        }

        /**
        * 后端保存
        */
                @RequestMapping("/save")
        public R save(@RequestBody ShangpinfenleiEntity shangpinfenlei, HttpServletRequest request) {
            
        
            shangpinfenlei.setId(new Date().getTime() + new Double(Math.floor(Math.random() * 1000)).longValue());
            //ValidatorUtils.validateEntity(address);
                            shangpinfenleiService.insert(shangpinfenlei);
            return R.ok();
        }

        /**
            * 前端保存
            */
                @RequestMapping("/add")
        public R add(@RequestBody ShangpinfenleiEntity shangpinfenlei, HttpServletRequest request) {
                                shangpinfenlei.setId(new Date().getTime() + new Double(Math.floor(Math.random() * 1000)).longValue());
            //ValidatorUtils.validateEntity(yiyuanjianjie);

                shangpinfenleiService.insert(shangpinfenlei);
            return R.ok();
        }

        /**
          * 修改
          */
                @RequestMapping("/update")
        public R update(@RequestBody ShangpinfenleiEntity shangpinfenlei, HttpServletRequest request) {
            //ValidatorUtils.validateEntity(zaixianbaoming);
                shangpinfenleiService.updateById(shangpinfenlei);//全部更新
            return R.ok();
        }

        /**
         * 删除
         */
                @RequestMapping("/delete")
        public R delete(@RequestBody Long[] ids) {
            shangpinfenleiService.deleteBatchIds(Arrays.asList(ids));
            return R.ok();
        }
    

    
    





    
}
