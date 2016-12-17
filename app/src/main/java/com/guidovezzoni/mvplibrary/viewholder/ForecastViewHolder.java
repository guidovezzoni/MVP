package com.guidovezzoni.mvplibrary.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.guidovezzoni.mvplibrary.R;


public class ForecastViewHolder extends RecyclerView.ViewHolder {
    public ImageView mIcon;
    public TextView mDate;
    public TextView mTime;
    public TextView mTemperature;

    public ForecastViewHolder(View itemView) {
        super(itemView);

        mIcon = (ImageView) itemView.findViewById(R.id.icon);
        mDate = (TextView) itemView.findViewById(R.id.date);
        mTime = (TextView) itemView.findViewById(R.id.time);
        mTemperature = (TextView) itemView.findViewById(R.id.temperature);
    }


}
