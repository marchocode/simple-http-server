package xyz.chaobei.server.factory;

import xyz.chaobei.server.servlet.HttpRequest;
import xyz.chaobei.server.servlet.HttpResponse;

import java.io.IOException;

/**
 * @description:
 * @author: <a href='mailto:maruichao52@gmail.com'>MRC</a>
 * @since 2022/5/19
 **/
public interface ServletFactory {

    HttpRequest request() throws IOException;

    HttpResponse response() throws IOException;

}
