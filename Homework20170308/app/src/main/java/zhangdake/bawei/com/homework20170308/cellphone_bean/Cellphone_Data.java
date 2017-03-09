package zhangdake.bawei.com.homework20170308.cellphone_bean;

import java.util.ArrayList;

/**
 * Created by Zhangdake on 2017/3/8.
 */
public class Cellphone_Data {

    private String title;
    private String title_id;
    private boolean group_check = false;
    private ArrayList<Cellphone_Num> datas;

    public boolean isGroup_check() {
        return group_check;
    }

    public void setGroup_check(boolean group_check) {
        this.group_check = group_check;
    }

    public ArrayList<Cellphone_Num> getDatas() {
        return datas;
    }

    public void setDatas(ArrayList<Cellphone_Num> datas) {
        this.datas = datas;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle_id() {
        return title_id;
    }

    public void setTitle_id(String title_id) {
        this.title_id = title_id;
    }
}
