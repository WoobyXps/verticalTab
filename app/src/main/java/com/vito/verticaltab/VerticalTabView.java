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
import java.util.ListIterator;
import java.util.Map;

/**
 * Created by pc on 2016/6/30.
 */
public class VerticalTabView implements TabHost.TabContentFactory {
    TabContentAdapter mTabContentAdapter;
    Activity mContext;
    TabContentBean contentBean;
    private GridView mTabContentGridView;

    public VerticalTabView(Activity context) {
        mContext = context;
    }

    public void initVerticalTabView() {
        final TabHost tabHost = (TabHost) mContext.findViewById(R.id.tabHost);
        tabHost.setup();
        JsonLoader jsonLoader = new JsonLoader(mContext);
        contentBean = jsonLoader.genContentWithLocalJson();
        mTabContentAdapter = new TabContentAdapter(mContext, R.layout.item);

        initTabWidget(tabHost, contentBean);
        initTabContentAdapter();
        mTabContentGridView = (GridView) mContext.findViewById(R.id.asset_grid);
        mTabContentGridView.setAdapter(mTabContentAdapter);
    }

    private void initTabWidget(final TabHost tabHost, TabContentBean contentBean) {

        TabWidget tw = tabHost.getTabWidget();

        tw.setOrientation(LinearLayout.VERTICAL);
        Log.d("qh", "size : " + contentBean.getTabInfo().size());

        for (int i = 0; i < contentBean.getTabInfo().size(); i++) {
            TabInfo tabInfo = contentBean.getTabInfo().get(i);
            Log.d("qh", tabInfo.getTabWidget());
            tabHost.addTab(tabHost.newTabSpec("tab" + i).setIndicator(createIndicatorView(tabHost, tabInfo.getTabWidget(), null)).setContent(this));
        }
        updateTab(tabHost);
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                updateTab(tabHost);
            }
        });
    }

    public void initTabContentAdapter() {
//        ArrayList<TabContentBean> list = new ArrayList<>();
//        TabContentBean bean1 = new TabContentBean();
//        bean1.setTitle("AAAA");
//        TabContentBean bean2 = new TabContentBean();
//        bean2.setTitle("BBBB");
//        TabContentBean bean3 = new TabContentBean();
//        bean3.setTitle("CCCC");
//        list.add(bean1);
//        list.add(bean2);
//        list.add(bean3);
//        list.add(bean3);
//        list.add(bean3);
//        list.add(bean3);
//        list.add(bean3);
//
//        mTabContentAdapter.setData();
    }

    /**
     * 更新Tab标签的颜色，和字体的颜色
     *
     * @param tabHost
     */
    private void updateTab(final TabHost tabHost) {
        for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
            View view = tabHost.getTabWidget().getChildAt(i);
            RelativeLayout tabIndicator = (RelativeLayout) tabHost.getTabWidget().getChildAt(i).findViewById(R.id.tab_indicator);
            TextView tv = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(R.id.title);
            View sideView = tabHost.getTabWidget().getChildAt(i).findViewById(R.id.side_view);
            tv.setTextSize(16);
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


    private View createIndicatorView(TabHost tabHost, CharSequence label, Drawable icon) {

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View tabIndicator = inflater.inflate(R.layout.tab_indicator,
                tabHost.getTabWidget(), // tab widget is the parent
                false); // no inflate params

        final TextView tv = (TextView) tabIndicator.findViewById(R.id.title);
        tv.setText(label);

        final ImageView iconView = (ImageView) tabIndicator.findViewById(R.id.icon);
        iconView.setImageDrawable(icon);

        return tabIndicator;
    }

    @Override
    public View createTabContent(String tag) {
        List<TabContent> contents = contentBean.getTabInfo().get(0).getTabContent();
        ArrayList<ContentBody> contentBodyList = new ArrayList<>();
        for (TabContent content : contents) {
            for (ContentBody body : content.getContentBody()) {
                body.setCategory(content.getContentHead());
                contentBodyList.add(body);
            }
        }

        mTabContentAdapter.setData(contentBodyList);


        final TextView tv = new TextView(mContext);
            return tv;

    }


    private List<ContentBody> generateHeaderId(List<ContentBody> nonHeaderIdList) {
        Map<String, Integer> mHeaderIdMap = new HashMap<String, Integer>();
        int mHeaderId = 1;
        List<ContentBody> hasHeaderIdList;

        for(ListIterator<ContentBody> it = nonHeaderIdList.listIterator(); it.hasNext();){
            ContentBody contentBody = it.next();

            ContentBody mGridItem = it.next();
            String category = contentBody.getCategory();
            if(!mHeaderIdMap.containsKey(category)){
                mGridItem.setCategoryId(mHeaderId);
                mHeaderIdMap.put(category, mHeaderId);
                mHeaderId ++;
            }else{
                mGridItem.setCategoryId(mHeaderIdMap.get(category));
            }
        }
        hasHeaderIdList = nonHeaderIdList;

        return hasHeaderIdList;
    }
    private void getDateFromJson() {
    }
}
