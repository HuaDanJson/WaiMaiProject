package cool.food.android.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cool.food.android.R;
import cool.food.android.adapter.OrderFoodAdapter;
import cool.food.android.bean.CurrentUser;
import cool.food.android.bean.FoodBean;
import cool.food.android.bean.OrderBean;
import cool.food.android.utils.CurrentUserHelper;
import cool.food.android.utils.FoodDaoUtils;
import cool.food.android.utils.ToastHelper;

//购物车
public class ShoppingCartFragment extends Fragment implements View.OnTouchListener {

    @BindView(R.id.rv_shopping_cart)
    RecyclerView mRecyclerView;
    @BindView(R.id.btn_pay)
    Button mPay;
    @BindView(R.id.tv_empty)
    TextView mEmpty;

    private OrderFoodAdapter mOrderFoodAdapter;
    private CurrentUser mCurrentUser;

    Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shopping_cart, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.setOnTouchListener(this);
        mCurrentUser = CurrentUserHelper.getInstance().getCurrentUser();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return true;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            getCollectionData();
        }
    }

    public void getCollectionData() {
        List<FoodBean> foodBeanList = FoodDaoUtils.getInstance().queryAllData();
        initRecyclerView(foodBeanList);
    }

    @OnClick(R.id.btn_pay)
    public void payClicked() {
        List<FoodBean> foodBeanList = mOrderFoodAdapter.getData();
        if (foodBeanList == null || foodBeanList.isEmpty()) {return;}

        String orderFoodString = "";
        float allPlay = 0;
        for (final FoodBean foodBean : foodBeanList) {
            if (foodBean == null || foodBean.getBuyCount() <= 0) {continue;}
            orderFoodString = orderFoodString + " 商家：" + foodBean.getRestaurantName()
                    + "，餐名：" + foodBean.getFoodName()
                    + ", 份数： " + foodBean.getBuyCount();
            allPlay = allPlay + Float.parseFloat(foodBean.getFoodPrice());
        }
        if (TextUtils.isEmpty(orderFoodString)) {
            ToastHelper.showShortMessage("请选择要下单的商品");
        } else {
            OrderBean orderBean = new OrderBean();
            orderBean.setOrderFood(orderFoodString);
            orderBean.setIncome(allPlay);
            if (mCurrentUser == null) {
                mCurrentUser = CurrentUserHelper.getInstance().getCurrentUser();
            }
            if (mCurrentUser != null) {
                orderBean.setBuyerName(mCurrentUser.getUsername());
            }
            orderBean.save(new SaveListener<String>() {
                @Override
                public void done(String s, BmobException e) {
                    if (e == null) {
                        ToastHelper.showShortMessage("下单成功");
                        FoodDaoUtils.getInstance().deleteAll();
                        initRecyclerView(null);
                    } else {
                        LogUtils.d("ShoppingCartFragment BmobException + " + e);
                        ToastHelper.showShortMessage("下单失败");
                    }
                }
            });
        }
    }

    public void initRecyclerView(List<FoodBean> list) {
        if (mRecyclerView == null) {return;}
        if (list == null || list.isEmpty()) {
            mPay.setVisibility(View.GONE);
            mEmpty.setVisibility(View.VISIBLE);
        } else {
            mEmpty.setVisibility(View.GONE);
            mPay.setVisibility(View.VISIBLE);
        }
        if (mOrderFoodAdapter == null) {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            mOrderFoodAdapter = new OrderFoodAdapter();
            mOrderFoodAdapter.setDataSilently(list);
            mRecyclerView.setAdapter(mOrderFoodAdapter);
        } else {
            mOrderFoodAdapter.setData(list);
        }
    }
}
