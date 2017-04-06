package coupon.com.getcoupon.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import coupon.com.getcoupon.R;
import coupon.com.getcoupon.adapter.CategoryAdapter;
import coupon.com.getcoupon.model.Category;

/**
 * Created by ngoc on 4/6/2017.
 */

public class CategoriesFragment extends Fragment {
    @BindView(R.id.rv_category)
    RecyclerView rvCategory;
    List<Category> mCategories = new ArrayList<>();
    private CategoryAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.category_fragment, container, false);
        ButterKnife.bind(this, rootView);
        mAdapter = new CategoryAdapter(mCategories);
        addCategories();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        rvCategory.setLayoutManager(mLayoutManager);
        rvCategory.setAdapter(mAdapter);
        return rootView;
    }

    private void addCategories() {
        mCategories.add(new Category(1, "Ẩm Thực"));
        mCategories.add(new Category(2, "Công Nghệ"));
        mAdapter.notifyDataSetChanged();
    }
}
