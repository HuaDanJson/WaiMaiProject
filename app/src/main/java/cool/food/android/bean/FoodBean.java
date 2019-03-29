package cool.food.android.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

import cn.bmob.v3.BmobObject;

@Entity
public class FoodBean extends BmobObject {

    @Id(autoincrement = true)
    private Long id;

    @Property(nameInDb = "FoodBean")
    private String restaurantId;
    private String restaurantName;
    private String foodName;
    private String foodPrice;
    private String foodAvatar;

    private int buyCount;

    @Generated(hash = 175853776)
    public FoodBean(Long id, String restaurantId, String restaurantName, String foodName,
            String foodPrice, String foodAvatar, int buyCount) {
        this.id = id;
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.foodName = foodName;
        this.foodPrice = foodPrice;
        this.foodAvatar = foodAvatar;
        this.buyCount = buyCount;
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

    public int getBuyCount() {
        return this.buyCount;
    }

    public void setBuyCount(int buyCount) {
        this.buyCount = buyCount;
    }

    public String getRestaurantName() {
        return this.restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "FoodBean{" +
                "id=" + id +
                ", restaurantId='" + restaurantId + '\'' +
                ", restaurantName='" + restaurantName + '\'' +
                ", foodName='" + foodName + '\'' +
                ", foodPrice='" + foodPrice + '\'' +
                ", foodAvatar='" + foodAvatar + '\'' +
                ", buyCount=" + buyCount +
                '}';
    }
}
