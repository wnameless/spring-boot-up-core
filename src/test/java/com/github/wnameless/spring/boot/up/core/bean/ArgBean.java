package com.github.wnameless.spring.boot.up.core.bean;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Component
public class ArgBean {

  public static final String BEAN_NAME = "argBean";

  public ArgBean(String arg) {}

}
