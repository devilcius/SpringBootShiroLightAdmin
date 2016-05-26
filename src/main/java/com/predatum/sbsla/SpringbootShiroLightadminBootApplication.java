package com.predatum.sbsla;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import org.lightadmin.api.config.LightAdmin;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

@SpringBootApplication
public class SpringbootShiroLightadminBootApplication extends SpringBootServletInitializer
{

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException
    {
        LightAdmin.configure(servletContext)
                .basePackage("com.predatum.sbsla.administration")
                .baseUrl("/ladmin")
                .security(false)
                .backToSiteUrl("/sbsla");
        super.onStartup(servletContext);
    }

    public static void main(String[] args) throws Exception
    {
        SpringApplication.run(SpringbootShiroLightadminBootApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application)
    {
        return application.sources(SpringbootShiroLightadminBootApplication.class);
    }
}
