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
import com.entity.ShangpinguanliEntity;
import com.entity.view.ShangpinguanliView;
import com.service.ShangpinguanliService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.CommonUtil;
/**
 * 商品管理Controller
 *后端接口
 */
@RestController
@RequestMapping("/shangpinguanli")
public class ShangpinguanliController {

    @Autowired
    private ShangpinguanliService shangpinguanliService;



    
    
            /**
         * 后端列表
         */
                @RequestMapping("/page")
        public R page(@RequestParam Map<String, Object> params, ShangpinguanliEntity shangpinguanli, HttpServletRequest request) {
                            if (!request.getSession().getAttribute("role").toString().equals("管理员")) {
                    shangpinguanli.setUserid((Long) request.getSession().getAttribute("userId"));
                }
                                    EntityWrapper<ShangpinguanliEntity> ew = new EntityWrapper<ShangpinguanliEntity>();
            PageUtils page = shangpinguanliService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, shangpinguanli), params), params));
            request.setAttribute("data", page);
            return R.ok().put("data", page);
        }

        /**
         * 前端列表
         */
                @IgnoreAuth
                @RequestMapping("/list")
        public R list(@RequestParam Map<String, Object> params, ShangpinguanliEntity shangpinguanli, HttpServletRequest request) {

         

                            shangpinguanli.setSfsh("是");
                                    EntityWrapper<ShangpinguanliEntity> ew = new EntityWrapper<ShangpinguanliEntity>();
            PageUtils page = shangpinguanliService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, shangpinguanli), params), params));
            request.setAttribute("data", page);
            return R.ok().put("data", page);
        }

        /**
         * 列表
         */
                @RequestMapping("/lists")
        public R list( ShangpinguanliEntity shangpinguanli) {
            EntityWrapper<ShangpinguanliEntity> ew = new EntityWrapper<ShangpinguanliEntity>();
            ew.allEq(MPUtil.allEQMapPre( shangpinguanli, "shangpinguanli"));
            return R.ok().put("data", shangpinguanliService.selectListView(ew));
        }

        /**
         * 查询
         */
                @RequestMapping("/query")
        public R query(ShangpinguanliEntity shangpinguanli) {
            EntityWrapper< ShangpinguanliEntity> ew = new EntityWrapper< ShangpinguanliEntity>();
            ew.allEq(MPUtil.allEQMapPre( shangpinguanli, "Shangpinguanli"));
            ShangpinguanliView shangpinguanliView = shangpinguanliService.selectView(ew);
            return R.ok("商品管理").put("data", shangpinguanliView);
        }

        /**
             * 后端详情
             */
                @IgnoreAuth
                @RequestMapping("/info/{id}")
        public R info(@PathVariable("id") Long id) {
            ShangpinguanliEntity shangpinguanli = shangpinguanliService.selectById(id);
            return R.ok().put("data", shangpinguanli);
        }

        /**
            * 前端详情
            */
                @IgnoreAuth
                @RequestMapping("/detail/{id}")
        public R detail(@PathVariable("id") Long id) {
            ShangpinguanliEntity shangpinguanli = shangpinguanliService.selectById(id);
                        return R.ok().put("data", shangpinguanli);
        }

        /**
        * 后端保存
        */
                @RequestMapping("/save")
        public R save(@RequestBody ShangpinguanliEntity shangpinguanli, HttpServletRequest request) {


            shangpinguanli.setId(new Date().getTime() + new Double(Math.floor(Math.random() * 1000)).longValue());
            //ValidatorUtils.validateEntity(address);
                            shangpinguanli.setUserid((Long) request.getSession().getAttribute("userId"));
                            shangpinguanli.setSjid((Long) request.getSession().getAttribute("userId"));
                            shangpinguanliService.insert(shangpinguanli);
            return R.ok();
        }

        /**
            * 前端保存
            */
                @RequestMapping("/add")
        public R add(@RequestBody ShangpinguanliEntity shangpinguanli, HttpServletRequest request) {

            shangpinguanli.setUserid((Long) request.getSession().getAttribute("userId"));
            shangpinguanli.setSjid((Long) request.getSession().getAttribute("userId"));

                        shangpinguanli.setId(new Date().getTime() + new Double(Math.floor(Math.random() * 1000)).longValue());
            //ValidatorUtils.validateEntity(yiyuanjianjie);

                shangpinguanliService.insert(shangpinguanli);
            return R.ok();
        }

        /**
          * 修改
          */
                @RequestMapping("/update")
        public R update(@RequestBody ShangpinguanliEntity shangpinguanli, HttpServletRequest request) {
            //ValidatorUtils.validateEntity(zaixianbaoming);
                shangpinguanliService.updateById(shangpinguanli);//全部更新
            return R.ok();
        }

        /**
         * 删除
         */
                @RequestMapping("/delete")
        public R delete(@RequestBody Long[] ids) {
            shangpinguanliService.deleteBatchIds(Arrays.asList(ids));
            return R.ok();
        }
    

            /**
         * 推荐
         * @param userId
         * @param num
         * @return
         */
        @PostMapping("/recommendList")
        public R recommendList(@RequestParam("userId") Long userId, @RequestParam("num") Integer num) {
            Map<String, Double> map = shangpinguanliService.recommend(userId, num);
            List<Map.Entry<String, Double>> entryList = new ArrayList<>(map.entrySet());
            // 使用Collections.sort()对List进行排序
            Collections.sort(entryList, (entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));
            // 提取排序后的键
            List<Long> sortedKeys = new ArrayList<>();
            for (Map.Entry<String, Double> entry : entryList) {
                if(entry.getValue() > 0) {
                    sortedKeys.add(Long.valueOf(entry.getKey()));
                }
            }
            if(sortedKeys.size() > 0) {
                return R.ok().put("data", shangpinguanliService.selectBatchIds(sortedKeys));
            }
            return R.ok().put("data", null);
        }
    
    





    
}
