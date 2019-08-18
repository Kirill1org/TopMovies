package com.koromyslov.topmovies.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.TimePicker;
import android.widget.Toast;


import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.koromyslov.topmovies.IFilmView;
import com.koromyslov.topmovies.Notification.AlertReceiver;
import com.koromyslov.topmovies.Presenter.FilmPresenter;
import com.koromyslov.topmovies.R;
import com.koromyslov.topmovies.ResponseDAO.Film;

import java.util.Calendar;
import java.util.List;

public class MainActivity extends MvpAppCompatActivity implements IFilmView, TimePickerDialog.OnTimeSetListener {

    @InjectPresenter
    FilmPresenter mFilmPresenter;

    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private RecyclerView rv;
    private ProgressBar progressBar;
    private AlarmManager alarmManager;


    private final RVAdapter.OnItemClickListener clickListener = filmUnit -> {

        mFilmPresenter.onBtnScheduleClick(filmUnit.getFilmTitle(), filmUnit.getFilmID());

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv = findViewById(R.id.rv);
        progressBar = findViewById(R.id.progressBar);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (datePickerDialog != null) datePickerDialog.dismiss();
        if (timePickerDialog != null) timePickerDialog.dismiss();
    }

    @Override
    public void showFilmList(List<Film> filmList) {
        progressBar.setVisibility(View.GONE);
        rv.setAdapter(new RVAdapter(this, filmList, clickListener));
        rv.setLayoutManager(new LinearLayoutManager(this));
    }


    @Override
    public void showErrorCode(Throwable t) {
        Toast.makeText(this, t.getMessage(), Toast.LENGTH_LONG).show();

    }


    @Override
    public void createNotify(int ID_NOTIFY, String filmTitle, int year, int month, int date, int hour, int minute) {

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, date, hour, minute);
        Log.e("filmTitle ", filmTitle);
        Log.e("filmID ", Integer.toString(ID_NOTIFY));

        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        intent.putExtra("filmTitle", filmTitle);
        intent.putExtra("filmID", ID_NOTIFY);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, ID_NOTIFY, intent, 0);

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

    }

    @Override
    public void showCalendarDialog(String filmTitle, int filmID) {

        Calendar calendar = Calendar.getInstance();

        int CURRENT_YEAR = calendar.get(Calendar.YEAR);
        int CURRENT_MONTH = calendar.get(Calendar.MONTH);
        int CURRENT_DATE = calendar.get(Calendar.DATE);
        int CURRENT_HOUR_OF_DAY = calendar.get(Calendar.HOUR_OF_DAY);
        int CURRENT_MINUTE = calendar.get(Calendar.MINUTE);

        timePickerDialog = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {

                mFilmPresenter.setFilmTime(hour, minute, filmTitle, filmID);
            }
        }, CURRENT_HOUR_OF_DAY, CURRENT_MINUTE, android.text.format.DateFormat.is24HourFormat(getApplicationContext()));

        datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int date) {
                mFilmPresenter.setFilmDate(year, month, date);
                timePickerDialog.show();
            }
        }, CURRENT_YEAR, CURRENT_MONTH, CURRENT_DATE);
        datePickerDialog.show();
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
        Toast.makeText(this, hourOfDay + ":" + minute, Toast.LENGTH_SHORT).show();

    }


}
