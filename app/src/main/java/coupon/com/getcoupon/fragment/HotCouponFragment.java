package coupon.com.getcoupon.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import coupon.com.getcoupon.AppApi;
import coupon.com.getcoupon.R;
import coupon.com.getcoupon.adapter.ICataLikeClickListener;
import coupon.com.getcoupon.model.Coupon;
import coupon.com.getcoupon.model.Store;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by ngoc on 4/6/2017.
 */

public class HotCouponFragment extends Fragment {
    IgetRetrofit mListener;
    @BindView(R.id.rv_store)
    RecyclerView rvCoupoun;
    private Retrofit retrofit;
    List<Store> mStores = new ArrayList<>();
    private Call<List<Coupon>> mCallResponse;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.hot_coupon_fragment, container, false);
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (IgetRetrofit) context;
        retrofit = mListener.getRetrofit();
    }

    private void getData(){
        mCallResponse = retrofit.create(AppApi.class).getCoupon("51");
        mCallResponse.enqueue(new Callback<List<Coupon>>() {
            @Override
            public void onResponse(Call<List<Coupon>> call, Response<List<Coupon>> response) {

            }

            @Override
            public void onFailure(Call<List<Coupon>> call, Throwable t) {

            }
        });
    }
}
