package io.github.uottahack_team_2019.classstart;

import android.view.View;
import android.widget.EditText;

public class Note {
    public Note(final MainActivity activity, final String courseCode, final String[] noteMessage) {
        activity.setContentView(R.layout.activity_changenote);

        ((EditText) activity.findViewById(R.id.noteTitleInput)).setText(noteMessage[0]);
        ((EditText) activity.findViewById(R.id.noteInput)).setText(noteMessage[1]);

        activity.findViewById(R.id.saveNote).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String noteTitle = ((EditText) activity.findViewById(R.id.noteTitleInput)).getText().toString();
                if (noteTitle.length() > 0) {
                    String noteContent = ((EditText) activity.findViewById(R.id.noteInput)).getText().toString();
                    activity.fileManager.saveNote(courseCode, noteTitle, noteContent, noteMessage[2]);
                    new NoteScreen(activity, courseCode);
                }
            }
        });
    }
}
