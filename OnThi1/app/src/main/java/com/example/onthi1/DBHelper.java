package com.example.onthi1;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "StudentDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Student(" +
                "id INTEGER PRIMARY KEY," +
                "name TEXT," +
                "math INTEGER," +
                "chemistry INTEGER," +
                "physics INTEGER)";
        db.execSQL(sql);

        db.execSQL("INSERT INTO Student VALUES(1,'Ha',8,9,7)");
        db.execSQL("INSERT INTO Student VALUES(2,'Hung',7,8,9)");
        db.execSQL("INSERT INTO Student VALUES(3,'Huy',6,7,8)");
        db.execSQL("INSERT INTO Student VALUES(4,'Phuong',9,9,8)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public List<Student> getAllStudents() {

        List<Student> list = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM Student", null);

        if (cursor.moveToFirst()) {
            do {

                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                int math = cursor.getInt(2);
                int physics = cursor.getInt(3);
                int chemistry = cursor.getInt(4);

                Student student = new Student(name, id, math, physics, chemistry);

                list.add(student);

            } while (cursor.moveToNext());
        }

        cursor.close();
        return list;
    }

    public void deleteStudent(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("Student", "id=?", new String[]{String.valueOf(id)});
        db.close();
    }
}