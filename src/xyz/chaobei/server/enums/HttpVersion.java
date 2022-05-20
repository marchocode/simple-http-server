package xyz.chaobei.server.enums;

/**
 * @description:
 * @author: <a href='mailto:maruichao52@gmail.com'>MRC</a>
 * @since 2022/5/20
 **/
public enum HttpVersion {

    HTTP1_1("HTTP/1.1");

    private final String version;

    HttpVersion(String version) {
        this.version = version;
    }

    public String getVersion() {
        return version;
    }
}
