package com.hm.numberninja

import android.appwidget.AppWidgetProvider
import android.appwidget.AppWidgetManager
import android.content.Context
import android.widget.RemoteViews

class WidgetProvider : AppWidgetProvider() {

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // Perform operations when the widget is updated

        // Loop through all widgets
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    companion object {
        internal fun updateAppWidget(
            context: Context,
            appWidgetManager: AppWidgetManager,
            appWidgetId: Int
        ) {
            // Create an instance of RemoteViews and set its layout
            val views = RemoteViews(context.packageName, R.layout.widget_layout)

            // Set some text on the widget
            views.setTextViewText(R.id.widgetTextView, "Number Ninja")

            views.setImageViewResource(R.id.widgetImageView, R.drawable.ic_launcher)

            // Update the widget
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }
}
