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
import cool.food.android.bean.FoodBean;


public class FoodAdapter extends BaseRVAdapter<FoodBean, FoodAdapter.ReaderAdapterHolder> {

    @Override
    protected ReaderAdapterHolder doCreateViewHolder(ViewGroup viewGroup, int viewType) {
        return new ReaderAdapterHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_food_adapter, viewGroup, false));
    }

    @Override
    protected void bindItemData(ReaderAdapterHolder viewHolder, FoodBean foodBean, int position) {
        viewHolder.bindView(foodBean, position);
    }

    public class ReaderAdapterHolder extends RecyclerView.ViewHolder implements IViewHolder<FoodBean> {

        @BindView(R.id.tv_title_item_book_reader)
        TextView mTitle;
        @BindView(R.id.tv_time_item_book_reader)
        TextView mTime;
        @BindView(R.id.tv_introduce_item_book_reader)
        TextView mIntroduce;

        public ReaderAdapterHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void bindView(FoodBean foodBean, int position) {
//            mTitle.setText(restaurantBean.getName());
//            mIntroduce.setText(weiBoBean.getIntroduce());
//            mTime.setText(TimeUtils.stampToDate(weiBoBean.getCreatTime()));
        }
    }
}

