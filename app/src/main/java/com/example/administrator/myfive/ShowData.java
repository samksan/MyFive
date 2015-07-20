package com.example.administrator.myfive;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.activeandroid.query.Select;

import java.util.List;

/**
 * 显示开奖号码的Activity
 */
public class ShowData extends ActionBarActivity {
    ListView lv_showdata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);

        lv_showdata = (ListView) findViewById(R.id.lv_showData);

        List<Lotnum> mLists = new Select().from(Lotnum.class).execute();

        ShowdataBaseAdatper showdataBaseAdatper = new ShowdataBaseAdatper(this, mLists);

        lv_showdata.setAdapter(showdataBaseAdatper);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_data, menu);
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
}
