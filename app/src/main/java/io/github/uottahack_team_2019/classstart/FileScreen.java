package io.github.uottahack_team_2019.classstart;

import android.content.Intent;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;

public class FileScreen {
    public static final int READ_REQUEST_CODE = 42;

    private static MainActivity activity;

    public static String courseCode;

    public FileScreen(final MainActivity activity, final String courseCode) {
        FileScreen.activity = activity;
        FileScreen.courseCode = courseCode;

        activity.setContentView(R.layout.activity_file);
        ((TextView) activity.findViewById(R.id.titleFile)).setText(courseCode + ": Files");

        Button addFile = (Button) activity.findViewById(R.id.addFile);

        addFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);

                // Filter to only show results that can be "opened", such as a
                // file (as opposed to a list of contacts or timezones)
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("*/*");
                activity.startActivityForResult(intent, READ_REQUEST_CODE);
            }
        });

        final File[] files = activity.fileManager.getFiles(courseCode);
        if (files != null) {
            Button[] classes = new Button[files.length * 2];

            for (int i = 0; i < classes.length - 1; i = i + 2) {
                makeBtn(activity, classes, i, files[i / 2].getName());
                final int courseIndex = i / 2;
                classes[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        activity.fileManager.openFile(files[courseIndex]);
                    }
                });
                classes[i + 1].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        files[courseIndex].delete();
                        new FileScreen(activity, courseCode);
                    }
                });
            }
        }
    }
    public void makeBtn(MainActivity activity, Button classes[], int i, String name) {
        classes[i] = new Button(activity);

        classes[i].setX(32);
        classes[i].setY(450 + 125*i);
        classes[i].setText(name);


        ConstraintLayout b1 = (ConstraintLayout)activity.findViewById(R.id.fileID);
        ConstraintLayout.LayoutParams l1 = new ConstraintLayout.LayoutParams(800, 200);
        b1.addView(classes[i], l1);



        classes[i+1] = new Button(activity);

        classes[i+1].setX(850);
        classes[i+1].setY(450 + 125*i);
        classes[i+1].setText("X");
        classes[i+1].setTextColor(Color.RED);

        ConstraintLayout b2 = (ConstraintLayout)activity.findViewById(R.id.fileID);
        ConstraintLayout.LayoutParams l2 = new ConstraintLayout.LayoutParams(200, 200);
        b2.addView(classes[i+1], l2);

    }
    public static void refresh() {
        new FileScreen(activity, courseCode);
    }
}
