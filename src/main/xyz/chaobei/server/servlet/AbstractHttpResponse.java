package xyz.chaobei.server.servlet;

import xyz.chaobei.server.enums.HttpCode;
import xyz.chaobei.server.enums.HttpVersion;

import java.io.*;
import java.net.Socket;
import java.util.Date;
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

    private HttpCode httpCode = HttpCode.OK;
    private HttpVersion httpVersion = HttpVersion.HTTP1_1;
    private OutputStream dateOutputStream = new ByteArrayOutputStream();

    private final Map<String, String> headers = new HashMap<>();

    public AbstractHttpResponse(Socket socket) throws IOException {
        this.socket = socket;
        this.outputStream = socket.getOutputStream();
        this.bufferedWriter = new PrintWriter(new OutputStreamWriter(this.outputStream));

        this.headers.put("Date", new Date().toString());
        this.headers.put("Server", "Simple-Server");
    }

    @Override
    public void sendError(HttpCode status) {
        this.httpCode = status;
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

    @Override
    public void build() {
        this.bufferedWriter.print(httpVersion.getVersion());
        this.bufferedWriter.print(" ");
        this.bufferedWriter.print(httpCode.getCode());
        this.bufferedWriter.print(" ");
        this.bufferedWriter.print(httpCode.getDesc());
        this.bufferedWriter.println();

        for (Map.Entry<String, String> entry : this.headers.entrySet()) {
            this.bufferedWriter.println(entry.getKey() + ": " + entry.getValue());
        }

        String message = "hello";

        this.bufferedWriter.println("Content-Type" + ": " + "text/html");
        this.bufferedWriter.println("Content-Length" + ": " + message.getBytes().length);
        this.bufferedWriter.println();

        this.bufferedWriter.print(message);

        this.bufferedWriter.flush();
    }

    @Override
    public OutputStream getOutputStream() {
        return this.dateOutputStream;
    }

}
