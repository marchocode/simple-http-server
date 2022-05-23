package xyz.chaobei.server.servlet.impl.response;

import xyz.chaobei.server.enums.ContextType;
import xyz.chaobei.server.servlet.AbstractHttpResponse;

import java.io.IOException;
import java.net.Socket;

/**
 * @description:
 * @author: <a href='mailto:maruichao52@gmail.com'>MRC</a>
 * @since 2022/5/19
 **/
public class TextHttpResponse extends AbstractHttpResponse {

    public TextHttpResponse(Socket socket) throws IOException {
        super(socket);
    }

    @Override
    public ContextType contextType() {
        return ContextType.TEXT_HTML;
    }

    @Override
    public void write(Object data) throws IOException {
        dateOutputStream.write(data.toString().getBytes());
    }
}
