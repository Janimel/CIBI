package com.example.cibi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.valueOf;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String NAME = "tasksDatabase";
    private static final String TASK_TABLE = "taskSched";
    private static final String ID = "id";
    private static final String TASKTITLE = "title";
    private static final String TASKDESCRIPTION = "desc";
    private static final String CREATE_TASK_TABLE = "CREATE TABLE " + TASK_TABLE + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+ TASKTITLE + "TEXT, " + TASKDESCRIPTION + "TEXT)";

    private static final String NOTES_TABLE = "notesList";
    private static final String NOTESTITLE = "notesTitle";
    private static final String NOTESBODY = "notesBody";
    private static final String CREATE_NOTES_TABLE = "CREATE TABLE " + NOTES_TABLE + "(" + NOTESTITLE + "TEXT, " + NOTESBODY + "TEXT)";


    private SQLiteDatabase taskDB;

    DatabaseHandler(Context context){
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase taskDB){
        taskDB.execSQL(CREATE_TASK_TABLE);
        taskDB.execSQL(CREATE_NOTES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase taskDB, int oldversion, int newVersion){
        //Drop old tables
        taskDB.execSQL("DROP TABLE IF EXISTS" + TASK_TABLE + NOTES_TABLE);
        //Create tables
        onCreate(taskDB);
    }

    public void openDatabase(){
        taskDB = this.getWritableDatabase();
    }

    public void insertTask(TaskItems title, TaskItems desc){
        ContentValues cv = new ContentValues();
        cv.put(TASKTITLE, title.getTasktitle());
        cv.put(TASKDESCRIPTION, desc.getTaskdesc());
        taskDB.insert(TASK_TABLE, null, cv);
    }

    public List<TaskItems> getAllTasks() {
        List<TaskItems> taskList = new ArrayList<>();
        Cursor cur = null;
        taskDB.beginTransaction();
            try {
                cur = taskDB.query(TASK_TABLE, null, null, null, null,null, null, null);
                if (cur != null){
                    if (cur.moveToFirst()){
                        do{
                            TaskItems task = new TaskItems();
                            task.setId(cur.getInt(cur.getColumnIndex(ID)));
                            task.setTasktitle(cur.getString(cur.getColumnIndex(TASKTITLE)));
                            task.setTaskdesc(cur.getString(cur.getColumnIndex(TASKDESCRIPTION)));
                            taskList.add(task);
                        }while(cur.moveToNext());
                    }
                }
            }
            finally {
                taskDB.endTransaction();
                cur.close();
            }
            return taskList;
    }

    public void updateTask(int id, String title, String desc){
        ContentValues cv = new ContentValues();
        cv.put(TASKTITLE, title);
        cv.put(TASKDESCRIPTION, desc);
        taskDB.update(TASK_TABLE, cv, ID + "=?", new String[] {String.valueOf(id)});
    }

    public void deleteTask(int id){
        taskDB.delete(TASK_TABLE, ID + "=?", new String[] {String.valueOf(id)});
    }

}
