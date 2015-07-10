package com.memo.studygroup.util;

import java.util.Collection;

public class CollectionUtil {

    public static boolean isEmpty(Collection collection) {
        return (collection == null || collection.isEmpty());
    }

    public static boolean isNotEmpty(Collection collection) {
        return !isEmpty(collection);
    }
}
