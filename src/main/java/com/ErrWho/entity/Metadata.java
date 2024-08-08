package com.ErrWho.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
public abstract class Metadata {

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "modified_by")
    private String modifiedBy;

    @Column(name = "creation_time")
    private LocalDateTime creationTime;

    @Column(name = "modification_time")
    private LocalDateTime modificationTime;

    @PreUpdate
    public void update() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        modifiedBy = user.getFullName();
        modificationTime = LocalDateTime.now();
    }

    @PrePersist
    public void persist() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        createdBy = modifiedBy = user.getFullName();
        creationTime = modificationTime = LocalDateTime.now();
    }
}
