package xyz.chaobei.server.factory;

import xyz.chaobei.server.enums.ContextType;
import xyz.chaobei.server.servlet.AbstractHttpRequest;
import xyz.chaobei.server.servlet.HttpRequest;
import xyz.chaobei.server.servlet.HttpResponse;
import xyz.chaobei.server.servlet.impl.response.JsonHttpResponse;
import xyz.chaobei.server.servlet.impl.response.TextHttpResponse;

import java.io.IOException;
import java.net.Socket;

/**
 * @description:
 * @author: <a href='mailto:maruichao52@gmail.com'>MRC</a>
 * @since 2022/5/23
 **/
public class SimpleServletFactory implements ServletFactory {

    private final Socket socket;

    public SimpleServletFactory(Socket socket) throws IOException {
        this.socket = socket;
    }

    @Override
    public HttpRequest request() throws IOException {
        return new AbstractHttpRequest(socket);
    }

    @Override
    public HttpResponse response(ContextType type) throws IOException {
        return ContextType.TEXT_HTML.equals(type) ? new TextHttpResponse(socket) : new JsonHttpResponse(socket);
    }
}
