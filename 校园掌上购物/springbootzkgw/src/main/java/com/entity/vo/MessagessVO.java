package com.entity.vo;

import com.entity.MessagessEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import java.sql.Timestamp;
import org.springframework.format.annotation.DateTimeFormat;
import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;


/**
 * 在线留言
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
public class MessagessVO  implements Serializable {
    private static final long serialVersionUID = 1L;
                        /** 主键 */
                    private Long id;

                                /** 创建时间 */
                                                                                                private Timestamp addtime;

                                /** 留言人id */
                                                                                                private Long userid;

                                /** 用户名 */
                                                                                                private String username;

                                /** 留言内容 */
                                                                                                private String content;

                                /** 留言图片 */
                                                                                                private String cpicture;

                                /** 回复内容 */
                                                                                                private String reply;

                                /** 回复图片 */
                                                                                                private String rpicture;

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
                                                        public void setUserid(Long userid)
            {
                this.userid = userid;
            }

            public Long getUserid()
            {
                return userid;
            }
                                                        public void setUsername(String username)
            {
                this.username = username;
            }

            public String getUsername()
            {
                return username;
            }
                                                        public void setContent(String content)
            {
                this.content = content;
            }

            public String getContent()
            {
                return content;
            }
                                                        public void setCpicture(String cpicture)
            {
                this.cpicture = cpicture;
            }

            public String getCpicture()
            {
                return cpicture;
            }
                                                        public void setReply(String reply)
            {
                this.reply = reply;
            }

            public String getReply()
            {
                return reply;
            }
                                                        public void setRpicture(String rpicture)
            {
                this.rpicture = rpicture;
            }

            public String getRpicture()
            {
                return rpicture;
            }
            
    }
