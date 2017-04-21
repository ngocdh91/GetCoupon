package coupon.com.getcoupon.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ngocdh on 4/21/17.
 */

public class CoupondAdapter extends RecyclerView.Adapter<CoupondAdapter.CoupondAdapterViewHolder>  {
    @Override
    public CoupondAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(CoupondAdapterViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class CoupondAdapterViewHolder extends RecyclerView.ViewHolder {
        public CoupondAdapterViewHolder(View itemView) {
            super(itemView);
        }
    }
}
