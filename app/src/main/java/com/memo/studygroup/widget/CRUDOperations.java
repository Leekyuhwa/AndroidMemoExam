package com.memo.studygroup.widget;

import com.memo.studygroup.vo.MemoVO;

import java.util.List;

public interface CRUDOperations {
    void insert(MemoVO memoVO);
    void update(MemoVO memoVO);
    void delete(MemoVO memoVo);
    MemoVO read(int id);
    List<MemoVO> readAll();
}
