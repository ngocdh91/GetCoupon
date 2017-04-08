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

import butterknife.BindView;
import butterknife.ButterKnife;
import coupon.com.getcoupon.CategoryDetailActivity;
import coupon.com.getcoupon.R;
import coupon.com.getcoupon.model.Category;
import io.realm.Realm;

import static coupon.com.getcoupon.CategoryDetailActivity.TRANSITION_IMAGE;
import static coupon.com.getcoupon.CategoryDetailActivity.TRANSITION_TITLE;

/**
 * Created by ngoc on 4/6/2017.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    private final Activity activity;
    List<Category> mCategories;
    Realm mRealm;

    public CategoryAdapter(List<Category> mCategories, Activity activity, Realm mRealm) {
        this.mCategories = mCategories;
        this.activity = activity;
        this.mRealm = mRealm;

    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_category, parent, false);
        return new CategoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final CategoryViewHolder holder, int position) {
        holder.imageView.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                startActivityTransition(holder.textView, holder.imageView, holder.imvLike, holder.getAdapterPosition());
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

        if (mRealm.where(Category.class).equalTo(Category.CATEGORY_ID, mCategories.get(holder.getAdapterPosition()).getCategoryId()).findAll().size() != 0) {
            holder.imvLike.setImageDrawable(holder.itemView.getContext().getResources().getDrawable(R.drawable.ic_like_pink));
            mCategories.get(holder.getAdapterPosition()).setSelected(true);
        } else
            holder.imvLike.setImageDrawable(holder.itemView.getContext().getResources().getDrawable(R.drawable.ic_like));
        holder.textView.setText(mCategories.get(position).getName());
    }

    private void startActivityTransition(TextView textView, ImageView imageview, ImageView imvLike, int position) {
        Intent intent = new Intent(activity, CategoryDetailActivity.class);
        Pair<View, String> p1 = Pair.create((View) textView, activity.getString(R.string.transition_title));
        Pair<View, String> p2 = Pair.create((View) imageview, activity.getString(R.string.transition_imv_category));
        Pair<View, String> p3 = Pair.create((View) imvLike, activity.getString(R.string.transition_like));
        intent.putExtra(TRANSITION_TITLE, mCategories.get(position).getName());
        intent.putExtra(TRANSITION_IMAGE, mCategories.get(position).getCategoryId());
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, p1, p2, p3);
        activity.startActivity(intent, options.toBundle());
    }

    @Override
    public int getItemCount() {
        return mCategories.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imv_category)
        ImageView imageView;
        @BindView(R.id.imv_like)
        ImageView imvLike;
        @BindView(R.id.tv_category)
        TextView textView;
        @BindView(R.id.ln_root)
        LinearLayout mRoot;
        @BindView(R.id.ln_wrap_category_content)
        LinearLayout mLnWrapContent;
        Category mCategory;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mLnWrapContent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCategory = mCategories.get(getAdapterPosition());
                    mRealm.beginTransaction();
                    mCategory.setSelected(!mCategory.isSelected());
                    if (!mCategory.isSelected()) {
                        imvLike.setImageDrawable(v.getContext().getResources().getDrawable(R.drawable.ic_like));
                        mRealm.where(Category.class).equalTo(Category.CATEGORY_ID, mCategory.getCategoryId()).findFirst().deleteFromRealm();
                    } else {
                        imvLike.setImageDrawable(v.getContext().getResources().getDrawable(R.drawable.ic_like_pink));
                        mRealm.insertOrUpdate(mCategory);
                    }
                    mRealm.commitTransaction();
                }
            });

        }
    }
}
