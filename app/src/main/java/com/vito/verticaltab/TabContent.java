package com.vito.verticaltab;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "contentHead",
        "contentBody"
})
public class TabContent {

    @JsonProperty("contentHead")
    private String contentHead;
    @JsonProperty("contentBody")
    private List<ContentBody> contentBody = new ArrayList<ContentBody>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * @return The contentHead
     */
    @JsonProperty("contentHead")
    public String getContentHead() {
        return contentHead;
    }

    /**
     * @param contentHead The contentHead
     */
    @JsonProperty("contentHead")
    public void setContentHead(String contentHead) {
        this.contentHead = contentHead;
    }

    /**
     * @return The contentBody
     */
    @JsonProperty("contentBody")
    public List<ContentBody> getContentBody() {
        return contentBody;
    }

    /**
     * @param contentBody The contentBody
     */
    @JsonProperty("contentBody")
    public void setContentBody(List<ContentBody> contentBody) {
        this.contentBody = contentBody;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
