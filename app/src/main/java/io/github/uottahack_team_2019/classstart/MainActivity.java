package io.github.uottahack_team_2019.classstart;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.WindowManager;

import java.io.FileNotFoundException;

public class MainActivity extends AppCompatActivity {

    public FileManager fileManager;

    private ClassSort classSort;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        setStatusBarColour();

        fileManager = new FileManager(this);
        classSort = new ClassSort(this);
    }
    @TargetApi(21)
    private void setStatusBarColour() {
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colourBlack));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (Integer.parseInt(android.os.Build.VERSION.SDK) > 5
                && keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {

            onBackPressed();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void onBackPressed() {
        new ClassSort(this);

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode,
                                 Intent resultData) {

        // The ACTION_OPEN_DOCUMENT intent was sent with the request code
        // READ_REQUEST_CODE. If the request code seen here doesn't match, it's the
        // response to some other intent, and the code below shouldn't run at all.

        if (requestCode == FileScreen.READ_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            // The document selected by the user won't be returned in the intent.
            // Instead, a URI to that document will be contained in the return intent
            // provided to this method as a parameter.
            // Pull that URI using resultData.getData().
            Uri uri = null;
            if (resultData != null) {
                try {
                    uri = resultData.getData();
                    fileManager.saveFile(getContentResolver().openInputStream(uri), FileScreen.courseCode, uri);
                    Log.d("12345", "saved file");
                    FileScreen.refresh();
                } catch (FileNotFoundException e) {
                    //oops
                }
            }
        }
    }

}
