package io.github.uottahack_team_2019.classstart;

import android.widget.TextView;

public class ReminderScreen {
    public ReminderScreen(MainActivity activity, String courseCode) {
        ((TextView) activity.findViewById(R.id.courseTitle)).setText(courseCode + ": Reminders");
    }

}
