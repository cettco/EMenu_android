package com.emenu.app.adapter;

import java.util.List;

import com.emenu.app.Data;
import com.emenu.app.R;
import com.emenu.app.entities.MenuItemEntity;
import com.emenu.app.entities.RestaurantItemEntity;
import com.emenu.app.utils.HttpConnection;
import com.loopj.android.http.AsyncHttpResponseHandler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class RestaurantListAdapter extends ArrayAdapter<RestaurantItemEntity> {

	private List<RestaurantItemEntity> list;
	private Context context;
	public RestaurantListAdapter(Context context, int resource,List<RestaurantItemEntity> objects) {
		super(context, resource, objects);
		// TODO Auto-generated constructor stub
		this.context = context;
		this.list = objects;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		//return super.getView(position, convertView, parent);
		
		RestaurantItemEntity item =list.get(position);
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.restaurant_list_item, parent,
				false);

		ImageView imageView = (ImageView) rowView.findViewById(R.id.restListItemPic);
		final TextView itemTitleView = (TextView) rowView.findViewById(R.id.restListItemTitle);
		itemTitleView.setText(item.getName());
		TextView itemTextView = (TextView) rowView.findViewById(R.id.restListItemText);
		
		//imageView.setImageResource(R.drawable.pic1);
		String url = item.getUrl();
		Data.IMAGE_CACHE.get(item.getUrl(), imageView);
		//itemTitleView.setText(item.getItemTitle());
		itemTextView.setText("地址："+item.getAddress());

		return rowView;
	}
	

}
