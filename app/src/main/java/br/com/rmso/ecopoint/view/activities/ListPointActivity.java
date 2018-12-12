package br.com.rmso.ecopoint.view.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import java.util.ArrayList;

import br.com.rmso.ecopoint.R;
import br.com.rmso.ecopoint.service.model.Point;
import br.com.rmso.ecopoint.view.adapters.PointAdapter;
import br.com.rmso.ecopoint.view.callback.AdapterOnClick;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ListPointActivity extends AppCompatActivity implements AdapterOnClick {

    private PointAdapter mPointAdapter;
    private LinearLayoutManager mLayoutManager;
    private ArrayList<Point> mPointList;

    @BindView(R.id.rv_points)
    RecyclerView mPointRecylcerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_point);
        ButterKnife.bind(this);

        mPointList = new ArrayList<>();
        mLayoutManager = new LinearLayoutManager(this);
        mPointRecylcerView.setLayoutManager(mLayoutManager);
        mPointRecylcerView.setHasFixedSize(true);
        mPointAdapter = new PointAdapter(ListPointActivity.this, mPointList, this);
        mPointRecylcerView.setAdapter(mPointAdapter);
    }

    @Override
    public void onClick(int position) {

    }
}
