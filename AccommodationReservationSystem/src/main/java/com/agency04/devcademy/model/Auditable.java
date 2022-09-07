package com.agency04.devcademy.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Auditable<U> {
    @Column(updatable = false)
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    protected java.util.Date createdAt;
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    protected java.util.Date modifiedAt;


    @PrePersist
    public void prePresist() {
        if (this.createdAt == null) {
            createdAt = new Date();
        }
        if (this.modifiedAt == null) {
            modifiedAt = createdAt;
        }
    }

    @PreUpdate()
    public void preUpdate(){
        this.modifiedAt=new Date();
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
    }
}
