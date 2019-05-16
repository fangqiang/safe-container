package cn.truthseeker.util;

import cn.truthseeker.TestUtil;
import cn.truthseeker.container.safe.Collections2;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @Description:
 * @author: qiang.fang
 * @email: lowping@163.com
 * @date: Created by on 19/3/17
 */
public class EmptysTest {
    @Test
    public void isNotEmpty() {
        assertTrue(Emptys.isNotEmpty(new Object()));
        assertFalse(Emptys.isNotEmpty(new HashMap()));
        assertFalse(Emptys.isNotEmpty(new ArrayList()));
        assertFalse(Emptys.isNotEmpty("\r"));
        assertFalse(Emptys.isNotEmpty(new Object[0]));
        assertFalse(Emptys.isNotEmpty(new String[0]));
        assertFalse(Emptys.isNotEmpty(new byte[0]));
        assertFalse(Emptys.isNotEmpty(new short[0]));
        assertFalse(Emptys.isNotEmpty(new int[0]));
        assertFalse(Emptys.isNotEmpty(new long[0]));
        assertFalse(Emptys.isNotEmpty(new double[0]));
        assertFalse(Emptys.isNotEmpty(new float[0]));
        assertFalse(Emptys.isNotEmpty(new boolean[0]));
        assertFalse(Emptys.isNotEmpty(new char[0]));
    }

    @Test
    public void oneNull() {
        assertTrue(Emptys.oneNull("a", null));
        assertTrue(Emptys.oneNull(null, "a"));
        assertFalse(Emptys.oneNull(null, null));
        assertFalse(Emptys.oneNull("a", "a"));
    }

    @Test
    public void oneEmpty() {
        assertTrue(Emptys.oneEmpty("", "a"));
        assertTrue(Emptys.oneEmpty("a", ""));
        assertFalse(Emptys.oneEmpty("", null));
        assertFalse(Emptys.oneEmpty("a", "a"));
    }

    @Test
    public void anyEmpty() {
        List l = new ArrayList();
        l.add(null);
        l.add(1);
        l.add(2);
        assertTrue(Emptys.isAnyEmpty(l));
        assertTrue(Emptys.isAnyNull(l));

        l.clear();
        l.add("");
        l.add(1);
        l.add(2);
        assertTrue(Emptys.isAnyEmpty(l));
        assertFalse(Emptys.isAnyNull(l));
    }

    @Test
    public void assertEmpty() {
        TestUtil.noException(() -> Emptys.assertNotNull("a"));
        TestUtil.noException(() -> Emptys.assertNotNull(""));
        TestUtil.withException(() -> Emptys.assertNotNull(null));

        TestUtil.noException(() -> Emptys.assertNotEmpty("a"));
        TestUtil.withException(() -> Emptys.assertNotEmpty(""));
        TestUtil.withException(() -> Emptys.assertNotEmpty(null));

        TestUtil.noException(() -> Emptys.assertNoneNull(Collections2.ofList("a", "a")));
        TestUtil.noException(() -> Emptys.assertNoneNull(Collections2.ofList("a", "")));
        TestUtil.withException(() -> Emptys.assertNoneNull(Collections2.ofList("a", null)));

        TestUtil.noException(() -> Emptys.assertNoneEmpty(Collections2.ofList("a", "a")));
        TestUtil.withException(() -> Emptys.assertNoneEmpty(Collections2.ofList("a", "")));
        TestUtil.withException(() -> Emptys.assertNoneEmpty(Collections2.ofList("a", null)));

    }

    @Test
    public void ifNotNullApply() {
        assertTrue(Emptys.applyIfNotNull("a ", String::trim).equals("a"));
        assertTrue(Emptys.applyIfNotNull(null, String::trim) == null);
    }
}