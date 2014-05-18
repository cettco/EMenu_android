package com.emenu.app.adapter;

import java.util.List;

import org.json.JSONArray;

import cn.trinea.android.common.service.impl.ImageCache;
import cn.trinea.android.common.util.CacheManager;

import com.emenu.app.Data;
import com.emenu.app.R;
import com.emenu.app.entities.MenuItemEntity;
import com.emenu.app.utils.HttpConnection;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MenuListItemAdapter extends ArrayAdapter<MenuItemEntity> {
	private List<MenuItemEntity> list;
	private Context context;
	private Button addToCartButton;

	public MenuListItemAdapter(Context context, int resource,
			List<MenuItemEntity> objects) {
		super(context, resource, objects);
		// TODO Auto-generated constructor stub
		this.list = objects;
		this.context = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		MenuItemEntity item =list.get(position);
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.menu_list_item, parent,
				false);

		ImageView imageView = (ImageView) rowView.findViewById(R.id.itemPic);
		final TextView itemTitleView = (TextView) rowView.findViewById(R.id.itemTitle);
		TextView itemTextView = (TextView) rowView.findViewById(R.id.itemText);
		addToCartButton = (Button)rowView.findViewById(R.id.itemAddToCartBtn);
		//imageView.setImageResource(R.drawable.pic1);
/*		String url = "http://www.ezhi.net/api/test/index.php";
		HttpConnection.get(url, null, new AsyncHttpResponseHandler() {
		    @Override
		    public void onSuccess(String response) {
		        System.out.println("response:"+response);
		        itemTitleView.setText(response);
		    }
		});*/
		//---Data.IMAGE_CACHE.get("http://d.hiphotos.baidu.com/image/pic/item/9d82d158ccbf6c81db35544dbe3eb13533fa4010.jpg", imageView);
		Data.IMAGE_CACHE.get(item.getItemPicUrl(), imageView);
		//itemTitleView.setText(item.getItemTitle());
		itemTextView.setText(item.getItemText());
		itemTitleView.setText(item.getItemTitle());
		addToCartButton.setOnClickListener(new lvButtonListener(position));

		return rowView;
	}
	
	class lvButtonListener implements OnClickListener{
		private int position;
		
		public lvButtonListener(int pos) {
			// TODO Auto-generated constructor stub
			position = pos;
		}

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			MenuItemEntity item =list.get(position);
			Log.i("cate", "id:"+item.getItemID());
			int vid = v.getId();
			if(vid == addToCartButton.getId()){
				Log.i("cate", "Clicked"+vid);
				Log.i("cate", "postion"+position);
			}
		}
		
		
	}

}
