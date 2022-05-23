package xyz.chaobei.server.annotation;

import xyz.chaobei.server.enums.ContextType;

import java.lang.annotation.*;

/**
 * @description:
 * @author: <a href='mailto:maruichao52@gmail.com'>MRC</a>
 * @since 2022/5/23
 **/
@Target(ElementType.METHOD)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface ResponseBody {

    ContextType type() default ContextType.TEXT_HTML;

}
