package xyz.chaobei.server.servlet.impl.response;

import com.alibaba.fastjson2.JSON;
import xyz.chaobei.server.enums.ContextType;
import xyz.chaobei.server.servlet.AbstractHttpResponse;

import java.io.IOException;
import java.net.Socket;

/**
 * @description:
 * @author: <a href='mailto:maruichao52@gmail.com'>MRC</a>
 * @since 2022/5/23
 **/
public class JsonHttpResponse extends AbstractHttpResponse {

    public JsonHttpResponse(Socket socket) throws IOException {
        super(socket);
    }

    @Override
    public ContextType contextType() {
        return ContextType.APPLICATION_JSON;
    }

    @Override
    public void write(Object data) throws IOException {
        dateOutputStream.write(JSON.toJSONBytes(data));
    }
}
