package com.vito.verticaltab;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tonicartos.widget.stickygridheaders.StickyGridHeadersSimpleAdapter;
import com.vito.verticaltab.bean.ContentBody;
import com.vito.verticaltab.bean.TabContent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc on 2016/6/30.
 */
public class TabContentAdapter extends BaseViewAdapter<ContentBody> implements StickyGridHeadersSimpleAdapter {
    private List<ContentBody> mContentBodyList = new ArrayList<>();
    public TabContentAdapter(Context context, int layoutId) {
        super(context, layoutId);
        mInflater = LayoutInflater.from(context);
    }
    private LayoutInflater mInflater;

    @Override
    public long getHeaderId(int i) {
        Log.d("qh","i : " + i);
        Log.d("qh","id : " + mData.get(i).getCategoryId());
        return mData.get(i).getCategoryId();
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
        ContentBody body = getItem(position);
        Log.d("qh","head position: "+ position);
        mHeaderHolder.mTextView.setText(body.getCategory());

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
        ContentBody body = getItem(position);
//
        viewholder.titleView.setText(body.getItemName());
        Log.d("qh","position" + position );
        return convertView;
    }

    public List<ContentBody> getContentBodyList() {
        return mContentBodyList;
    }

    public void setContentBodyList(List<ContentBody> contentBodyList) {
        mContentBodyList = contentBodyList;
    }

    public final class ViewHolder {
        public TextView titleView;
    }
    public final class HeaderViewHolder {
        public TextView mTextView;
    }
}
