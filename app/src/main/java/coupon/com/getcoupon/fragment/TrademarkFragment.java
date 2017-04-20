package coupon.com.getcoupon.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import coupon.com.getcoupon.AppApi;
import coupon.com.getcoupon.R;
import coupon.com.getcoupon.adapter.ICataLikeClickListener;
import coupon.com.getcoupon.adapter.TradeMarkAdapter;
import coupon.com.getcoupon.model.Store;
import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by ngoc on 4/6/2017.
 */

public class TrademarkFragment extends Fragment {
    IgetRetrofit mListener;
    List<Store> mStores = new ArrayList<>();
    Realm mRealm;
    ICataLikeClickListener mLikeListner;
    @BindView(R.id.rv_store)
    RecyclerView rvStore;
    TradeMarkAdapter mAdapter;
    private Retrofit retrofit;
    private Call<List<Store>> mCallResponse;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.trademark_fragment, container, false);
        ButterKnife.bind(this, rootView);
        mRealm = Realm.getDefaultInstance();
        mStores = new ArrayList<>();
        mAdapter = new TradeMarkAdapter(mStores, getActivity(), mRealm, mLikeListner);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);
        mStores.addAll(mRealm.where(Store.class).findAll());
        if (mStores.size() == 0) getData();
        mAdapter.notifyDataSetChanged();
        rvStore.setLayoutManager(mLayoutManager);
        rvStore.setAdapter(mAdapter);

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (IgetRetrofit) context;
        retrofit = mListener.getRetrofit();
        mLikeListner = (ICataLikeClickListener) context;
    }

    public void getData() {
        mCallResponse = retrofit.create(AppApi.class).getStore();
        mCallResponse.enqueue(new Callback<List<Store>>() {
            @Override
            public void onResponse(Call<List<Store>> call, Response<List<Store>> response) {
                for (Store store : response.body()) {
                    Log.d("Đây là store", store.getName());
                }
                mStores.addAll(response.body());
                mAdapter.notifyDataSetChanged();
                mRealm.beginTransaction();
                mRealm.where(Store.class).findAll().deleteAllFromRealm();
                mRealm.insert(response.body());
                mRealm.commitTransaction();
            }

            @Override
            public void onFailure(Call<List<Store>> call, Throwable t) {
                Log.d("Đây Là lỗi", t.getMessage());
                t.printStackTrace();
                Toast.makeText(getContext(), "Không thể kết nối tới máy chủ", Toast.LENGTH_LONG).show();
            }
        });
    }
}
