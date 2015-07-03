package com.memo.studygroup.activity;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.memo.studygroup.Intent.IntentHandler;
import com.memo.studygroup.R;
import com.memo.studygroup.adapter.MemoListAdapter;
import com.memo.studygroup.vo.MemoVO;
import com.memo.studygroup.widget.DataBaseHandler;

public class MemoListActivity extends Activity {

    private DataBaseHandler dbHandler;

    private ListView listView ;
    private Button memoWirteButton;

    private List<MemoVO> memoList;
    private MemoListAdapter memoAdapter;

    public MemoListActivity() {
        this.dbHandler = new DataBaseHandler(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_list);

        initializationData();
        initializationView();
        initializationEvent();

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        initializationData();
        memoAdapter.notifyDataSetChanged();
    }

    private void initializationData() {
        List<MemoVO> dbMemoList = dbHandler.readAll();
        this.memoList = dbMemoList;
    }

    private void initializationView() {
        listView = (ListView) findViewById(R.id.list);
        memoWirteButton = (Button)findViewById(R.id.listwrite);

        if(memoList.size() != 0) {
            memoAdapter = new MemoListAdapter(this, memoList);
            listView.setAdapter(memoAdapter);
        }
    }

    private void initializationEvent() {
        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                IntentHandler.moveToMemoActivity(MemoListActivity.this, memoList.get(position));
            }
        });

        memoWirteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentHandler.moveToMemoActivity(MemoListActivity.this);
            }
        });
    }

}
