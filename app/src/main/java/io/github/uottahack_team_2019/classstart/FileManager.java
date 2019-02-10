package io.github.uottahack_team_2019.classstart;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
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
    /**Saves the file from the given website*/
    public void saveFile(String location) {
        try {
            URL url = new URL(location);

            DataInputStream inputStream = new DataInputStream(url.openStream());

            byte[] buffer = new byte[1024];
            int length;

            String fileName = location.substring(location.lastIndexOf('/') + 1);

            FileOutputStream outputStream = activity.openFileOutput(FILE_FOLDER + "/" + fileName, Context.MODE_PRIVATE);
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
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
    /**Returns the given note as a list of strings (the bullet points)*/
    public List<String> getNote(String courseCode, String noteName) {
        return getListFromFile(courseCode + "/" + NOTES_FOLDER + "/" + noteName);
    }
    public void saveNote(List<String> note, String courseCode, String noteName) {
        saveList(note, courseCode +  "/" + NOTES_FOLDER + "/" + noteName);
    }

    public File[] getFiles(String courseCode) {
        return new File(activity.getFilesDir() + courseCode + "/" + NOTES_FOLDER + "/").listFiles();
    }
}
