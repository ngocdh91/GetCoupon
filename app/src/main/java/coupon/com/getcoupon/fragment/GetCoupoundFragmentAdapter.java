package coupon.com.getcoupon.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ngoc on 4/6/2017.
 */

public class GetCoupoundFragmentAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 3;
    List<Fragment> mFragments;
    private String tabTitles[] = new String[]{"Thương hiệu", "Danh mục", "Giảm giá"};
    private Context context;

    public GetCoupoundFragmentAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
        mFragments = new ArrayList<>();
        mFragments.add(new TrademarkFragment());
        mFragments.add(new CategoriesFragment());
        mFragments.add(new HotCouponFragment());
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}
