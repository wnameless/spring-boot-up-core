package com.github.wnameless.spring.boot.up;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.context.annotation.ComponentScan;

/**
 * 
 * {@link EnableSpringBootUp} is made for Spring to activate all features brought by SpringBootUp
 * library. All SpringBootUp components under package {@code com.github.wnameless.spring.boot.up}
 * will be found automatically.<br>
 * <br>
 * Add this annotation to an {@code @Configuration} class to enable SpringBootUp.<br>
 * <br>
 * For example:
 *
 * <pre class="code">
 * 
 * &#064;EnableSpringBootUp
 * &#064;Configuration
 * public class MyWebConfiguration {}
 * </pre>
 *
 * @see SpringBootUpApplicationContextProvider
 *
 * @author Wei-Ming Wu
 * 
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ComponentScan(basePackageClasses = {SpringBootUpApplicationContextProvider.class})
public @interface EnableSpringBootUp {}
