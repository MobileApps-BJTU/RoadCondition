package com.example.ang.roadcondition;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class RouteInfoActivity extends ActionBarActivity {
    private Button button;
    private ListView listView;
    public static SharedPreferences savedRoute;
    private static final String COLLECTS = "collects";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_info);

        ListView InfoList=(ListView)findViewById(R.id.listView);

        ArrayList<String> listData = new ArrayList<String>();
        Resources res =getResources();
        // 取xml文件格式的字符数组
        final String[] route_info=res.getStringArray(R.array.route_info);
        listData.add(route_info[0]);
        listData.add(route_info[1]);

        listData.add(route_info[2]);
        listData.add("                 "+route_info[3]);
        listData.add("                 "+route_info[4]);
        listData.add("                 "+route_info[5]);

        listData.add(route_info[6]);
        listData.add(route_info[7]);

        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(
                this, R.layout.route_item, R.id.itemText, listData );
        InfoList.setAdapter(listAdapter);


        this.button = (Button) this.findViewById(R.id.button_collect);
        this.button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                Bundle bundle = intent.getBundleExtra("bundle");
                String start = bundle.getString("start");
                String end = bundle.getString("end");

                savedRoute = getSharedPreferences(COLLECTS, MODE_PRIVATE);
                SharedPreferences.Editor preferencesEditor = savedRoute.edit();
                preferencesEditor.putString(start+"-"+end, start+"="+end+"="+route_info[4]+"-"+route_info[5]+"="+route_info[7]); // store current search
                preferencesEditor.apply(); // store the updated preferences

                intent = new Intent();

                intent.setClass(RouteInfoActivity.this, CollectActivity.class);
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
