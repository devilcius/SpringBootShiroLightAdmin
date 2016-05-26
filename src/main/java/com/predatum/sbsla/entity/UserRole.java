package com.predatum.sbsla.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "userrole")
public class UserRole extends AbstractEntity implements java.io.Serializable
{

    @Column(length = 45)
    private String roleName;
    @Column(length = 128)
    private String email;

    public UserRole()
    {
    }

    public String getRoleName()
    {
        return this.roleName;
    }

    public void setRoleName(String roleName)
    {
        this.roleName = roleName;
    }

    public String getEmail()
    {
        return this.email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

}
