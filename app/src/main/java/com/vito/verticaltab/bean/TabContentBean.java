package com.vito.verticaltab.bean;

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
        "TabInfo"
})
public class TabContentBean {

    @JsonProperty("TabInfo")
    private List<TabInfo> tabInfo = new ArrayList<TabInfo>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * @return The tabInfo
     */
    @JsonProperty("TabInfo")
    public List<TabInfo> getTabInfo() {
        return tabInfo;
    }

    /**
     * @param tabInfo The TabInfo
     */
    @JsonProperty("TabInfo")
    public void setTabInfo(List<TabInfo> tabInfo) {
        this.tabInfo = tabInfo;
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
