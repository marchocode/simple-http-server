package xyz.chaobei.server.controller;

import xyz.chaobei.server.annotation.GetMapping;
import xyz.chaobei.server.annotation.RequestMapping;
import xyz.chaobei.server.servlet.HttpRequest;
import xyz.chaobei.server.servlet.HttpResponse;

/**
 * @description:
 * @author: <a href='mailto:maruichao52@gmail.com'>MRC</a>
 * @since 2022/5/18
 **/
@RequestMapping("/user")
public class UserController {

    @GetMapping(value = "/getById")
    public void user(HttpRequest request, HttpResponse response) {


    }

}
