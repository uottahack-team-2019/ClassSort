package io.github.uottahack_team_2019.classstart;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Course {
    public Course(final MainActivity activity, final String courseCode) {
        activity.setContentView(R.layout.activity_course);
        ((TextView) activity.findViewById(R.id.courseTitle)).setText(courseCode);

        activity.findViewById(R.id.buttonFiles).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new FileScreen(activity, courseCode);
            }
        });
        activity.findViewById(R.id.buttonNotes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new NoteScreen(activity, courseCode);
            }
        });
        activity.findViewById(R.id.buttonReminders).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ReminderScreen(activity, courseCode);
            }
        });
    }
}