package by.school.diary.security;

public class SecurityConstants {
    public static final String SIGN_UP_URLS = "/auth/**";

    public static final String SECRET = "SecretKeyGenJWT";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String CONTENT_TYPE = "application/json";
    public static final long EXPIRATION_TIME = 1200_000;

    public static final String API_DOCS = "/api-docs/**";
    public static final String SWAGGER_UI = "/swagger-ui/**";
    public static final String V_1_AUTH = "/v1/auth/**";
    public static final String V_1 = "/v1/**";
    public static final String ADMIN = "ADMIN";
    public static final String USER = "USER";
    public static final String STUDENT = "STUDENT";
    public static final String DIRECTOR = "DIRECTOR";
    public static final String PARENT = "PARENT";
    public static final String TEACHER = "TEACHER";
    public static final String H_2_CONSOLE = "/h2-console/**";

    public static final String ID = "id";
    public static final String USERNAME = "username";
}
