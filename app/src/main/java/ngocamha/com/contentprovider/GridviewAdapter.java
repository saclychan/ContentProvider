package ngocamha.com.contentprovider;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by PL on 7/16/2017.
 */

public class GridviewAdapter extends BaseAdapter{
    private Context mContext;
    private ArrayList<Bitmap> mData;
    private LayoutInflater mLayoutInflater;


    public GridviewAdapter(Context mContext, ArrayList<Bitmap> mData) {
        this.mContext = mContext;
        this.mData = mData;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int i) {
        return mData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view == null){
            view = mLayoutInflater.inflate(R.layout.item_gridview, viewGroup, false);
            viewHolder = new ViewHolder();
            viewHolder.iv  = (ImageView) view.findViewById(R.id.iv);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.iv.setImageBitmap(mData.get(i));

        return view;
    }

    private static class ViewHolder{
        ImageView iv;
    }
}

