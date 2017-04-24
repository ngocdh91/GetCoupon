package coupon.com.getcoupon.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;
import coupon.com.getcoupon.R;
import coupon.com.getcoupon.model.Coupon;

/**
 * Created by ngocdh on 4/21/17.
 */

public class CoupondAdapter extends RecyclerView.Adapter<CoupondAdapter.CoupondAdapterViewHolder> {


    List<Coupon> mCoupons;
    Activity activity;
    Calendar calendar;

    public CoupondAdapter(List<Coupon> mCoupons, Activity activity) {
        this.mCoupons = mCoupons;
        this.activity = activity;
        calendar = Calendar.getInstance();
    }


    @Override
    public CoupondAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_coupond, parent, false);
        return new CoupondAdapterViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CoupondAdapterViewHolder holder, int position) {
        //image
        // Glide.with(activity).load(mCoupons.get(holder.getAdapterPosition()).getGuid()).into(holder.imv_store);
        holder.tv_content.setText(mCoupons.get(position).getPostContent());
        holder.tv_des.setText(mCoupons.get(position).getPost_title());
        if (mCoupons.get(position).getExpire() != 0) {
            calendar.setTimeInMillis(mCoupons.get(position).getExpire());
            holder.tv_expire.setText(getFullFormatTime(calendar.getTimeInMillis()));
        }

        holder.tv_code.setText(mCoupons.get(position).getCode());

    }

    public String getFullFormatTime(long timestamp) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
        dateFormat.setTimeZone(TimeZone.getDefault());
        return dateFormat.format(new Date(timestamp));
    }


    @Override
    public int getItemCount() {
        return mCoupons.size();
    }

    public class CoupondAdapterViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imv_store)
        ImageView imv_store;
        @BindView(R.id.tv_coupond_content)
        TextView tv_content;
        @BindView(R.id.tv_coupond_descreption)
        TextView tv_des;
        @BindView(R.id.tv_expire)
        TextView tv_expire;
        @BindView(R.id.tv_code)
        TextView tv_code;

        public CoupondAdapterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
