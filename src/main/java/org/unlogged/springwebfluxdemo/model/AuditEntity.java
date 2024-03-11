package org.unlogged.springwebfluxdemo.model;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

public class AuditEntity extends BaseEntity {

    @CreatedDate
    Date createdDate;

    @CreatedBy
    String createdBy;

    @LastModifiedDate
    Date lastModifiedDate;

    @LastModifiedBy
    String lastModifiedBy;

    public Date getCreatedDate() {
        return createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    @Override
    public String toString() {
        return "AuditEntity{" +
                "createdDate=" + createdDate +
                ", createdBy='" + createdBy + '\'' +
                ", lastModifiedDate=" + lastModifiedDate +
                ", lastModifiedBy='" + lastModifiedBy + '\'' +
                '}';
    }
}
