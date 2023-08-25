package com.github.wnameless.spring.boot.up.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.github.wnameless.spring.boot.up.SpringBootUp;
import com.github.wnameless.spring.boot.up.SpringBootUpApplicationContextProvider;
import com.github.wnameless.spring.boot.up.core.bean.ArgBean;
import com.github.wnameless.spring.boot.up.core.bean.GenericBean;
import com.github.wnameless.spring.boot.up.core.bean.NoBean;
import com.github.wnameless.spring.boot.up.core.bean.TestAnnotation;
import com.github.wnameless.spring.boot.up.core.bean.TestBean;
import com.github.wnameless.spring.boot.up.core.event.TestApplicationEvent;
import com.github.wnameless.spring.boot.up.core.event.TestPayloadSpringEventListener;
import com.github.wnameless.spring.boot.up.core.event.TestSpringEventListener;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SpringBootUpTest {

  @Autowired
  ApplicationContext appCtx;

  @Test
  public void testApplicationContext() {
    assertSame(appCtx, SpringBootUpApplicationContextProvider.getApplicationContext());
    assertSame(appCtx, SpringBootUp.applicationContext());
  }

  @Test
  public void testEnvironment() {
    assertNotNull(SpringBootUp.environment());
    assertSame(appCtx.getEnvironment(), SpringBootUp.environment());
  }

  @Test
  public void testFindGenericBean() {
    assertTrue(SpringBootUp.findGenericBean(GenericBean.class, String.class).isPresent());
    assertTrue(SpringBootUp.findGenericBean(GenericBean.class, Integer.class).isPresent());
    assertNotSame(SpringBootUp.findGenericBean(GenericBean.class, String.class).get(),
        SpringBootUp.findGenericBean(GenericBean.class, Integer.class).get());
    assertFalse(SpringBootUp.findGenericBean(GenericBean.class, Double.class).isPresent());
  }

  @Test
  public void testFindAllGenericBeans() {
    assertEquals(1, SpringBootUp.findAllGenericBeans(GenericBean.class, String.class).size());
    assertEquals(1, SpringBootUp.findAllGenericBeans(GenericBean.class, Integer.class).size());
    assertEquals(0, SpringBootUp.findAllGenericBeans(GenericBean.class, Double.class).size());
  }

  @Test
  public void testGetBean() {
    assertSame(appCtx.getBean(TestBean.class), SpringBootUp.getBean(TestBean.class));
    assertSame(appCtx.getBean(TestBean.BEAN_NAME), SpringBootUp.getBean(TestBean.BEAN_NAME));
  }

  @Test
  public void testFindBean() {
    assertSame(appCtx.getBean(TestBean.class), SpringBootUp.findBean(TestBean.class).get());
    assertSame(appCtx.getBean(TestBean.BEAN_NAME), SpringBootUp.findBean(TestBean.BEAN_NAME).get());
    assertFalse(SpringBootUp.findBean(NoBean.class).isPresent());
    assertFalse(SpringBootUp.findBean(NoBean.BEAN_NAME).isPresent());
  }

  @Test
  public void testGetBeanWithArg() {
    assertNotNull(SpringBootUp.getBean(ArgBean.class, "arg"));
    assertNotNull(SpringBootUp.getBean(ArgBean.BEAN_NAME, "arg"));
    assertNotSame(appCtx.getBean(ArgBean.class, "arg"), SpringBootUp.getBean(ArgBean.class, "arg"));
    assertNotSame(appCtx.getBean(ArgBean.BEAN_NAME, "arg"),
        SpringBootUp.getBean(ArgBean.BEAN_NAME, "arg"));
  }

  @Test
  public void testFindBeanWithArg() {
    assertTrue(SpringBootUp.findBean(ArgBean.class, "arg").isPresent());
    assertTrue(SpringBootUp.findBean(ArgBean.BEAN_NAME, "arg").isPresent());
    assertNotSame(appCtx.getBean(ArgBean.class, "arg"),
        SpringBootUp.findBean(ArgBean.class, "arg").get());
    assertNotSame(appCtx.getBean(ArgBean.BEAN_NAME, "arg"),
        SpringBootUp.findBean(ArgBean.BEAN_NAME, "arg").get());
    assertFalse(SpringBootUp.findBean(NoBean.class, "arg").isPresent());
    assertFalse(SpringBootUp.findBean(NoBean.BEAN_NAME, "arg").isPresent());
  }

  @Test
  public void testGetBeanWithRequiredType() {
    assertSame(appCtx.getBean(TestBean.BEAN_NAME, TestBean.class),
        SpringBootUp.getBean(TestBean.BEAN_NAME, TestBean.class));
  }

  @Test
  public void testFindBeanWithRequiredType() {
    assertSame(appCtx.getBean(TestBean.BEAN_NAME, TestBean.class),
        SpringBootUp.findBean(TestBean.BEAN_NAME, TestBean.class).get());
    assertFalse(SpringBootUp.findBean(TestBean.BEAN_NAME, NoBean.class).isPresent());
  }

  @Test
  public void testContainsBean() {
    assertTrue(SpringBootUp.containsBean(TestBean.BEAN_NAME));
    assertFalse(SpringBootUp.containsBean(NoBean.BEAN_NAME));
    assertTrue(SpringBootUp.containsBean(TestBean.class));
    assertFalse(SpringBootUp.containsBean(NoBean.class));
  }

  @Test
  public void testGetBeansOfType() {
    assertEquals(2, SpringBootUp.getBeansOfType(GenericBean.class).size());
    assertEquals(appCtx.getBeansOfType(GenericBean.class),
        SpringBootUp.getBeansOfType(GenericBean.class));
    assertEquals(appCtx.getBeansOfType(GenericBean.class, true, true),
        SpringBootUp.getBeansOfType(GenericBean.class, true, true));
  }

  @Test
  public void testGetBeansWithAnnotation() {
    assertSame(appCtx.getBeansWithAnnotation(TestAnnotation.class).values().iterator().next(),
        SpringBootUp.getBeansWithAnnotation(TestAnnotation.class).values().iterator().next());
    assertEquals(appCtx.getBeansWithAnnotation(TestAnnotation.class),
        SpringBootUp.getBeansWithAnnotation(TestAnnotation.class));
  }

  @Test
  public void testGetMessage() {
    assertEquals("TEST{0}", SpringBootUp.getMessage("test.spring.boot.up.message"));
    assertEquals("TESTtest", SpringBootUp.getMessage("test.spring.boot.up.message", "test"));
  }

  @Test
  public void testFindMessage() {
    assertEquals("TEST{0}", SpringBootUp.findMessage("test.spring.boot.up.message").get());
    assertEquals("TESTtest", SpringBootUp.findMessage("test.spring.boot.up.message", "test").get());
    assertFalse(SpringBootUp.findMessage("test.spring.boot.up.no_message").isPresent());
  }

  @Test
  public void testGetMessageWithDefault() {
    assertEquals("TEST{0}",
        SpringBootUp.getMessage("test.spring.boot.up.message", new Object[] {}, ""));
    assertEquals("TESTtest",
        SpringBootUp.getMessage("test.spring.boot.up.message", new Object[] {"test"}, ""));
    assertEquals("default",
        SpringBootUp.getMessage("test.spring.boot.up.no_message", new Object[] {}, "default"));
    assertEquals("default", SpringBootUp.getMessage("test.spring.boot.up.no_message",
        new Object[] {"test"}, "default"));
  }

  @Test
  public void testGetMessageWithMessageSourceResolvable() {
    var resolvable = new DefaultMessageSourceResolvable("test.spring.boot.up.message");
    assertEquals("TEST{0}", SpringBootUp.getMessage(resolvable));
    resolvable = new DefaultMessageSourceResolvable(new String[] {"test.spring.boot.up.message"},
        new Object[] {"test"});
    assertEquals("TESTtest", SpringBootUp.getMessage(resolvable));
  }

  @Test
  public void testFindMessageWithMessageSourceResolvable() {
    var resolvable = new DefaultMessageSourceResolvable("test.spring.boot.up.message");
    assertEquals("TEST{0}", SpringBootUp.findMessage(resolvable).get());
    resolvable = new DefaultMessageSourceResolvable(new String[] {"test.spring.boot.up.message"},
        new Object[] {"test"});
    assertEquals("TESTtest", SpringBootUp.findMessage(resolvable).get());
    resolvable =
        new DefaultMessageSourceResolvable(new String[] {"test.spring.boot.up.no_message"});
    assertFalse(SpringBootUp.findMessage(resolvable).isPresent());
  }

  @Test
  public void testPublishEvent() {
    SpringBootUp.publishEvent(new TestApplicationEvent("testEvent"));
    assertEquals(1, appCtx.getBean(TestSpringEventListener.class).messages.size());
    assertEquals("testEvent", appCtx.getBean(TestSpringEventListener.class).messages.get(0));
  }

  @Test
  public void testPublishEventWithPayload() {
    SpringBootUp.publishEvent("testEvent");
    assertEquals(1, appCtx.getBean(TestPayloadSpringEventListener.class).messages.size());
    assertEquals("testEvent", appCtx.getBean(TestPayloadSpringEventListener.class).messages.get(0));
  }

}
