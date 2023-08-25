package com.github.wnameless.spring.boot.up.core.event;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.ApplicationListener;
import org.springframework.context.PayloadApplicationEvent;
import org.springframework.stereotype.Component;

@Component
public class TestPayloadSpringEventListener
    implements ApplicationListener<PayloadApplicationEvent<String>> {

  public final List<String> messages = new ArrayList<>();

  @Override
  public void onApplicationEvent(PayloadApplicationEvent<String> event) {
    messages.add(event.getPayload());
  }

}
