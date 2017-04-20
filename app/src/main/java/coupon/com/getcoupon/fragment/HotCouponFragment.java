package coupon.com.getcoupon.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import coupon.com.getcoupon.R;
import retrofit2.Retrofit;

/**
 * Created by ngoc on 4/6/2017.
 */

public class HotCouponFragment extends Fragment {
    IgetRetrofit mListener;
    @BindView(R.id.rv_store)
    RecyclerView rvCoupoun;
    private Retrofit retrofit;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.hot_coupon_fragment, container, false);
        return rootView;
    }
}
