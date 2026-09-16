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
 * 商品分类
 * 数据库通用操作实体类（普通增删改查）
 */
@TableName("shangpinfenlei")
public class ShangpinfenleiEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


    public ShangpinfenleiEntity() {

    }

    public ShangpinfenleiEntity(T t) {
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

 /** 分类名称 */
    private String fenleimingcheng;

 /** 分类简介 */
    private String fenleijianjie;

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
    public void setFenleimingcheng(String fenleimingcheng)
    {
        this.fenleimingcheng = fenleimingcheng;
    }
    public String getFenleimingcheng()
    {
        return fenleimingcheng;
    }
    public void setFenleijianjie(String fenleijianjie)
    {
        this.fenleijianjie = fenleijianjie;
    }
    public String getFenleijianjie()
    {
        return fenleijianjie;
    }

}
