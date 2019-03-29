package cool.food.android.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cool.food.android.R;
import cool.food.android.adapter.MyOrderAdapter;
import cool.food.android.base.BaseActivity;
import cool.food.android.bean.CurrentUser;
import cool.food.android.bean.OrderBean;
import cool.food.android.utils.CurrentUserHelper;

public class MyOrderActivity extends BaseActivity {

    @BindView(R.id.rv_my_order)
    RecyclerView mRecyclerView;
    @BindView(R.id.tv_empty)
    TextView mEmpty;

    private MyOrderAdapter mMyOrderAdapter;
    private CurrentUser mCurrentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);
        ButterKnife.bind(this);
        mCurrentUser = CurrentUserHelper.getInstance().getCurrentUser();
        if (mCurrentUser == null) {finish();}
        getMyOrderList();
    }

    public void getMyOrderList() {
        BmobQuery<OrderBean> query = new BmobQuery<>();
        query.addWhereEqualTo("buyerName", mCurrentUser.getUsername());
        query.setLimit(50).order("-createdAt")
                .findObjects(new FindListener<OrderBean>() {
                    @Override
                    public void done(List<OrderBean> orderBeanList, BmobException e) {
                        if (e == null) {
                            LogUtils.d("MyOrderActivity BmobQuery orderBeanList : " + orderBeanList);
                            initRecyclerView(orderBeanList);
                        } else {
                            LogUtils.d("MyOrderActivity BmobQuery failed : " + e);
                        }
                    }
                });
    }

    public void initRecyclerView(List<OrderBean> list) {
        LogUtils.d("MyOrderActivity initRecyclerView  list : " + list);
        if (mRecyclerView == null) {return;}
        if (list == null || list.isEmpty()) {
            LogUtils.d("MyOrderActivity initRecyclerView  000 ");
            mEmpty.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.GONE);
        } else {
            LogUtils.d("MyOrderActivity initRecyclerView  1111 ");
            mEmpty.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(MyOrderActivity.this));
            mMyOrderAdapter = new MyOrderAdapter();
            mMyOrderAdapter.setDataSilently(list);
            mRecyclerView.setAdapter(mMyOrderAdapter);
        }
    }
}
