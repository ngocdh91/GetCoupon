package coupon.com.getcoupon;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import coupon.com.getcoupon.model.Store;
import io.realm.Realm;

/**
 * Created by ngocdh on 4/20/17.
 */

public class StoreDetailActivity extends AppCompatActivity {
    public static final String TRANSITION_TITLE = "transition_title";
    public static final String TRANSITION_IMAGE = "transition_image";
    public static final String STORE_ID = "store_id";
    @BindView(R.id.toolBar)
    Toolbar mToolBar;
    @BindView(R.id.title)
    TextView mTvTitle;
    @BindView(R.id.imv)
    ImageView imageView;
    @BindView(R.id.float_like)
    FloatingActionButton mLike;
    private String mStoreId;
    private String imageIcon;
    private Realm mRealm;
    private Store mStoreSelected;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_detail);
        ButterKnife.bind(this);
        setSupportActionBar(mToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mTvTitle.setText(getIntent().getStringExtra(TRANSITION_TITLE));
        mStoreId = getIntent().getStringExtra(STORE_ID);
        imageIcon = getIntent().getStringExtra(TRANSITION_IMAGE);
        mRealm = Realm.getDefaultInstance();
        mStoreSelected = mRealm.where(Store.class).equalTo(Store.STORE_ID, String.valueOf(mStoreId)).findFirst();
        Glide.with(this).load(imageIcon).into(imageView);
        if (!mStoreSelected.isSelected())
            mLike.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_like));
        else
            mLike.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_like_pink));

        mLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(StoreDetailActivity.this, TestActivity.class);
                startActivity(i);
            }
        });
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
