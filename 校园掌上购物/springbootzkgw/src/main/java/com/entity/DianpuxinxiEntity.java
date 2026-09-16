package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.sql.Timestamp;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;


/**
 * 店铺信息
 * 数据库通用操作实体类（普通增删改查）
 */
@TableName("dianpuxinxi")
public class DianpuxinxiEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


    public DianpuxinxiEntity() {

    }

    public DianpuxinxiEntity(T t) {
        try {
            BeanUtils.copyProperties(this, t);
        } catch (IllegalAccessException | InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
 /** 主键 */
    private Long id;

 /** 创建时间 */
@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
@DateTimeFormat
    private Timestamp addtime;

 /** 店铺名称 */
    private String dianpumingcheng;

 /** 图片 */
    private String tupian;

 /** 昵称 */
    private String nicheng;

 /** 商家电话 */
    private String shangjiadianhua;

 /** 店铺简介 */
    private String dianpujianjie;

 /** 店铺地址 */
    private String dianpudizhi;

 /** 用户id */
    private Long userid;

 /** lat */
    private BigDecimal lat;

 /** lnt */
    private BigDecimal lnt;

 /** 标记 */
    private String conent;

 /** 是否审核 */
    private String sfsh;

 /** 审核回复 */
    private String shhf;

    public void setId(Long id)
    {
        this.id = id;
    }
    public Long getId()
    {
        return id;
    }
    public void setAddtime(Timestamp addtime)
    {
        this.addtime = addtime;
    }
    public Timestamp getAddtime()
    {
        return addtime;
    }
    public void setDianpumingcheng(String dianpumingcheng)
    {
        this.dianpumingcheng = dianpumingcheng;
    }
    public String getDianpumingcheng()
    {
        return dianpumingcheng;
    }
    public void setTupian(String tupian)
    {
        this.tupian = tupian;
    }
    public String getTupian()
    {
        return tupian;
    }
    public void setNicheng(String nicheng)
    {
        this.nicheng = nicheng;
    }
    public String getNicheng()
    {
        return nicheng;
    }
    public void setShangjiadianhua(String shangjiadianhua)
    {
        this.shangjiadianhua = shangjiadianhua;
    }
    public String getShangjiadianhua()
    {
        return shangjiadianhua;
    }
    public void setDianpujianjie(String dianpujianjie)
    {
        this.dianpujianjie = dianpujianjie;
    }
    public String getDianpujianjie()
    {
        return dianpujianjie;
    }
    public void setDianpudizhi(String dianpudizhi)
    {
        this.dianpudizhi = dianpudizhi;
    }
    public String getDianpudizhi()
    {
        return dianpudizhi;
    }
    public void setUserid(Long userid)
    {
        this.userid = userid;
    }
    public Long getUserid()
    {
        return userid;
    }
    public void setLat(BigDecimal lat)
    {
        this.lat = lat;
    }
    public BigDecimal getLat()
    {
        return lat;
    }
    public void setLnt(BigDecimal lnt)
    {
        this.lnt = lnt;
    }
    public BigDecimal getLnt()
    {
        return lnt;
    }
    public void setConent(String conent)
    {
        this.conent = conent;
    }
    public String getConent()
    {
        return conent;
    }
    public void setSfsh(String sfsh)
    {
        this.sfsh = sfsh;
    }
    public String getSfsh()
    {
        return sfsh;
    }
    public void setShhf(String shhf)
    {
        this.shhf = shhf;
    }
    public String getShhf()
    {
        return shhf;
    }

}
