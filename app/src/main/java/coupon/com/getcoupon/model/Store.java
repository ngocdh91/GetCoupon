package coupon.com.getcoupon.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Created by ngocdh on 4/19/17.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "name",
        "term_id",
        "meta_id",
        "meta_key",
        "meta_value"
})
public class Store {

    @JsonProperty("name")
    private String name;
    @JsonProperty("term_id")
    private String termId;
    @JsonProperty("meta_id")
    private String metaId;
    @JsonProperty("meta_key")
    private String metaKey;
    @JsonProperty("meta_value")
    private String metaValue;


    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("term_id")
    public String getTermId() {
        return termId;
    }

    @JsonProperty("term_id")
    public void setTermId(String termId) {
        this.termId = termId;
    }

    @JsonProperty("meta_id")
    public String getMetaId() {
        return metaId;
    }

    @JsonProperty("meta_id")
    public void setMetaId(String metaId) {
        this.metaId = metaId;
    }

    @JsonProperty("meta_key")
    public String getMetaKey() {
        return metaKey;
    }

    @JsonProperty("meta_key")
    public void setMetaKey(String metaKey) {
        this.metaKey = metaKey;
    }

    @JsonProperty("meta_value")
    public String getMetaValue() {
        return metaValue;
    }

    @JsonProperty("meta_value")
    public void setMetaValue(String metaValue) {
        this.metaValue = metaValue;
    }


}