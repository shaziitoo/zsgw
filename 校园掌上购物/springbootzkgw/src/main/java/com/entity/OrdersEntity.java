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
 * 订单
 * 数据库通用操作实体类（普通增删改查）
 */
@TableName("orders")
public class OrdersEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


    public OrdersEntity() {

    }

    public OrdersEntity(T t) {
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

 /** 订单编号 */
    private String orderid;

 /** 商品表名 */
    private String tablename;

 /** 用户id */
    private Long userid;

 /** 商品id */
    private Long goodid;

 /** 商品名称 */
    private String goodname;

 /** 商品图片 */
    private String picture;

 /** 购买数量 */
    private Long buynumber;

 /** 价格/积分 */
    private BigDecimal price;

 /** 折扣价格 */
    private BigDecimal discountprice;

 /** 总价格/总积分 */
    private BigDecimal total;

 /** 折扣总价格 */
    private BigDecimal discounttotal;

 /** 支付类型 */
    private Long type;

 /** 状态 */
    private String status;

 /** 地址 */
    private String address;

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
    public void setOrderid(String orderid)
    {
        this.orderid = orderid;
    }
    public String getOrderid()
    {
        return orderid;
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
    public void setTotal(BigDecimal total)
    {
        this.total = total;
    }
    public BigDecimal getTotal()
    {
        return total;
    }
    public void setDiscounttotal(BigDecimal discounttotal)
    {
        this.discounttotal = discounttotal;
    }
    public BigDecimal getDiscounttotal()
    {
        return discounttotal;
    }
    public void setType(Long type)
    {
        this.type = type;
    }
    public Long getType()
    {
        return type;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }
    public String getStatus()
    {
        return status;
    }
    public void setAddress(String address)
    {
        this.address = address;
    }
    public String getAddress()
    {
        return address;
    }

}
