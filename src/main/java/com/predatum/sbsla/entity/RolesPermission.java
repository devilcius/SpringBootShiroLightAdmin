package com.predatum.sbsla.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "rolespermission")
public class RolesPermission extends AbstractEntity implements java.io.Serializable
{

    @Column(length = 80)
    private String permission;
    @Column(length = 128)
    private String roleName;

    public RolesPermission()
    {
    }

    public String getPermission()
    {
        return this.permission;
    }

    public void setPermission(String permission)
    {
        this.permission = permission;
    }

    public String getRoleName()
    {
        return this.roleName;
    }

    public void setRoleName(String roleName)
    {
        this.roleName = roleName;
    }

}
