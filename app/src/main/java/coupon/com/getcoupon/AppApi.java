package coupon.com.getcoupon;

import java.util.List;

import coupon.com.getcoupon.model.Store;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by ngocdh on 4/19/17.
 */

public interface AppApi {
    @GET("get_store.php")
    Call<List<Store>> getStore();
}
