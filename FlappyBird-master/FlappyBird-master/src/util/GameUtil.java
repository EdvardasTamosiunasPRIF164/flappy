package util;

import main.Bird;

import java.awt.*;

public final class GameUtil {
    private static GameUtil instance = new GameUtil();
//    singleton creation design pattern
    private GameUtil() {
    }

    public static GameUtil getInstance(){
        return instance;
    }

    public static boolean intersects(Bird r, Rectangle c) {
        int tw = c.width;
        int th = c.height;
        int rw = r.width;
        int rh = r.height;
        if (rw <= 0 || rh <= 0 || tw <= 0 || th <= 0) {
            return false;
        }
        int tx = c.x;
        int ty = c.y;
        int rx = r.x;
        int ry = r.y;
        rw += rx;
        rh += ry;
        tw += tx;
        th += ty;
        //      overflow || intersect
        return ((rw < rx || rw > tx) &&
                (rh < ry || rh > ty) &&
                (tw < tx || tw > rx) &&
                (th < ty || th > ry));
    }
}
