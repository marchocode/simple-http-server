package xyz.chaobei.server.factory;

import java.net.Socket;

/**
 * @description:
 * @author: <a href='mailto:maruichao52@gmail.com'>MRC</a>
 * @since 2022/5/19
 **/
public class SwitchServletFactory {

    private Socket socket;

    private SwitchServletFactory(Socket socket){
        this.socket = socket;
    }

    public static SwitchServletFactory build(Socket socket) {
        return new SwitchServletFactory(socket);
    }

    public ServletFactory get(String type) {
        return "GET".equals(type) ? new GetServletFactory(this.socket) : new PostServletFactory(this.socket);
    }

}
