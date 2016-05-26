package com.predatum.sbsla.administration;

import com.predatum.sbsla.entity.Feature;
import org.lightadmin.api.config.AdministrationConfiguration;
import org.lightadmin.api.config.builder.EntityMetadataConfigurationUnitBuilder;
import org.lightadmin.api.config.builder.FieldSetConfigurationUnitBuilder;
import org.lightadmin.api.config.builder.FiltersConfigurationUnitBuilder;
import org.lightadmin.api.config.builder.PersistentFieldSetConfigurationUnitBuilder;
import org.lightadmin.api.config.builder.ScreenContextConfigurationUnitBuilder;
import org.lightadmin.api.config.unit.EntityMetadataConfigurationUnit;
import org.lightadmin.api.config.unit.FieldSetConfigurationUnit;
import org.lightadmin.api.config.unit.FiltersConfigurationUnit;
import org.lightadmin.api.config.unit.ScreenContextConfigurationUnit;

/**
 *
 * @author Marcos Peña
 */
public class FeatureAdministration extends AdministrationConfiguration<Feature>
{

    @Override
    public EntityMetadataConfigurationUnit configuration(EntityMetadataConfigurationUnitBuilder configurationBuilder)
    {
        return configurationBuilder
                .nameField("name")
                .pluralName("Features")
                .singularName("Feature")
                .build();
    }

    @Override
    public FieldSetConfigurationUnit formView(final PersistentFieldSetConfigurationUnitBuilder fragmentBuilder)
    {
        return fragmentBuilder
                .field("name").caption("Name")
                .field("releaseVersion").caption("Version")
                .field("description").caption("Description")
                .field("enabled").caption("Enabled")
                .field("application").caption("Application")
                .build();
    }

    @Override
    public ScreenContextConfigurationUnit screenContext(ScreenContextConfigurationUnitBuilder screenContextBuilder)
    {
        return screenContextBuilder
                .screenName("Features Administration").build();
    }

    @Override
    public FieldSetConfigurationUnit listView(final FieldSetConfigurationUnitBuilder fragmentBuilder)
    {
        return fragmentBuilder
                .field("name").caption("Name")
                .field("releaseVersion").caption("Version")
                .field("description").caption("Description")
                .field("enabled").caption("Enabled")
                .build();
    }

    @Override
    public FieldSetConfigurationUnit showView(final FieldSetConfigurationUnitBuilder fragmentBuilder)
    {
        return fragmentBuilder
                .field("name").caption("Name")
                .field("releaseVersion").caption("Version")
                .field("description").caption("Description")
                .field("application").caption("Application")
                .field("enabled").caption("Enabled")
                .build();
    }

    @Override
    public FiltersConfigurationUnit filters(final FiltersConfigurationUnitBuilder filterBuilder)
    {
        return filterBuilder
                .filter("Name", "name")
                .filter("Application", "application")
                .build();
    }
}
