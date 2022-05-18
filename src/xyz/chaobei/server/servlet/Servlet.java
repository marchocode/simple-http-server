package xyz.chaobei.server.servlet;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * @description:
 * @author: <a href='mailto:maruichao52@gmail.com'>MRC</a>
 * @since 2022/5/18
 **/
public interface Servlet {

    InputStream getInputStream();

    OutputStream getOutputStream();

}
