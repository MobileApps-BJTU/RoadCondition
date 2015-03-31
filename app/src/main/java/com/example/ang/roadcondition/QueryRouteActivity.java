package com.example.ang.roadcondition;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class QueryRouteActivity extends ActionBarActivity {

    private static final String[] city={"Beijing","Shanghai","Guangzhou","DaLian","Hongkong"};
    private Button button_search;
    private Button button_clear;
    private Spinner spinner_city;

    private ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_route);

        final EditText edit_start=(EditText)findViewById(R.id.edit_start);
        final EditText edit_end=(EditText)findViewById(R.id.edit_end);
        spinner_city = (Spinner) findViewById(R.id.spinner_city);
        //将可选内容与ArrayAdapter连接起来
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,city);

        //设置下拉列表的风格
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //将adapter 添加到spinner中
        spinner_city.setAdapter(adapter);

        //添加事件Spinner事件监听
        //spinner.setOnItemSelectedListener(new SpinnerSelectedListener());

        //设置默认值
        //spinner_city.setVisibility(View.VISIBLE);



        this.button_search = (Button) this.findViewById(R.id.button_search);
        this.button_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

           //     Toast.makeText(QueryRouteActivity.this, textView_start.getText().toString(), Toast.LENGTH_SHORT).show();
                if (edit_start.getText().length() > 0 &&
                        edit_end.getText().length() > 0)
                {
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putString("start", edit_start.getText().toString());
                    bundle.putString("end",edit_end.getText().toString() );
                    intent.putExtra("bundle", bundle);

                    intent.setClass(QueryRouteActivity.this, Routes_listActivity.class);
                    startActivity(intent);
                    finish();
                }
                else // display message asking user to provide a query and a tag
                {
                    // create a new AlertDialog Builder
                    AlertDialog.Builder builder =
                            new AlertDialog.Builder(QueryRouteActivity.this);

                    // set dialog's message to display
                    builder.setMessage(R.string.missingMessage);

                    // provide an OK button that simply dismisses the dialog
                    builder.setPositiveButton(R.string.OK, null);

                    // create AlertDialog from the AlertDialog.Builder
                    AlertDialog errorDialog = builder.create();
                    errorDialog.show(); // display the modal dialog
                }
            }
        });
        this.button_clear = (Button) this.findViewById(R.id.button_clear);
        this.button_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //     Toast.makeText(QueryRouteActivity.this, textView_start.getText().toString(), Toast.LENGTH_SHORT).show();

                edit_start.setText("");
                edit_end.setText("");

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
