package zhangdake.bawei.com.homework20170308.many_adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

import zhangdake.bawei.com.homework20170308.R;
import zhangdake.bawei.com.homework20170308.cellphone_bean.Cellphone_Data;
import zhangdake.bawei.com.homework20170308.cellphone_bean.Cellphone_Num;
import zhangdake.bawei.com.homework20170308.inter_face.Adapter_to_Activity;

/**
 * Created by Zhangdake on 2017/3/8.
 */
public class MyAdapter extends BaseExpandableListAdapter{

    private Adapter_to_Activity ata;
    private ArrayList<Cellphone_Data> list;
    private Context context;

    public MyAdapter(Context context, ArrayList<Cellphone_Data> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getGroupCount() {
        return list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return list.get(groupPosition).getDatas().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return list.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return list.get(groupPosition).getDatas().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        Group_ViewHolder holder = null;

        if(convertView==null){

            convertView = View.inflate(context, R.layout.group_item, null);
            TextView tv = (TextView) convertView.findViewById(R.id.tv_group);
            CheckBox cb = (CheckBox) convertView.findViewById(R.id.cb_group);
            holder = new Group_ViewHolder();
            holder.tv = tv;
            holder.cb_group = cb;
            cb.setFocusable(false);
            convertView.setTag(holder);

        }else{
            holder = (Group_ViewHolder) convertView.getTag();
        }

        holder.tv.setText(list.get(groupPosition).getTitle());
        holder.cb_group.setChecked(list.get(groupPosition).isGroup_check());
        holder.cb_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Cellphone_Num> temp_list = list.get(groupPosition).getDatas();
                boolean temp_k =!list.get(groupPosition).isGroup_check();
                list.get(groupPosition).setGroup_check(temp_k);
                for(int i = 0 ; i<temp_list.size() ; i++){
                    temp_list.get(i).setCheck(temp_k);
                }
                send_activity();
            }
        });

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        Child_ViewHolder holder = null;

        if(convertView == null){

            convertView = View.inflate(context, R.layout.child_item, null);
            TextView tv_tv_type_name = (TextView) convertView.findViewById(R.id.tv_type_name);
            TextView tv_msg = (TextView) convertView.findViewById(R.id.tv_msg);
            TextView tv_price = (TextView) convertView.findViewById(R.id.tv_price);
            TextView tv_add_time = (TextView) convertView.findViewById(R.id.tv_add_time);
            CheckBox cb_check = (CheckBox) convertView.findViewById(R.id.cb_child);
            holder = new Child_ViewHolder();
            holder.cb_check = cb_check;
            holder.tv_add_time = tv_add_time;
            holder.tv_msg = tv_msg;
            holder.tv_price = tv_price;
            holder.tv_type_name = tv_tv_type_name;
            convertView.setTag(holder);

        }else{
            holder = (Child_ViewHolder) convertView.getTag();
        }

        holder.setMessage(list.get(groupPosition).getDatas().get(childPosition), groupPosition);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    private class Group_ViewHolder{
        TextView tv;
        CheckBox cb_group;
    }

    private class Child_ViewHolder{
        TextView tv_type_name;
        TextView tv_msg;
        TextView tv_add_time;
        TextView tv_price;
        CheckBox cb_check;

        //给条目的控件显示内容
        public void setMessage(final Cellphone_Num num, final int group){

            tv_type_name.setText(num.getType_name());
            tv_msg.setText(num.getMsg());
            tv_price.setText("$"+num.getPrice());
            tv_add_time.setText(num.getAdd_time());
            cb_check.setChecked(num.isCheck());

            cb_check.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean temp_k = cb_check.isChecked();
                    num.setCheck(temp_k);
                    checkedItem(temp_k, group);
                    send_activity();
                }
            });
            
        }

    }

    //获得接口的方法
    public void send_total(Adapter_to_Activity ata){
        this.ata = ata;
    }

    //计算选中的手机数和总价格
    private String check_num_price(){

        int total_num = 0;
        int total_price = 0;

        for(int i = 0 ; i<list.size() ; i++){
            for(int k = 0 ; k<list.get(i).getDatas().size() ; k++){
                Cellphone_Num cn = list.get(i).getDatas().get(k);
                if(cn.isCheck()){
                    total_num++;
                    total_price += cn.getPrice();
                }
            }
        }

        return total_num+"台旧机  合计："+total_price+"元";
    }

    //将处理好的信息传递给activity显示
    private void send_activity(){
        if(ata!=null){
            String total = check_num_price();
            ata.setText(total);
            MyAdapter.this.notifyDataSetChanged();
        }
    }

    //当子条目全部选中时选中其父条目 当子条目未都选中时 父条目不会被选中
    private void checkedItem(boolean temp_k, int group){
        if(!temp_k){
            list.get(group).setGroup_check(temp_k);
        }else{
            boolean select_all = true;
            ArrayList<Cellphone_Num> temp_list = list.get(group).getDatas();
            for(int i = 0 ; i<temp_list.size() ; i++){
                if(!temp_list.get(i).isCheck()){
                    select_all = false;
                    break;
                }
            }
            if(select_all){
                list.get(group).setGroup_check(select_all);
            }
        }
    }

}
