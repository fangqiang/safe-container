package cn.truthseeker.container.safe.set;

import cn.truthseeker.container.util.Utils;

import java.util.Set;

/**
 * @Description:
 * @author: qiang.fang
 * @email: lowping@163.com
 * @date: Created by on 19/3/15
 */
public interface SafeSet<E> extends Set<E> {
    default void addIgnoreNull(E e){
        if(e != null){
            add(e);
        }
    }

    default void addIgnoreEmpty(E e){
        if(Utils.isNotEmpty(e)){
            add(e);
        }
    }

    default SafeSet<E> cleanEmpty(){
        removeIf(Utils::isEmpty);
        return this;
    }
}
