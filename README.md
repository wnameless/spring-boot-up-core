[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.wnameless.spring/spring-boot-up-core/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.wnameless.spring/spring-boot-up-core)

spring-boot-up-core
=============
Core library for spring-boot-up.

## Purpose
Easily access to the Spring ApplicationContext within any application.
```java
SpringBootUp.getBean("MyBean");
```

# Maven Repo
```xml
<dependency>
	<groupId>com.github.wnameless.spring.boot.up</groupId>
	<artifactId>spring-boot-up-core</artifactId>
	<version>${newestVersion}</version>
	<!-- Newest version shows in the maven-central badge above -->
</dependency>
```
The version follows the pattern of {MajorVersion.MinorVersion.IncrementalVersion}.<br>
However, the MajorVersion is always matched the Spring Boot major version.<br>
Only MinorVersion and IncrementalVersion are used to represent the changes of this library.

# Quick Start
```java
@EnableSpringBootUp
@Configuration
public class MyConfiguration {}
```

# Feature List<a id='top'></a>
| Name | Description | Since |
| --- | --- | --- |
| [Bean](#3.0.0-5) | Shortcut for Bean access | v3.0.0 |
| [Message](#3.0.0-4) | Shortcut for Message access | v3.0.0 |
| [Event](#3.0.0-3) | Shortcut for Event publishing | v3.0.0 |
| [Enviroment](#3.0.0-2) | Shortcut for Enviroment | v3.0.0 |
| [ApplicationContext](#3.0.0-1) | Shortcut for ApplicationContext | v3.0.0 |

### [:top:](#top) Bean<a id='3.0.0-5'></a> - providing Optional returning methods to avoid exceptions
```java
// Find generic bean
Optional<GenericBean<String>> genericBean = SpringBootUp.findGenericBean(GenericBean.class, String.class);
// Find all generic beans
List<GenericBean<String>> allGenericBeans = SpringBootUp.findAllGenericBeans(GenericBean.class, String.class);
```

```java
TestBean bean;
// Equivalent to ApplicationContext#getBean(Class)
bean = SpringBootUp.getBean(TestBean.class);
// Equivalent to ApplicationContext#getBean(String)
bean = SpringBootUp.getBean("testBean");
// Returning an Optional instead of target bean or throwing an exception
Optional<TestBean> beanOpt;
beanOpt = SpringBootUp.findBean(TestBean.class);
beanOpt = SpringBootUp.findBean("testBean");
```

```java
TestArgBean argBean;
// Equivalent to ApplicationContext#getBean(Class, Object...)
argBean = SpringBootUp.getBean(TestBean.class, "arg");
// Equivalent to ApplicationContext#getBean(String, Object...)
argBean = SpringBootUp.getBean("testBean", "arg");
// Returning an Optional instead of target bean or throwing an exception
Optional<TestArgBean> argBeanOpt;
argBeanOpt = SpringBootUp.findBean(TestBean.class, "arg");
argBeanOpt = SpringBootUp.findBean("testBean", "arg");
```

```java
GenericBean<?> beanWithRequiredType;
// Equivalent to ApplicationContext#getBean(Class, Class<?>...)
beanWithRequiredType = SpringBootUp.getBean(GenericBean.class, String.class);
// Returning an Optional instead of target bean or throwing an exception
Optional<GenericBean<?>> beanWithRequiredType;
beanWithRequiredTypeOpt = SpringBootUp.findBean(GenericBean.class, String.class);
```

```java
Map<String, GenericBean<?>> beansOfType;
// Equivalent to ApplicationContext#getBeansOfType(Class)
beansOfType = SpringBootUp.getBeansOfType(GenericBean.class)
```

```java
Map<String, Object> beansWithAnnotation;
// Equivalent to ApplicationContext#getBeansWithAnnotation(Class)
beansWithAnnotation = SpringBootUp.getBeansWithAnnotation(TestAnnotation.class);
```

```java
// Check bean existence
// Equivalent to ApplicationContext#containsBean(String)
SpringBootUp.containsBean("testBean");
SpringBootUp.containsBean(TestBean.class);
```

### [:top:](#top) Message<a id='3.0.0-4'></a> - removing Locale arguments by using LocaleContextHolder.getLocale()
```java
String msg;
Optional<String> msgOpt;
// Get message, may throw Exception
msg = SpringBootUp.getMessage("test.spring.boot.up.message");
// Find message Optional, no Exception
msgOpt = SpringBootUp.findMessage("test.spring.boot.up.message");

// Get message with args, may throw Exception
msg = SpringBootUp.getMessage("test.spring.boot.up.message", "test");
// Find message Optional with args, no Exception
msgOpt = SpringBootUp.findMessage("test.spring.boot.up.message", "test");

// Get message with args and default, no Exception
msg = SpringBootUp.getMessage("test.spring.boot.up.message", new Object[] {"test"}, ""));

// Get message with MessageSourceResolvable, may throw Exception
MessageSourceResolvable resolvable = new DefaultMessageSourceResolvable("test.spring.boot.up.message");
msg = SpringBootUp.getMessage(resolvable);
// Find message with MessageSourceResolvable, no Exception
msgOpt = SpringBootUp.findMessage(resolvable);
```

### [:top:](#top) Event<a id='3.0.0-3'></a>
```java
// TestApplicationEvent extends ApplicationEvent
SpringBootUp.publishEvent(new TestApplicationEvent("testEvent"));
SpringBootUp.publishEvent("testEvent");
```

### [:top:](#top) Enviroment<a id='3.0.0-2'></a>
```java
Enviroment env = SpringBootUp.enviroment();
```

### [:top:](#top) ApplicationContext<a id='3.0.0-1'></a>
```java
ApplicationContext appCtx = SpringBootUp.appicationContext();
```

## MISC
| Note| Since |
| --- | --- |
| Java 17 required. | v3.0.0 |
| Spring Boot 3.0.0+ required. | v3.0.0 |
