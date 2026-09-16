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
import com.entity.DianpuxinxiEntity;
import com.entity.view.DianpuxinxiView;
import com.service.DianpuxinxiService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.CommonUtil;
/**
 * 店铺信息Controller
 *后端接口
 */
@RestController
@RequestMapping("/dianpuxinxi")
public class DianpuxinxiController {

    @Autowired
    private DianpuxinxiService dianpuxinxiService;



    
    
            /**
         * 后端列表
         */
                @RequestMapping("/page")
        public R page(@RequestParam Map<String, Object> params, DianpuxinxiEntity dianpuxinxi, HttpServletRequest request) {
                            if (!request.getSession().getAttribute("role").toString().equals("管理员")) {
                    dianpuxinxi.setUserid((Long) request.getSession().getAttribute("userId"));
                }
                                    EntityWrapper<DianpuxinxiEntity> ew = new EntityWrapper<DianpuxinxiEntity>();
            PageUtils page = dianpuxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, dianpuxinxi), params), params));
            request.setAttribute("data", page);
            return R.ok().put("data", page);
        }

        /**
         * 前端列表
         */
                @RequestMapping("/list")
        public R list(@RequestParam Map<String, Object> params, DianpuxinxiEntity dianpuxinxi, HttpServletRequest request) {

         

                            dianpuxinxi.setSfsh("是");
                                    EntityWrapper<DianpuxinxiEntity> ew = new EntityWrapper<DianpuxinxiEntity>();
            PageUtils page = dianpuxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, dianpuxinxi), params), params));
            request.setAttribute("data", page);
            return R.ok().put("data", page);
        }

        /**
         * 列表
         */
                @RequestMapping("/lists")
        public R list( DianpuxinxiEntity dianpuxinxi) {
            EntityWrapper<DianpuxinxiEntity> ew = new EntityWrapper<DianpuxinxiEntity>();
            ew.allEq(MPUtil.allEQMapPre( dianpuxinxi, "dianpuxinxi"));
            return R.ok().put("data", dianpuxinxiService.selectListView(ew));
        }

        /**
         * 查询
         */
                @RequestMapping("/query")
        public R query(DianpuxinxiEntity dianpuxinxi) {
            EntityWrapper< DianpuxinxiEntity> ew = new EntityWrapper< DianpuxinxiEntity>();
            ew.allEq(MPUtil.allEQMapPre( dianpuxinxi, "Dianpuxinxi"));
            DianpuxinxiView dianpuxinxiView = dianpuxinxiService.selectView(ew);
            return R.ok("店铺信息").put("data", dianpuxinxiView);
        }

        /**
             * 后端详情
             */
                @RequestMapping("/info/{id}")
        public R info(@PathVariable("id") Long id) {
            DianpuxinxiEntity dianpuxinxi = dianpuxinxiService.selectById(id);
            return R.ok().put("data", dianpuxinxi);
        }

        /**
            * 前端详情
            */
                @RequestMapping("/detail/{id}")
        public R detail(@PathVariable("id") Long id) {
            DianpuxinxiEntity dianpuxinxi = dianpuxinxiService.selectById(id);
                        return R.ok().put("data", dianpuxinxi);
        }

        /**
        * 后端保存
        */
                @RequestMapping("/save")
        public R save(@RequestBody DianpuxinxiEntity dianpuxinxi, HttpServletRequest request) {
            
        
            dianpuxinxi.setId(new Date().getTime() + new Double(Math.floor(Math.random() * 1000)).longValue());
            //ValidatorUtils.validateEntity(address);
                            dianpuxinxi.setUserid((Long) request.getSession().getAttribute("userId"));
                            dianpuxinxiService.insert(dianpuxinxi);
            return R.ok();
        }

        /**
            * 前端保存
            */
                @RequestMapping("/add")
        public R add(@RequestBody DianpuxinxiEntity dianpuxinxi, HttpServletRequest request) {
                    
            dianpuxinxi.setUserid((Long) request.getSession().getAttribute("userId"));

                        dianpuxinxi.setId(new Date().getTime() + new Double(Math.floor(Math.random() * 1000)).longValue());
            //ValidatorUtils.validateEntity(yiyuanjianjie);

                dianpuxinxiService.insert(dianpuxinxi);
            return R.ok();
        }

        /**
          * 修改
          */
                @RequestMapping("/update")
        public R update(@RequestBody DianpuxinxiEntity dianpuxinxi, HttpServletRequest request) {
            //ValidatorUtils.validateEntity(zaixianbaoming);
                dianpuxinxiService.updateById(dianpuxinxi);//全部更新
            return R.ok();
        }

        /**
         * 删除
         */
                @RequestMapping("/delete")
        public R delete(@RequestBody Long[] ids) {
            dianpuxinxiService.deleteBatchIds(Arrays.asList(ids));
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
            Map<String, Double> map = dianpuxinxiService.recommend(userId, num);
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
                return R.ok().put("data", dianpuxinxiService.selectBatchIds(sortedKeys));
            }
            return R.ok().put("data", null);
        }
    
    





    
}
