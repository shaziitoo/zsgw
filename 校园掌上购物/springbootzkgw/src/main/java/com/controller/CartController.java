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
import com.entity.CartEntity;
import com.entity.view.CartView;
import com.service.CartService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.CommonUtil;
/**
 * 购物车Controller
 *后端接口
 */
@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;



    
    
            /**
         * 后端列表
         */
                @RequestMapping("/page")
        public R page(@RequestParam Map<String, Object> params, CartEntity cart, HttpServletRequest request) {
                                    EntityWrapper<CartEntity> ew = new EntityWrapper<CartEntity>();
            PageUtils page = cartService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, cart), params), params));
            request.setAttribute("data", page);
            return R.ok().put("data", page);
        }

        /**
         * 前端列表
         */
                @RequestMapping("/list")
        public R list(@RequestParam Map<String, Object> params, CartEntity cart, HttpServletRequest request) {

         

                                    EntityWrapper<CartEntity> ew = new EntityWrapper<CartEntity>();
            PageUtils page = cartService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, cart), params), params));
            request.setAttribute("data", page);
            return R.ok().put("data", page);
        }

        /**
         * 列表
         */
                @RequestMapping("/lists")
        public R list( CartEntity cart) {
            EntityWrapper<CartEntity> ew = new EntityWrapper<CartEntity>();
            ew.allEq(MPUtil.allEQMapPre( cart, "cart"));
            return R.ok().put("data", cartService.selectListView(ew));
        }

        /**
         * 查询
         */
                @RequestMapping("/query")
        public R query(CartEntity cart) {
            EntityWrapper< CartEntity> ew = new EntityWrapper< CartEntity>();
            ew.allEq(MPUtil.allEQMapPre( cart, "Cart"));
            CartView cartView = cartService.selectView(ew);
            return R.ok("购物车").put("data", cartView);
        }

        /**
             * 后端详情
             */
                @RequestMapping("/info/{id}")
        public R info(@PathVariable("id") Long id) {
            CartEntity cart = cartService.selectById(id);
            return R.ok().put("data", cart);
        }

        /**
            * 前端详情
            */
                @RequestMapping("/detail/{id}")
        public R detail(@PathVariable("id") Long id) {
            CartEntity cart = cartService.selectById(id);
                        return R.ok().put("data", cart);
        }

        /**
        * 后端保存
        */
                @RequestMapping("/save")
        public R save(@RequestBody CartEntity cart, HttpServletRequest request) {
            
        
            cart.setId(new Date().getTime() + new Double(Math.floor(Math.random() * 1000)).longValue());
            //ValidatorUtils.validateEntity(address);
                            cartService.insert(cart);
            return R.ok();
        }

        /**
            * 前端保存
            */
                @RequestMapping("/add")
        public R add(@RequestBody CartEntity cart, HttpServletRequest request) {
                                cart.setId(new Date().getTime() + new Double(Math.floor(Math.random() * 1000)).longValue());
            //ValidatorUtils.validateEntity(yiyuanjianjie);

                cartService.insert(cart);
            return R.ok();
        }

        /**
          * 修改
          */
                @RequestMapping("/update")
        public R update(@RequestBody CartEntity cart, HttpServletRequest request) {
            //ValidatorUtils.validateEntity(zaixianbaoming);
                cartService.updateById(cart);//全部更新
            return R.ok();
        }

        /**
         * 删除
         */
                @RequestMapping("/delete")
        public R delete(@RequestBody Long[] ids) {
            cartService.deleteBatchIds(Arrays.asList(ids));
            return R.ok();
        }
    

    
    





    
}
