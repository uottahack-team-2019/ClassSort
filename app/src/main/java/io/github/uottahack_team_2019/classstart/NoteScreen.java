package io.github.uottahack_team_2019.classstart;

import android.widget.TextView;

public class NoteScreen {
    public NoteScreen(MainActivity activity, String courseCode) {
        activity.setContentView(R.layout.activity_note);
        ((TextView) activity.findViewById(R.id.titleNote)).setText(courseCode + ": Screen");
    }
}
