package coupon.com.getcoupon.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import coupon.com.getcoupon.AppApi;
import coupon.com.getcoupon.R;
import coupon.com.getcoupon.model.Store;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by ngoc on 4/6/2017.
 */

public class TrademarkFragment extends Fragment {
    public static final String ARG_RETROFIT = "arg_retrofit";
    IgetRetrofit mListener;
    private Retrofit retrofit;
    private Call<List<Store>> mCallResponse;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.trademark_fragment, container, false);
        mCallResponse = retrofit.create(AppApi.class).getStore();
        mCallResponse.enqueue(new Callback<List<Store>>() {
            @Override
            public void onResponse(Call<List<Store>> call, Response<List<Store>> response) {
                for (Store store: response.body()){
                    Log.d("Đây là store",store.getName());
                }
            }

            @Override
            public void onFailure(Call<List<Store>> call, Throwable t) {

            }
        });
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (IgetRetrofit) context;
        retrofit = mListener.getRetrofit();
    }
}
