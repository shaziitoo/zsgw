package com.entity.vo;

import com.entity.ShangpinfenleiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import java.sql.Timestamp;
import org.springframework.format.annotation.DateTimeFormat;
import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;


/**
 * 商品分类
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
public class ShangpinfenleiVO  implements Serializable {
    private static final long serialVersionUID = 1L;
                        /** 主键 */
                    private Long id;

                                /** 创建时间 */
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
