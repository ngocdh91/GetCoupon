package coupon.com.getcoupon.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by ngoc on 4/6/2017.
 */

public class Category extends RealmObject {
    public static final String CATEGORY_ID = "categoryId";
    public static final String IS_SELECTED = "isSelected";
    @PrimaryKey
    private int categoryId;
    private String name;
    private boolean isSelected;
    private int icon;

    public Category() {
        // require default Contructor
    }

    public Category(int categoryId, String name, int icon) {
        this.categoryId = categoryId;
        this.name = name;
        this.icon = icon;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
