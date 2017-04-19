package coupon.com.getcoupon;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
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
    public static final String CATEGORYID = "categoryid";
    @BindView(R.id.toolBar)
    Toolbar mToolBar;
    @BindView(R.id.title)
    TextView mTvTitle;
    @BindView(R.id.imv)
    ImageView imageView;
    @BindView(R.id.float_like)
    FloatingActionButton mLike;
    private int mCateID;
    private int imageIcon;
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
        mCateID = getIntent().getIntExtra(CATEGORYID, 0);
        imageIcon = getIntent().getIntExtra(TRANSITION_IMAGE, 0);
        mRealm = Realm.getDefaultInstance();
        mCategorySelected = mRealm.where(Category.class).equalTo(Category.CATEGORY_ID, mCateID).findFirst();

        imageView.setImageDrawable(ContextCompat.getDrawable(this, imageIcon));

        if (!mCategorySelected.isSelected())
            mLike.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_like));
        else
            mLike.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_like_pink));

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
