package com.thon.entity.audit;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.collect.Lists;
import com.thon.commons.persistence.IdEntity;
import com.thon.entity.system.User;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

/**
 * Created by somedargon on 4/9/2017.
 */
@Entity
@Table(name = "user_flow_info")
@DynamicInsert
@DynamicUpdate
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class FlowInfo extends IdEntity {
    private String x1;
    private String x2;
    private Date insertTime;
    private TaskInstance instance;
    private User dealPeople;
    private Integer taskName;
    @NotFound(action= NotFoundAction.IGNORE)
    @ManyToOne(fetch = FetchType.LAZY,optional = true)
    @JoinColumn(name="processId")
    @JsonIgnore
    public TaskInstance getInstance() {
        return instance;
    }

    public void setInstance(TaskInstance instance) {
        this.instance = instance;
    }

    private String deadline;
    private User nextActor;
    private String proType;
    public String getProType() {
        return proType;
    }

    public void setProType(String proType) {
        this.proType = proType;
    }



    @NotFound(action= NotFoundAction.IGNORE)
    @ManyToOne(fetch = FetchType.LAZY,optional = true)
    @JoinColumn(name="dealPeople")
    public User getDealPeople() {
        return dealPeople;
    }

    public void setDealPeople(User dealPeople) {
        this.dealPeople = dealPeople;
    }
    @NotFound(action= NotFoundAction.IGNORE)
    @ManyToOne(fetch = FetchType.LAZY,optional = true)
    @JoinColumn(name="nextActor")
    public User getNextActor() {
        return nextActor;
    }

    public void setNextActor(User nextActor) {
        this.nextActor = nextActor;
    }

    private int type;

    public String getX1() {
        return x1;
    }

    public void setX1(String x1) {
        this.x1 = x1;
    }

    public String getX2() {
        return x2;
    }

    public void setX2(String x2) {
        this.x2 = x2;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Integer getTaskName() {
        return taskName;
    }

    public void setTaskName(Integer taskName) {
        this.taskName = taskName;
    }
}
