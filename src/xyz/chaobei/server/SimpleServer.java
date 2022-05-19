package xyz.chaobei.server;

import xyz.chaobei.server.annotation.RequestMapping;
import xyz.chaobei.server.config.Configuation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.logging.Logger;

/**
 * @description:
 * @author: <a href='mailto:maruichao52@gmail.com'>MRC</a>
 * @since 2022/5/18
 **/
public class SimpleServer {

    private static final Logger logger = Logger.getLogger("SimpleServer");

    public static void start(int port, Class clas) throws Exception {

        Configuation.init(clas);
        ServerSocket serverSocket = new ServerSocket(port);
        logger.info("Server is start in port:" + port);

        Socket socket;
        while ((socket = serverSocket.accept()) != null) {
            Configuation.execute(socket);
        }

    }

}
