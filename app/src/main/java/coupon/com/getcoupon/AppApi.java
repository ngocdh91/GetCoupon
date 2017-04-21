package coupon.com.getcoupon;

import java.util.List;

import coupon.com.getcoupon.model.Coupon;
import coupon.com.getcoupon.model.Store;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ngocdh on 4/19/17.
 */

public interface AppApi {
    @GET("/wordpress//api/get_store")
    Call<List<Store>> getStore();

    @GET("/wordpress//api/get_item")
    Call<List<Coupon>> getCoupon(@Query("") String catagoryID);
}
