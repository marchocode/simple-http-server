## Simple http server

[English]()/中文



这是一个简单的HTTP服务器，基于Java语言构建，通过学习此项目，让你能更加了解：

1. HTTP协议
2. Socket 网络编程
3. 字节流相关知识
4. 反射调用相关内容



### HTTP Protocol

> https://zh.wikipedia.org/wiki/%E8%B6%85%E6%96%87%E6%9C%AC%E4%BC%A0%E8%BE%93%E5%8D%8F%E8%AE%AE

HTTP是一种无状态的协议，意味着每一次的请求都是最新的，即服务器分不清每一个客户端的请求。需要构建服务器，首先需要了解 HTTP 协议



#### Request

客户端发送如下格式的请求到服务端，服务端通过解析，得到path,以及query参数等。

```http request
GET /api/user?id=1 HTTP/1.1
Host: www.example.com
User-Agent: Mozilla/5.0
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8
Accept-Language: en-GB,en;q=0.5
Accept-Encoding: gzip, deflate, br
Connection: keep-alive
```



#### Response

服务端处理完请求后，响应内容如下格式：

```http request
HTTP/1.1 200 OK
Date: Mon, 23 May 2005 22:38:34 GMT
Content-Type: text/html; charset=UTF-8
Content-Length: 155
Last-Modified: Wed, 08 Jan 2003 23:11:55 GMT
Server: Apache/1.3.3.7 (Unix) (Red-Hat/Linux)
ETag: "3f80f-1b6-3e1cb03b"
Accept-Ranges: bytes
Connection: close

<html>
  <head>
    <title>An Example Page</title>
  </head>
  <body>
    <p>Hello World, this is a very simple HTML document.</p>
  </body>
</html>
```



### Get Start

开始运行实践：

```bash
# 拉取代码到本地
git clone https://github.com/maruichao52/simple-http-server.git

# 通过maven插件运行
mvn spring-boot:run

# 测试请求
curl http://localhost/user/getUserById
```



### After

- :white_large_square: Support POST Request
- :white_large_square: More faster
- :white_large_square: Asynchronous IO Stream