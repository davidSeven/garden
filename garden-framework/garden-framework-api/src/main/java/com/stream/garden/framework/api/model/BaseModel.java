package com.stream.garden.framework.api.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 基础父类
 *
 * @param <ID>
 * @author garden
 */
public class BaseModel<ID> extends OrderBy implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private ID id;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建人ID
     */
    private String createdBy;

    /**
     * 创建时间
     */
    private Timestamp creationDate;

    /**
     * 修改人ID
     */
    private String updatedBy;

    /**
     * 修改时间
     */
    private Timestamp updationDate;

    /**
     * 是否禁用
     */
    private Integer enabledFlag;

    /**
     * 日志ID
     */
    private String traceId;

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Timestamp getUpdationDate() {
        return updationDate;
    }

    public void setUpdationDate(Timestamp updationDate) {
        this.updationDate = updationDate;
    }

    public Integer getEnabledFlag() {
        return enabledFlag;
    }

    public void setEnabledFlag(Integer enabledFlag) {
        this.enabledFlag = enabledFlag;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
