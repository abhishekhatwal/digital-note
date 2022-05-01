package com.example.dnotes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class databaseclass extends SQLiteOpenHelper {

    Context context;
    private static final String databasename="Mynotes";
    private static final int databaseversion=1;
    private static final String tablename="Dnotes";
    private static final String columnid="id";
    private static final String columntitle="title";
    private static final String columndescription="description";

    public databaseclass(@Nullable Context context) {
        super(context, databasename, null, databaseversion);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query="CREATE TABLE " + tablename +
                " (" + columnid + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                columntitle + " TEXT, " +
                columndescription + " TEXT);";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+tablename);
        onCreate(db);
    }

    void addnotefun(String title,String description)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv= new ContentValues();
        cv.put(columntitle,title);
        cv.put(columndescription,description);

        long resultvalue=db.insert(tablename,null,cv);

        if(resultvalue==-1)
        {
            Toast.makeText(context,"DATA NOT ADDED",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context,"DATA added successfully",Toast.LENGTH_SHORT).show();
        }

    }


    Cursor readdata()
    {
        String query="SELECT * FROM "+tablename;
        SQLiteDatabase database=this.getReadableDatabase();

        Cursor cursor=null;
        if(database!=null)
        {
            cursor=database.rawQuery(query,null);
        }
      return cursor;
    }

    void delete_allnotes_from_database()
    {
        SQLiteDatabase database=this.getWritableDatabase();
        String query="DELETE FROM "+tablename;
        database.execSQL(query);
    }

    void updatenote(String title,String description,String id)
    {
        SQLiteDatabase database=this.getWritableDatabase();

        ContentValues cv=new ContentValues();
        cv.put(columntitle,title);
        cv.put(columndescription,description);

        long result=database.update(tablename,cv,"id=?",new String[]{id});
        if(result==-1)
        {
            Toast.makeText(context,"Failed to udate",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context,"successfully Updated",Toast.LENGTH_SHORT).show();
        }
    }

      public void deletesingleitem(String id)
    {
        SQLiteDatabase database=this.getWritableDatabase();

        long result=database.delete(tablename,"id=?",new String[]{id});
        if(result==-1)
        {
            Toast.makeText(context,"item not deleted",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context,"item deleted",Toast.LENGTH_SHORT).show();
        }
    }



}
