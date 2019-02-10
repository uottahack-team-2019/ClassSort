package io.github.uottahack_team_2019.classstart;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.OpenableColumns;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.webkit.MimeTypeMap;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private MainActivity activity;
    private static final String COURSE_FILE = "course_codes.txt";
    private static final String FILE_FOLDER = "files";
    private static final String NOTES_FOLDER = "notes";

    public List<String> courseCodes = new ArrayList<>();

    public FileManager(MainActivity activity) {
        this.activity = activity;
        loadCourseCodes();

    }
    /**Loads the existing courses, adds
     *  them to courseCodes*/
    private void loadCourseCodes() {
        List<String> courseCodes = getListFromFile(COURSE_FILE);
        if (courseCodes != null) {
            this.courseCodes.addAll(courseCodes);
        }
    }
    /**Given a file path, returns a list of strings in the file*/
    private List<String> getListFromFile(String path) {
        List<String> list = new ArrayList<>();
        try {
            FileInputStream fis = activity.openFileInput(path);
            if (fis == null) return null;
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                list.add(line);
            }
        } catch (FileNotFoundException e) {
            //File not found
            return null;
        } catch (IOException e) {
            return null;
        }
        return list;
    }
    /**Adds a new course, updates the course codes file*/
    public void addCourse(String courseCode) {
        courseCodes.add(courseCode);
        saveCourseCodes();
    }
    /**Removes the given course, updates the course codes file (assumes the course code exists), and deletes the resources saved with the course*/
    public void removeCourse(String courseCode) {
        courseCodes.remove(courseCode);
        new File(courseCode).delete();
        saveCourseCodes();
    }
    /**Saves the strings in courseCodes to the internal storage*/
    private void saveCourseCodes() {
        saveList(courseCodes, COURSE_FILE);
    }
    /**Opens a file in the correct app, given the selected course code and file name*/
    public void openFile(String courseCode, String fileName) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            File file = new File(activity.getFilesDir() + courseCode + "/" + FILE_FOLDER + "/" + fileName);
            String extension = android.webkit.MimeTypeMap.getFileExtensionFromUrl(Uri.fromFile(file).toString());
            String mimetype = android.webkit.MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
            intent.setDataAndType(Uri.fromFile(file), mimetype);
            activity.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }
    /**Saves the file to the internal storage*/
    public void saveFile(InputStream in, String courseCode, Uri uri) {
        try {
            File file = new File(activity.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS) + courseCode + "/" + FILE_FOLDER + "/" + getFileName(uri));
            file.getParentFile().mkdirs();
            file.createNewFile();
            OutputStream out = new FileOutputStream(file);
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            out.close();
            in.close();
        } catch (IOException e) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            String sStackTrace = sw.toString(); // stack trace as a string

            Log.d("12345", sStackTrace);
            //oops
        }
    }

    /**Saves the given list of strings to the given path*/
    private void saveList(List<String> list, String path) {
        try {
            FileOutputStream outputStream = activity.openFileOutput(path, Context.MODE_PRIVATE);
            for (String code: list) {
                outputStream.write((code + System.getProperty("line.separator")).getBytes());
            }
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void saveNote(String courseCode, String noteTitle, String noteContent, String noteFileName) {
        File file = new File(activity.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS) + courseCode + "/" + NOTES_FOLDER + "/" + noteFileName);
        file.getParentFile().mkdirs();
        try {
            file.createNewFile();
            OutputStream out = new FileOutputStream(file);
            out.write((noteTitle + System.getProperty("line.separator")).getBytes());
            out.write(noteContent.getBytes());
            out.close();
        } catch (IOException e) {
            //guess i'll die
        }
    }
    public void openNote(File noteFile, String courseCode) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(noteFile));
            String[] note = new String[] {reader.readLine(), reader.readLine(), noteFile.getName()};
            reader.close();
            new Note(activity, courseCode, note);

        } catch (IOException e) {
            //hope this doesnt happen during the demo
        }
    }
    public File[] getNotes(String courseCode) {
        return new File(activity.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS) + courseCode + "/" + NOTES_FOLDER + "/").listFiles();
    }
    public File[] getFiles(String courseCode) {
        return new File(activity.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS) + courseCode + "/" + FILE_FOLDER + "/").listFiles();
    }
    /**Given a Uri, returns the file name*/
    public String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = activity.getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }
    public void openFile(File file) {
        String mime = MimeTypeMap.getSingleton().getMimeTypeFromExtension(".JPG");

        Intent intent = new Intent();
        intent.setAction(android.content.Intent.ACTION_VIEW);
        Uri uri = FileProvider.getUriForFile(activity, activity.getApplicationContext().getPackageName() + ".my.package.name.provider", file);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.setDataAndType(uri, mime);

        activity.startActivityForResult(intent, 10);
    }

}
