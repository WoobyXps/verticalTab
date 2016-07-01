package com.vito.verticaltab;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import com.vito.verticaltab.bean.ContentBody;
import com.vito.verticaltab.bean.TabContent;
import com.vito.verticaltab.bean.TabContentBean;
import com.vito.verticaltab.bean.TabInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pc on 2016/6/30.
 */
public class VerticalTabView implements TabHost.TabContentFactory {
    Activity mContext;
    TabContentBean contentBean;

    public VerticalTabView(Activity context) {
        mContext = context;
    }

    public void initVerticalTabView() {
        final TabHost tabHost = (TabHost) mContext.findViewById(R.id.tabHost);
        tabHost.setup();
        JsonLoader jsonLoader = new JsonLoader(mContext);
        contentBean = jsonLoader.genContentWithLocalJson();

        initTabWidget(tabHost, contentBean);
    }

    private void initTabWidget(final TabHost tabHost, TabContentBean contentBean) {

        TabWidget tw = tabHost.getTabWidget();
        tw.setOrientation(LinearLayout.VERTICAL);

        for (int i = 0; i < contentBean.getTabInfo().size(); i++) {
            TabInfo tabInfo = contentBean.getTabInfo().get(i);
            tabHost.addTab(tabHost.newTabSpec(String.valueOf(i)).setIndicator(createIndicatorView(tabHost, tabInfo.getTabWidget(), null)).setContent(this));
        }
        updateTabHost(tabHost);

        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                updateTabHost(tabHost);
            }
        });
    }
    private void updateTabHost(final TabHost tabHost) {
        updateWidget(tabHost);
        updateContent(tabHost);
    }
    /**
     * 更新Tab标签的颜色，和字体的颜色
     *
     * @param tabHost
     */
    private void updateWidget(final TabHost tabHost) {
        for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
            RelativeLayout tabIndicator = (RelativeLayout) tabHost.getTabWidget().getChildAt(i).findViewById(R.id.tab_indicator);
            TextView tv = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(R.id.title);
            View sideView = tabHost.getTabWidget().getChildAt(i).findViewById(R.id.side_view);
            if (tabHost.getCurrentTab() == i) {//选中.
                tv.setTextColor(mContext.getResources().getColor(R.color.text_selected));
                sideView.setVisibility(View.VISIBLE);
                tabIndicator.setBackgroundColor(Color.WHITE);

            } else {//不选中
                tv.setTextColor(mContext.getResources().getColor(R.color.text_not_select));
                sideView.setVisibility(View.GONE);
                tabIndicator.setBackground(mContext.getResources().getDrawable(R.drawable.tab_item_bg));

            }
        }
    }

    private void updateContent(TabHost tabHost) {
        int tabId = Integer.valueOf(tabHost.getCurrentTabTag());
        List<TabContent> contents = contentBean.getTabInfo().get(tabId).getTabContent();
        ArrayList<ContentBody> contentBodyList = new ArrayList<>();
        for (TabContent content : contents) {
            for (ContentBody body : content.getContentBody()) {
                body.setCategory(content.getContentHead());
                contentBodyList.add(body);
            }
        }

        TabContentAdapter mTabContentAdapter = new TabContentAdapter(mContext, R.layout.item);
        mTabContentAdapter.setData(generateHeaderId(contentBodyList));
        GridView mTabContentGridView = (GridView) mContext.findViewById(R.id.asset_grid);
        mTabContentGridView.setAdapter(mTabContentAdapter);
    }


    private View createIndicatorView(TabHost tabHost, CharSequence label, Drawable icon) {

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View tabIndicator = inflater.inflate(R.layout.tab_indicator,
                tabHost.getTabWidget(),false);

        final TextView tv = (TextView) tabIndicator.findViewById(R.id.title);
        tv.setText(label);

        final ImageView iconView = (ImageView) tabIndicator.findViewById(R.id.icon);
        iconView.setImageDrawable(icon);

        return tabIndicator;
    }

    @Override
    public View createTabContent(String tag) {
        TextView tv = new TextView(mContext);
            return tv;
    }

    private List<ContentBody> generateHeaderId(List<ContentBody> nonHeaderIdList) {
        Map<String, Integer> mHeaderIdMap = new HashMap<>();
        int mHeaderId = 1;
        List<ContentBody> hasHeaderIdList;
        for(ContentBody contentBody : nonHeaderIdList){

            String category = contentBody.getCategory();
            if(!mHeaderIdMap.containsKey(category)){
                contentBody.setCategoryId(mHeaderId);
                mHeaderIdMap.put(category, mHeaderId);
                mHeaderId ++;
            }else{
                contentBody.setCategoryId(mHeaderIdMap.get(category));
            }
        }
        hasHeaderIdList = nonHeaderIdList;

        return hasHeaderIdList;
    }

}
