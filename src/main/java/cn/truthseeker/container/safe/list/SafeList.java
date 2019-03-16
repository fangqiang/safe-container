package cn.truthseeker.container.safe.list;

import cn.truthseeker.container.safe.set.SafeSet;
import cn.truthseeker.container.util.Utils;

import java.util.List;

/**
 * @Description:
 * @author: qiang.fang
 * @email: lowping@163.com
 * @date: Created by on 19/3/14
 */
public interface SafeList<E> extends List<E> {
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

    default SafeList<E> cleanEmpty(){
        removeIf(Utils::isEmpty);
        return this;
    }
}