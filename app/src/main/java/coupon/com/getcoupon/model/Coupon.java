package coupon.com.getcoupon.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.realm.RealmObject;

/**
 * Created by ngocdh on 4/21/17.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "ID",
        "post_status",
        "post_content",
        "post_name",
        "guid",
        "code",
        "expire"
})
public class Coupon extends RealmObject {
    @JsonProperty("ID")
    private String iD;
    @JsonProperty("post_status")
    private String postStatus;
    @JsonProperty("post_content")
    private String postContent;
    @JsonProperty("post_name")
    private String postName;
    @JsonProperty("guid")
    private String guid;
    @JsonProperty("code")
    private String code;
    @JsonProperty("expire")
    private String expire;

    @JsonProperty("ID")
    public String getID() {
        return iD;
    }

    @JsonProperty("ID")
    public void setID(String iD) {
        this.iD = iD;
    }

    @JsonProperty("post_status")
    public String getPostStatus() {
        return postStatus;
    }

    @JsonProperty("post_status")
    public void setPostStatus(String postStatus) {
        this.postStatus = postStatus;
    }

    @JsonProperty("post_content")
    public String getPostContent() {
        return postContent;
    }

    @JsonProperty("post_content")
    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    @JsonProperty("post_name")
    public String getPostName() {
        return postName;
    }

    @JsonProperty("post_name")
    public void setPostName(String postName) {
        this.postName = postName;
    }

    @JsonProperty("guid")
    public String getGuid() {
        return guid;
    }

    @JsonProperty("guid")
    public void setGuid(String guid) {
        this.guid = guid;
    }

    @JsonProperty("code")
    public String getCode() {
        return code;
    }

    @JsonProperty("code")
    public void setCode(String code) {
        this.code = code;
    }

    @JsonProperty("expire")
    public String getExpire() {
        return expire;
    }

    @JsonProperty("expire")
    public void setExpire(String expire) {
        this.expire = expire;
    }


}
