package xyz.chaobei.server.servlet;

import java.io.Closeable;
import java.io.InputStream;
import java.util.Map;

/**
 * @description:
 * @author: <a href='mailto:maruichao52@gmail.com'>MRC</a>
 * @since 2022/5/18
 **/
public interface ServletRequest extends Closeable {

    /**
     * <p>获得输入流
     * <p>author: <a href='mailto:maruichao52@gmail.com'>MRC</a>
     *
     * @return java.io.InputStream
     * @since 2022/5/18
     **/
    InputStream getInputStream();

    /***
     * <p>获得请求头
     * <p>author: <a href='mailto:maruichao52@gmail.com'>MRC</a>
     *
     * @return java.util.Map<java.lang.String, java.lang.String>
     * @since 2022/5/18
     **/
    Map<String, String> getHeaders();

}
