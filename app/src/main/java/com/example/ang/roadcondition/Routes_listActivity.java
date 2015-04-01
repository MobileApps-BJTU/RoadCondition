package com.example.ang.roadcondition;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Routes_listActivity extends Activity {

    private TextView textView_start;
    private TextView textView_end;
    private ListView listView_route;
    private MyAdapter listViewAdapter;
    private List<Map<String, Object>> mData;
    String start;
    String end;


    private List<Map<String, Object>> getListItems() {
        Resources res =getResources();
        // 取xml文件格式的字符数组
        String[] goodsNames=res.getStringArray(R.array.route_name);
        String[] goodsDetails=res.getStringArray(R.array.route_detail);
        List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
        for(int i = 0; i < goodsNames.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("route", goodsNames[i]);     //物品名称
            map.put("info", goodsDetails[i]); //物品详情
            listItems.add(map);
        }
        return listItems;
    }
    public class MyAdapter extends BaseAdapter {
        private Context context;
        private LayoutInflater mInflater;
        public List<Map<String, Object>> listItems;
        public final class ViewHolder {

            public TextView title;
            public TextView rank;

        }
        public MyAdapter(Context context, List<Map<String, Object>> listItems) {
            this.context = context;
            mInflater = LayoutInflater.from(context);

            this.listItems=listItems;

        }

        public int getCount() {
            // TODO Auto-generated method stub
            return mData.size();
        }

        public Object getItem(int arg0) {
            // TODO Auto-generated method stub
            return null;
        }

        public long getItemId(int arg0) {
            // TODO Auto-generated method stub
            return 0;
        }
        public View getView(int position, View convertView, ViewGroup parent) {



                ViewHolder holder = null;
                holder= new ViewHolder();

                convertView = mInflater.inflate(R.layout.routes_list_item,
                        null);

                holder.title = (TextView) convertView
                        .findViewById(R.id.item_route_Text);
                holder.rank = (TextView) convertView
                        .findViewById(R.id.item_info_Text);


                convertView.setTag(holder);

            holder.title.setText((String) listItems.get(position)
             .get("route"));
            holder.rank.setText((String) listItems.get(position)
            .get("info"));





            return convertView;
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routes_list);

        textView_start = (TextView)findViewById(R.id.start_textView);
        textView_end = (TextView)findViewById(R.id.end_textView);
        Intent intent = getIntent();


        Bundle bundle = intent.getBundleExtra("bundle");
        start = bundle.getString("start");
        end = bundle.getString("end");


        textView_start.setText(start);
        textView_end.setText(end);

        listView_route = (ListView) findViewById(R.id.listView);
        mData = getListItems();


        listViewAdapter = new MyAdapter(this, mData); //创建适配器
        listView_route.setAdapter(listViewAdapter);
        listView_route.setOnItemClickListener(new OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("start",start);
                bundle.putString("end",end);
                intent.putExtra("bundle", bundle);
                intent.setClass(Routes_listActivity.this, RouteInfoActivity.class);
                startActivity(intent);
                finish();
            }

        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
