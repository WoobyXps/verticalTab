package com.vito.verticaltab.bean;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "itemName",
        "itemImage"
})
public class ContentBody {

    @JsonProperty("itemName")
    private String itemName;
    @JsonProperty("itemImage")
    private String itemImage;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private String category;
    private long categoryId;

    /**
     * @return The itemName
     */
    @JsonProperty("itemName")
    public String getItemName() {
        return itemName;
    }

    /**
     * @param itemName The itemName
     */
    @JsonProperty("itemName")
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * @return The itemImage
     */
    @JsonProperty("itemImage")
    public String getItemImage() {
        return itemImage;
    }

    /**
     * @param itemImage The itemImage
     */
    @JsonProperty("itemImage")
    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }
}
