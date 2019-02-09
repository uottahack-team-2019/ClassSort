package io.github.uottahack_team_2019.classstart;

import android.annotation.TargetApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    private FileManager fileManager;
    private ClassSort classSort;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        setStatusBarColour();

        fileManager = new FileManager(this);
        classSort = new ClassSort(this);

        setContentView(R.layout.activity_main);
    }
    @TargetApi(21)
    private void setStatusBarColour() {
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colourBlack));
    }
}
