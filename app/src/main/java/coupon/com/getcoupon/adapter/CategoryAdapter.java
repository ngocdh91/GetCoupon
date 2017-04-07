package coupon.com.getcoupon.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import coupon.com.getcoupon.CategoryDetailActivity;
import coupon.com.getcoupon.R;
import coupon.com.getcoupon.model.Category;

import static coupon.com.getcoupon.CategoryDetailActivity.TRANSITION_IMAGE;
import static coupon.com.getcoupon.CategoryDetailActivity.TRANSITION_TITLE;

/**
 * Created by ngoc on 4/6/2017.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    private final Activity activity;
    List<Category> mCategories;

    public CategoryAdapter(List<Category> mCategories, Activity activity) {
        this.mCategories = mCategories;
        this.activity = activity;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_category, parent, false);
        return new CategoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final CategoryViewHolder holder, int position) {
        holder.mRoot.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                startActivityTransition(holder.textView, holder.imageView, holder.getAdapterPosition());
            }
        });
        switch (mCategories.get(position).getCategoryId()) {
            case 1:
                holder.imageView.setImageDrawable(holder.itemView.getContext().getResources().getDrawable(R.drawable.ic_am_thuc));
                break;
            case 2:
                holder.imageView.setImageDrawable(holder.itemView.getContext().getResources().getDrawable(R.drawable.ic_computer));
                break;
            case 3:
                holder.imageView.setImageDrawable(holder.itemView.getContext().getResources().getDrawable(R.drawable.ic_dien_tu));
                break;
            case 4:
                holder.imageView.setImageDrawable(holder.itemView.getContext().getResources().getDrawable(R.drawable.ic_gia_dung));
                break;
            case 5:
                holder.imageView.setImageDrawable(holder.itemView.getContext().getResources().getDrawable(R.drawable.ic_giai_tri_du_lich));
                break;
            case 6:
                holder.imageView.setImageDrawable(holder.itemView.getContext().getResources().getDrawable(R.drawable.ic_giao_duc));
                break;
            case 7:
                holder.imageView.setImageDrawable(holder.itemView.getContext().getResources().getDrawable(R.drawable.ic_hot_coupon));
                break;
            case 8:
                holder.imageView.setImageDrawable(holder.itemView.getContext().getResources().getDrawable(R.drawable.ic_me_va_be));
                break;
            case 9:
                holder.imageView.setImageDrawable(holder.itemView.getContext().getResources().getDrawable(R.drawable.ic_sach_qua_tang));
                break;
            case 10:
                holder.imageView.setImageDrawable(holder.itemView.getContext().getResources().getDrawable(R.drawable.ic_suc_khoe_lam_dep));
                break;
            case 11:
                holder.imageView.setImageDrawable(holder.itemView.getContext().getResources().getDrawable(R.drawable.ic_tai_chinh_ngan_hang));
                break;
            case 12:
                holder.imageView.setImageDrawable(holder.itemView.getContext().getResources().getDrawable(R.drawable.ic_the_thao));
                break;
            case 13:
                holder.imageView.setImageDrawable(holder.itemView.getContext().getResources().getDrawable(R.drawable.ic_thoi_trang));
                break;
            case 14:
                holder.imageView.setImageDrawable(holder.itemView.getContext().getResources().getDrawable(R.drawable.ic_van_phong_pham));
                break;
        }
        holder.textView.setText(mCategories.get(position).getName());
    }

    private void startActivityTransition(TextView textView, ImageView imageview, int position) {
        Intent intent = new Intent(activity, CategoryDetailActivity.class);
        Pair<View, String> p1 = Pair.create((View) textView, "transitionTitle");
        Pair<View, String> p2 = Pair.create((View) imageview, "imvTest");
        intent.putExtra(TRANSITION_TITLE, mCategories.get(position).getName());
        intent.putExtra(TRANSITION_IMAGE, mCategories.get(position).getCategoryId());
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, p1, p2);
        activity.startActivity(intent, options.toBundle());
    }

    @Override
    public int getItemCount() {
        return mCategories.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        LinearLayout mRoot;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imv_category);
            textView = (TextView) itemView.findViewById(R.id.tv_category);
            mRoot = (LinearLayout) itemView.findViewById(R.id.ln_root);
        }
    }
}
