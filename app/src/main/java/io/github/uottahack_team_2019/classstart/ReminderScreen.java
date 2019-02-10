package io.github.uottahack_team_2019.classstart;

import android.widget.TextView;

public class ReminderScreen {
    public ReminderScreen(MainActivity activity, String courseCode) {
        activity.setContentView(R.layout.activity_reminder);
        ((TextView) activity.findViewById(R.id.titleReminder)).setText(courseCode + ": Reminders");
    }

}
