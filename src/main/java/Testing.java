import models.mediaDB.Content;
import models.mediaDB.UploaderImpl;
import models.mediaDB.VideoImpl;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.Date;

public class Testing {
    public static void main(String[] args) {
        Content c = new VideoImpl(1920, 1080, "ETW", 48000, Duration.ofSeconds(68), BigDecimal.ZERO, "alpha", null, 0, new UploaderImpl("Citrus"), new Date());
        System.out.println(c.toString());
        try {
            Field member_name = c.getClass().getDeclaredField("accessCount");
            member_name.setAccessible(true);
            member_name.set(c, 9);
            member_name.setAccessible(false);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println(c.toString());
    }
}
