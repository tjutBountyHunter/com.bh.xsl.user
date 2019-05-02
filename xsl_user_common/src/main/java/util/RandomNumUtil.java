package util;

/**
 * 生成随机数
 */
public class RandomNumUtil {
    public static String getRandom() {
        return ((Math.random() * 9 + 1) * 1000) + "";
    }
}
