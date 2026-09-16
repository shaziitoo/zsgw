package com.entity.vo;

import com.entity.CartEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import java.sql.Timestamp;
import org.springframework.format.annotation.DateTimeFormat;
import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;


/**
 * 购物车
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
public class CartVO  implements Serializable {
    private static final long serialVersionUID = 1L;
                        /** 主键 */
                    private Long id;

                                /** 创建时间 */
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
