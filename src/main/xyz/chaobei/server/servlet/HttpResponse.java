package xyz.chaobei.server.servlet;

import xyz.chaobei.server.enums.ContextType;
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

    /**
     * <p>获得输出流，http body的输出
     * <p>author: <a href='mailto:maruichao52@gmail.com'>MRC</a>
     *
     * @return java.io.OutputStream
     * @since 2022/5/23
     **/
    OutputStream getOutputStream();

    /**
     * <p>添加HTTP相应头
     * <p>author: <a href='mailto:maruichao52@gmail.com'>MRC</a>
     *
     * @param name  名称
     * @param value 值
     * @return void
     * @since 2022/5/23
     **/
    void addHeader(String name, String value);

    /**
     * <p>发送一个错误请求到客户端
     * <p>author: <a href='mailto:maruichao52@gmail.com'>MRC</a>
     *
     * @param status
     * @return void
     * @since 2022/5/23
     **/
    void sendError(HttpCode status);

    /**
     * <p>获得当前请求的响应类型
     * <p>author: <a href='mailto:maruichao52@gmail.com'>MRC</a>
     *
     * @return xyz.chaobei.server.enums.ContextType
     * @since 2022/5/23
     **/
    ContextType contextType();

    /**
     * <p>构建相应内容
     * <p>author: <a href='mailto:maruichao52@gmail.com'>MRC</a>
     *
     * @return void
     * @since 2022/5/23
     **/
    void build();

    /**
     * <p>写入需要响应的内容
     * <p>author: <a href='mailto:maruichao52@gmail.com'>MRC</a>
     *
     * @param data 将对象写入输出流
     * @return void
     * @since 2022/5/23
     **/
    void write(Object data) throws IOException;
}
