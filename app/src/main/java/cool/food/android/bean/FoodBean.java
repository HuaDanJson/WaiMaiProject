package cool.food.android.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

import cn.bmob.v3.BmobObject;

@Entity
public class FoodBean extends BmobObject {

    @Id(autoincrement = true)
    private long creatTime;

    @Property(nameInDb = "FoodBean")
    private String restaurantId;
    private String foodName;
    private String foodPrice;
    private String foodAvatar;

    @Generated(hash = 948189695)
    public FoodBean(long creatTime, String restaurantId, String foodName,
                    String foodPrice, String foodAvatar) {
        this.creatTime = creatTime;
        this.restaurantId = restaurantId;
        this.foodName = foodName;
        this.foodPrice = foodPrice;
        this.foodAvatar = foodAvatar;
    }

    @Generated(hash = 895705851)
    public FoodBean() {
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(String foodPrice) {
        this.foodPrice = foodPrice;
    }

    public String getFoodAvatar() {
        return foodAvatar;
    }

    public void setFoodAvatar(String foodAvatar) {
        this.foodAvatar = foodAvatar;
    }

    public long getCreatTime() {
        return this.creatTime;
    }

    public void setCreatTime(long creatTime) {
        this.creatTime = creatTime;
    }

    @Override
    public String toString() {
        return "FoodBean{" +
                "creatTime=" + creatTime +
                ", restaurantId='" + restaurantId + '\'' +
                ", foodName='" + foodName + '\'' +
                ", foodPrice='" + foodPrice + '\'' +
                ", foodAvatar='" + foodAvatar + '\'' +
                '}';
    }
}
