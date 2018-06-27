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

@Entity
@Table(name = "user_task_instance")
@DynamicInsert
@DynamicUpdate
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskInstance extends IdEntity{
    private List<FlowInfo> flowInfoList = Lists.newArrayList();
    private User nextPeople;
    private User initiator;
    private Integer status;
    private Integer taskStatus;

    public Integer getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
    }

    @NotFound(action= NotFoundAction.IGNORE)
    @ManyToOne(fetch = FetchType.LAZY,optional = true)
    @JoinColumn(name="next_people")
    public User getNextPeople() {
        return nextPeople;
    }

    public void setNextPeople(User nextPeople) {
        this.nextPeople = nextPeople;
    }

    private Date insertTime;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE},fetch=FetchType.LAZY,mappedBy="instance")
    @Where(clause="del_flag='"+DEL_FLAG_NORMAL+"'")
    @NotFound(action = NotFoundAction.IGNORE)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    public List<FlowInfo> getFlowInfoList() {
        return flowInfoList;
    }

    public void setFlowInfoList(List<FlowInfo> flowInfoList) {
        this.flowInfoList = flowInfoList;
    }
    @NotFound(action= NotFoundAction.IGNORE)
    @ManyToOne(fetch = FetchType.LAZY,optional = true)
    @JoinColumn(name="initiator")
    public User getInitiator() {
        return initiator;
    }

    public void setInitiator(User initiator) {
        this.initiator = initiator;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
