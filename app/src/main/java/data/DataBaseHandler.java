package data;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import Utils.Util;
import model.TermData;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHandler extends SQLiteOpenHelper {
    public DataBaseHandler( Context context) {
        super(context,Util.DATABASE_NAME,null,Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_DATABASE = "CREATE TABLE "+Util.TABLE_NAME+"("
                + Util.KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT" +", "
                + Util.TERM_NAME+" TEXT"+", "
                + Util.TERM_DESCRIPTION + " TEXT" +" )";

        db.execSQL(CREATE_DATABASE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ Util.TABLE_NAME);
    }

    public void addTerm(TermData termData){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.TERM_NAME,termData.getTerm());
        contentValues.put(Util.TERM_DESCRIPTION,termData.getDescription());
        db.insert(Util.TABLE_NAME,null,contentValues);
        db.close();
    }
    public TermData getTerm(int ID){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Util.TABLE_NAME,new String[]{Util.KEY_ID,Util.TERM_NAME,Util.TERM_DESCRIPTION},
                Util.KEY_ID+"=?",new String[]{String.valueOf(ID)},null,null,null,null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
        return new TermData(cursor.getInt(0),cursor.getString(1),cursor.getString(2));
    }
    public List<TermData> getOllTerm(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<TermData> termDataList = new ArrayList<>();
        String select = "SELECT "+Util.KEY_ID+", "+Util.TERM_NAME+", "+Util.TERM_DESCRIPTION+" FROM " + Util.TABLE_NAME;
        Cursor cursor = db.rawQuery(select,null);
        if(cursor.moveToFirst()){
            do{
                TermData termData = new TermData(cursor.getInt(0),cursor.getString(1),cursor.getString(2));
                termDataList.add(termData);
            }while (cursor.moveToNext());
        }
        return termDataList;
    }
    public void updateTerm(TermData termData){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.TERM_NAME,termData.getTerm());
        contentValues.put(Util.TERM_DESCRIPTION,termData.getDescription());
        db.update(Util.TABLE_NAME,contentValues,Util.KEY_ID+" =?",
                new String[]{String.valueOf(termData.getId())});
    }
    public void deleteTerm(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Util.TABLE_NAME,Util.KEY_ID +"=?",new String[]{String.valueOf(id)});
    }
}
