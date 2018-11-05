package br.com.rmso.ecopoint.view.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import br.com.rmso.ecopoint.R;
import br.com.rmso.ecopoint.service.model.Point;
import br.com.rmso.ecopoint.view.adapters.MaterialTypeAdapter;
import br.com.rmso.ecopoint.view.callback.AdapterOnClick;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements AdapterOnClick{

    private MaterialTypeAdapter mMaterialTypeAdapter;
    private ArrayList<Point> mPointList;
    private GridLayoutManager mLayoutManager;

    @BindView(R.id.rv_material_type)
    RecyclerView mPointRecylcerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mPointList = new ArrayList<>();
        mLayoutManager = new GridLayoutManager(this, 2);
        mPointRecylcerView.setLayoutManager(mLayoutManager);
        mPointRecylcerView.setHasFixedSize(true);
        mMaterialTypeAdapter = new MaterialTypeAdapter(this, mPointList, this);
        mPointRecylcerView.setAdapter(mMaterialTypeAdapter);
    }

    @Override
    public void onClick(int position) {

    }
}
