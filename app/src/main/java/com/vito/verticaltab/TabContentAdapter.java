package com.vito.verticaltab;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tonicartos.widget.stickygridheaders.StickyGridHeadersSimpleAdapter;

/**
 * Created by pc on 2016/6/30.
 */
public class TabContentAdapter extends BaseViewAdapter<ContentBody> implements StickyGridHeadersSimpleAdapter {
    public TabContentAdapter(Context context, int layoutId) {
        super(context, layoutId);
        mInflater = LayoutInflater.from(context);
    }
    private LayoutInflater mInflater;

    @Override
    public long getHeaderId(int i) {
        return 0;
    }

    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        HeaderViewHolder mHeaderHolder = null;
        if (convertView == null) {
            mHeaderHolder = new HeaderViewHolder();
            convertView = mInflater.inflate(R.layout.header, parent, false);
            mHeaderHolder.mTextView = (TextView) convertView
                    .findViewById(R.id.header);
            convertView.setTag(mHeaderHolder);
        } else {
            mHeaderHolder = (HeaderViewHolder) convertView.getTag();
        }
        mHeaderHolder.mTextView.setText("header");

        return convertView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewholder = null;
        if (convertView == null) {
            //获取list_item布局文件的视图
            convertView = super.getView(position, convertView, parent);
            //获取控件对象
            viewholder = new ViewHolder();
            viewholder.titleView = (TextView) convertView.findViewById(R.id.title);
            convertView.setTag(viewholder);

        } else
            viewholder = (ViewHolder) convertView.getTag();

        ContentBody tempbean = getItem(position);
        viewholder.titleView.setText(tempbean.getItemName());
        return convertView;
    }

    public final class ViewHolder {
        public TextView titleView;
    }
    public final class HeaderViewHolder {
        public TextView mTextView;
    }
}
