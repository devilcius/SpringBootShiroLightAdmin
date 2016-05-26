package com.predatum.sbsla.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sbslafeature")
public class Feature extends AbstractEntity implements java.io.Serializable
{
    @ManyToOne
    private Application application;
    @Column(length = 80)
    private String name;
    @Column(length = 80)
    private String releaseVersion;
    @Column(length = 256)
    private String description;
    @Column
    private Boolean enabled;

    public Feature()
    {
    }

    public Feature(Application application)
    {
        this.application = application;
    }

    public Application getApplication()
    {
        return this.application;
    }

    public void setApplication(Application application)
    {
        this.application = application;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getReleaseVersion()
    {
        return this.releaseVersion;
    }

    public void setReleaseVersion(String releaseVersion)
    {
        this.releaseVersion = releaseVersion;
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

}
