package com.github.wnameless.spring.boot.up;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 
 * {@link SpringBootUpApplicationContextProvider} is made for SpringBootUp library to store the
 * Spring {@link ApplicationContext} of current running application.
 * 
 * @author Wei-Ming Wu
 * 
 */
@Component(SpringBootUpApplicationContextProvider.BEAN_NAME)
public final class SpringBootUpApplicationContextProvider implements ApplicationContextAware {

  public static final String BEAN_NAME = "springBootUpApplicationContextProvider";

  private static final class AplicationContextHolder {

    private static final InnerContextResource RESOURCE = new InnerContextResource();

    private AplicationContextHolder() {}

  }

  private static final class InnerContextResource {

    private ApplicationContext context;

    private InnerContextResource() {}

    private void setContext(ApplicationContext context) {
      this.context = context;
    }

  }

  public static ApplicationContext getApplicationContext() {
    return AplicationContextHolder.RESOURCE.context;
  }

  @Override
  public void setApplicationContext(ApplicationContext ac) {
    AplicationContextHolder.RESOURCE.setContext(ac);
  }

}
