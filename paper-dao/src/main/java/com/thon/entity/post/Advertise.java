package com.thon.entity.post;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.thon.commons.persistence.IdEntity;
import com.thon.commons.utils.ImageUtil;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.*;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by win on 2016/1/27.
 */
@Entity
@Table(name = "bi_advertise")
@DynamicInsert
@DynamicUpdate
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Advertise extends IdEntity {

    private String title;   //标题
    private Integer attachment;    //标题图片
    private Date createDate;    //创建时间
    private String description;    //描述
    private String pushUrl;     //链接
    private String type;   //类型 0轮播/1图片咨询/2侧边栏图片/3合作媒体/4合作学校/5专家咨询/6中间banner/7下方banner/8高考政策/9招办主任访谈/10高考新闻/11校园资讯/12专业咨询/13新闻资讯
    private Integer status;    //状态 0启用/1关闭
    private Integer recommend;     //是否推荐  0推荐/1不推荐
    private String author;
    private String source;



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getAttachment() {
        return attachment;
    }

    public void setAttachment(Integer attachment) {
        this.attachment = attachment;
    }

    @Transient
    public String getAttachmentUrl(){
        return ImageUtil.formatUrl(attachment);
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPushUrl() {
        return pushUrl;
    }

    public void setPushUrl(String pushUrl) {
        this.pushUrl = pushUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getRecommend() {
        return recommend;
    }

    public void setRecommend(Integer recommend) {
        this.recommend = recommend;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }



}



