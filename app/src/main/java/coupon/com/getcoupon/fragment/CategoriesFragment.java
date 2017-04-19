package coupon.com.getcoupon.fragment;

import android.content.Context;
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
import coupon.com.getcoupon.adapter.ICataLikeClickListener;
import coupon.com.getcoupon.model.Category;
import io.realm.Realm;
import io.realm.Sort;

/**
 * Created by ngoc on 4/6/2017.
 */

public class CategoriesFragment extends Fragment {
    @BindView(R.id.rv_category)
    RecyclerView rvCategory;
    List<Category> mCategories = new ArrayList<>();
    Realm mRealm;
    ICataLikeClickListener mLikeListner;
    private CategoryAdapter mAdapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mLikeListner = (ICataLikeClickListener) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.category_fragment, container, false);
        ButterKnife.bind(this, rootView);
        mRealm = Realm.getDefaultInstance();
        mAdapter = new CategoryAdapter(mCategories, getActivity(), mRealm, mLikeListner);
        addCategories();
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);
        rvCategory.setLayoutManager(mLayoutManager);
        rvCategory.setAdapter(mAdapter);
        return rootView;
    }

    private void addCategories() {
        mRealm.beginTransaction();
        if (mRealm.where(Category.class).equalTo(Category.CATEGORY_ID, 1).findFirst() == null)
            mRealm.insert(new Category(1, "Ẩm Thực", R.drawable.ic_am_thuc));

        if (mRealm.where(Category.class).equalTo(Category.CATEGORY_ID, 2).findFirst() == null)
            mRealm.insert(new Category(2, "Công Nghệ", R.drawable.ic_computer));

        if (mRealm.where(Category.class).equalTo(Category.CATEGORY_ID, 3).findFirst() == null)
            mRealm.insert(new Category(3, "Điện tử - Điện lạnh", R.drawable.ic_dien_tu));

        if (mRealm.where(Category.class).equalTo(Category.CATEGORY_ID, 4).findFirst() == null)
            mRealm.insert(new Category(4, "Gia dụng", R.drawable.ic_gia_dung));

        if (mRealm.where(Category.class).equalTo(Category.CATEGORY_ID, 5).findFirst() == null)
            mRealm.insert(new Category(5, "Giải trí - Du lịch", R.drawable.ic_giai_tri_du_lich));

        if (mRealm.where(Category.class).equalTo(Category.CATEGORY_ID, 6).findFirst() == null)
            mRealm.insert(new Category(6, "Giáo dục", R.drawable.ic_giao_duc));

        if (mRealm.where(Category.class).equalTo(Category.CATEGORY_ID, 7).findFirst() == null)
            mRealm.insert(new Category(7, "Hot Coupon", R.drawable.ic_hot_coupon));

        if (mRealm.where(Category.class).equalTo(Category.CATEGORY_ID, 8).findFirst() == null)
            mRealm.insert(new Category(8, "Mẹ & Bé", R.drawable.ic_me_va_be));

        if (mRealm.where(Category.class).equalTo(Category.CATEGORY_ID, 9).findFirst() == null)
            mRealm.insert(new Category(9, "Sách - Quà tặng", R.drawable.ic_sach_qua_tang));

        if (mRealm.where(Category.class).equalTo(Category.CATEGORY_ID, 10).findFirst() == null)
            mRealm.insert(new Category(10, "Sức khỏe - làm đẹp", R.drawable.ic_suc_khoe_lam_dep));

        if (mRealm.where(Category.class).equalTo(Category.CATEGORY_ID, 11).findFirst() == null)
            mRealm.insert(new Category(11, "Tài chính - Ngân hàng", R.drawable.ic_tai_chinh_ngan_hang));

        if (mRealm.where(Category.class).equalTo(Category.CATEGORY_ID, 12).findFirst() == null)
            mRealm.insert(new Category(12, "Thể thao", R.drawable.ic_the_thao));

        if (mRealm.where(Category.class).equalTo(Category.CATEGORY_ID, 13).findFirst() == null)
            mRealm.insert(new Category(13, "Thời trang", R.drawable.ic_thoi_trang));

        if (mRealm.where(Category.class).equalTo(Category.CATEGORY_ID, 14).findFirst() == null)
            mRealm.insert(new Category(14, "Văn phòng phẩm", R.drawable.ic_van_phong_pham));
        mRealm.commitTransaction();
        mCategories.addAll(mRealm.where(Category.class).findAllSorted(Category.CATEGORY_ID, Sort.ASCENDING));
        mAdapter.notifyDataSetChanged();
    }
}
