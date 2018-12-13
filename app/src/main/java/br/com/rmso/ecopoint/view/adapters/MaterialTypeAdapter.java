package br.com.rmso.ecopoint.view.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.rmso.ecopoint.PointTypesEnum;
import br.com.rmso.ecopoint.R;
import br.com.rmso.ecopoint.service.model.Point;
import br.com.rmso.ecopoint.view.callback.AdapterOnClick;

public class MaterialTypeAdapter extends RecyclerView.Adapter<MaterialTypeAdapter.MaterialTypeViewHolder>{

    private List<Point> mPointList;
    private final Context mContext;
    private final AdapterOnClick mMaterialTypeOnClick;

    public MaterialTypeAdapter(Context context, ArrayList<Point> pointList, AdapterOnClick genreOnClick){
        mContext = context;
        mPointList = pointList;
        mMaterialTypeOnClick = genreOnClick;
    }

    @NonNull
    @Override
    public MaterialTypeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_material_type, parent, false);
        return new MaterialTypeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MaterialTypeViewHolder holder, int position) {
        final Point point = mPointList.get(position);
        holder.mNameMaterialTypeTextView.setText(point.getTypeWaste());
    }

    @Override
    public int getItemCount() {
        if (mPointList != null) return mPointList.size(); else return 0;
    }

    public void setPoint(List<Point> pointList){
        if (mPointList != null){
            this.mPointList = pointList;
            notifyDataSetChanged();
        }
    }

    public class MaterialTypeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final ImageView mMaterialTypeImageView;
        public final TextView mNameMaterialTypeTextView;

        public MaterialTypeViewHolder(View itemView){
            super(itemView);

            mMaterialTypeImageView = itemView.findViewById(R.id.img_material_type);
            mNameMaterialTypeTextView = itemView.findViewById(R.id.tv_material_type);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int itemPosition = getAdapterPosition();
            mMaterialTypeOnClick.onClick(itemPosition);
        }
    }
}
