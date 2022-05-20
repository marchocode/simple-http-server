package xyz.chaobei.server.servlet.impl;

import xyz.chaobei.server.enums.HttpMethod;
import xyz.chaobei.server.servlet.AbstractHttpRequest;

import java.io.IOException;
import java.net.Socket;

/**
 * @description:
 * @author: <a href='mailto:maruichao52@gmail.com'>MRC</a>
 * @since 2022/5/19
 **/
public class PostHttpRequest extends AbstractHttpRequest {

    public PostHttpRequest(Socket socket) throws IOException {
        super(socket);
    }

    @Override
    public HttpMethod method() {
        return HttpMethod.POST;
    }

    @Override
    public String data() {
        return null;
    }
}
