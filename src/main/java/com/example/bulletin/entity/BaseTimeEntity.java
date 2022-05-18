package com.example.bulletin.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import javax.persistence.MappedSuperclass;
import javax.persistence.EntityListeners;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseTimeEntity {

    @CreatedDate
    private LocalDateTime registerTime;

    @LastModifiedDate
    private LocalDateTime updateTime;
}
