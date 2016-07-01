package com.vito.verticaltab;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tonicartos.widget.stickygridheaders.StickyGridHeadersSimpleAdapter;
import com.vito.verticaltab.bean.ContentBody;

/**
 * Created by pc on 2016/6/30.
 */
public class TabContentAdapter extends BaseViewAdapter<ContentBody> implements StickyGridHeadersSimpleAdapter {
    private LayoutInflater mInflater;

    public TabContentAdapter(Context context, int layoutId) {
        super(context, layoutId);
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public long getHeaderId(int i) {
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
        mHeaderHolder.mTextView.setText(body.getCategory());

        return convertView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BodyViewHolder viewholder = null;
        if (convertView == null) {
            //获取list_item布局文件的视图
            convertView = super.getView(position, convertView, parent);
            //获取控件对象
            viewholder = new BodyViewHolder();
            viewholder.titleView = (TextView) convertView.findViewById(R.id.title);
            viewholder.imageView = (ImageView) convertView.findViewById(R.id.image);
            convertView.setTag(viewholder);

        } else
            viewholder = (BodyViewHolder) convertView.getTag();
        ContentBody body = getItem(position);
        String internetUrl = body.getItemImage();
        viewholder.titleView.setText(body.getItemName());
        Glide.with(mContext)
                .load(internetUrl)
                .placeholder(R.drawable.sq_10)
                .error(R.drawable.sq_12)
                .fitCenter()
                .crossFade()
                .into(viewholder.imageView);
        return convertView;
    }

    public final class BodyViewHolder {
        public TextView titleView;
        public ImageView imageView;
    }

    public final class HeaderViewHolder {
        public TextView mTextView;
    }
}
