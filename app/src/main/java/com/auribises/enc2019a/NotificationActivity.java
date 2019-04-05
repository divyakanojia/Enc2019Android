package com.auribises.enc2019a;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NotificationActivity extends AppCompatActivity {


    @BindView(R.id.buttonShow)
    Button btnShow;

    @BindView(R.id.textViewTitle)
    TextView txtTitle;

    DatePickerDialog datePickerDialog;
    TimePickerDialog timePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        ButterKnife.bind(this);

        btnShow.setOnClickListener(clickListener);

    }

    DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            String date = year+"/"+(month+1)+"/"+dayOfMonth;
            txtTitle.setText(date);
        }
    };

    TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            String time = hourOfDay+" : "+minute;
            txtTitle.setText(time);
        }
    };

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //showDatePickerDialog();
            //showTimePickerDialog();
            showNotification();
        }
    };

    void showDatePickerDialog(){

        // get todays date
        Calendar calendar = Calendar.getInstance();
        int dd = calendar.get(Calendar.DAY_OF_MONTH);
        int mm = calendar.get(Calendar.MONTH);
        int yy = calendar.get(Calendar.YEAR);

        // show the same date on dialog
        datePickerDialog = new DatePickerDialog(this, onDateSetListener, yy, mm, dd);
        datePickerDialog.show();
    }

    void showTimePickerDialog(){
        Calendar calendar = Calendar.getInstance();
        int hh = calendar.get(Calendar.HOUR_OF_DAY);
        int mi = calendar.get(Calendar.MINUTE);

        timePickerDialog = new TimePickerDialog(this, timeSetListener, hh, mi, true);
        timePickerDialog.show();
    }

    void showNotification(){

        // To show or cancel notification
        NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel("myId","myName",NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        // Builder builds Notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"myId");
        builder.setSmallIcon(R.drawable.folder);
        builder.setContentTitle("This is Title");
        builder.setContentText("This is Text");

        // What should open on click of Notification
        Intent intent = new Intent(NotificationActivity.this, HomeActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 111, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        builder.setContentIntent(pendingIntent);

        // Styled Notification
        builder.setStyle(new NotificationCompat.BigTextStyle());
        builder.addAction(android.R.drawable.ic_menu_add, "Add",pendingIntent);
        builder.addAction(android.R.drawable.ic_menu_delete, "Delete",pendingIntent);

        Notification notification = builder.build();
        notificationManager.notify(101, notification); // show the notification

        // Use AlarmManager to put some reminders at fixed time
        // AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);


    }
}
