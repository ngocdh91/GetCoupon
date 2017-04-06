package coupon.com.getcoupon.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import coupon.com.getcoupon.R;
import coupon.com.getcoupon.model.Category;

/**
 * Created by ngoc on 4/6/2017.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    List<Category> mCategories;

    public CategoryAdapter(List<Category> mCategories) {
        this.mCategories = mCategories;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_category, parent, false);
        return new CategoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {
        switch (mCategories.get(position).getCategoryId()) {
            case 1:
                holder.imageView.setImageDrawable(holder.itemView.getContext().getResources().getDrawable(R.drawable.ic_am_thuc));
                break;
            case 2:
                holder.imageView.setImageDrawable(holder.itemView.getContext().getResources().getDrawable(R.drawable.ic_cong_nghe));
                break;
        }
        holder.textView.setText(mCategories.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return mCategories.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imv_category);
            textView = (TextView) itemView.findViewById(R.id.tv_category);
        }
    }
}
