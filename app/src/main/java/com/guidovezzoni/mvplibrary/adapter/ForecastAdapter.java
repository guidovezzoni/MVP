package com.guidovezzoni.mvplibrary.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.guidovezzoni.mvplibrary.R;
import com.guidovezzoni.mvplibrary.model.List_;
import com.guidovezzoni.mvplibrary.utils.DateHelper;
import com.guidovezzoni.mvplibrary.viewholder.ForecastViewHolder;

import java.util.List;

/**
 * Created by guido on 09/12/16.
 */

public class ForecastAdapter extends RecyclerView.Adapter<ForecastViewHolder> {
    private static final String ICONS_URL_PARAM = "http://openweathermap.org/img/w/%s.png";
    private static final String DEG_CENT = " °C";
    private static final String NA = "N/A";
    ;

    private List<List_> mList;
    private Context mContext;

    public ForecastAdapter(Context context, List<List_> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public ForecastViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_forecast, parent, false);
        return new ForecastViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ForecastViewHolder holder, int position) {
        List_ list_ = mList.get(position);

        holder.mDate.setText(DateHelper.getFormattedDate(DateHelper.DAY_OF_WEEK_TEXT_FORMAT, list_.dtTxt));
        holder.mTime.setText(DateHelper.getFormattedDate(DateHelper.TIME_HOURS_ONLY_FORMAT, list_.dtTxt));

        if (list_.main != null && list_.main.temp != null) {
            // TODO °C should be handled at business logic level, and coordinated with param sent to the REST service
            holder.mTemperature.setText(list_.main.temp.toString() + DEG_CENT);
        } else {
            holder.mTemperature.setText(NA);
        }

        String url;
        if (list_.weather != null && list_.weather.size() > 0 && list_.weather.get(0).icon != null) {
            url = String.format(ICONS_URL_PARAM, list_.weather.get(0).icon);
        } else {
            // Glide will load the placeholder
            url = "";
        }

        Glide.with(mContext)
                .load(url)
                .centerCrop()
                .placeholder(R.drawable.ic_cached_black_24dp)
                .into(holder.mIcon);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
