package cool.food.android.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

import cn.bmob.v3.BmobObject;

@Entity
public class RestaurantBean extends BmobObject {

    @Id(autoincrement = true)
    private Long id;

    @Property(nameInDb = "RestaurantBean")
    private String name;
    private String avatar;
    private String address;
    private String phoneNumber;

    @Generated(hash = 1344363489)
    public RestaurantBean(Long id, String name, String avatar, String address,
                          String phoneNumber) {
        this.id = id;
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

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "RestaurantBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", avatar='" + avatar + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
