package xyz.chaobei.server.factory;

import xyz.chaobei.server.servlet.HttpRequest;
import xyz.chaobei.server.servlet.HttpResponse;
import xyz.chaobei.server.servlet.impl.CommonHttpResponse;
import xyz.chaobei.server.servlet.impl.PostHttpRequest;

import java.io.IOException;
import java.net.Socket;

/**
 * @description:
 * @author: <a href='mailto:maruichao52@gmail.com'>MRC</a>
 * @since 2022/5/19
 **/
public class PostServletFactory implements ServletFactory {

    private final Socket socket;

    public PostServletFactory(Socket socket) {
        this.socket = socket;
    }

    @Override
    public HttpRequest request() throws IOException {
        return new PostHttpRequest(this.socket);
    }

    @Override
    public HttpResponse response() throws IOException {
        return new CommonHttpResponse(this.socket);
    }
}
