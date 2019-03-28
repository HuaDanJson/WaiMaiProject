package cool.food.android.bean;

import cn.bmob.v3.BmobObject;

public class OrderBean extends BmobObject {

    private float income;
    private String buyerName;
    private String orderFood;

    public float getIncome() {
        return income;
    }

    public void setIncome(float income) {
        this.income = income;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getOrderFood() {
        return orderFood;
    }

    public void setOrderFood(String orderFood) {
        this.orderFood = orderFood;
    }

    @Override
    public String toString() {
        return "OrderBean{" +
                "income=" + income +
                ", buyerName='" + buyerName + '\'' +
                ", orderFood='" + orderFood + '\'' +
                '}';
    }
}
