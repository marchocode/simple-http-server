package xyz.chaobei.server;

/**
 * @description:
 * @author: <a href='mailto:maruichao52@gmail.com'>MRC</a>
 * @since 2022/5/18
 **/
public class Application {

    public static void main(String[] args) throws Exception {
        SimpleServer.start(8080, Application.class);
    }

}
