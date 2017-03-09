package zhangdake.bawei.com.homework20170308;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;

import zhangdake.bawei.com.homework20170308.Utils.HW_Utils;
import zhangdake.bawei.com.homework20170308.cellphone_bean.Cellphone;
import zhangdake.bawei.com.homework20170308.cellphone_bean.Cellphone_Data;
import zhangdake.bawei.com.homework20170308.inter_face.Adapter_to_Activity;
import zhangdake.bawei.com.homework20170308.many_adapters.MyAdapter;

public class MainActivity extends Activity {

    private Context context = MainActivity.this;
    private ExpandableListView ex_listview;
    private TextView tv_total;
    private ArrayList<Cellphone_Data> list;
    private final String http_url = "https://mock.eolinker.com/success/rq7m6GNqurs93zYkEANkY8Z4358Aihf1";
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Object k = msg.obj;

            if(k!=null){
                if(k instanceof String){
                    getList(k);
                }
            }

            super.handleMessage(msg);
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        getData();

    }

    private void initView(){
        ex_listview = (ExpandableListView) findViewById(R.id.ex_list_view);
        tv_total = (TextView) findViewById(R.id.tv_total);
    }

    //获取json串
    private void getData(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String temp = HW_Utils.getJson(http_url);
                Message message = Message.obtain();
                message.obj = temp;
                handler.sendMessage(message);
            }
        }).start();
    }

    //解析json获得其中的数据 并封装集合 装入适配器
    private void getList(Object k){
        String f = (String) k;
        Gson gson = new Gson();
        Cellphone cp = gson.fromJson(f, Cellphone.class);
        list = cp.getData();
        MyAdapter ma = new MyAdapter(context, list);

        ma.send_total(new Adapter_to_Activity() {
            @Override
            public void setText(String text) {
                tv_total.setText(text);
            }
        });

        ex_listview.setAdapter(ma);

    }

}
