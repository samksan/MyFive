package com.example.administrator.myfive;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


public class MainActivity extends ActionBarActivity {
    private Spinner s1,s2,s3,s4,s5;
    Integer[] dataSource = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        s1 = (Spinner) findViewById(R.id.spinner1);
        s2 = (Spinner) findViewById(R.id.spinner2);
        s3 = (Spinner) findViewById(R.id.spinner3);
        s4 = (Spinner) findViewById(R.id.spinner4);
        s5 = (Spinner) findViewById(R.id.spinner5);
        s1.setAdapter(new ArrayAdapter<Integer>(this,android.R.layout.simple_expandable_list_item_1,dataSource));
        s2.setAdapter(new ArrayAdapter<Integer>(this,android.R.layout.simple_expandable_list_item_1,dataSource));
        s3.setAdapter(new ArrayAdapter<Integer>(this,android.R.layout.simple_expandable_list_item_1,dataSource));
        s4.setAdapter(new ArrayAdapter<Integer>(this,android.R.layout.simple_expandable_list_item_1,dataSource));
        s5.setAdapter(new ArrayAdapter<Integer>(this,android.R.layout.simple_expandable_list_item_1,dataSource));



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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

    public void getData(View view) {
        //从网络获取开奖数据并存入数据库





    }

    public void addData(View view) {
        //手动添加一条数据到数据库里





    }

    public void showData(View view) {
        //从数据库中读入数据，显示数据



    }
}
