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
import android.widget.LinearLayout;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import coupon.com.getcoupon.adapter.CategoryAdapter;
import coupon.com.getcoupon.fragment.GetCoupoundFragmentAdapter;
import coupon.com.getcoupon.model.Category;
import coupon.com.getcoupon.widget.DrawerArrowDrawable;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

import static android.view.Gravity.START;

public class MainActivity extends AppCompatActivity implements Drawer.OnDrawerItemClickListener, CategoryAdapter.ICataLikeClickListener {
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawer;
    @BindView(R.id.drawer_indicator)
    ImageView mImageView;
    @BindView(R.id.viewpager)
    ViewPager mPager;
    @BindView(R.id.tb_slide)
    TabLayout mSlide;
    Realm mRealm;
    RealmResults<Category> mCategoriesSelected;
    private DrawerArrowDrawable drawerArrowDrawable;
    private float offset;
    private boolean flipped;
    private Drawer mMaterialDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        final Resources resources = getResources();
        Realm.init(this);
        mRealm = Realm.getDefaultInstance();
        drawerArrowDrawable = new DrawerArrowDrawable(resources);
        drawerArrowDrawable.setStrokeColor(ContextCompat.getColor(this, R.color.white));
        mImageView.setImageDrawable(drawerArrowDrawable);
        mCategoriesSelected = mRealm.where(Category.class).equalTo(Category.IS_SELECTED, true).findAllSorted(Category.CATEGORY_ID, Sort.ASCENDING);

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
        initDrawer();

    }

    public void initDrawer() {
        DrawerBuilder drawerBuilder = new DrawerBuilder()
                .withActivity(this)
                .withSliderBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.bluegrey))
                .addDrawerItems(new PrimaryDrawerItem().withSelectable(false).withIdentifier(0).withName("Yêu Thích").withIcon(R.drawable.ic_like_pink).withTextColor(ContextCompat.getColor(MainActivity.this, R.color.white)))
                .withCloseOnClick(true)
                .withOnDrawerItemClickListener(this)
                .withOnDrawerListener(new Drawer.OnDrawerListener() {
                    @Override
                    public void onDrawerOpened(View drawerView) {
                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {

                    }

                    @Override
                    public void onDrawerSlide(View drawerView, float slideOffset) {

                    }
                });
        for (Category category : mCategoriesSelected) {
            drawerBuilder.addDrawerItems(new SecondaryDrawerItem()
                    .withIdentifier(category.getCategoryId())
                    .withName(category.getName())
                    .withSelectedColor(ContextCompat.getColor(MainActivity.this, R.color.white_tran_25))
                    .withIcon(category.getIcon())
                    .withSelectedIconColor(ContextCompat.getColor(MainActivity.this, R.color.lightblue))
                    .withTextColor(ContextCompat.getColor(MainActivity.this, R.color.white)));
            drawerBuilder.addDrawerItems(new DividerDrawerItem());
        }
        drawerBuilder.withSelectedItem(1);
        mMaterialDrawer = drawerBuilder.buildView();
        ((LinearLayout) findViewById(R.id.drawer_content)).removeAllViews();
        ((LinearLayout) findViewById(R.id.drawer_content)).addView(mMaterialDrawer.getSlider());
    }


    @Override
    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
        return false;
    }


    @Override
    public void likeClick() {
        mCategoriesSelected = mRealm.where(Category.class).equalTo(Category.IS_SELECTED, true).findAllSorted(Category.CATEGORY_ID, Sort.ASCENDING);
        initDrawer();
    }

}
