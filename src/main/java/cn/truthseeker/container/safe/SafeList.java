package cn.truthseeker.container.safe;

import cn.truthseeker.container.Collections2;
import cn.truthseeker.container.Maps;

import java.util.Collection;

/**
 * @Description:
 * @author: qiang.fang
 * @email: lowping@163.com
 * @date: Created by on 19/3/14
 */
public final class SafeList<E> extends SafeArrayList<E> {
    public SafeList() {
        super();
    }

    public SafeList(Collection<? extends E> c) {
        super(c);
        if(! (c instanceof SafeContainer)){
            Collections2.checkSafe(c);
        }
    }

    public SafeList(int initialCapacity) {
        super(initialCapacity);
    }
}