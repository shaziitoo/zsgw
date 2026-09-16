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
import com.entity.OrdersEntity;
import com.entity.view.OrdersView;
import com.service.OrdersService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.CommonUtil;
/**
 * 订单Controller
 *后端接口
 */
@RestController
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;



    
    
            /**
         * 后端列表
         */
                @RequestMapping("/page")
        public R page(@RequestParam Map<String, Object> params, OrdersEntity orders, HttpServletRequest request) {
                                    EntityWrapper<OrdersEntity> ew = new EntityWrapper<OrdersEntity>();
            String role = request.getSession().getAttribute("role").toString();
            // 只有普通用户才看自己的订单，管理员和商家看所有订单
            if ("用户".equals(role)) {
                ew.eq("userid", request.getSession().getAttribute("userId"));
            }
            PageUtils page = ordersService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, orders), params), params));
            request.setAttribute("data", page);
            return R.ok().put("data", page);
        }

        /**
         * 前端列表
         */
                @RequestMapping("/list")
        public R list(@RequestParam Map<String, Object> params, OrdersEntity orders, HttpServletRequest request) {



                                    EntityWrapper<OrdersEntity> ew = new EntityWrapper<OrdersEntity>();
            String role = request.getSession().getAttribute("role").toString();
            // 只有普通用户才看自己的订单，管理员和商家看所有订单
            if ("用户".equals(role)) {
                ew.eq("userid", request.getSession().getAttribute("userId"));
            }
            PageUtils page = ordersService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, orders), params), params));
            request.setAttribute("data", page);
            return R.ok().put("data", page);
        }

        /**
         * 列表
         */
                @RequestMapping("/lists")
        public R list( OrdersEntity orders) {
            EntityWrapper<OrdersEntity> ew = new EntityWrapper<OrdersEntity>();
            ew.allEq(MPUtil.allEQMapPre( orders, "orders"));
            return R.ok().put("data", ordersService.selectListView(ew));
        }

        /**
         * 查询
         */
                @RequestMapping("/query")
        public R query(OrdersEntity orders) {
            EntityWrapper< OrdersEntity> ew = new EntityWrapper< OrdersEntity>();
            ew.allEq(MPUtil.allEQMapPre( orders, "Orders"));
            OrdersView ordersView = ordersService.selectView(ew);
            return R.ok("订单").put("data", ordersView);
        }

        /**
             * 后端详情
             */
                @RequestMapping("/info/{id}")
        public R info(@PathVariable("id") Long id) {
            OrdersEntity orders = ordersService.selectById(id);
            return R.ok().put("data", orders);
        }

        /**
            * 前端详情
            */
                @RequestMapping("/detail/{id}")
        public R detail(@PathVariable("id") Long id) {
            OrdersEntity orders = ordersService.selectById(id);
                        return R.ok().put("data", orders);
        }

        /**
        * 后端保存
        */
                @RequestMapping("/save")
        public R save(@RequestBody OrdersEntity orders, HttpServletRequest request) {
            
        
            orders.setId(new Date().getTime() + new Double(Math.floor(Math.random() * 1000)).longValue());
            //ValidatorUtils.validateEntity(address);
                            ordersService.insert(orders);
            return R.ok();
        }

        /**
            * 前端保存
            */
                @RequestMapping("/add")
        public R add(@RequestBody OrdersEntity orders, HttpServletRequest request) {
                                orders.setId(new Date().getTime() + new Double(Math.floor(Math.random() * 1000)).longValue());
            //ValidatorUtils.validateEntity(yiyuanjianjie);

                ordersService.insert(orders);
            return R.ok();
        }

        /**
          * 修改
          */
                @RequestMapping("/update")
        public R update(@RequestBody OrdersEntity orders, HttpServletRequest request) {
            //ValidatorUtils.validateEntity(zaixianbaoming);
                ordersService.updateById(orders);//全部更新
            return R.ok();
        }

        /**
         * 删除
         */
                @RequestMapping("/delete")
        public R delete(@RequestBody Long[] ids) {
            ordersService.deleteBatchIds(Arrays.asList(ids));
            return R.ok();
        }
    

    
    





    
}
