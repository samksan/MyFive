package com.example.administrator.myfive;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2015/7/20.
 */
public class ShowdataBaseAdatper extends BaseAdapter{

    private List<Lotnum> mList;
    private LayoutInflater mInflater;

    /**
     * 构造函数：初始化传递过来的数据，根据 LayoutInflater 初始化 ListView 布局视图
     * @param context
     * @param list      传进来的泛型集合(Lotnum)数据库查询结果集合(ActiveAndroid)
     */
    public ShowdataBaseAdatper(Context context, List<Lotnum> list) {
        mList = list;
        mInflater = LayoutInflater.from(context);
    }

    /**
     * How many items are in the data set represented by this Adapter.
     *
     * @return Count of items.
     */
    @Override
    public int getCount() {
        return mList.size();
    }

    /**
     * Get the data item associated with the specified position in the data set.
     *
     * @param position Position of the item whose data we want within the adapter's
     *                 data set.
     * @return The data at the specified position.
     */
    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    /**
     * Get the row id associated with the specified position in the list.
     *
     * @param position The position of the item within the adapter's data set whose row id we want.
     * @return The id of the item at the specified position.
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Get a View that displays the data at the specified position in the data set. You can either
     * create a View manually or inflate it from an XML layout file. When the View is inflated, the
     * parent View (GridView, ListView...) will apply default layout parameters unless you use
     * {@link LayoutInflater#inflate(int, ViewGroup, boolean)}
     * to specify a root view and to prevent attachment to the root.
     *
     * @param position    The position of the item within the adapter's data set of the item whose view
     *                    we want.
     * @param convertView The old view to reuse, if possible. Note: You should check that this view
     *                    is non-null and of an appropriate type before using. If it is not possible to convert
     *                    this view to display the correct data, this method can create a new view.
     *                    Heterogeneous lists can specify their number of view types, so that this View is
     *                    always of the right type (see {@link #getViewTypeCount()} and
     *                    {@link #getItemViewType(int)}).
     * @param parent      The parent that this view will eventually be attached to
     * @return A View corresponding to the data at the specified position.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        //逗比式：最简单，没有任何的优化
//        //1 定义并找到 view (mInflater.inflate)
//        View view = mInflater.inflate(R.layout.item_show_data,null);
//
//        //2 通过找到的 视图 找到视图中的 控件
//        TextView tv_lotnum = (TextView) view.findViewById(R.id.tv_lotnum);
//        TextView tv_num1 = (TextView) view.findViewById(R.id.tv_num1);
//        TextView tv_num2 = (TextView) view.findViewById(R.id.tv_num2);
//        TextView tv_num3 = (TextView) view.findViewById(R.id.tv_num3);
//        TextView tv_num4 = (TextView) view.findViewById(R.id.tv_num4);
//        TextView tv_num5 = (TextView) view.findViewById(R.id.tv_num5);
//
//        //3 得到一组数据的集合 (通过构造函数传进来的数据)
//        Lotnum now_lotnum = mList.get(position);
//
//        //4 把集合中的数据分别赋值给相应的控件
//        tv_lotnum.setText(now_lotnum.qh);
//        tv_num1.setText(String.valueOf(now_lotnum.num1));
//        tv_num2.setText(String.valueOf(now_lotnum.num2));
//        tv_num3.setText(String.valueOf(now_lotnum.num3));
//        tv_num4.setText(String.valueOf(now_lotnum.num4));
//        tv_num5.setText(String.valueOf(now_lotnum.num5));
//
//        //5 返回结果
//        return view;
//        //逗比式


//        //普通式：利用了传入的参数：convertView 并判断是否创建了视图实例，利用了缓存的机制
//        //1 定义并找到 view (mInflater.inflate)
//        if (convertView == null) {
//            convertView = mInflater.inflate(R.layout.item_show_data, null);
//        }
//
//        //2 通过找到的 视图 找到视图中的 控件
//        TextView tv_lotnum = (TextView) convertView.findViewById(R.id.tv_lotnum);
//        TextView tv_num1 = (TextView) convertView.findViewById(R.id.tv_num1);
//        TextView tv_num2 = (TextView) convertView.findViewById(R.id.tv_num2);
//        TextView tv_num3 = (TextView) convertView.findViewById(R.id.tv_num3);
//        TextView tv_num4 = (TextView) convertView.findViewById(R.id.tv_num4);
//        TextView tv_num5 = (TextView) convertView.findViewById(R.id.tv_num5);
//
//        //3 得到一组数据的集合 (通过构造函数传进来的数据)
//        Lotnum now_lotnum = mList.get(position);
//
//        //4 把集合中的数据分别赋值给相应的控件
//        tv_lotnum.setText(now_lotnum.qh);
//        tv_num1.setText(String.valueOf(now_lotnum.num1));
//        tv_num2.setText(String.valueOf(now_lotnum.num2));
//        tv_num3.setText(String.valueOf(now_lotnum.num3));
//        tv_num4.setText(String.valueOf(now_lotnum.num4));
//        tv_num5.setText(String.valueOf(now_lotnum.num5));
//
//        //5 返回结果
//        return convertView;
//        //普通式


        //文艺式：优化 findViewById
        ViewHolder viewHolder;

        //1 定义并找到 view (mInflater.inflate)
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item_show_data, null);
            //2 通过找到的 视图 找到视图中的 控件
            viewHolder.tv_lotnum = (TextView) convertView.findViewById(R.id.tv_lotnum);
            viewHolder.tv_num1 = (TextView) convertView.findViewById(R.id.tv_num1);
            viewHolder.tv_num2 = (TextView) convertView.findViewById(R.id.tv_num2);
            viewHolder.tv_num3 = (TextView) convertView.findViewById(R.id.tv_num3);
            viewHolder.tv_num4 = (TextView) convertView.findViewById(R.id.tv_num4);
            viewHolder.tv_num5 = (TextView) convertView.findViewById(R.id.tv_num5);

            //关联 convertView == viewHolder
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        //3 得到一组数据的集合 (通过构造函数传进来的数据)
        Lotnum now_lotnum = mList.get(position);

        //4 把集合中的数据分别赋值给相应的控件

        viewHolder.tv_lotnum.setText(now_lotnum.qh);
        viewHolder.tv_num1.setText(String.valueOf(now_lotnum.num1));
        viewHolder.tv_num2.setText(String.valueOf(now_lotnum.num2));
        viewHolder.tv_num3.setText(String.valueOf(now_lotnum.num3));
        viewHolder.tv_num4.setText(String.valueOf(now_lotnum.num4));
        viewHolder.tv_num5.setText(String.valueOf(now_lotnum.num5));

        //5 返回结果
        return convertView;
        //文艺式


    }

    class ViewHolder{
        public TextView tv_lotnum;
        public TextView tv_num1;
        public TextView tv_num2;
        public TextView tv_num3;
        public TextView tv_num4;
        public TextView tv_num5;
    }
}
