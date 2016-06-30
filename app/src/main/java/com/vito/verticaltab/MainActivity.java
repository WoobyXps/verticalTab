package com.vito.verticaltab;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends Activity implements TabHost.TabContentFactory {
    private GridView mGridView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        final TabHost tabHost = (TabHost) findViewById(R.id.tabHost);
        tabHost.setup();

        TabWidget tw = tabHost.getTabWidget();
        tw.setOrientation(LinearLayout.VERTICAL);
        tabHost.addTab(tabHost.newTabSpec("tab1")
                .setIndicator(createIndicatorView(tabHost, "水果蔬菜", null))
                .setContent(this));
        tabHost.addTab(tabHost.newTabSpec("tab2")
                .setIndicator(createIndicatorView(tabHost, "肉禽蛋奶", null))
                .setContent(this));
        tabHost.addTab(tabHost.newTabSpec("tab3")
                .setIndicator(createIndicatorView(tabHost, "冷热素食", null))
                .setContent(this));
        tabHost.addTab(tabHost.newTabSpec("tab4")
                .setIndicator(createIndicatorView(tabHost, "休闲食品", null))
                .setContent(this));
        tabHost.addTab(tabHost.newTabSpec("tab5")
                .setIndicator(createIndicatorView(tabHost, "酒水饮料", null))
                .setContent(this));
        updateTab(tabHost);
        tabHost.getTabContentView().setBackgroundColor(Color.WHITE);
        tabHost.getTabWidget().setDividerDrawable(R.color.text_selected);

        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                updateTab(tabHost);
            }
        });
        mGridView = (GridView) findViewById(R.id.asset_grid);
        TabContentAdapter adapter = new TabContentAdapter(this, R.layout.item);
        ArrayList<TabContentBean> list = new ArrayList<>();
        TabContentBean bean1 = new TabContentBean();
        bean1.setTitle("AAAA");
        TabContentBean bean2 = new TabContentBean();
        bean2.setTitle("BBBB");
        TabContentBean bean3 = new TabContentBean();
        bean3.setTitle("CCCC");
        list.add(bean1);
        list.add(bean2);
        list.add(bean3);
        list.add(bean3);
        list.add(bean3);
        list.add(bean3);
        list.add(bean3);

        adapter.setData(list);
        mGridView.setAdapter(adapter);
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
                view.setSelected(true);
//                view.setBackgroundColor(Color.WHITE);//选中后的背景
                tv.setTextColor(getResources().getColor(R.color.text_selected));
                sideView.setVisibility(View.VISIBLE);
                tabIndicator.setBackgroundColor(Color.WHITE);

            } else {//不选中
                view.setSelected(false);
//                view.setBackgroundColor(getResources().getColor(R.color.base_bg));
                tv.setTextColor(getResources().getColor(R.color.text_not_select));
                sideView.setVisibility(View.GONE);
                tabIndicator.setBackground(getResources().getDrawable(R.drawable.tab_item_bg));

            }
        }
    }


    private View createIndicatorView(TabHost tabHost, CharSequence label, Drawable icon) {

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

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
        final TextView tv = new TextView(this);
        return tv;
    }


}
