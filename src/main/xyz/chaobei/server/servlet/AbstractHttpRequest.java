package xyz.chaobei.server.servlet;

import xyz.chaobei.server.enums.HttpMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @description:
 * @author: <a href='mailto:maruichao52@gmail.com'>MRC</a>
 * @since 2022/5/19
 **/
public class AbstractHttpRequest implements HttpRequest {

    protected final Socket socket;
    protected final InputStream inputStream;
    protected final BufferedReader bufferedReader;

    private HttpMethod method;
    protected String path;
    protected String version;
    protected Map<String,String> heads;

    private final Pattern pattern = Pattern.compile("^[A-Za-z0-9_-]+:\\s[\\x00-\\x7E]+$");

    public AbstractHttpRequest(Socket socket) throws IOException {
        this.socket = socket;
        this.inputStream = socket.getInputStream();
        this.bufferedReader = new BufferedReader(new InputStreamReader(this.inputStream));

        this.buildTitle(this.bufferedReader);
        this.buildHeaders(this.bufferedReader);
    }

    public void buildTitle(BufferedReader bufferedReader) throws IOException {
        String title = bufferedReader.readLine();
        String[] arrays = title.split("\\s");

        this.method = HttpMethod.valueOf(arrays[0]);
        this.path = arrays[1];
        this.version = arrays[2];
    }

    private void buildHeaders(BufferedReader bufferedReader) throws IOException {

        Map<String, String> heads = new HashMap<>(16);

        String item;

        while ((item = bufferedReader.readLine()) != null && item.length() > 0) {
            if (pattern.matcher(item).find()) {
                String[] array = item.split(":\\s");
                heads.put(array[0], array[1]);
            }
        }
        this.heads = heads;
    }

    @Override
    public Map<String, String> getHeaders() {
        return this.heads;
    }

    @Override
    public InputStream getInputStream() {
        return this.inputStream;
    }

    @Override
    public String version() {
        return this.version;
    }

    @Override
    public String path() {
        return this.path;
    }

    @Override
    public Map<String, String> query() {
        return null;
    }

    @Override
    public void close() throws IOException {
        bufferedReader.close();
        inputStream.close();
        socket.close();
    }

    @Override
    public HttpMethod method() {
        return this.method;
    }

    @Override
    public String data() {
        return null;
    }
}
