package br.com.rmso.ecopoint.view.widget;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;

import br.com.rmso.ecopoint.Constants;

/**
 * Created by Raquel on 12/12/2018.
 */

public class PointWidgetService extends IntentService {
    public static final String ACTION_UPDATE_RECIPE_WIDGETS = "br.com.rmso.ecopoint.action.update_point_widgets";
    static SharedPreferences sharedPreferences;

    public PointWidgetService(){
        super("PointWidgetService");
    }

    public static void startActionPoint(Context context) {
        Intent intent = new Intent(context, PointWidgetService.class);
        intent.setAction(ACTION_UPDATE_RECIPE_WIDGETS);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_UPDATE_RECIPE_WIDGETS.equals(action)) {
                handleActionUpdatePointWidgets();
            }
        }
    }

    private void handleActionUpdatePointWidgets() {

        Context context = getApplicationContext();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, PointWidgetProvider.class));

        String id = sharedPreferences.getString(Constants.PREF_KEY_LAST_POINT_ID, "Indefinido");
        String neighborhood = sharedPreferences.getString(Constants.PREF_KEY_LAST_POINT_NEIGHBORHOOD, "Indefinido");
        String address = sharedPreferences.getString(Constants.PREF_KEY_LAST_POINT_ADDRESS, "Indefinido");
        String note = sharedPreferences.getString(Constants.PREF_KEY_LAST_POINT_COMPLEMENT, "Indefinido");

        PointWidgetProvider.updateWidget(this, appWidgetManager, id, neighborhood, address, note, appWidgetIds);

    }
}
