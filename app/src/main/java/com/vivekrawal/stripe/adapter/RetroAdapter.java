package com.vivekrawal.stripe.adapter;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import com.vivekrawal.stripe.R;
import com.vivekrawal.stripe.model.ModelListView;
import com.vivekrawal.stripe.model.RoundedTransformation;

import java.util.ArrayList;
public class RetroAdapter extends BaseAdapter{

    private Context context;
    private ArrayList<ModelListView> dataModelArrayList;

    public RetroAdapter(Context context, ArrayList<ModelListView> dataModelArrayList) {

        this.context = context;
        this.dataModelArrayList = dataModelArrayList;
    }

    @Override
    public int getViewTypeCount() {
        return getCount();
    }
    @Override
    public int getItemViewType(int position) {

        return position;
    }

    @Override
    public int getCount() {
        return dataModelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataModelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.retro_lv, null, true);

            holder.iv = (ImageView) convertView.findViewById(R.id.iv);
            holder.tvname = (TextView) convertView.findViewById(R.id.name);
            holder.tvcountry = (TextView) convertView.findViewById(R.id.country);
            holder.tvcity = (TextView) convertView.findViewById(R.id.city);
            holder.simpleRatingBar = (RatingBar) convertView.findViewById(R.id.simpleRatingBar);

            convertView.setTag(holder);
        }else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (ViewHolder)convertView.getTag();
        }

        Picasso.get().load(dataModelArrayList.get(position).getbackdrop_path()).transform(new RoundedTransformation(30, 0)).into(holder.iv);

        holder.tvname.setText("Movie: "+dataModelArrayList.get(position).getoriginal_title());
        holder.tvcountry.setText("Language: "+dataModelArrayList.get(position).getoriginal_language());
        holder.tvcity.setText("Release: "+dataModelArrayList.get(position).getrelease_date());
        holder.simpleRatingBar.setRating(Float.parseFloat(dataModelArrayList.get(position).getvote_average().toString()));

        return convertView;
    }

    private class ViewHolder {

        protected TextView tvname, tvcountry, tvcity;
        protected ImageView iv;
        protected RatingBar simpleRatingBar;
    }
}
