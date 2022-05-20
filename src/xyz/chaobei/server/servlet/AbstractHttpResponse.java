package xyz.chaobei.server.servlet;

import xyz.chaobei.server.enums.HttpCode;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: <a href='mailto:maruichao52@gmail.com'>MRC</a>
 * @since 2022/5/19
 **/
public abstract class AbstractHttpResponse implements HttpResponse {

    protected final Socket socket;

    protected final OutputStream outputStream;
    protected final PrintWriter bufferedWriter;

    private final Map<String, String> headers = new HashMap<>();

    public AbstractHttpResponse(Socket socket) throws IOException {
        this.socket = socket;
        this.outputStream = socket.getOutputStream();
        this.bufferedWriter = new PrintWriter(new OutputStreamWriter(this.outputStream));
    }

    @Override
    public void sendError(HttpCode status) {

    }

    @Override
    public void addHeader(String name, String value) {
        headers.put(name, value);
    }

    @Override
    public void close() throws IOException {
        bufferedWriter.close();
        outputStream.close();
        socket.close();
    }
}
