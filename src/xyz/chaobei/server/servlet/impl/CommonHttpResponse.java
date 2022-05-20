package xyz.chaobei.server.servlet.impl;

import xyz.chaobei.server.enums.HttpCode;
import xyz.chaobei.server.servlet.AbstractHttpResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @description:
 * @author: <a href='mailto:maruichao52@gmail.com'>MRC</a>
 * @since 2022/5/19
 **/
public class CommonHttpResponse extends AbstractHttpResponse {

    public CommonHttpResponse(Socket socket) throws IOException {
        super(socket);
    }

}
