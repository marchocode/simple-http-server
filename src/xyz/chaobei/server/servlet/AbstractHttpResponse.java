package xyz.chaobei.server.servlet;

import java.net.Socket;

/**
 * @description:
 * @author: <a href='mailto:maruichao52@gmail.com'>MRC</a>
 * @since 2022/5/19
 **/
public abstract class AbstractHttpResponse implements HttpResponse {

    private final Socket socket;

    public AbstractHttpResponse(Socket socket) {
        this.socket = socket;
    }
}
