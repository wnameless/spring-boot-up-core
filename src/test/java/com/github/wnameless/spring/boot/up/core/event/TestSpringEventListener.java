package com.github.wnameless.spring.boot.up.core.event;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class TestSpringEventListener implements ApplicationListener<TestApplicationEvent> {

  public final List<String> messages = new ArrayList<>();

  @Override
  public void onApplicationEvent(TestApplicationEvent event) {
    messages.add(event.getSource().toString());
  }

}
