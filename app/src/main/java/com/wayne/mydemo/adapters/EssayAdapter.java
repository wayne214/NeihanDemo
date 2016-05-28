package com.wayne.mydemo.adapters;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wayne.mydemo.R;
import com.wayne.mydemo.model.TextEssay;

import java.util.List;

/**
 * Project:MyDemo
 * Author:wayne
 * Date:2016/5/27
 */
public class EssayAdapter extends BaseAdapter {
    private Context mContext;
    private List<TextEssay> mItems;

    public EssayAdapter(Context context, List<TextEssay> items) {
        mContext = context;
        mItems = items;
    }

    @Override
    public int getCount() {
        int ret = 0;
        if (mItems != null) {
            ret = mItems.size();
        }
        return ret;
    }

    @Override
    public Object getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View ret = null;
        //1,检查view的复用
        if (convertView != null) {
            ret = convertView;
        } else {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            ret = inflater.inflate(R.layout.item_text_essay, parent, false);
        }
        //2，viewholder使用
        ViewHolder holder = (ViewHolder) ret.getTag();
        if (holder == null) {
            holder = new ViewHolder(ret);
            ret.setTag(holder);
        }
        //3.设置内容
        TextEssay essay= mItems.get(position);
        holder.mTextName.setText(essay.getUserName());
        holder.mTextContent.setText(essay.getContent());
        String avatarUrl = essay.getAvatarUrl();
        if (avatarUrl != null) {
            Picasso.with(mContext)
                    .load(avatarUrl)
                    .into(holder.mImageView);
        }
        return ret;
    }

    /**
     * 减少findviewById
     */
    private static class ViewHolder {
        public ImageView mImageView;
        public TextView mTextName;
        public TextView mTextContent;

        public ViewHolder(View itemView) {
            mImageView = (ImageView) itemView.findViewById(R.id.item_icon);
            mTextName = (TextView) itemView.findViewById(R.id.item_user_name);
            mTextContent = (TextView) itemView.findViewById(R.id.item_content);
        }
    }
}
