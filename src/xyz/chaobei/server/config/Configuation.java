package xyz.chaobei.server.config;

import xyz.chaobei.server.annotation.GetMapping;
import xyz.chaobei.server.annotation.RequestMapping;

import java.io.File;
import java.lang.reflect.Method;
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
            Class<?> targetClass = urlClassLoader.loadClass(targetDir.concat(fileName));

            RequestMapping requestMapping = targetClass.getAnnotation(RequestMapping.class);
            if (requestMapping == null) {
                continue;
            }

            String base = requestMapping.value();
            Method[] allRequestMethod = targetClass.getMethods();

            for (Method method : allRequestMethod) {

                GetMapping getMapping = method.getAnnotation(GetMapping.class);
                if (getMapping == null) {
                    continue;
                }

                String methodUrl = base.concat(getMapping.value());
                System.out.println(methodUrl);

                GET_MAPPING.put(methodUrl, method);
            }
        }
    }

}
