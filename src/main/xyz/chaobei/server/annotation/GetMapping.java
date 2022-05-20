package xyz.chaobei.server.annotation;

import xyz.chaobei.server.enums.HttpMethod;

import java.lang.annotation.*;

/**
 * @description:
 * @author: <a href='mailto:maruichao52@gmail.com'>MRC</a>
 * @since 2022/5/18
 **/
@Target(ElementType.METHOD)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface GetMapping {

    String value();

    HttpMethod method() default HttpMethod.GET;
}
