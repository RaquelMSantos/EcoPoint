package br.com.rmso.ecopoint.view.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.rmso.ecopoint.R;
import br.com.rmso.ecopoint.service.model.Point;
import br.com.rmso.ecopoint.view.callback.AdapterOnClick;

public class PointAdapter extends RecyclerView.Adapter<PointAdapter.PointViewHolder>{

    private List<Point> mPointList;
    private final Context mContext;
    private final AdapterOnClick mPointOnClick;

    public PointAdapter (Context context, ArrayList<Point> pointList, AdapterOnClick pointOnClick){
        mContext = context;
        mPointList = pointList;
        mPointOnClick = pointOnClick;
    }

    @NonNull
    @Override
    public PointViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_point_data, parent, false);
        return new PointViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PointViewHolder holder, int position) {
        final Point point = mPointList.get(position);
        holder.mAddressTextView.setText(point.getAddress());
        holder.mNeighborhoodTextView.setText(point.getNeighborhood());
    }

    @Override
    public int getItemCount() {
        if (mPointList != null) return mPointList.size(); else return 0;
    }

    public class PointViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView mNeighborhoodTextView;
        public final TextView mAddressTextView;

        public PointViewHolder(View itemView) {
            super(itemView);
            mNeighborhoodTextView = itemView.findViewById(R.id.tv_neighborhood);
            mAddressTextView = itemView.findViewById(R.id.tv_address);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            int itemPosition = getAdapterPosition();
            mPointOnClick.onClick(itemPosition);
        }
    }
}
