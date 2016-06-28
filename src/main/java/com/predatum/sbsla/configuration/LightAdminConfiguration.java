package com.predatum.sbsla.configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import org.apache.catalina.Container;
import org.apache.catalina.Context;
import org.apache.catalina.Wrapper;
import org.lightadmin.api.config.LightAdmin;
import org.lightadmin.core.config.LightAdminWebApplicationInitializer;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.boot.context.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LightAdminConfiguration
{
    /* Used for running in "embedded" mode */
    @Bean
    public ServletContextInitializer servletContextInitializer()
    {
        return new ServletContextInitializer()
        {
            @Override
            public void onStartup(ServletContext servletContext) throws ServletException
            {
                LightAdmin.configure(servletContext)
                        .basePackage("com.predatum.sbsla.administration")
                        .baseUrl("/ladmin")
                        .security(false)
                        .backToSiteUrl("/");

                new LightAdminWebApplicationInitializer().onStartup(servletContext);
            }
        };
    }

    /* https://github.com/spring-projects/spring-boot/issues/2825#issuecomment-93479758 */
    @Bean
    public EmbeddedServletContainerCustomizer servletContainerCustomizer()
    {
        return new EmbeddedServletContainerCustomizer()
        {

            @Override
            public void customize(ConfigurableEmbeddedServletContainer container)
            {
                if (container instanceof TomcatEmbeddedServletContainerFactory) {
                    customizeTomcat((TomcatEmbeddedServletContainerFactory) container);
                }
            }

            private void customizeTomcat(TomcatEmbeddedServletContainerFactory tomcatFactory)
            {
                tomcatFactory.addContextCustomizers(new TomcatContextCustomizer()
                {

                    @Override
                    public void customize(Context context)
                    {
                        Container jsp = context.findChild("jsp");
                        if (jsp instanceof Wrapper) {
                            ((Wrapper) jsp).addInitParameter("development", "false");
                        }

                    }

                });
            }

        };
    }
}
