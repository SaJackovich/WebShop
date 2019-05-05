package util;

public class IdGenerator {

    private IdGenerator() {
    }

    private static int captchaId = 1;
    private static int userId = 1;
    private static int avatarId = 1;
    private static int orderId = 1;

    public static int getCaptchaId(){
        return captchaId++;
    }

    public static int getUserId(){
        return userId++;
    }

    public static int getAvatarId() {
        return avatarId++;
    }

    public static int getOrderId(){
        return orderId++;
    }
}
