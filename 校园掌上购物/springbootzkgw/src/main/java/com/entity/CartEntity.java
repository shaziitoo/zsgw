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
 * 购物车
 * 数据库通用操作实体类（普通增删改查）
 */
@TableName("cart")
public class CartEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


    public CartEntity() {

    }

    public CartEntity(T t) {
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

 /** 商品表名 */
    private String tablename;

 /** 用户id */
    private Long userid;

 /** 商品id */
    private Long goodid;

 /** 商品名称 */
    private String goodname;

 /** 图片 */
    private String picture;

 /** 购买数量 */
    private Long buynumber;

 /** 单价 */
    private BigDecimal price;

 /** 会员价 */
    private BigDecimal discountprice;

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
    public void setTablename(String tablename)
    {
        this.tablename = tablename;
    }
    public String getTablename()
    {
        return tablename;
    }
    public void setUserid(Long userid)
    {
        this.userid = userid;
    }
    public Long getUserid()
    {
        return userid;
    }
    public void setGoodid(Long goodid)
    {
        this.goodid = goodid;
    }
    public Long getGoodid()
    {
        return goodid;
    }
    public void setGoodname(String goodname)
    {
        this.goodname = goodname;
    }
    public String getGoodname()
    {
        return goodname;
    }
    public void setPicture(String picture)
    {
        this.picture = picture;
    }
    public String getPicture()
    {
        return picture;
    }
    public void setBuynumber(Long buynumber)
    {
        this.buynumber = buynumber;
    }
    public Long getBuynumber()
    {
        return buynumber;
    }
    public void setPrice(BigDecimal price)
    {
        this.price = price;
    }
    public BigDecimal getPrice()
    {
        return price;
    }
    public void setDiscountprice(BigDecimal discountprice)
    {
        this.discountprice = discountprice;
    }
    public BigDecimal getDiscountprice()
    {
        return discountprice;
    }

}
