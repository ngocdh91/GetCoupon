package coupon.com.getcoupon;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import coupon.com.getcoupon.model.Category;
import io.realm.Realm;

/**
 * Created by ngocdh on 4/7/17.
 */

public class CategoryDetailActivity extends AppCompatActivity {
    public static final String TRANSITION_TITLE = "transition_title";
    public static final String TRANSITION_IMAGE = "transition_image";
    @BindView(R.id.toolBar)
    Toolbar mToolBar;
    @BindView(R.id.title)
    TextView mTvTitle;
    @BindView(R.id.imv)
    ImageView imageView;
    @BindView(R.id.float_like)
    FloatingActionButton mLike;
    private int imageID;
    private Realm mRealm;
    private Category mCategorySelected;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_detail);
        ButterKnife.bind(this);
        setSupportActionBar(mToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mTvTitle.setText(getIntent().getStringExtra(TRANSITION_TITLE));
        imageID = getIntent().getIntExtra(TRANSITION_IMAGE, 0);
        mRealm = Realm.getDefaultInstance();
        mCategorySelected = mRealm.where(Category.class).equalTo(Category.CATEGORY_ID, imageID).findFirst();
        switch (imageID) {
            case 1:
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_am_thuc));
                break;
            case 2:
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_computer));
                break;
            case 3:
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_dien_tu));
                break;
            case 4:
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_gia_dung));
                break;
            case 5:
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_giai_tri_du_lich));
                break;
            case 6:
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_giao_duc));
                break;
            case 7:
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_hot_coupon));
                break;
            case 8:
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_me_va_be));
                break;
            case 9:
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_sach_qua_tang));
                break;
            case 10:
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_suc_khoe_lam_dep));
                break;
            case 11:
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_tai_chinh_ngan_hang));
                break;
            case 12:
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_the_thao));
                break;
            case 13:
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_thoi_trang));
                break;
            case 14:
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_van_phong_pham));
                break;
        }
        if (mCategorySelected == null)
            mLike.setImageDrawable(getDrawable(R.drawable.ic_like));
        else
            mLike.setImageDrawable(getDrawable(R.drawable.ic_like_pink));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                supportFinishAfterTransition();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
