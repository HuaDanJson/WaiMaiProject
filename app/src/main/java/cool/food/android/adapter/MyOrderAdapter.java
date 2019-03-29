package cool.food.android.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cool.food.android.R;
import cool.food.android.base.BaseRVAdapter;
import cool.food.android.base.IViewHolder;
import cool.food.android.bean.OrderBean;


public class MyOrderAdapter extends BaseRVAdapter<OrderBean, MyOrderAdapter.MyOrderAdapterHolder> {

    @Override
    protected MyOrderAdapterHolder doCreateViewHolder(ViewGroup viewGroup, int viewType) {
        return new MyOrderAdapterHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_my_order_adapter, viewGroup, false));
    }

    @Override
    protected void bindItemData(MyOrderAdapterHolder viewHolder, OrderBean orderBean, int position) {
        viewHolder.bindView(orderBean, position);
    }

    public class MyOrderAdapterHolder extends RecyclerView.ViewHolder implements IViewHolder<OrderBean> {

        @BindView(R.id.tv_time) TextView mTime;
        @BindView(R.id.tv_income) TextView mPay;
        @BindView(R.id.tv_value) TextView mValue;

        public MyOrderAdapterHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void bindView(OrderBean orderBean, int position) {
            mValue.setText(orderBean.getOrderFood());
            mPay.setText("总价：" + orderBean.getIncome());
            mTime.setText(orderBean.getCreatedAt());
        }
    }
}

