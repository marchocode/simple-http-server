package xyz.chaobei.server.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.chaobei.server.annotation.GetMapping;
import xyz.chaobei.server.annotation.RequestMapping;
import xyz.chaobei.server.annotation.ResponseBody;
import xyz.chaobei.server.enums.ContextType;
import xyz.chaobei.server.servlet.HttpRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;

/**
 * @description:
 * @author: <a href='mailto:maruichao52@gmail.com'>MRC</a>
 * @since 2022/5/18
 **/
@RequestMapping("/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping(value = "/getById")
    @ResponseBody(type = ContextType.APPLICATION_JSON)
    public Map<String, String> user(HttpRequest request) {

        logger.info("request user header={}", request.getHeaders().toString());
        logger.info("request user path={}", request.path());

        Map<String, String> data = new HashMap<>();

        data.put("id", UUID.randomUUID().toString());
        data.put("name", "mrc");
        data.put("github", "https://github.com/maruichao52");

        return data;
    }

}
