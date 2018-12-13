package br.com.rmso.ecopoint.view.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Set;

import br.com.rmso.ecopoint.AppExecutors;
import br.com.rmso.ecopoint.Constants;
import br.com.rmso.ecopoint.R;
import br.com.rmso.ecopoint.Utility;
import br.com.rmso.ecopoint.database.AppDatabase;
import br.com.rmso.ecopoint.service.model.Point;
import br.com.rmso.ecopoint.view.widget.PointWidgetService;
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
    private AppDatabase mDb;
    private int idPoint;
    private boolean  isFavorite = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        mDb = AppDatabase.getInstance(getApplicationContext());
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
            idPoint = Integer.parseInt(point.getId());
            latitude = point.getLatitude();
            longitude = point.getLongitude();
            Picasso.with(this).load("http://maps.googleapis.com/maps/api/staticmap?&size=" + screenHeightDp + "x" + screenWidthDp + "&markers=" + latitude + "," + longitude + "%7C"
                    + "&key=" + Constants.keyStatic).into(mMapImageView);

            pointDB(String.valueOf(idPoint));

        }

        mBottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.n_navigation:
                    Intent intent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://maps.google.com/maps?daddr="+latitude+","+longitude));
                            startActivity(intent);
                    break;
                case R.id.n_share:
                    String type = Utility.mCurrentPointList.get(position).getTypeWaste();
                    String address = Utility.mCurrentPointList.get(position).getAddress();
                    Intent intentShare = new Intent(Intent.ACTION_SEND);
                    String textSend = getResources().getString(R.string.title_type_send) + " " + type + "\n" +
                            getResources().getString(R.string.title_adrress_send) + " " + address;
                    intentShare.putExtra(Intent.EXTRA_TEXT, textSend);
                    intentShare.setType("text/plain");
                    startActivity(intentShare);
                    break;
            }
            return true;
        });

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        sharedPreferences.edit().putString(Constants.PREF_KEY_LAST_POINT_ID, point.getId()).apply();
        sharedPreferences.edit().putString(Constants.PREF_KEY_LAST_POINT_NEIGHBORHOOD, point.getNeighborhood()).apply();
        sharedPreferences.edit().putString(Constants.PREF_KEY_LAST_POINT_ADDRESS, point.getAddress()).apply();
        sharedPreferences.edit().putString(Constants.PREF_KEY_LAST_POINT_COMPLEMENT, point.getComplement()).apply();

        PointWidgetService.startActionPoint(getApplicationContext());
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        int itemid = item.getItemId();
        final int idPoint = Integer.parseInt(point.getId());

        switch (itemid){
            case R.id.action_favorites:
                AppExecutors.getInstance().diskIO().execute(() -> {
                    List<Point> points = mDb.pointDao().loadPointByUId(idPoint);

                    if (points.size() > 0) {
                        onDeleteFavorites();
                        runOnUiThread(() -> item.setIcon(R.drawable.baseline_star_border_white_24));

                    }else {
                        onSaveFavorites();
                        runOnUiThread(() -> item.setIcon(R.drawable.baseline_star_white_24));
                    }
                });
                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_favorites, menu);

        if (isFavorite) {
            menu.getItem(0).setIcon(R.drawable.baseline_star_white_24);
        }else {
            menu.getItem(0).setIcon(R.drawable.baseline_star_border_white_24);
        }

        return true;
    }

    private void pointDB(String id) {
        final int idInt =  Integer.parseInt(id);
        AppExecutors.getInstance().diskIO().execute(() -> {
            List<Point> points = mDb.pointDao().loadPointByUId(idInt);

            if (points.size() > 0){
                isFavorite = true;
            }
        });
    }

    public void onSaveFavorites(){
        final Point newPoint = new Point(point.getIdKey(), point.getId(), point.getTypeWaste(), point.getNeighborhood(), point.getNote(),
                point.getLongitude(), point.getLatitude(), point.getAddress(), point.getComplement());
        AppExecutors.getInstance().diskIO().execute(() -> mDb.pointDao().insertPoint(newPoint));
    }

    public void onDeleteFavorites(){
        AppExecutors.getInstance().diskIO().execute(() -> {
            List<Point> points = mDb.pointDao().loadPointByUId(Integer.parseInt(point.getId()));
            mDb.pointDao().deletePoint(points.get(0));
        });
    }
}
