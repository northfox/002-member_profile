package com.github.northfox.web.mempro.config;

import java.util.Set;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

@Profile("container")
public class MemproInitializer implements WebApplicationInitializer {

  private static final Logger logger =
      LoggerFactory.getLogger(MemproInitializer.class);

  @Override
  public void onStartup(ServletContext servletContext) {
    String scanedPackage = "com.github.northfox.web.mempro";
    String encording = "UTF-8";
    logger.info("Do onStartup and scan to the package[{}].", scanedPackage);
    logger.info("Do onStartup and encording[{}].", encording);

    // Create the 'root' Spring application context
    AnnotationConfigWebApplicationContext rootContext =
        new AnnotationConfigWebApplicationContext();
    rootContext.register(ApplicationConfig.class);

    // Manage the life-cycle of the root application context
    servletContext.addListener(new ContextLoaderListener(rootContext));

    // Create the dispatcher servlet's SpringMVC application context
    AnnotationConfigWebApplicationContext mvcContext =
        new AnnotationConfigWebApplicationContext();
    mvcContext.register(WebMvcConfig.class);

    // mvcContext.setParent(rootContext);
    mvcContext.scan(scanedPackage);

    // Register encoding filter
    final FilterRegistration characterEncodingFilter =
        servletContext.addFilter("CharacterEncodingFilter",
            CharacterEncodingFilter.class);
    characterEncodingFilter.setInitParameter("encoding", encording);
    characterEncodingFilter.setInitParameter("forceEncoding", "true");
    characterEncodingFilter.addMappingForUrlPatterns(null, false, "/*");

    // Register and map the dispatcher servlet
    ServletRegistration.Dynamic dispatcherServlet = servletContext.addServlet(
        "dispatcherServlet", new DispatcherServlet(mvcContext));
    dispatcherServlet.setLoadOnStartup(1);
    Set<String> mappingConflicts = dispatcherServlet.addMapping("/");

    dispatcherServlet.setInitParameter("spring.profiles.active", "container");

    // Check the servlet mappings
    if (!mappingConflicts.isEmpty()) {
      for (String s : mappingConflicts) {
        logger.error("[ERROR] Mapping conflict: {}", s);
      }
      throw new IllegalStateException(
          "'webservice' cannot be mapped to '/'");
    }
  }
}