package xyz.chaobei.server.servlet;

import xyz.chaobei.server.enums.HttpCode;

import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @description:
 * @author: <a href='mailto:maruichao52@gmail.com'>MRC</a>
 * @since 2022/5/19
 **/
public interface HttpResponse extends Closeable {

    OutputStream getOutputStream() throws IOException;

    void addHeader(String name, String value) throws IOException;

    void sendError(HttpCode status);
}
