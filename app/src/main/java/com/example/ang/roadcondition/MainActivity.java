package com.example.ang.roadcondition;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	private Button uploadConditionButton;
	private Button collectionButton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
			
		setContentView(R.layout.main);
		
		uploadConditionButton = (Button) findViewById(R.id.button_upload);
		
		uploadConditionButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, UploadCondition.class); 
				startActivity(intent);
			}
			
		});
		
		collectionButton = (Button) findViewById(R.id.button_myCollections);
		
		collectionButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent();
                intent.setClass(MainActivity.this, CollectActivity.class);
		        startActivity(intent);
			}
			
		});
		 
	}

}
