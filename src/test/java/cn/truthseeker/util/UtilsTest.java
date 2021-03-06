package cn.truthseeker.util;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @Description:
 * @author: qiang.fang
 * @email: lowping@163.com
 * @date: Created by on 19/3/16
 */
public class UtilsTest {

    @Test
    public void isAnyNull() {
        assertFalse(Emptys.isAnyNull(1, 2));
        assertFalse(Emptys.isAnyNull("", 1, 2));
        assertTrue(Emptys.isAnyNull(1, 2, null));
    }

    @Test
    public void isAnyEmpty() {
        assertFalse(Emptys.isAnyEmpty(1, 2));
        assertTrue(Emptys.isAnyEmpty("", 1, 2));
        assertTrue(Emptys.isAnyEmpty(1, 2, null));
    }

    @Test
    public void sleep() {
        Utils.sleep(1);

        Thread thread = new Thread(() -> {
            Utils.sleep(100);
        });

        thread.start();
        thread.interrupt();
    }

    @Test
    public void mapAddList() {
        HashMap<String, List<Object>> map = new HashMap<>();
        Utils.mapAddList(map, "a", 1);
        Utils.mapAddList(map, "a", 1);
    }

    @Test
    public void splitOmitEmpty() {
        List<String> strings = Utils.splitOmitEmpty(",, a ,b ,\r \t,c,,", ",");
        assertTrue(strings.size() == 3);
        assertTrue(strings.containsAll(Arrays.asList("a", "b", "c")));
        strings = Utils.splitOmitEmpty(" , ", ",");
        assertTrue(strings.size() == 0);
    }
}