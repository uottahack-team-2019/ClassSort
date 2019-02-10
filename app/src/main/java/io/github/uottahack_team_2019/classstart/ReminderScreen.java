package io.github.uottahack_team_2019.classstart;

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
                    final int time = Integer.parseInt(((EditText) activity.findViewById(R.id.reminderTimeInput)).getText().toString());
                    final EditText editText = ((EditText) activity.findViewById(R.id.reminderInput));
                    activity.executor.schedule(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                String message = editText.getText().toString();
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
