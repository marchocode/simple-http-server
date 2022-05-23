package xyz.chaobei.server.enums;

/**
 * @description:
 * @author: <a href='mailto:maruichao52@gmail.com'>MRC</a>
 * @since 2022/5/23
 **/
public enum ContextType {

    TEXT_HTML("text/html"),
    APPLICATION_JSON("application/json");

    private final String value;

    ContextType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
