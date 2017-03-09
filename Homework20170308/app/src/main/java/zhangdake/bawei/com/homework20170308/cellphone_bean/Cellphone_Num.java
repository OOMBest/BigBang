package zhangdake.bawei.com.homework20170308.cellphone_bean;

/**
 * Created by Zhangdake on 2017/3/8.
 */
public class Cellphone_Num {

    private String add_time;
    private String cart_id;
    private String house_id;
    private String msg;
    private int price;
    private String type_name;
    private String type_sn_id;
    private boolean check = false;

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public String getAdd_time() {
        return add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }

    public String getCart_id() {
        return cart_id;
    }

    public void setCart_id(String cart_id) {
        this.cart_id = cart_id;
    }

    public String getHouse_id() {
        return house_id;
    }

    public void setHouse_id(String house_id) {
        this.house_id = house_id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public String getType_sn_id() {
        return type_sn_id;
    }

    public void setType_sn_id(String type_sn_id) {
        this.type_sn_id = type_sn_id;
    }
}
