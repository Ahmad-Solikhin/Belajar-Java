package com.gayuh.jpa.listener;

import com.gayuh.jpa.entity.UpdatedAtAware;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDateTime;

public class UpdatedAtListener {
    @PrePersist
    @PreUpdate
    public void setLastUpdatedAt(UpdatedAtAware data){
        data.setUpdatedAt(LocalDateTime.now());
    }
}
