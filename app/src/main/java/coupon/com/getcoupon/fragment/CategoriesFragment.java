package coupon.com.getcoupon.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
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
import io.realm.Realm;

/**
 * Created by ngoc on 4/6/2017.
 */

public class CategoriesFragment extends Fragment {
    @BindView(R.id.rv_category)
    RecyclerView rvCategory;
    List<Category> mCategories = new ArrayList<>();
    Realm mRealm;
    private CategoryAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.category_fragment, container, false);
        ButterKnife.bind(this, rootView);
        mRealm = Realm.getDefaultInstance();
        mAdapter = new CategoryAdapter(mCategories, getActivity(), mRealm);
        addCategories();
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);
        rvCategory.setLayoutManager(mLayoutManager);
        rvCategory.setAdapter(mAdapter);
        return rootView;
    }

    private void addCategories() {
        mCategories.add(new Category(1, "Ẩm Thực"));
        mCategories.add(new Category(2, "Công Nghệ"));
        mCategories.add(new Category(3, "Điện tử - Điện lạnh"));
        mCategories.add(new Category(4, "Gia dụng"));
        mCategories.add(new Category(5, "Giải trí - Du lịch"));
        mCategories.add(new Category(6, "Giáo dục"));
        mCategories.add(new Category(7, "Hot Coupon"));
        mCategories.add(new Category(8, "Mẹ & Bé"));
        mCategories.add(new Category(9, "Sách - Quà tặng"));
        mCategories.add(new Category(10, "Sức khỏe - làm đẹp"));
        mCategories.add(new Category(11, "Tài chính - Ngân hàng"));
        mCategories.add(new Category(12, "Thể thao"));
        mCategories.add(new Category(13, "Thời trang"));
        mCategories.add(new Category(14, "Văn phòng phẩm"));
        mAdapter.notifyDataSetChanged();
    }
}
