package io.github.uottahack_team_2019.classstart;

import android.widget.TextView;

public class FileScreen {
    public FileScreen(MainActivity activity, String courseCode) {
        ((TextView) activity.findViewById(R.id.courseTitle)).setText(courseCode + ": Files");
    }
}
