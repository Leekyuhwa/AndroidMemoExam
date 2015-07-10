package com.memo.studygroup.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.memo.studygroup.Intent.IntentHandler;
import com.memo.studygroup.R;
import com.memo.studygroup.util.DateUtil;
import com.memo.studygroup.vo.MemoVO;
import com.memo.studygroup.widget.DataBaseHandler;

public class MemoActivity extends Activity implements View.OnClickListener{

    private Button writeButton;
    private Button deleteButton;
    private Button saveButton;
    private Button loadButton;
    private EditText editMemoView;

    private MemoVO intentTrasformMemoVO;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        processIntent(getIntent());
        initializationEvent();

    }

    private void processIntent(Intent intent) {

        String intentMemo = intent.getStringExtra(IntentHandler.KEY_MEMO);

        if (intentMemo != null) {
            intentTrasformMemoVO = new MemoVO();
            intentTrasformMemoVO.setId(intent.getIntExtra(IntentHandler.KEY_ID, 0));
            intentTrasformMemoVO.setRegdate(intent.getStringExtra(IntentHandler.KEY_DATE));
            intentTrasformMemoVO.setMemo(intentMemo);
            initializationView(false);
        } else {
            initializationView(true);
        }
    }

    private void initializationView(Boolean viewCondition) {

        writeButton = (Button) findViewById(R.id.write);
        deleteButton = (Button) findViewById(R.id.delete);
        saveButton = (Button) findViewById(R.id.save);
        loadButton = (Button) findViewById(R.id.load);
        editMemoView = (EditText) findViewById(R.id.edit);

        if(viewCondition) {
            writeButton.setVisibility(View.VISIBLE);
            deleteButton.setVisibility(View.GONE);
            saveButton.setVisibility(View.GONE);
            loadButton.setVisibility(View.GONE);
        } else {
            writeButton.setVisibility(View.GONE);
            deleteButton.setVisibility(View.VISIBLE);
            saveButton.setVisibility(View.VISIBLE);
            loadButton.setVisibility(View.GONE);

            editMemoView.setText(intentTrasformMemoVO.getMemo());
        }

    }

    private void initializationEvent() {
        writeButton.setOnClickListener(this);
        deleteButton.setOnClickListener(this);
        saveButton.setOnClickListener(this);
        loadButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        int viewId = v.getId();

        switch (viewId) {
            case R.id.write:
                MemoVO memoVO = new MemoVO();
                memoVO.setMemo(editMemoView.getText().toString());
                memoVO.setRegdate(DateUtil.getToday());
                DataBaseHandler.getInstance(this).insert(memoVO);
                IntentHandler.meveToMemoListActivity(this);
                break;
            case R.id.delete:
                DataBaseHandler.getInstance(this).delete(intentTrasformMemoVO);
                IntentHandler.meveToMemoListActivity(this);
                break;
            case R.id.save:
                intentTrasformMemoVO.setMemo(editMemoView.getText().toString());
                intentTrasformMemoVO.setRegdate(DateUtil.getToday());
                DataBaseHandler.getInstance(this).update(intentTrasformMemoVO);
                IntentHandler.meveToMemoListActivity(this);
                break;
            case R.id.load:
                //없어도 되는 기능
                break;
            default:
                break;
        }
    }
}
