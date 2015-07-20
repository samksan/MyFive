package com.example.administrator.myfive;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainActivity extends ActionBarActivity {

    //定义 UI 变量
    private TextView tv;
    Button getData; //获取数据
    Button showData;//显示数据
    Button addData; //添加一条数据


    private Spinner s1,s2,s3,s4,s5;
    Integer[] dataSource = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();       //绑定 UI 变量 到 ID

        s1.setAdapter(new ArrayAdapter<Integer>(this,android.R.layout.simple_expandable_list_item_1,dataSource));
        s2.setAdapter(new ArrayAdapter<Integer>(this,android.R.layout.simple_expandable_list_item_1,dataSource));
        s3.setAdapter(new ArrayAdapter<Integer>(this,android.R.layout.simple_expandable_list_item_1,dataSource));
        s4.setAdapter(new ArrayAdapter<Integer>(this,android.R.layout.simple_expandable_list_item_1,dataSource));
        s5.setAdapter(new ArrayAdapter<Integer>(this,android.R.layout.simple_expandable_list_item_1,dataSource));

        //获取数据 监听事件
        getData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //从网络获取开奖数据并存入数据库
                MyAsyncTask myAsyncTask = new MyAsyncTask();
                myAsyncTask.execute("http://chart.cp.360.cn/zst/ln11?span=100");
            }
        });

        //显示开奖数据的按钮监听事件
        showData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ShowData.class);
                startActivity(intent);
            }
        });

    }

    private void initUI() {
        getData = (Button) findViewById(R.id.getData);
        showData = (Button) findViewById(R.id.showData);
        addData = (Button) findViewById(R.id.addData);
        tv = (TextView) findViewById(R.id.tv);

        s1 = (Spinner) findViewById(R.id.spinner1);
        s2 = (Spinner) findViewById(R.id.spinner2);
        s3 = (Spinner) findViewById(R.id.spinner3);
        s4 = (Spinner) findViewById(R.id.spinner4);
        s5 = (Spinner) findViewById(R.id.spinner5);
    }

    public class MyAsyncTask extends AsyncTask<String,Void,String>{
        @Override
        protected String doInBackground(String... params) {
            try {
                //利用 url地址 创建 URL
                URL url = new URL(params[0]);

                //连接 url
                URLConnection urlConnection = url.openConnection();
                urlConnection.setConnectTimeout(1000 * 10);

                //获取输入流
                InputStream is = urlConnection.getInputStream();

                //输入流读取原网页内容
                InputStreamReader isr = new InputStreamReader(is,"GBK");

                //根据输入流读取数据
                BufferedReader br = new BufferedReader(isr);

                //临时字符串
                String line;

                //字符串存储对象
//                StringBuilder builder = new StringBuilder();

                //数据库连接并删除表中的数据
                new Delete().from(Lotnum.class).execute();

                //把网页内容存储到 字符串存储实例
                while ((line = br.readLine()) != null) {
                    //20150715-05</td><td class='tdbdr'></td><td class='tdbg_1' ><strong class='num'>01 05 04 08 09
                    //8 1 2 68 2 1 2 1 2 1 2 1 2 = 93 //总字符数
                    //8   2    2   2   2   2   2        //需要的字符数
                    //0-7 9-10  79-80  82-83  85-86  88-89   91-92
                    //创建 Pattern 实例 并用该实例创建 Matcher 实例
                    String patternString = "\\d{8}-\\d{2}.{68}\\d{2}.\\d{2}.\\d{2}.\\d{2}.\\d{2}";
                    Pattern p = Pattern.compile(patternString);
                    Matcher m = p.matcher(line);

                    //模糊匹配 68
                    while (m.find()) {
                        //临时字符串
                        String allStr,qh;
                        int i1,i2,i3,i4,i5;

                        //取得匹配的字符串
                        allStr = m.group();

                        //字符串截取赋值
                        qh = allStr.substring(4, 8) + allStr.substring(9, 11);
                        i1 = Integer.parseInt(allStr.substring(79, 81));
                        i2 = Integer.parseInt(allStr.substring(82, 84));
                        i3 = Integer.parseInt(allStr.substring(85, 87));
                        i4 = Integer.parseInt(allStr.substring(88, 90));
                        i5 = Integer.parseInt(allStr.substring(91));

                        int[] intDesc = {i1, i2, i3, i4, i5};
                        Arrays.sort(intDesc);

                        Lotnum lotnum = new Lotnum();
                        lotnum.qh = qh;
                        lotnum.num1 = intDesc[0];
                        lotnum.num2 = intDesc[1];
                        lotnum.num3 = intDesc[2];
                        lotnum.num4 = intDesc[3];
                        lotnum.num5 = intDesc[4];
                        lotnum.save();
                    }
                }

                //关闭流
                br.close();
                isr.close();
                is.close();

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
            //当 DoInBackground 执行完成后执行
            Toast.makeText(MainActivity.this, "执行完成，可以更新 UI 了！！！！！", Toast.LENGTH_LONG).show();
            super.onPostExecute(s);
            tv.setText(s);
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
    }



}
