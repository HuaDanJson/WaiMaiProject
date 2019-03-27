package cool.food.android.base;

public interface IViewHolder<DATA> {

    void bindView(DATA data, int position);
}
