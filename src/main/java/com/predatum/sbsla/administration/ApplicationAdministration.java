package com.predatum.sbsla.administration;

import com.predatum.sbsla.entity.Application;
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
public class ApplicationAdministration extends AdministrationConfiguration<Application>
{

    @Override
    public EntityMetadataConfigurationUnit configuration(EntityMetadataConfigurationUnitBuilder configurationBuilder)
    {
        return configurationBuilder
                .nameField("name")
                .pluralName("Applications")
                .singularName("Application")
                .build();
    }

    @Override
    public FieldSetConfigurationUnit formView(final PersistentFieldSetConfigurationUnitBuilder fragmentBuilder)
    {
        return fragmentBuilder
                .field("name").caption("Name")
                .field("description").caption("Description")
                .build();
    }

    @Override
    public ScreenContextConfigurationUnit screenContext(ScreenContextConfigurationUnitBuilder screenContextBuilder)
    {
        return screenContextBuilder
                .screenName("Application Administration").build();
    }

    @Override
    public FieldSetConfigurationUnit listView(final FieldSetConfigurationUnitBuilder fragmentBuilder)
    {
        return fragmentBuilder
                .field("name").caption("Name")
                .field("description").caption("Description")
                .build();
    }

    @Override
    public FieldSetConfigurationUnit showView(final FieldSetConfigurationUnitBuilder fragmentBuilder)
    {
        return fragmentBuilder
                .field("name").caption("Name")
                .field("description").caption("Description")
                .build();
    }
}
