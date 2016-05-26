package com.predatum.sbsla.entity;

import static com.google.common.collect.Sets.newLinkedHashSet;
import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import static javax.persistence.TemporalType.DATE;
import static javax.persistence.TemporalType.TIMESTAMP;

@Entity
@Table(name = "sbslauser")
public class User extends AbstractEntity implements java.io.Serializable
{

    @Column(length = 45)
    private String name;
    @Column(length = 45)
    private String username;
    @Column(length = 128)
    private String email;
    @Column(length = 256)
    private String password;
    @Column(length = 128)
    private String salt;
    @Column(length = 256)
    private String description;
    @Column
    private Boolean enabled;
    @Column
    @Temporal(DATE)
    private Date createdAt;
    @Column
    @Temporal(DATE)
    private Date updatedAt;
    @Column
    @Temporal(DATE)
    private Date disabledAt;
    @Column(length = 256)
    private String confirmationToken;
    @Column(columnDefinition = "DATETIME")
    @Temporal(TIMESTAMP)
    private Date passwordRequestedAt;
    @ManyToMany(targetEntity = Application.class, cascade = {CascadeType.ALL})
    @JoinTable(name = "userapplication",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "application_id", referencedColumnName = "id")
    )
    private Set<Application> applications = newLinkedHashSet();


    public User()
    {
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getUsername()
    {
        return this.username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getEmail()
    {
        return this.email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPassword()
    {
        return this.password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getSalt()
    {
        return this.salt;
    }

    public void setSalt(String salt)
    {
        this.salt = salt;
    }

    public String getDescription()
    {
        return this.description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Boolean getEnabled()
    {
        return this.enabled;
    }

    public void setEnabled(Boolean enabled)
    {
        this.enabled = enabled;
    }

    public Date getCreatedAt()
    {
        return this.createdAt;
    }

    public void setCreatedAt(Date createdAt)
    {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt()
    {
        return this.updatedAt;
    }

    public void setUpdatedAt(Date updatedAt)
    {
        this.updatedAt = updatedAt;
    }

    public Date getDisabledAt()
    {
        return this.disabledAt;
    }

    public void setDisabledAt(Date disabledAt)
    {
        this.disabledAt = disabledAt;
    }

    /**
     * @return the confirmationToken
     */
    public String getConfirmationToken()
    {
        return confirmationToken;
    }

    /**
     * @param confirmationToken the confirmationToken to set
     */
    public void setConfirmationToken(String confirmationToken)
    {
        this.confirmationToken = confirmationToken;
    }

    /**
     * @return the passwordRequestedAt
     */
    public Date getPasswordRequestedAt()
    {
        return passwordRequestedAt;
    }

    /**
     * @param passwordRequestedAt the passwordRequestedAt to set
     */
    public void setPasswordRequestedAt(Date passwordRequestedAt)
    {
        this.passwordRequestedAt = passwordRequestedAt;
    }

    public Set<Application> getApplications()
    {
        return this.applications;
    }

    public void setApplications(Set<Application> applications)
    {
        this.applications = applications;
    }
}
