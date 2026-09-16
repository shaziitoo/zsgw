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
 * 商品管理
 * 数据库通用操作实体类（普通增删改查）
 */
@TableName("shangpinguanli")
public class ShangpinguanliEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


    public ShangpinguanliEntity() {

    }

    public ShangpinguanliEntity(T t) {
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

 /** 名称 */
    private String mingcheng;

 /** 图片 */
    private String tupian;

 /** 分类名称 */
    private String fenleimingcheng;

 /** 介绍 */
    private String jieshao;

 /** 详情 */
    private String xiangqing;

 /** 店铺名称 */
    private String dianpumingcheng;

 /** 用户id */
    private Long userid;

 /** 价格 */
    private BigDecimal price;

 /** 单限 */
    private Long onelimittimes;

 /** 库存 */
    private Long alllimittimes;

 /** 是否审核 */
    private String sfsh;

 /** 审核回复 */
    private String shhf;

 /** 赞 */
    private Long thumbsupnum;

 /** 踩 */
    private Long crazilynum;

 /** 商家id */
    private Long sjid;

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
    public void setMingcheng(String mingcheng)
    {
        this.mingcheng = mingcheng;
    }
    public String getMingcheng()
    {
        return mingcheng;
    }
    public void setTupian(String tupian)
    {
        this.tupian = tupian;
    }
    public String getTupian()
    {
        return tupian;
    }
    public void setFenleimingcheng(String fenleimingcheng)
    {
        this.fenleimingcheng = fenleimingcheng;
    }
    public String getFenleimingcheng()
    {
        return fenleimingcheng;
    }
    public void setJieshao(String jieshao)
    {
        this.jieshao = jieshao;
    }
    public String getJieshao()
    {
        return jieshao;
    }
    public void setXiangqing(String xiangqing)
    {
        this.xiangqing = xiangqing;
    }
    public String getXiangqing()
    {
        return xiangqing;
    }
    public void setDianpumingcheng(String dianpumingcheng)
    {
        this.dianpumingcheng = dianpumingcheng;
    }
    public String getDianpumingcheng()
    {
        return dianpumingcheng;
    }
    public void setUserid(Long userid)
    {
        this.userid = userid;
    }
    public Long getUserid()
    {
        return userid;
    }
    public void setPrice(BigDecimal price)
    {
        this.price = price;
    }
    public BigDecimal getPrice()
    {
        return price;
    }
    public void setOnelimittimes(Long onelimittimes)
    {
        this.onelimittimes = onelimittimes;
    }
    public Long getOnelimittimes()
    {
        return onelimittimes;
    }
    public void setAlllimittimes(Long alllimittimes)
    {
        this.alllimittimes = alllimittimes;
    }
    public Long getAlllimittimes()
    {
        return alllimittimes;
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
    public void setThumbsupnum(Long thumbsupnum)
    {
        this.thumbsupnum = thumbsupnum;
    }
    public Long getThumbsupnum()
    {
        return thumbsupnum;
    }
    public void setCrazilynum(Long crazilynum)
    {
        this.crazilynum = crazilynum;
    }
    public Long getCrazilynum()
    {
        return crazilynum;
    }
    public void setSjid(Long sjid)
    {
        this.sjid = sjid;
    }
    public Long getSjid()
    {
        return sjid;
    }

}
