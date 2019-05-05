package container;

public final class CaptchaParameterContainer {

    private CaptchaParameterContainer() {
    }

    public static final int FONT_SIZE = 30;
    public static final String FONT_NAME = "Arial";
    public static final int DEFAULT_X = 25;
    public static final int UP_Y = 24;
    public static final int DOWN_Y = 35;
    public static final int BLACK_COLOR = 255;

    public static final String CAPTCHA_FILE_PATH = "src/main/webapp/images/registrCaptcha.jpeg";

    public static final long CAPTCHA_LIVE_TIME = 300_000;
    public static final String CAPTCHA = "captcha";

}
