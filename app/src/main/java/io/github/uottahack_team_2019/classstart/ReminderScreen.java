package io.github.uottahack_team_2019.classstart;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class ReminderScreen {
    public ReminderScreen(final MainActivity activity, final String courseCode) {
        activity.setContentView(R.layout.activity_reminder);
        ((TextView) activity.findViewById(R.id.titleReminder)).setText(courseCode + ": Reminders");

        activity.findViewById(R.id.setReminder).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    final int time = Integer.parseInt(((EditText) (activity.findViewById(R.id.reminderTimeInput))).getText().toString());
                    activity.executor.schedule(new Runnable() {
                        @Override
                        public void run() {
                            Log.d("12345", "showing notification "+time);
                            try {
                                EditText editText = ((EditText) activity.findViewById(R.id.reminderMessageInput));
                                Log.d("12345", "passed first message");
                                String message = editText.getText().toString();
                                Log.d("12345", "passed the message");
                                activity.showNotification("ClassSort", message);
                            }catch(Exception e) {e.printStackTrace();};
                        }
                    }, time, TimeUnit.SECONDS);
                    new Course(activity, courseCode);

                } catch (Exception e) {
                    //User entered a non-number for the time input
                    ((EditText) (activity.findViewById(R.id.reminderTimeInput))).setText("");
                }
            }
        });
    }

}
