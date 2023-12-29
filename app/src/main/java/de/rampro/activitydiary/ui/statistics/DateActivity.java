/*
 * ActivityDiary
 *
 * Copyright (C) 2023 Raphael Mack http://www.raphael-mack.de
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package de.rampro.activitydiary.ui.statistics;

import static de.rampro.activitydiary.R.id.nav_about;
import static de.rampro.activitydiary.R.id.nav_date;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import org.osmdroid.config.Configuration;

import java.util.Calendar;

import de.rampro.activitydiary.R;
import de.rampro.activitydiary.ui.generic.BaseActivity;
import de.rampro.activitydiary.ui.history.HistoryActivity;
import de.rampro.activitydiary.ui.main.MainActivity;

public class DateActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Context ctx = getApplicationContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));

        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View contentView = inflater.inflate(R.layout.activity_canlendar, null, false);

        setContent(contentView);

        CalendarView calendarview = (CalendarView) findViewById(R.id.calendarview);

        calendarview.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                int month1=month+1;
                Log.d("TAG","month1:"+month1);
                Toast.makeText(DateActivity.this, "The date you choose is: " + year + "." + month1 + "." + dayOfMonth + ".", Toast.LENGTH_SHORT).show();
                String temp =year+"."+month1+"."+dayOfMonth;
                Intent inte = new Intent(DateActivity.this,HistoryActivity.class);
                inte.putExtra("date",temp);
                inte.setAction("de.rampro.activitydiary.action.SEARCH_DATE");
                startActivity(inte);

            }
        });

        mDrawerToggle.setDrawerIndicatorEnabled(false);
    }

    @Override
    public void onResume(){
        mNavigationView.getMenu().findItem(nav_date).setChecked(true);
        super.onResume();
    }
}
