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
import com.entity.ShangjiaEntity;
import com.entity.view.ShangjiaView;
import com.service.ShangjiaService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.CommonUtil;
/**
 * 商家Controller
 *后端接口
 */
@RestController
@RequestMapping("/shangjia")
public class ShangjiaController {

    @Autowired
    private ShangjiaService shangjiaService;



            @Autowired
        private TokenService tokenService;
    
    
            /**
         * 后端列表
         */
                @RequestMapping("/page")
        public R page(@RequestParam Map<String, Object> params, ShangjiaEntity shangjia, HttpServletRequest request) {
                                    EntityWrapper<ShangjiaEntity> ew = new EntityWrapper<ShangjiaEntity>();
            PageUtils page = shangjiaService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, shangjia), params), params));
            request.setAttribute("data", page);
            return R.ok().put("data", page);
        }

        /**
         * 前端列表
         */
                @RequestMapping("/list")
        public R list(@RequestParam Map<String, Object> params, ShangjiaEntity shangjia, HttpServletRequest request) {

         

                                    EntityWrapper<ShangjiaEntity> ew = new EntityWrapper<ShangjiaEntity>();
            PageUtils page = shangjiaService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, shangjia), params), params));
            request.setAttribute("data", page);
            return R.ok().put("data", page);
        }

        /**
         * 列表
         */
                @RequestMapping("/lists")
        public R list( ShangjiaEntity shangjia) {
            EntityWrapper<ShangjiaEntity> ew = new EntityWrapper<ShangjiaEntity>();
            ew.allEq(MPUtil.allEQMapPre( shangjia, "shangjia"));
            return R.ok().put("data", shangjiaService.selectListView(ew));
        }

        /**
         * 查询
         */
                @RequestMapping("/query")
        public R query(ShangjiaEntity shangjia) {
            EntityWrapper< ShangjiaEntity> ew = new EntityWrapper< ShangjiaEntity>();
            ew.allEq(MPUtil.allEQMapPre( shangjia, "Shangjia"));
            ShangjiaView shangjiaView = shangjiaService.selectView(ew);
            return R.ok("商家").put("data", shangjiaView);
        }

        /**
             * 后端详情
             */
                @RequestMapping("/info/{id}")
        public R info(@PathVariable("id") Long id) {
            ShangjiaEntity shangjia = shangjiaService.selectById(id);
            return R.ok().put("data", shangjia);
        }

        /**
            * 前端详情
            */
                @RequestMapping("/detail/{id}")
        public R detail(@PathVariable("id") Long id) {
            ShangjiaEntity shangjia = shangjiaService.selectById(id);
                        return R.ok().put("data", shangjia);
        }

        /**
        * 后端保存
        */
                @RequestMapping("/save")
        public R save(@RequestBody ShangjiaEntity shangjia, HttpServletRequest request) {
            
        
            shangjia.setId(new Date().getTime() + new Double(Math.floor(Math.random() * 1000)).longValue());
            //ValidatorUtils.validateEntity(address);
                            shangjiaService.insert(shangjia);
            return R.ok();
        }

        /**
            * 前端保存
            */
                @RequestMapping("/add")
        public R add(@RequestBody ShangjiaEntity shangjia, HttpServletRequest request) {
                                shangjia.setId(new Date().getTime() + new Double(Math.floor(Math.random() * 1000)).longValue());
            //ValidatorUtils.validateEntity(yiyuanjianjie);

                shangjiaService.insert(shangjia);
            return R.ok();
        }

        /**
          * 修改
          */
                @RequestMapping("/update")
        public R update(@RequestBody ShangjiaEntity shangjia, HttpServletRequest request) {
            //ValidatorUtils.validateEntity(zaixianbaoming);
                shangjiaService.updateById(shangjia);//全部更新
            return R.ok();
        }

        /**
         * 删除
         */
                @RequestMapping("/delete")
        public R delete(@RequestBody Long[] ids) {
            shangjiaService.deleteBatchIds(Arrays.asList(ids));
            return R.ok();
        }
    

    
    





            /**
         * 注册
         */
    @IgnoreAuth
    @RequestMapping("/register")
    public R register(@RequestBody ShangjiaEntity shangjia) {
            ShangjiaEntity user = shangjiaService.selectOne(new EntityWrapper<ShangjiaEntity>().eq("zhanghao", shangjia.getZhanghao()))
        ;
        if (user != null) {
            return R.error("注册用户已存在");
        }

  Long uId = new Date().getTime();
            shangjia.setId(uId);
                shangjiaService.insert(shangjia);
   
          
            return R.ok();
        }

        /**
             * 登录
             */
        @IgnoreAuth
        @RequestMapping(value = "/login")
        public R login(@RequestBody UserEntity userEntity , String captcha, HttpServletRequest request) {
                ShangjiaEntity user = shangjiaService.selectOne(new EntityWrapper<ShangjiaEntity>().eq("zhanghao",userEntity.getUsername() ));
            if (user == null || !user.getMima().equals(userEntity.getPassword())) {
                return R.error("账号或密码不正确");
            }
            String token = tokenService.generateToken(user.getId(), userEntity.getUsername(), "shangjia", "商家");
            return R.ok().put("token", token);
        }

        /**
	 * 退出
	 */
        @RequestMapping("/logout")
        public R logout(HttpServletRequest request) {
            request.getSession().invalidate();
            return R.ok("退出成功");
        }

        /**
     * 获取用户的session用户信息
     */
        @RequestMapping("/session")
        public R getCurrUser(HttpServletRequest request) {
            Long id = (Long) request.getSession().getAttribute("userId");
            ShangjiaEntity user = shangjiaService.selectById(id);
            JSONObject json = (JSONObject)JSON.toJSON(user);
            json.put("role", "shangjia");
            return R.ok().put("data", json);
        }

        /**
     * 密码重置
     */
        @IgnoreAuth
        @RequestMapping(value = "/resetPass")
        public R resetPass(String username, HttpServletRequest request) {
                ShangjiaEntity user = shangjiaService.selectOne(new EntityWrapper<ShangjiaEntity>().eq("zhanghao", username));
            if (user == null) {
                return R.error("账号不存在");
            }
            user.setMima("123456");
                shangjiaService.updateById(user);
            return R.ok("密码已重置为：123456");
        }
    
}
