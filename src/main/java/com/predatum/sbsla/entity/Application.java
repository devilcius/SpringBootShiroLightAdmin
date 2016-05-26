package com.predatum.sbsla.entity;

import java.util.Set;
import static javax.persistence.CascadeType.ALL;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "sbslaapplication")
public class Application extends AbstractEntity implements java.io.Serializable
{

    @Column(length = 80)
    private String name;
    @Column(length = 256)
    private String description;
    @OneToMany(cascade = ALL, orphanRemoval = true)
    @JoinColumn(name = "application_id")
    private Set<Feature> features;
    @ManyToMany(mappedBy = "applications")
    private Set<User> users;


    public Application()
    {
    }

    public Application(String name, String description)
    {
        this.name = name;
        this.description = description;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return this.description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public void add(Feature feature)
    {
        this.features.add(feature);
    }

    public Set<Feature> getFeatures()
    {
        return this.features;
    }

    public void setFeatures(final Set<Feature> features)
    {
        this.features = features;
    }

    public Set<User> getUsers()
    {
        return this.users;
    }

    public void setUsers(Set<User> users)
    {
        this.users = users;
    }
}
