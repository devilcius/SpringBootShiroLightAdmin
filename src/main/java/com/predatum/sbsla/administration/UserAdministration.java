package com.predatum.sbsla.administration;

import com.predatum.sbsla.entity.User;
import com.predatum.sbsla.listener.UserRepositoryEventListener;
import org.lightadmin.api.config.AdministrationConfiguration;
import org.lightadmin.api.config.builder.EntityMetadataConfigurationUnitBuilder;
import org.lightadmin.api.config.builder.FieldSetConfigurationUnitBuilder;
import org.lightadmin.api.config.builder.PersistentFieldSetConfigurationUnitBuilder;
import org.lightadmin.api.config.builder.ScreenContextConfigurationUnitBuilder;
import org.lightadmin.api.config.unit.EntityMetadataConfigurationUnit;
import org.lightadmin.api.config.unit.FieldSetConfigurationUnit;
import org.lightadmin.api.config.unit.ScreenContextConfigurationUnit;

/**
 *
 * @author Marcos Peña
 */
public class UserAdministration extends AdministrationConfiguration<User>
{

    @Override
    public EntityMetadataConfigurationUnit configuration(EntityMetadataConfigurationUnitBuilder configurationBuilder)
    {
        return configurationBuilder
                .nameField("name")
                .pluralName("Users")
                .singularName("User")
                .repositoryEventListener(UserRepositoryEventListener.class).build();
    }
    @Override
    public FieldSetConfigurationUnit formView(final PersistentFieldSetConfigurationUnitBuilder fragmentBuilder)
    {
        return fragmentBuilder
                .field("name").caption("Name")
                .field("username").caption("Username")
                .field("email").caption("Email")
                .field("password").caption("password")
                .field("description").caption("Description")
                .field("enabled").caption("Enabled")
                .field("applications").caption("Applications")
                .build();
    }

    @Override
    public ScreenContextConfigurationUnit screenContext(ScreenContextConfigurationUnitBuilder screenContextBuilder)
    {
        return screenContextBuilder
                .screenName("Users Administration").build();
    }

    @Override
    public FieldSetConfigurationUnit listView(final FieldSetConfigurationUnitBuilder fragmentBuilder)
    {
        return fragmentBuilder
                .field("name").caption("Name")
                .field("username").caption("Username")
                .field("email").caption("Email")
                .field("description").caption("Description")
                .build();
    }

    @Override
    public FieldSetConfigurationUnit showView(final FieldSetConfigurationUnitBuilder fragmentBuilder)
    {
        return fragmentBuilder
                .field("name").caption("Name")
                .field("username").caption("Username")
                .field("email").caption("Email")
                .field("description").caption("Description")
                .build();
    }
}
