package xyz.chaobei.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.chaobei.server.config.Configuation;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * @description:
 * @author: <a href='mailto:maruichao52@gmail.com'>MRC</a>
 * @since 2022/5/18
 **/
public class SimpleServer {

    private static Logger logger = LoggerFactory.getLogger(SimpleServer.class);

    public static void start(int port, Class clas) throws Exception {

        Configuation.init(clas);
        ServerSocket serverSocket = new ServerSocket(port);

        logger.info("server is start in {}", port);

        Socket socket;
        while ((socket = serverSocket.accept()) != null) {
            Configuation.execute(socket);
        }

    }

}
