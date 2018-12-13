package br.com.rmso.ecopoint.view.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.RemoteViews;

import br.com.rmso.ecopoint.Constants;
import br.com.rmso.ecopoint.R;
import br.com.rmso.ecopoint.Utility;
import br.com.rmso.ecopoint.view.activities.DetailActivity;

/**
 * Created by Raquel on 12/12/2018.
 */

public class PointWidgetProvider extends AppWidgetProvider {
    static SharedPreferences sharedPreferences;

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, String pointId, String neighborhood, String address, String note, int appWidgetId) {

        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(Constants.bundlePoint, pointId);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.point_widget);
        views.setTextViewText(R.id.widget_neighborhood, neighborhood);
        views.setTextViewText(R.id.widget_address, address);
        views.setTextViewText(R.id.widget_note, note);
        views.setOnClickPendingIntent(R.id.widget_address, pendingIntent);

        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    static void updateWidget (Context context, AppWidgetManager appWidgetManager, String pointId, String neighborhood, String address, String note, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds){
            updateAppWidget(context, appWidgetManager, pointId, neighborhood, address, note, appWidgetId);
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        PointWidgetService.startActionPoint(context);
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
    }

    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
    }

    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
    }
}
