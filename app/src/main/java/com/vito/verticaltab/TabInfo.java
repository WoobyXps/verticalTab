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
        "tabWidget",
        "tabContent"
})
public class TabInfo {

    @JsonProperty("tabWidget")
    private String tabWidget;
    @JsonProperty("tabContent")
    private List<TabContent> tabContent = new ArrayList<TabContent>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * @return The tabWidget
     */
    @JsonProperty("tabWidget")
    public String getTabWidget() {
        return tabWidget;
    }

    /**
     * @param tabWidget The tabWidget
     */
    @JsonProperty("tabWidget")
    public void setTabWidget(String tabWidget) {
        this.tabWidget = tabWidget;
    }

    /**
     * @return The tabContent
     */
    @JsonProperty("tabContent")
    public List<TabContent> getTabContent() {
        return tabContent;
    }

    /**
     * @param tabContent The tabContent
     */
    @JsonProperty("tabContent")
    public void setTabContent(List<TabContent> tabContent) {
        this.tabContent = tabContent;
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
