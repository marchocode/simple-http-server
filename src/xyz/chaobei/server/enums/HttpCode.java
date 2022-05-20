package xyz.chaobei.server.enums;

/**
 * @description:
 * @author: <a href='mailto:maruichao52@gmail.com'>MRC</a>
 * @since 2022/5/20
 **/
public enum HttpCode {

    OK(200, "OK"),
    NOT_FOUND(404, "NOT FOUND"),
    ERROR(500, "ERROR");

    private final Integer code;

    private final String desc;

    HttpCode(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
