package br.com.rmso.ecopoint.view.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.core.CrashlyticsCore;
import com.crashlytics.android.ndk.CrashlyticsNdk;
import com.crashlytics.android.ndk.CrashlyticsNdk;

import br.com.rmso.ecopoint.BuildConfig;
import io.fabric.sdk.android.Fabric;
import java.util.ArrayList;

import br.com.rmso.ecopoint.Constants;
import br.com.rmso.ecopoint.R;
import br.com.rmso.ecopoint.database.AppDatabase;
import br.com.rmso.ecopoint.service.model.Point;
import br.com.rmso.ecopoint.view.adapters.MaterialTypeAdapter;
import br.com.rmso.ecopoint.view.callback.AdapterOnClick;
import br.com.rmso.ecopoint.viewmodel.PointViewModel;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements AdapterOnClick{

    private MaterialTypeAdapter mMaterialTypeAdapter;
    private ArrayList<Point> mPointList;
    private GridLayoutManager mLayoutManager;
    private AppDatabase mDb;
    private PointViewModel pointViewModel;

    @BindView(R.id.rv_material_type)
    RecyclerView mPointRecylcerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Crashlytics crashlyticsKit = new Crashlytics.Builder()
                .core(new CrashlyticsCore.Builder().disabled(BuildConfig.DEBUG).build())
                .build();
        Fabric.with(this,crashlyticsKit);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mPointList = new ArrayList<>();
        mLayoutManager = new GridLayoutManager(this, 2);
        mPointRecylcerView.setLayoutManager(mLayoutManager);
        mPointRecylcerView.setHasFixedSize(true);
        mMaterialTypeAdapter = new MaterialTypeAdapter(MainActivity.this, mPointList, this);
        mPointRecylcerView.setAdapter(mMaterialTypeAdapter);

        pointViewModel = ViewModelProviders.of(this).get(PointViewModel.class);
        pointViewModel.getPoint().observe(this, points -> {
            if (points != null) {
                mMaterialTypeAdapter.setPoint(points);
                mMaterialTypeAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onClick(int position) {
        Intent intent = new Intent(MainActivity.this, ListPointActivity.class);
        intent.putExtra(Constants.bundlePoints, position);
        startActivity(intent);
    }
}
