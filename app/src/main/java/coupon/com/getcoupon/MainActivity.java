package coupon.com.getcoupon;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import coupon.com.getcoupon.fragment.GetCoupoundFragmentAdapter;
import coupon.com.getcoupon.widget.DrawerArrowDrawable;

import static android.view.Gravity.START;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawer;
    @BindView(R.id.drawer_indicator)
    ImageView mImageView;
    @BindView(R.id.viewpager)
    ViewPager mPager;
    @BindView(R.id.tb_slide)
    TabLayout mSlide;

    private DrawerArrowDrawable drawerArrowDrawable;
    private float offset;
    private boolean flipped;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        final Resources resources = getResources();

        drawerArrowDrawable = new DrawerArrowDrawable(resources);
        drawerArrowDrawable.setStrokeColor(ContextCompat.getColor(this, R.color.white));
        mImageView.setImageDrawable(drawerArrowDrawable);
        mDrawer.setDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                offset = slideOffset;
                if (slideOffset >= .995) {
                    flipped = true;
                    drawerArrowDrawable.setFlip(flipped);
                } else if (slideOffset <= .005) {
                    flipped = false;
                    drawerArrowDrawable.setFlip(flipped);
                }
                drawerArrowDrawable.setParameter(offset);
            }
        });
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDrawer.isDrawerVisible(START)) {
                    mDrawer.closeDrawer(START);
                } else {
                    mDrawer.openDrawer(START);
                }
            }
        });
        mPager.setAdapter(new GetCoupoundFragmentAdapter(getSupportFragmentManager(), this));
        mSlide.setupWithViewPager(mPager);

    }
}
