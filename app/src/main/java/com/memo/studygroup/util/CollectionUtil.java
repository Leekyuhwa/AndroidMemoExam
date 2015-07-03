package com.memo.studygroup.util;

import java.util.Collection;

/**
 * Created by coupang on 2015. 6. 24..
 */
public class CollectionUtil {

    public static boolean isEmpty(Collection collection) {
        return (collection == null || collection.isEmpty());
    }

    public static boolean isNotEmpty(Collection collection) {
        return !isEmpty(collection);
    }
}
