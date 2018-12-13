package br.com.rmso.ecopoint.view.activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import br.com.rmso.ecopoint.Constants;
import br.com.rmso.ecopoint.R;
import br.com.rmso.ecopoint.Utility;
import br.com.rmso.ecopoint.service.model.Point;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.tv_neighborhood)
    TextView mNeighborhoodTextView;
    @BindView(R.id.tv_address)
    TextView mAddressTextView;
    @BindView(R.id.tv_complement)
    TextView mComplementTextView;
    @BindView(R.id.tv_local)
    TextView mLocalTextView;
    @BindView(R.id.img_map)
    ImageView mMapImageView;
    @BindView(R.id.navigationView)
    BottomNavigationView mBottomNavigationView;

    private Point point;
    private int position;
    private String latitude;
    private String longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        position = getIntent().getExtras().getInt(Constants.bundlePoint, 0);
        point = Utility.mCurrentPointList.get(position);

        Configuration configuration = getResources().getConfiguration();
        int screenWidthDp = configuration.screenWidthDp;
        int screenHeightDp = configuration.screenHeightDp;


        if (point != null) {
            mNeighborhoodTextView.setText(point.getNeighborhood());
            mAddressTextView.setText(point.getAddress());
            mComplementTextView.setText(point.getComplement());
            mLocalTextView.setText(point.getNote());
            latitude = point.getLatitude();
            longitude = point.getLongitude();
            Picasso.with(this).load("http://maps.googleapis.com/maps/api/staticmap?&size=" + screenHeightDp + "x" + screenWidthDp + "&markers=" + latitude + "," + longitude + "%7C"
                    + "&key=" + Constants.keyStatic).into(mMapImageView);

        }

        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.n_favotites:

                        break;
                    case R.id.n_navigation:
                        Intent intent = new Intent(Intent.ACTION_VIEW,
                                Uri.parse("http://maps.google.com/maps?daddr="+latitude+","+longitude));
                                startActivity(intent);

                        break;
                    case R.id.n_share:
                        break;
                }
                return true;
            }
        });
    }
}
