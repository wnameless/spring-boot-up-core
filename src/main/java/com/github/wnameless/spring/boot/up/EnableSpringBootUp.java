package com.github.wnameless.spring.boot.up;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.context.annotation.Import;

/**
 * 
 * {@link EnableSpringBootUp} is made for {@link SpringBootUp} utility class to work properly by
 * registrating the Spring ApplicationContext to {@link SpringBootUpApplicationContextProvider}.<br>
 * <br>
 * Add this annotation to a {@code @Configuration} class to enable SpringBootUp.<br>
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
@Import({SpringBootUpApplicationContextProvider.class})
public @interface EnableSpringBootUp {}
