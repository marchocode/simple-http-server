package xyz.chaobei.server.servlet;

import xyz.chaobei.server.enums.HttpMethod;

import java.io.Closeable;
import java.io.InputStream;
import java.util.Map;

/**
 * @description:
 * @author: <a href='mailto:maruichao52@gmail.com'>MRC</a>
 * @since 2022/5/19
 **/
public interface HttpRequest extends Closeable {

    Map<String,String> getHeaders();

    String version();

    HttpMethod method();

    String path();

    Map<String,String> query();

    String data();

    InputStream getInputStream();

}
