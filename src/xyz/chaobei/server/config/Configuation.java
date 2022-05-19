package xyz.chaobei.server.config;

import xyz.chaobei.server.annotation.GetMapping;
import xyz.chaobei.server.annotation.RequestMapping;
import xyz.chaobei.server.factory.ServletFactory;
import xyz.chaobei.server.factory.SwitchServletFactory;
import xyz.chaobei.server.servlet.HttpRequest;
import xyz.chaobei.server.servlet.impl.GetHttpRequest;

import java.io.*;
import java.lang.reflect.Method;
import java.net.Socket;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: <a href='mailto:maruichao52@gmail.com'>MRC</a>
 * @since 2022/5/18
 **/
public class Configuation {

    private static final Map<String, String> map = new HashMap<>();
    private static final Map<String, Class<?>> namespace = new HashMap<>();

    // GET /api/test -> UserController.test();
    private static final Map<String, Method> GET_MAPPING = new HashMap<>();
    private static final Map<String, Object> URL_OBJECT = new HashMap<>();

    public static void init(Class clas) throws Exception {

        String basePath = clas.getPackage().getName();
        ClassLoader classLoader = clas.getClassLoader();

        String targetDir = basePath.concat(".controller.");
        URL baseDir = classLoader.getResource(targetDir.replace('.', '/'));

        URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{baseDir});

        for (File classFile : new File(baseDir.toURI()).listFiles()) {

            if (!classFile.isFile()) {
                continue;
            }
            String fileName = classFile.getName().split("\\.")[0];
            Class<?> typeClass = urlClassLoader.loadClass(targetDir.concat(fileName));

            RequestMapping requestMapping = typeClass.getAnnotation(RequestMapping.class);
            if (requestMapping == null) {
                continue;
            }

            Object typeObject = typeClass.newInstance();

            String base = requestMapping.value();
            Method[] allRequestMethod = typeClass.getMethods();

            for (Method method : allRequestMethod) {

                GetMapping getMapping = method.getAnnotation(GetMapping.class);
                if (getMapping == null) {
                    continue;
                }

                String methodUrl = base.concat(getMapping.value());

                GET_MAPPING.put(methodUrl, method);
                URL_OBJECT.put(methodUrl, typeObject);
            }
        }
    }
    /**
     * <p>解析输入流，获得HTTP
     * <p>author: <a href='mailto:maruichao52@gmail.com'>MRC</a>
     *
     * @param socket 请求
     * @return void
     * @since 2022/5/19
     **/
    public static void execute(Socket socket) throws Exception {

        ServletFactory servletFactory = SwitchServletFactory.build(socket).get("GET");

        HttpRequest request = servletFactory.request();

    }

}
