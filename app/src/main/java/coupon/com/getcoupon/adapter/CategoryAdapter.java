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

import static coupon.com.getcoupon.CategoryDetailActivity.CATEGORYID;
import static coupon.com.getcoupon.CategoryDetailActivity.TRANSITION_IMAGE;
import static coupon.com.getcoupon.CategoryDetailActivity.TRANSITION_TITLE;

/**
 * Created by ngoc on 4/6/2017.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    private final Activity activity;
    List<Category> mCategories;
    Realm mRealm;
    ICataLikeClickListener mLikeListner;

    public CategoryAdapter(List<Category> mCategories, Activity activity, Realm mRealm, ICataLikeClickListener mLikeListner) {
        this.mCategories = mCategories;
        this.activity = activity;
        this.mRealm = mRealm;
        this.mLikeListner = mLikeListner;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_category, parent, false);
        return new CategoryViewHolder(itemView,mLikeListner);
    }

    @Override
    public void onBindViewHolder(final CategoryViewHolder holder, int position) {
        holder.imageView.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                startActivityTransition(holder.textView, holder.imageView, holder.imvLike, holder.getAdapterPosition());
            }
        });

        holder.imageView.setImageDrawable(holder.itemView.getContext().getResources().getDrawable(mCategories.get(position).getIcon()));


        if (mCategories.get(holder.getAdapterPosition()).isSelected()) {
            holder.imvLike.setImageDrawable(holder.itemView.getContext().getResources().getDrawable(R.drawable.ic_like_pink));
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
        intent.putExtra(CATEGORYID, mCategories.get(position).getCategoryId());
        intent.putExtra(TRANSITION_IMAGE, mCategories.get(position).getIcon());
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, p1, p2, p3);
        activity.startActivity(intent, options.toBundle());
    }

    @Override
    public int getItemCount() {
        return mCategories.size();
    }

    public interface ICataLikeClickListener {
        void likeClick();
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
        ICataLikeClickListener mLikeListner;

        public CategoryViewHolder(View itemView, final ICataLikeClickListener mLikeListner) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.mLikeListner = mLikeListner;
            mLnWrapContent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCategory = mCategories.get(getAdapterPosition());
                    mRealm.beginTransaction();
                    mCategory.setSelected(!mCategory.isSelected());
                    if (!mCategory.isSelected()) {
                        imvLike.setImageDrawable(v.getContext().getResources().getDrawable(R.drawable.ic_like));
                    } else {
                        imvLike.setImageDrawable(v.getContext().getResources().getDrawable(R.drawable.ic_like_pink));
                    }
                    mRealm.commitTransaction();
                    mLikeListner.likeClick();
                }
            });

        }
    }
}
