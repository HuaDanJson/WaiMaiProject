package cool.food.android.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cool.food.android.R;
import cool.food.android.adapter.OrderFoodAdapter;
import cool.food.android.bean.FoodBean;
import cool.food.android.utils.FoodDaoUtils;

//购物车
public class ShoppingCartFragment extends Fragment implements View.OnTouchListener {

    @BindView(R.id.rv_shopping_cart)
    RecyclerView mRecyclerView;
    @BindView(R.id.btn_pay)
    Button mPay;

    private List<FoodBean> mFoodBeanList = new ArrayList<>();
    private OrderFoodAdapter mOrderFoodAdapter;

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
        mFoodBeanList = FoodDaoUtils.getInstance().queryAllData();
        if (mOrderFoodAdapter == null) {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            mOrderFoodAdapter = new OrderFoodAdapter();
            mOrderFoodAdapter.setDataSilently(mFoodBeanList);
            mRecyclerView.setAdapter(mOrderFoodAdapter);
        } else {
            mOrderFoodAdapter.setData(mFoodBeanList);
        }
    }

    @OnClick(R.id.btn_pay)
    public void payClicked() {

    }
}
