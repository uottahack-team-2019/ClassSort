package io.github.uottahack_team_2019.classstart;

import android.util.Log;
import android.widget.TextView;

public class FileScreen {
    public FileScreen(MainActivity activity, String courseCode) {
        activity.setContentView(R.layout.activity_file);
        ((TextView) activity.findViewById(R.id.titleFile)).setText(courseCode + ": Files");
    }
}
