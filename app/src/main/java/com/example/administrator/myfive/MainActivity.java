package com.example.administrator.myfive;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


public class MainActivity extends ActionBarActivity {
    private TextView tv;

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
        readURL("http://chart.cp.360.cn/zst/p3?span=30");




    }

    public void addData(View view) {
        //手动添加一条数据到数据库里





    }

    public void showData(View view) {
        //从数据库中读入数据，显示数据



    }

    /**
     * 从网络读取数据
     * @param url 网址
     */
    public void readURL(String url){
        new AsyncTask<String,Void,String>(){

            @Override
            protected String doInBackground(String... params) {
                try {
                    URL url = new URL(params[0]);

                    URLConnection urlConnection = url.openConnection();
                    urlConnection.setConnectTimeout(1000 * 10);

                    InputStream is = urlConnection.getInputStream();

                    InputStreamReader isr = new InputStreamReader(is,"GBK");
                    BufferedReader br = new BufferedReader(isr);

                    String line;
                    StringBuilder builder = new StringBuilder();

                    while ((line = br.readLine()) != null) {
                        builder.append(line);
                    }

                    br.close();
                    isr.close();
                    is.close();
                    return builder.toString();

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPreExecute() {
                Toast.makeText(MainActivity.this, "开始连接网络更新数据", Toast.LENGTH_SHORT).show();
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                tv = (TextView) findViewById(R.id.tv);
                tv.setText(s);
                super.onPostExecute(s);
            }

            @Override
            protected void onProgressUpdate(Void... values) {
                super.onProgressUpdate(values);
            }

            @Override
            protected void onCancelled(String s) {
                super.onCancelled(s);
            }

            @Override
            protected void onCancelled() {
                super.onCancelled();
            }
        }.execute(url);
    }
}
