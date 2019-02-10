package io.github.uottahack_team_2019.classstart;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddCourse {
    Button b;
    EditText cName;

    public AddCourse(final MainActivity activity) {
        activity.setContentView(R.layout.activity_addcourse);

        b = activity.findViewById(R.id.adder);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cName = (EditText) activity.findViewById(R.id.enterCourse);
                String courseName= cName.getText().toString();
                activity.fileManager.addCourse(courseName);
                new ClassSort(activity);
            }
        });
    }
}


