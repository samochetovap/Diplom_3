package enums;

public enum RouteEnum {
    REGISTER("register"),
    FORGOT_PASSWORD("forgot-password");

    private final String value;

    RouteEnum(String value) {
        this.value = value;
    }

    public String getValue(){
        return value;
    }

}
