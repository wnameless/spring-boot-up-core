[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.wnameless.spring.boot.up/spring-boot-up-core/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.wnameless.spring.boot.up/spring-boot-up-core)
[![codecov](https://codecov.io/gh/wnameless/spring-boot-up-core/branch/main/graph/badge.svg)](https://codecov.io/gh/wnameless/spring-boot-up-core)

spring-boot-up-core
=============
Core library for spring-boot-up.

## Purpose
Easily access to the Spring ApplicationContext within an application.
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
This lib uses Semantic Versioning: `{MAJOR.MINOR.PATCH}`.<br>
However, the MAJOR version is always matched the Spring Boot MAJOR version.

# Quick Start
```java
@EnableSpringBootUp
@Configuration
public class MyConfiguration {}
```

# Feature List<a id='top'></a>
| Name | Option | Description | Exception-Free | Since |
| --- | --- | --- | --- | --- |
| [Bean](#3.0.0-5) | | Provide methods that return Optional values to prevent exceptions | | v3.0.0 |
| | [findGenericBean](#3.0.0-5.1) | Find an Optional of target generic bean | :heavy_check_mark: | v3.0.0 |
| | [findAllGenericBeans](#3.0.0-5.2) | Find a List of target generic beans | :heavy_check_mark: | v3.0.0 |
| | [getBean(type)<br> getBean(beanName)](#3.0.0-5.3) | Shortcut: ApplicationContext#getBean | | v3.0.0 |
| | [findBean(type)<br> findBean(beanName)](#3.0.0-5.4) | Find an Optional of target bean | :heavy_check_mark: | v3.0.0 |
| | [getBean(type, arg...)](#3.0.0-5.5) | Shortcut: ApplicationContext#getBean | | v3.0.0 |
| | [findBean(type, arg...)](#3.0.0-5.6) | Find an Optional of target bean with args | :heavy_check_mark: | v3.0.0 |
| | [getBean(beanName, requiredType)](#3.0.0-5.7) | Shortcut: ApplicationContext#getBean | | v3.0.0 |
| | [findBean(beanName, requiredType)](#3.0.0-5.8) | Find an Optional of target bean with generics | :heavy_check_mark: | v3.0.0 |
| | [getBeansOfType](#3.0.0-5.9) | Shortcut: ApplicationContext#getBeansOfType | | v3.0.0 |
| | [getBeansWithAnnotation](#3.0.0-5.10) | Shortcut: ApplicationContext#getBeansWithAnnotation | | v3.0.0 |
| | [containsBean(type)<br> containsBean(beanName)](#3.0.0-5.11) | Check bean existence | :heavy_check_mark: | v3.0.0 |
| [Message](#3.0.0-4) | | Remove Locale argument by utilizing LocaleContextHolder#getLocale | | v3.0.0 |
| | [getMessage(code)](#3.0.0-4.1) | Shortcut: ApplicationContext#getMessage | | v3.0.0 |
| | [findMessage(code)](#3.0.0-4.2) | Find an Optional of a message | :heavy_check_mark: | v3.0.0 |
| | [getMessage(code, arg...)](#3.0.0-4.3) | Shortcut: ApplicationContext#getMessage | | v3.0.0 |
| | [findMessage(code, arg...)](#3.0.0-4.4) | Find an Optional of a message with args | :heavy_check_mark: | v3.0.0 |
| | [getMessage(code, args, default)](#3.0.0-4.5) | Shortcut: ApplicationContext#getMessage | :heavy_check_mark: | v3.0.0 |
| | [getMessage(MessageSourceResolvable)](#3.0.0-4.6) | Shortcut: ApplicationContext#getMessage | | v3.0.0 |
| | [findMessage(MessageSourceResolvable)](#3.0.0-4.7) | Find an Optional of a message with MessageSourceResolvable | :heavy_check_mark: | v3.0.0 |
| [Event](#3.0.0-3) | | Shortcut: ApplicationContext#publishEvent | - | v3.0.0 |
| [Enviroment](#3.0.0-2) | | Shortcut: Enviroment | - | v3.0.0 |
| [ApplicationContext](#3.0.0-1) | | Shortcut: ApplicationContext | - | v3.0.0 |

### [:top:](#top) Bean<a id='3.0.0-5'></a>
#### [:top:](#top) findGenericBean<a id='3.0.0-5.1'></a>
```java
Optional<GenericBean<String>> genericBean = SpringBootUp.findGenericBean(GenericBean.class, String.class);
```
#### [:top:](#top) findAllGenericBeans<a id='3.0.0-5.2'></a>
```java
List<GenericBean<String>> allGenericBeans = SpringBootUp.findAllGenericBeans(GenericBean.class, String.class);
```
#### [:top:](#top) getBean(type), getBean(beanName)<a id='3.0.0-5.3'></a>
```java
TestBean bean;
// Equivalent to ApplicationContext#getBean(Class)
bean = SpringBootUp.getBean(TestBean.class);
// Equivalent to ApplicationContext#getBean(String)
bean = SpringBootUp.getBean("testBean");
```
#### [:top:](#top) findBean(type), findBean(beanName)<a id='3.0.0-5.4'></a>
```java
Optional<TestBean> beanOpt;
beanOpt = SpringBootUp.findBean(TestBean.class);
beanOpt = SpringBootUp.findBean("testBean");
```
#### [:top:](#top) getBean(type, arg...)<a id='3.0.0-5.5'></a>
```java
TestArgBean argBean;
// Equivalent to ApplicationContext#getBean(Class, Object...)
argBean = SpringBootUp.getBean(TestBean.class, "arg");
// Equivalent to ApplicationContext#getBean(String, Object...)
argBean = SpringBootUp.getBean("testBean", "arg");
```
#### [:top:](#top) findBean(type, arg...)<a id='3.0.0-5.6'></a>
```java
Optional<TestArgBean> argBeanOpt;
argBeanOpt = SpringBootUp.findBean(TestBean.class, "arg");
argBeanOpt = SpringBootUp.findBean("testBean", "arg");
```
#### [:top:](#top) getBean(beanName, requiredType)<a id='3.0.0-5.7'></a>
```java
// Equivalent to ApplicationContext#getBean(String, Class)
TestBean beanWithRequiredType = SpringBootUp.getBean("testBean", TestBean.class);
```
#### [:top:](#top) findBean(beanName, requiredType)<a id='3.0.0-5.8'></a>
```java
Optional<TestBean> beanWithRequiredType = SpringBootUp.findBean("testBean", TestBean.class);
```
#### [:top:](#top) getBeansOfType<a id='3.0.0-5.9'></a>
```java
Map<String, GenericBean<?>> beansOfType;
// Equivalent to ApplicationContext#getBeansOfType(Class)
beansOfType = SpringBootUp.getBeansOfType(GenericBean.class);
// Equivalent to ApplicationContext#getBeansOfType(Class, boolean, boolean)
beansOfType = SpringBootUp.getBeansOfType(GenericBean.class, true, true);
```
#### [:top:](#top) getBeansWithAnnotation<a id='3.0.0-5.10'></a>
```java
// Equivalent to ApplicationContext#getBeansWithAnnotation(Class)
Map<String, Object> beansWithAnnotation = SpringBootUp.getBeansWithAnnotation(TestAnnotation.class);
```
#### [:top:](#top) containsBean<a id='3.0.0-5.11'></a>
```java
// Equivalent to ApplicationContext#containsBean(String)
SpringBootUp.containsBean("testBean");
SpringBootUp.containsBean(TestBean.class);
```

### [:top:](#top) Message<a id='3.0.0-4'></a> - removing Locale arguments by using LocaleContextHolder#getLocale
#### [:top:](#top) getMessage(code)<a id='3.0.0-4.1'></a>
```java
String msg = SpringBootUp.getMessage("test.spring.boot.up.message");
```
#### [:top:](#top) findMessage(code)<a id='3.0.0-4.2'></a>
```java
Optional<String> msgOpt = SpringBootUp.findMessage("test.spring.boot.up.message");
```
#### [:top:](#top) getMessage(code, arg...)<a id='3.0.0-4.3'></a>
```java
String msg = SpringBootUp.getMessage("test.spring.boot.up.message", "test");
```
#### [:top:](#top) findMessage(code, arg...)<a id='3.0.0-4.4'></a>
```java
Optional<String> msgOpt = SpringBootUp.findMessage("test.spring.boot.up.message", "test");
```
#### [:top:](#top) getMessage(code, args, default)<a id='3.0.0-4.5'></a>
```java
// Get message with args and default, no Exception
String msg = SpringBootUp.getMessage("test.spring.boot.up.message", new Object[] {"test"}, ""));
```
#### [:top:](#top) getMessage(MessageSourceResolvable)<a id='3.0.0-4.6'></a>
```java
MessageSourceResolvable resolvable = new DefaultMessageSourceResolvable("test.spring.boot.up.message");
String msg = SpringBootUp.getMessage(resolvable);
```
#### [:top:](#top) findMessage(MessageSourceResolvable)<a id='3.0.0-4.7'></a>
```java
MessageSourceResolvable resolvable = new DefaultMessageSourceResolvable("test.spring.boot.up.message");
Optional<String> msgOpt = SpringBootUp.findMessage(resolvable);
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
| Note | Since |
| --- | --- |
| Java 17 required. | v3.0.0 |
| Spring Boot 3.0.0+ required. | v3.0.0 |
