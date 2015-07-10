package com.memo.studygroup.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.memo.studygroup.R;
import com.memo.studygroup.util.CollectionUtil;
import com.memo.studygroup.vo.MemoVO;

import java.util.List;

public class MemoListAdapter extends BaseAdapter {

	private final LayoutInflater inflater;
	private List<MemoVO> itemList;

	public MemoListAdapter(Context context, List<MemoVO> memoVOList) {
		this.itemList = memoVOList;
		this.inflater = LayoutInflater.from(context);
	}

	/**
	 * @return 리스트뷰 아이템 갯수 몇개를 노출 할지 결정
	 */
	@Override
	public int getCount() {
		return (itemList.size() > 0) ? itemList.size() : 0;
	}

	/**
	 * @param position 리스트뷰 아이템의 위치
	 * @return 인자값으로 받은 리스트뷰의 아이템을 Object로 반환
	 */
	@Override
	public Object getItem(int position) {
		return itemList.get(position);
	}

	/**
	 * @param position 리스트뷰 아이템의 위치
	 * @return 인자값으로 받은 리스트뷰의 아이템의 ID를 반환
	 */
	@Override
	public long getItemId(int position) {
		return itemList.get(position).hashCode();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View view;
		ViewHolder holder;

		if (convertView == null) {
			view = inflater.inflate(R.layout.item_memo_list, parent, false);
			holder = new ViewHolder();
			holder.idTextView = (TextView) view.findViewById(R.id.item_id_textView);
			holder.memoTextView = (TextView) view.findViewById(R.id.item_memo_textView);
			holder.regDateTextView = (TextView) view.findViewById(R.id.item_regdate_textView);
			view.setTag(holder);
		} else {
			view = convertView;
			holder = (ViewHolder) view.getTag();
		}

		if (CollectionUtil.isNotEmpty(itemList)) {
			holder.idTextView.setText(String.valueOf(itemList.get(position).getId()));
			holder.memoTextView.setText(itemList.get(position).getMemo());
			holder.regDateTextView.setText(itemList.get(position).getRegdate());
		}

		return view;
	}

	class ViewHolder {
		public TextView idTextView;
		public TextView memoTextView;
		public TextView regDateTextView;
	}
}
