package xyz.chaobei.server.servlet.impl;

import xyz.chaobei.server.servlet.ServletRequest;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Map;

/**
 * @description:
 * @author: <a href='mailto:maruichao52@gmail.com'>MRC</a>
 * @since 2022/5/18
 **/
public class HttpServletRequest implements ServletRequest {

    private final Socket socket;

    public HttpServletRequest(Socket socket) {
        this.socket = socket;
    }

    @Override
    public InputStream getInputStream() {
        try {
            return socket.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Map<String, String> getHeaders() {
        return null;
    }

    @Override
    public void close()  {

    }
}
