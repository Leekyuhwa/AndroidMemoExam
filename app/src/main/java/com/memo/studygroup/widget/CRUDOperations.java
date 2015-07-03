package com.memo.studygroup.widget;

import com.memo.studygroup.vo.MemoVO;

import java.util.List;

/**
 * Created by coupang on 2015. 6. 25..
 */
public interface CRUDOperations {
    void insert(MemoVO memoVO);
    void update(MemoVO memoVO);
    void delete(MemoVO memoVo);
    MemoVO read(int id);
    List<MemoVO> readAll();
}
