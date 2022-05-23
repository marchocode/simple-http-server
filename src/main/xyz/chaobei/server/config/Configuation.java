package xyz.chaobei.server.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.chaobei.server.annotation.GetMapping;
import xyz.chaobei.server.annotation.RequestMapping;
import xyz.chaobei.server.annotation.ResponseBody;
import xyz.chaobei.server.enums.HttpCode;
import xyz.chaobei.server.enums.HttpMethod;
import xyz.chaobei.server.factory.ServletFactory;
import xyz.chaobei.server.factory.SimpleServletFactory;
import xyz.chaobei.server.servlet.HttpRequest;
import xyz.chaobei.server.servlet.HttpResponse;

import java.io.*;
import java.lang.reflect.Method;
import java.net.Socket;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @description:
 * @author: <a href='mailto:maruichao52@gmail.com'>MRC</a>
 * @since 2022/5/18
 **/
public class Configuation {

    private static final Logger logger = LoggerFactory.getLogger(Configuation.class);

    // GET /api/test -> UserController.test();
    private static final Map<String, Method> URL_METHOD = new HashMap<>();
    private static final Map<String, Object> URL_OBJECT = new HashMap<>();
    private static final Map<String, ResponseBody> RESPONSE_TYPE = new HashMap<>();

    /**
     * <p>获得主要类加载器，扫描包，按照注解进行实例化
     * <p>author: <a href='mailto:maruichao52@gmail.com'>MRC</a>
     *
     * @param clas 启动Class
     * @return void
     * @since 2022/5/20
     **/
    public static void init(Class clas) throws Exception {

        String basePath = clas.getPackage().getName();
        logger.info("init class {}", clas);

        ClassLoader classLoader = clas.getClassLoader();

        String targetDir = basePath.concat(".controller.");
        logger.info("init targetDir {}", targetDir);

        URL baseDir = classLoader.getResource(targetDir.replace('.', '/'));
        logger.info("init baseDir {}", baseDir.toString());

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
                ResponseBody responseBody = method.getAnnotation(ResponseBody.class);

                if (getMapping == null) {
                    continue;
                }

                String methodUrl = getMapping.method().getValue() + ":" + base.concat(getMapping.value());
                logger.info("init url mapping url={}", methodUrl);

                URL_METHOD.put(methodUrl, method);
                URL_OBJECT.put(methodUrl, typeObject);
                RESPONSE_TYPE.put(methodUrl, responseBody);
            }
        }

        // create response factory
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

        ServletFactory servletFactory = new SimpleServletFactory(socket);
        HttpRequest request = servletFactory.request();

        String path = request.path();
        String url = request.method().getValue().concat(":").concat(path);

        Object type = URL_OBJECT.get(url);
        Method method = URL_METHOD.get(url);
        ResponseBody responseBody = RESPONSE_TYPE.get(url);
        HttpResponse response = servletFactory.response(responseBody.type());

        if (Objects.isNull(type) || Objects.isNull(method) || Objects.isNull(responseBody)) {
            response.sendError(HttpCode.NOT_FOUND);
        } else {
            Object result = method.invoke(type, request);
            response.write(result);
        }

        response.build();
        response.close();
    }

}
