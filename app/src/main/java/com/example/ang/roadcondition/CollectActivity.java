package com.example.roadcondition;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Collections;


public class CollectActivity extends Activity implements CollectFragment.OnFragmentInteractionListener{
    private static final String COLLECTS = "collects";
    public static SharedPreferences savedRoute;
    private ArrayList<String> collects;

    public void sendToShowDetail(String collect,String infor){
        getFragmentManager().beginTransaction().
                replace(R.id.fragment_holder, ShowDetailFragment.newInstance(collect,infor))
                .addToBackStack(null)
                .commit();
    }

    public void deleteCollect(final String collect){
        AlertDialog.Builder confirmBuilder = new AlertDialog.Builder(this);

        confirmBuilder.setMessage(
                getString(R.string.confirmMessage, collect));

        confirmBuilder.setNegativeButton( getString(R.string.cancel),
                new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        dialog.cancel();
                    }
                }
        );

        confirmBuilder.setPositiveButton(getString(R.string.OK),
                new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        collects.remove(collect);

                        // get SharedPreferences.Editor to remove saved search
                        SharedPreferences.Editor preferencesEditor =
                                savedRoute.edit();
                        preferencesEditor.remove(collect); // remove search
                        preferencesEditor.apply(); // saves the changes

                        Bundle bundle = new Bundle();
                        bundle.putStringArrayList("collects",collects);
                        CollectFragment cf = new CollectFragment();
                        cf.setArguments(bundle);
                        getFragmentManager().beginTransaction()
                                .replace(R.id.fragment_holder, cf)
                                .commit();
                    }
                });
        confirmBuilder.create().show(); // display AlertDialog
    }

    public void onBackPressed(){
        FragmentManager fm = getFragmentManager();
        if(fm.getBackStackEntryCount()>0){
            fm.popBackStack();
        }else{
            super.onBackPressed();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);

        savedRoute = getSharedPreferences(COLLECTS, MODE_PRIVATE);

        SharedPreferences.Editor editor = savedRoute.edit();
        editor.putString("路线1", "西直门=北交大=西直门-北交大=路况信息良好");
        editor.putString("路线2", "西直门=北交大=西直门-交大东路-北交大=交大东路两车相撞，路况较差");
        editor.commit();

        collects = new ArrayList<String>(savedRoute.getAll().keySet());
        Collections.sort(collects, String.CASE_INSENSITIVE_ORDER);

        Bundle bundle = new Bundle();
        bundle.putStringArrayList("collects",collects);
        CollectFragment cf = new CollectFragment();
        cf.setArguments(bundle);
        getFragmentManager().beginTransaction()
                .add(R.id.fragment_holder,cf)
                .commit();
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
