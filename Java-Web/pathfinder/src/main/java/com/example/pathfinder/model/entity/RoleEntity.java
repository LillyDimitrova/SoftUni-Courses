package com.example.pathfinder.model.entity;

import com.example.pathfinder.model.enums.UserRoleEnum;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class RoleEntity extends BaseEntity{

    @Enumerated(EnumType.STRING)
    private UserRoleEnum role;

    public RoleEntity() {
    }

    public UserRoleEnum getRole() {
        return role;
    }

    public RoleEntity setRole(UserRoleEnum role) {
        this.role = role;
        return this;
    }
}
