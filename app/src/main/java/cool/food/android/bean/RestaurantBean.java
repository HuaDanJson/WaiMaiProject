package cool.food.android.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

import cn.bmob.v3.BmobObject;

@Entity
public class RestaurantBean extends BmobObject {

    @Id(autoincrement = true)
    private long creatTime;

    @Property(nameInDb = "RestaurantBean")
    private String name;
    private String avatar;
    private String address;
    private String phoneNumber;

    @Generated(hash = 1320861530)
    public RestaurantBean(long creatTime, String name, String avatar,
                          String address, String phoneNumber) {
        this.creatTime = creatTime;
        this.name = name;
        this.avatar = avatar;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    @Generated(hash = 1781710585)
    public RestaurantBean() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public long getCreatTime() {
        return this.creatTime;
    }

    public void setCreatTime(long creatTime) {
        this.creatTime = creatTime;
    }

    @Override
    public String toString() {
        return "RestaurantBean{" +
                "creatTime=" + creatTime +
                ", name='" + name + '\'' +
                ", avatar='" + avatar + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
