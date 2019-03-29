package cool.food.android.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cool.food.android.R;
import cool.food.android.adapter.OrderFoodAdapter;
import cool.food.android.base.BaseActivity;
import cool.food.android.base.CCApplication;
import cool.food.android.bean.FoodBean;
import cool.food.android.bean.RestaurantBean;
import cool.food.android.constants.AppConstant;
import cool.food.android.dialog.CommentDialog;
import cool.food.android.utils.FoodDaoUtils;
import cool.food.android.utils.RestaurantDaoUtils;
import cool.food.android.utils.ToastHelper;
import de.hdodenhof.circleimageview.CircleImageView;

public class RestaurantActivity extends BaseActivity {

    @BindView(R.id.tv_restaurant_name) TextView mRestaurantName;
    @BindView(R.id.btn_collection) Button mCollection;
    @BindView(R.id.tv_restaurant_address) TextView mRestaurantAddress;
    @BindView(R.id.tv_restaurant_tel) TextView mRestaurantTel;
    @BindView(R.id.civ_call_phone) CircleImageView mCallPhone;
    @BindView(R.id.rlv_food) RecyclerView mRecyclerView;
    @BindView(R.id.btn_add_shopping_cart) Button mAddShoppingCart;
    @BindView(R.id.btn_comment) Button mComment;

    private RestaurantBean mRestaurantBean;
    private OrderFoodAdapter mOrderFoodAdapter;
    private List<FoodBean> mFoodBeanList = new ArrayList<>();
    private CommentDialog mCommentDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);
        ButterKnife.bind(this);
        mRestaurantBean = (RestaurantBean) getIntent().getSerializableExtra(AppConstant.IntentKey.EXTRA_DATA);
        if (mRestaurantBean == null) {
            onBackPressed();
            return;
        }
        getFoodList();
        mRestaurantName.setText(mRestaurantBean.getName());
        mRestaurantAddress.setText(mRestaurantBean.getAddress());
        mRestaurantTel.setText("电话：" + mRestaurantBean.getPhoneNumber());
        List<RestaurantBean> restaurantBeanList = RestaurantDaoUtils.getInstance().queryAllData();
        if (restaurantBeanList == null || restaurantBeanList.isEmpty()) {return;}
        for (RestaurantBean restaurantBean : restaurantBeanList) {
            if (restaurantBean == null) {continue;}
            String resID = restaurantBean.getObjectId();
            if (TextUtils.isEmpty(resID)) {continue;}
            if (resID.equals(mRestaurantBean.getObjectId())) {
                mCollection.setText("取消收藏");
                break;
            }
        }

    }

    public void getFoodList() {
        BmobQuery<FoodBean> query = new BmobQuery<>();
        query.addWhereEqualTo("restaurantId", mRestaurantBean.getObjectId());
        query.setLimit(80).order("createdAt")
                .findObjects(new FindListener<FoodBean>() {
                    @Override
                    public void done(List<FoodBean> foodBeanList, BmobException e) {
                        if (e == null) {
                            LogUtils.d("getFoodList BmobQuery success:" + foodBeanList);
                            mFoodBeanList = foodBeanList;
                            if (mOrderFoodAdapter == null) {
                                mRecyclerView.setLayoutManager(new LinearLayoutManager(RestaurantActivity.this));
                                mOrderFoodAdapter = new OrderFoodAdapter();
                                mOrderFoodAdapter.setDataSilently(mFoodBeanList);
                                mRecyclerView.setAdapter(mOrderFoodAdapter);
                            } else {
                                mOrderFoodAdapter.setData(mFoodBeanList);
                            }

                        } else {
                            LogUtils.d("OrderFoodFragment BmobQuery failed : " + e);
                        }
                    }
                });
    }

    @OnClick(R.id.civ_call_phone)
    public void sendCall() {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + mRestaurantBean.getPhoneNumber()));
        if (ActivityCompat.checkSelfPermission(CCApplication.getInstance(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startActivity(intent);
    }

    @OnClick(R.id.btn_collection)
    public void collectionClicked() {
        String text = mCollection.getText().toString();
        if (TextUtils.isEmpty(text)) {return;}
        if ("收藏".equals(text)) {
            RestaurantDaoUtils.getInstance().insertOneData(mRestaurantBean);
            mCollection.setText("取消收藏");
            ToastHelper.showShortMessage("收藏成功");
        } else {
            RestaurantDaoUtils.getInstance().deleteOneData(mRestaurantBean);
            mCollection.setText("收藏");
            ToastHelper.showShortMessage("取消收藏成功");
        }
    }

    @OnClick(R.id.btn_add_shopping_cart)
    public void addShopingCartClicked() {
        List<FoodBean> foodBeanList = mOrderFoodAdapter.getData();
        if (foodBeanList == null || foodBeanList.isEmpty()) {return;}
        for (FoodBean foodBean : foodBeanList) {
            if (foodBean.getBuyCount() > 0) {
                foodBean.setRestaurantName(mRestaurantBean.getName());
                FoodDaoUtils.getInstance().insertOneData(foodBean);
            }
        }
        ToastHelper.showShortMessage("添加购物车成功");
        finish();
    }

    @OnClick(R.id.btn_comment)
    public void commentClicked() {
        showCommentDialog();
    }

    public void showCommentDialog() {
        if (mCommentDialog == null) {
            mCommentDialog = new CommentDialog();
        }
        mCommentDialog.setData(mRestaurantBean);
        mCommentDialog.tryShow(getSupportFragmentManager());
    }
}
