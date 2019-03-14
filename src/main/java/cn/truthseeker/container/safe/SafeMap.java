package cn.truthseeker.container.safe;

import cn.truthseeker.container.Maps;

import java.util.Map;

/**
 * @Description:
 * @author: qiang.fang
 * @email: lowping@163.com
 * @date: Created by on 19/3/14
 */
public final class SafeMap<K,V> extends SafeHashMap<K,V>{
    public SafeMap(){
        super();
    }

    public SafeMap(Map<? extends K, ? extends V> m){
        super(m);
        if(! (m instanceof SafeContainer)){
            Maps.checkSafe(m);
        }
    }

    public SafeMap(int initialCapacity){
        super(initialCapacity);
    }

    public SafeMap(int initialCapacity, float loadFactor){
        super(initialCapacity, loadFactor);
    }
}
