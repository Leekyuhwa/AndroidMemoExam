package com.memo.studygroup.Intent;

import android.content.Context;
import android.content.Intent;

import com.memo.studygroup.activity.MemoActivity;
import com.memo.studygroup.activity.MemoListActivity;
import com.memo.studygroup.vo.MemoVO;

/**
 * Created by coupang on 2015. 6. 25..
 */
public class IntentHandler {

    public static final String KEY_ID = "id";
    public static final String KEY_MEMO = "memo";
    public static final String KEY_DATE = "date";

    public static void moveToMemoActivity(Context context) {
        Intent intent = new Intent(context, MemoActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }

    public static void moveToMemoActivity(Context context, MemoVO memoVO) {
        Intent intent = new Intent(context, MemoActivity.class);
        intent.putExtra(KEY_ID, memoVO.getId());
        intent.putExtra(KEY_MEMO, memoVO.getMemo());
        intent.putExtra(KEY_DATE, memoVO.getRegdate());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }

    public static void meveToMemoListActivity(Context context) {
        Intent intent = new Intent(context, MemoListActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }


}
