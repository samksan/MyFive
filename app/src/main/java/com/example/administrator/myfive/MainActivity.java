package com.example.administrator.myfive;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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

    public void getData(View view) {
        //从网络获取开奖数据并存入数据库
        readURL("http://chart.cp.360.cn/zst/ln11?span=100");




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
                    StringBuilder builder = new StringBuilder();

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
                            String allStr,qh,s1,s2,s3,s4,s5;

                            //取得匹配的字符串
                            allStr = m.group();

                            //字符串截取赋值
                            qh = allStr.substring(0, 8) + allStr.substring(9, 11);
                            s1 = allStr.substring(79, 81);
                            s2 = allStr.substring(82, 84);
                            s3 = allStr.substring(85, 87);
                            s4 = allStr.substring(88, 90);
                            s5 = allStr.substring(91);
//                            allStr = qh + " " + s1 + " " + s2 + " " + s3 + " " + s4 + " " + s5;

                            builder.append(qh + " " + s1 + " " + s2 + " " + s3 + " " + s4 + " " + s5 + "\n");

                        }

                    }

                    //关闭流
                    br.close();
                    isr.close();
                    is.close();

                    //返回字符串
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
                //当 DoInBackground 执行完成后执行

                //正则表达式 处理数据

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
