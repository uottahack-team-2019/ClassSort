package io.github.uottahack_team_2019.classstart;

import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class ClassSort extends AppCompatActivity {


    public void makeBtn(MainActivity activity, Button classes[], int i) {


        classes[i].setX(32);
        classes[i].setY(200 + 80*i);
        classes[i].setWidth(250);
        classes[i].setHeight(80);
        classes[i].setText(activity.fileManager.courseCodes.get(i));

        classes[i+1].setX(314);
        classes[i+1].setY(200 + 80*i);
        classes[i+1].setWidth(80);
        classes[i+1].setHeight(80);
        classes[i+1].setText("X");
        classes[i+1].setTextColor(Color.RED);

    }

    private static MainActivity activity1;

    public ClassSort(MainActivity activity) {
        activity.setContentView(R.layout.activity_class_sort);

        activity1 = activity;
        Button addCrs = (Button) findViewById(R.id.addCrs);

        addCrs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AddCourse(activity1);
            }
        });

        Button classes[] = new Button[activity.fileManager.courseCodes.size()];

        for(int i=0; i<activity.fileManager.courseCodes.size()-1; i=i+2)
        {
            makeBtn(activity, classes, i);
        }
    }



}
