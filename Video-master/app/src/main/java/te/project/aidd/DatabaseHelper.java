package te.project.aidd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "aidd.db";
    public static final String TABLE_NAME = "registeruser";
    public static final String TABLE_COLOR = "colormatch";
    public static final String TABLE_COLORMATCH_ANALYSIS = "colormatchanalysis";
    public static final String TABLE_PUZZLE = "puzzle_analysis";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "name";
    public static final String COL_3 = "email";
    //    email here is doctors email and username is parents email
    public static final String COL_4 = "username";
    public static final String COL_5 = "password";
    public static final String COL_6 = "childPassword";
    private static final String game_1 = "game_1";
    private static final String game_2 = "game_2";
    private static final String game_3 = "game_3";
    private static final String game_4 = "game_4";
    private static final String game_5 = "game_5";
    private static final String game_6 = "game_6";
    private static final String game_7 = "game_7";
    private static final String game_8 = "game_8";
    private static final String game_9 = "game_9";
    private static final String game_10 = "game_10";
    private static final String game_11 = "game_11";
    private static final String game_12 = "game_12";
    private static int countID = 1;

    //For Find the match
    public static final String TABLE_FINDTHEMATCH = "findthematch";
    public static final String TABLE_FINDTHEMATCH_ANALYSIS = "findthematchanalysis";
    public static final String COLFTM_1 = "ID";
    public static final String COLFTM_2 = "name";
    public static final String COLFTM_3 = "email";
    public static final String COlFTM_4 = "correctscore";
    public static final String COlFTM_5 = "wrongscore";
    public static final String COlFTM_6 = "totalscore";
    public static final String emailString = "email";
    public static final String usernameString = "username";
    public int id;


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE registeruser (ID INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT, email TEXT,username TEXT UNIQUE , password TEXT,childPassword TEXT)");
        db.execSQL("CREATE TABLE colormatch ( ID INTEGER PRIMARY KEY AUTOINCREMENT , name VARCHAR,parentname VARCHAR, game_1 INTEGER, game_2 INTEGER, game_3 INTEGER, game_4 INTEGER, game_5 INTEGER,game_6 INTEGER,game_7  INTEGER, game_8  INTEGER, game_9  INTEGER, game_10  INTEGER, game_11  INTEGER,game_12 INTEGER)");
        db.execSQL("CREATE TABLE colormatchanalysis (  ID INTEGER PRIMARY KEY AUTOINCREMENT , name VARCHAR,parentname VARCHAR, game_1  INTEGER, game_2  INTEGER, game_3  INTEGER, game_4  INTEGER, game_5  INTEGER,game_6 INTEGER,game_7  INTEGER, game_8  INTEGER, game_9  INTEGER, game_10  INTEGER, game_11  INTEGER,game_12 INTEGER)");
        db.execSQL("CREATE TABLE findthematchanalysis (  ID INTEGER PRIMARY KEY AUTOINCREMENT , name VARCHAR,parentname VARCHAR, game_1  INTEGER, game_2  INTEGER, game_3  INTEGER, game_4  INTEGER, game_5  INTEGER,game_6 INTEGER,game_7  INTEGER, game_8  INTEGER, game_9  INTEGER, game_10  INTEGER, game_11  INTEGER,game_12 INTEGER)");
        db.execSQL("CREATE TABLE puzzle_analysis (  ID INTEGER PRIMARY KEY AUTOINCREMENT , name VARCHAR,parentname VARCHAR, game_1  INTEGER, game_2  INTEGER, game_3  INTEGER, game_4  INTEGER, game_5  INTEGER,game_6 INTEGER,game_7  INTEGER, game_8  INTEGER, game_9  INTEGER, game_10  INTEGER, game_11  INTEGER,game_12 INTEGER)");
        db.execSQL("CREATE TABLE records_30_colormatch(ID INTEGER PRIMARY KEY AUTOINCREMENT , name VARCHAR,parentname VARCHAR, score  INTEGER, analysis  INTEGER)");
        db.execSQL("CREATE TABLE records_30_puzzle(ID INTEGER PRIMARY KEY AUTOINCREMENT , name VARCHAR,parentname VARCHAR,  analysis  INTEGER)");
        db.execSQL("CREATE TABLE simon_analysis (  ID INTEGER PRIMARY KEY AUTOINCREMENT , name VARCHAR,parentname VARCHAR, game_1  INTEGER, game_2  INTEGER, game_3  INTEGER, game_4  INTEGER, game_5  INTEGER,game_6 INTEGER,game_7  INTEGER, game_8  INTEGER, game_9  INTEGER, game_10  INTEGER, game_11  INTEGER,game_12 INTEGER)");
        db.execSQL("CREATE TABLE records_30_simon(ID INTEGER PRIMARY KEY AUTOINCREMENT , name VARCHAR,parentname VARCHAR, score  INTEGER, analysis  INTEGER)");
        db.execSQL("CREATE TABLE colorweek ( ID INTEGER PRIMARY KEY AUTOINCREMENT , name VARCHAR,parentname VARCHAR, week1 INTEGER, week2 INTEGER, week3 INTEGER, week4 INTEGER)");
        db.execSQL("CREATE TABLE findweek ( ID INTEGER PRIMARY KEY AUTOINCREMENT , name VARCHAR,parentname VARCHAR, week1 INTEGER, week2 INTEGER, week3 INTEGER, week4 INTEGER)");
        db.execSQL("CREATE TABLE puzzleweek ( ID INTEGER PRIMARY KEY AUTOINCREMENT , name VARCHAR,parentname VARCHAR, week1 INTEGER, week2 INTEGER, week3 INTEGER, week4 INTEGER)");
        db.execSQL("CREATE TABLE blinkweek ( ID INTEGER PRIMARY KEY AUTOINCREMENT , name VARCHAR,parentname VARCHAR, week1 INTEGER, week2 INTEGER, week3 INTEGER, week4 INTEGER)");
        String CREATE_TABLE = "CREATE TABLE " + TABLE_FINDTHEMATCH + "(" + COLFTM_1 + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLFTM_2 + " TEXT," + COLFTM_3 + " TEXT," + COlFTM_4 + " INTEGER, " + COlFTM_5 + " INTEGER, " + COlFTM_6 + " INTEGER ,analysis INTEGER )";

        db.execSQL(CREATE_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COLOR);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COLORMATCH_ANALYSIS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FINDTHEMATCH);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FINDTHEMATCH_ANALYSIS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PUZZLE);
        db.execSQL("DROP TABLE IF EXISTS " + "simon_analysis");
        db.execSQL("DROP TABLE IF EXISTS " + "records_30_colormatch");
        db.execSQL("DROP TABLE IF EXISTS " + "records_30_puzzle");
        db.execSQL("DROP TABLE IF EXISTS " + "records_30_simon");
        db.execSQL("DROP TABLE IF EXISTS " + "colorweek");
        db.execSQL("DROP TABLE IF EXISTS " + "findweek");
        db.execSQL("DROP TABLE IF EXISTS " + "puzzleweek");
        db.execSQL("DROP TABLE IF EXISTS " + "blinkweek");
        onCreate(db);

    }

    public long addUser(String name, String email, String user, String password) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM registeruser", null);
        if (cursor.moveToLast()) {
            countID = cursor.getInt(cursor.getColumnIndex(COL_1));
        } else {
            countID = 0;
        }
        countID++;
        db = this.getWritableDatabase();
        ContentValues cV = new ContentValues();
        ContentValues cc = new ContentValues();
        ContentValues contentValues = new ContentValues();
        cV.put("name", name);
        cV.put("email", email);
        cV.put("username", user);
        cV.put("password", password);


        cV.put("childPassword", name + String.valueOf(countID));
        cc.put("name", name);
        cc.put("parentname", user);
        cc.put(game_1, 0);
        cc.put(game_2, 0);
        cc.put(game_3, 0);
        cc.put(game_4, 0);
        cc.put(game_5, 0);
        cc.put(game_6, 0);
        cc.put(game_7, 0);
        cc.put(game_8, 0);
        cc.put(game_9, 0);
        cc.put(game_10, 0);
        cc.put(game_11, 0);
        cc.put(game_12, 0);
        contentValues.put("name", name);
        contentValues.put("parentname", user);
        contentValues.put("week1", 0);
        contentValues.put("week2", 0);
        contentValues.put("week3", 0);
        contentValues.put("week4", 0);

        long res = db.insert("registeruser", null, cV);
        db.insert(TABLE_COLOR, null, cc);
        db.insert(TABLE_COLORMATCH_ANALYSIS, null, cc);
        db.insert(TABLE_FINDTHEMATCH_ANALYSIS, null, cc);
        db.insert(TABLE_PUZZLE, null, cc);
        db.insert("simon_analysis", null, cc);
        db.insert("colorweek", null, contentValues);
        db.insert("findweek", null, contentValues);
        db.insert("puzzleweek", null, contentValues);
        db.insert("blinkweek", null, contentValues);


//        ContentValues cV2 = new ContentValues();
//        cV2.put("name",name);
//        cV2.put("email",email);
//        cV2.put(game_1, 0);
//        cV2.put(game_2, 0);
//        cV2.put(game_3, 0);
//        cV2.put(game_4, 0);
//        cV2.put(game_5, 0);
//        long flag=db.insert(TABLE_FINDTHEMATCH,null,cV2);
        db.close();
        return res;
    }

    public Boolean checkUser(String username, String password) {
//        String[] columns={COL_1};
        SQLiteDatabase db = this.getReadableDatabase();
//        String selection=COL_4 + "=?" + "and" + COL_5 + "=?";
//        String[] selectionArgs={username,password};
//        Cursor cursor=db.query(TABLE_NAME,columns,selection,selectionArgs,null,null,null);
        Cursor cursor = db.rawQuery("select * from registeruser where username=? and password=?", new String[]{username, password});
//        int count=cursor.getCount();
//        cursor.close();
//        db.close();
//        if(count>0)
//            return true;
//        else
//            return false;
        if (cursor.getCount() > 0) return true;
        else return false;
    }

    public int childCheckUser(String name, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from registeruser where name=? and childPassword=?", new String[]{name, password});
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            int id = cursor.getInt(cursor.getColumnIndex(COL_1));
            return id;
        }
        return 0;
    }


    // color match game
    public void color_match_30(String email, String name, int score, int analysis) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        Log.d("Email ", email);
        contentValues.put("name", name);
        contentValues.put("parentname", email);
        contentValues.put("score", score);
        contentValues.put("analysis", analysis);
        db.insert("records_30_colormatch", null, contentValues);
    }

    public void addscore(int score, String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        Cursor cursor = db.rawQuery("SELECT * FROM colormatch WHERE parentname=?", new String[]{name});
        if (cursor.moveToFirst()) {
            int c1 = cursor.getInt(cursor.getColumnIndex(game_2));
            int c2 = cursor.getInt(cursor.getColumnIndex(game_3));
            int c3 = cursor.getInt(cursor.getColumnIndex(game_4));
            int c4 = cursor.getInt(cursor.getColumnIndex(game_5));
            int c5 = cursor.getInt(cursor.getColumnIndex(game_6));
            int c6 = cursor.getInt(cursor.getColumnIndex(game_7));
            int c7 = cursor.getInt(cursor.getColumnIndex(game_8));
            int c8 = cursor.getInt(cursor.getColumnIndex(game_9));
            int c9 = cursor.getInt(cursor.getColumnIndex(game_10));
            int c10 = cursor.getInt(cursor.getColumnIndex(game_11));
            int c11 = cursor.getInt(cursor.getColumnIndex(game_12));
            values.put(game_1, c1);
            values.put(game_2, c2);
            values.put(game_3, c3);
            values.put(game_4, c4);
            values.put(game_5, c5);
            values.put(game_6, c6);
            values.put(game_7, c7);
            values.put(game_8, c8);
            values.put(game_9, c9);
            values.put(game_10, c10);
            values.put(game_11, c11);
            values.put(game_12, score);
        }
        db.update(TABLE_COLOR, values, "parentname=?", new String[]{name});
        db.close();

    }

    public int[] color_match_arr(String name) {
        int[] array = new int[6];
        SQLiteDatabase db = this.getReadableDatabase();
        //("SELECT * FROM " + TABLE_NAME + " where " + PEOPLE_RATION_NUMBER + "='" + rationNumber + "'", null);

        Cursor cursor = db.rawQuery("SELECT * FROM colormatch WHERE parentname=?", new String[]{name});
        //Cursor cursor=db.rawQuery("SELECT * FROM "+ TABLE_COLOR + " WHERE ID = " + id + " ",null);
        if (cursor.moveToFirst()) {
            int c1 = cursor.getInt(cursor.getColumnIndex(game_7));
            int c2 = cursor.getInt(cursor.getColumnIndex(game_8));
            int c3 = cursor.getInt(cursor.getColumnIndex(game_9));
            int c4 = cursor.getInt(cursor.getColumnIndex(game_10));
            int c5 = cursor.getInt(cursor.getColumnIndex(game_11));
            int c6 = cursor.getInt(cursor.getColumnIndex(game_12));
            array[0] = c1;
            array[1] = c2;
            array[2] = c3;
            array[3] = c4;
            array[4] = c5;
            array[5] = c6;

        }
        return array;

    }


    public void time_analysis(int analysis, String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        Cursor cursor = db.rawQuery("SELECT * FROM colormatchanalysis  WHERE parentname=?", new String[]{name});
        if (cursor.moveToFirst()) {
            int c1 = cursor.getInt(cursor.getColumnIndex(game_2));
            int c2 = cursor.getInt(cursor.getColumnIndex(game_3));
            int c3 = cursor.getInt(cursor.getColumnIndex(game_4));
            int c4 = cursor.getInt(cursor.getColumnIndex(game_5));
            int c5 = cursor.getInt(cursor.getColumnIndex(game_6));
            int c6 = cursor.getInt(cursor.getColumnIndex(game_7));
            int c7 = cursor.getInt(cursor.getColumnIndex(game_8));
            int c8 = cursor.getInt(cursor.getColumnIndex(game_9));
            int c9 = cursor.getInt(cursor.getColumnIndex(game_10));
            int c10 = cursor.getInt(cursor.getColumnIndex(game_11));
            int c11 = cursor.getInt(cursor.getColumnIndex(game_12));
            values.put(game_1, c1);
            values.put(game_2, c2);
            values.put(game_3, c3);
            values.put(game_4, c4);
            values.put(game_5, c5);
            values.put(game_6, c6);
            values.put(game_7, c7);
            values.put(game_8, c8);
            values.put(game_9, c9);
            values.put(game_10, c10);
            values.put(game_11, c11);
            values.put(game_12, analysis);
        }
        db.update(TABLE_COLORMATCH_ANALYSIS, values, "parentname=?", new String[]{name});
        db.close();

    }

    public int[] time_analysis_graph(String name) {
        int[] array = new int[6];
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM colormatchanalysis WHERE parentname=?", new String[]{name});
        if (cursor.moveToFirst()) {
            int c1 = cursor.getInt(cursor.getColumnIndex(game_7));
            int c2 = cursor.getInt(cursor.getColumnIndex(game_8));
            int c3 = cursor.getInt(cursor.getColumnIndex(game_9));
            int c4 = cursor.getInt(cursor.getColumnIndex(game_10));
            int c5 = cursor.getInt(cursor.getColumnIndex(game_11));
            int c6 = cursor.getInt(cursor.getColumnIndex(game_12));
            array[0] = c1;
            array[1] = c2;
            array[2] = c3;
            array[3] = c4;
            array[4] = c5;
            array[5] = c6;

        }
        return array;

    }

    public int[] sheet_colormatch(String name) {
        int[] array = new int[12];
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM colormatchanalysis WHERE parentname=?", new String[]{name});
        if (cursor.moveToFirst()) {
            int c0 = cursor.getInt(cursor.getColumnIndex(game_1));
            int c1 = cursor.getInt(cursor.getColumnIndex(game_2));
            int c2 = cursor.getInt(cursor.getColumnIndex(game_3));
            int c3 = cursor.getInt(cursor.getColumnIndex(game_4));
            int c4 = cursor.getInt(cursor.getColumnIndex(game_5));
            int c5 = cursor.getInt(cursor.getColumnIndex(game_6));
            int c6 = cursor.getInt(cursor.getColumnIndex(game_7));
            int c7 = cursor.getInt(cursor.getColumnIndex(game_8));
            int c8 = cursor.getInt(cursor.getColumnIndex(game_9));
            int c9 = cursor.getInt(cursor.getColumnIndex(game_10));
            int c10 = cursor.getInt(cursor.getColumnIndex(game_11));
            int c11 = cursor.getInt(cursor.getColumnIndex(game_12));
            array[0] = c0;
            array[1] = c1;
            array[2] = c2;
            array[3] = c3;
            array[4] = c4;
            array[5] = c5;
            array[6] = c6;
            array[7] = c7;
            array[8] = c8;
            array[9] = c9;
            array[10] = c10;
            array[11] = c11;

        }
        return array;

    }
    public int[] sheet_findmatch(String name) {
        int[] array = new int[12];
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM findthematchanalysis WHERE parentname=?", new String[]{name});
        if (cursor.moveToFirst()) {
            int c0 = cursor.getInt(cursor.getColumnIndex(game_1));
            int c1 = cursor.getInt(cursor.getColumnIndex(game_2));
            int c2 = cursor.getInt(cursor.getColumnIndex(game_3));
            int c3 = cursor.getInt(cursor.getColumnIndex(game_4));
            int c4 = cursor.getInt(cursor.getColumnIndex(game_5));
            int c5 = cursor.getInt(cursor.getColumnIndex(game_6));
            int c6 = cursor.getInt(cursor.getColumnIndex(game_7));
            int c7 = cursor.getInt(cursor.getColumnIndex(game_8));
            int c8 = cursor.getInt(cursor.getColumnIndex(game_9));
            int c9 = cursor.getInt(cursor.getColumnIndex(game_10));
            int c10 = cursor.getInt(cursor.getColumnIndex(game_11));
            int c11 = cursor.getInt(cursor.getColumnIndex(game_12));
            array[0] = c0;
            array[1] = c1;
            array[2] = c2;
            array[3] = c3;
            array[4] = c4;
            array[5] = c5;
            array[6] = c6;
            array[7] = c7;
            array[8] = c8;
            array[9] = c9;
            array[10] = c10;
            array[11] = c11;

        }
        return array;

    }

    public int[] sheet_puzzle(String name) {
        int[] array = new int[12];
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM puzzle_analysis WHERE parentname=?", new String[]{name});
        if (cursor.moveToFirst()) {
            int c0 = cursor.getInt(cursor.getColumnIndex(game_1));
            int c1 = cursor.getInt(cursor.getColumnIndex(game_2));
            int c2 = cursor.getInt(cursor.getColumnIndex(game_3));
            int c3 = cursor.getInt(cursor.getColumnIndex(game_4));
            int c4 = cursor.getInt(cursor.getColumnIndex(game_5));
            int c5 = cursor.getInt(cursor.getColumnIndex(game_6));
            int c6 = cursor.getInt(cursor.getColumnIndex(game_7));
            int c7 = cursor.getInt(cursor.getColumnIndex(game_8));
            int c8 = cursor.getInt(cursor.getColumnIndex(game_9));
            int c9 = cursor.getInt(cursor.getColumnIndex(game_10));
            int c10 = cursor.getInt(cursor.getColumnIndex(game_11));
            int c11 = cursor.getInt(cursor.getColumnIndex(game_12));
            array[0] = c0;
            array[1] = c1;
            array[2] = c2;
            array[3] = c3;
            array[4] = c4;
            array[5] = c5;
            array[6] = c6;
            array[7] = c7;
            array[8] = c8;
            array[9] = c9;
            array[10] = c10;
            array[11] = c11;

        }
        return array;

    }

    public int[] sheet_blink(String name) {
        int[] array = new int[12];
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM simon_analysis WHERE parentname=?", new String[]{name});
        if (cursor.moveToFirst()) {
            int c0 = cursor.getInt(cursor.getColumnIndex(game_1));
            int c1 = cursor.getInt(cursor.getColumnIndex(game_2));
            int c2 = cursor.getInt(cursor.getColumnIndex(game_3));
            int c3 = cursor.getInt(cursor.getColumnIndex(game_4));
            int c4 = cursor.getInt(cursor.getColumnIndex(game_5));
            int c5 = cursor.getInt(cursor.getColumnIndex(game_6));
            int c6 = cursor.getInt(cursor.getColumnIndex(game_7));
            int c7 = cursor.getInt(cursor.getColumnIndex(game_8));
            int c8 = cursor.getInt(cursor.getColumnIndex(game_9));
            int c9 = cursor.getInt(cursor.getColumnIndex(game_10));
            int c10 = cursor.getInt(cursor.getColumnIndex(game_11));
            int c11 = cursor.getInt(cursor.getColumnIndex(game_12));
            array[0] = c0;
            array[1] = c1;
            array[2] = c2;
            array[3] = c3;
            array[4] = c4;
            array[5] = c5;
            array[6] = c6;
            array[7] = c7;
            array[8] = c8;
            array[9] = c9;
            array[10] = c10;
            array[11] = c11;

        }
        return array;

    }

    public String getEmailForChild(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM registeruser WHERE ID=?", new String[]{String.valueOf(id)});
        String email;
        if (cursor.moveToFirst()) {
            email = cursor.getString(cursor.getColumnIndex(usernameString));
        } else {
            Log.i("Error", "Error");
            email = "";
        }
        return email;
    }

    //For Find the Match

    public void insertScore(String email, String name, int correctScore, int wrongScore, int totalScore, int analysis) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        Log.d("Email ", email);
        contentValues.put(COLFTM_2, name);
        contentValues.put(COLFTM_3, email);
        contentValues.put(COlFTM_4, correctScore);
        contentValues.put(COlFTM_5, wrongScore);
        contentValues.put(COlFTM_6, totalScore);
        contentValues.put("analysis", analysis);
        db.insert(TABLE_FINDTHEMATCH, null, contentValues);
    }

    public void insert_findmatch_analysis(int analysis, String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        Cursor cursor = db.rawQuery("SELECT * FROM findthematchanalysis  WHERE parentname=?", new String[]{email});
        if (cursor.moveToFirst()) {
            int c1 = cursor.getInt(cursor.getColumnIndex(game_2));
            int c2 = cursor.getInt(cursor.getColumnIndex(game_3));
            int c3 = cursor.getInt(cursor.getColumnIndex(game_4));
            int c4 = cursor.getInt(cursor.getColumnIndex(game_5));
            int c5 = cursor.getInt(cursor.getColumnIndex(game_6));
            int c6 = cursor.getInt(cursor.getColumnIndex(game_7));
            int c7 = cursor.getInt(cursor.getColumnIndex(game_8));
            int c8 = cursor.getInt(cursor.getColumnIndex(game_9));
            int c9 = cursor.getInt(cursor.getColumnIndex(game_10));
            int c10 = cursor.getInt(cursor.getColumnIndex(game_11));
            int c11 = cursor.getInt(cursor.getColumnIndex(game_12));
            values.put(game_1, c1);
            values.put(game_2, c2);
            values.put(game_3, c3);
            values.put(game_4, c4);
            values.put(game_5, c5);
            values.put(game_6, c6);
            values.put(game_7, c7);
            values.put(game_8, c8);
            values.put(game_9, c9);
            values.put(game_10, c10);
            values.put(game_11, c11);
            values.put(game_12, analysis);

        }
        db.update(TABLE_FINDTHEMATCH_ANALYSIS, values, "parentname=?", new String[]{email});
        db.close();

    }

    public int[] find_match_graph(String name) {
        int[] array = new int[6];
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM findthematchanalysis WHERE parentname=?", new String[]{name});
        Log.i("data", name);
        if (cursor.moveToFirst()) {
            int c1 = cursor.getInt(cursor.getColumnIndex(game_7));
            int c2 = cursor.getInt(cursor.getColumnIndex(game_8));
            int c3 = cursor.getInt(cursor.getColumnIndex(game_9));
            int c4 = cursor.getInt(cursor.getColumnIndex(game_10));
            int c5 = cursor.getInt(cursor.getColumnIndex(game_11));
            int c6 = cursor.getInt(cursor.getColumnIndex(game_12));
            array[0] = c1;
            array[1] = c2;
            array[2] = c3;
            array[3] = c4;
            array[4] = c5;
            array[5] = c6;

        }
        return array;

    }

    // PUZZLE GAME
    public void insert_puzzle_analysis(int analysis, String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        Cursor cursor = db.rawQuery("SELECT * FROM  puzzle_analysis WHERE parentname=?", new String[]{email});
        if (cursor.moveToFirst()) {
            int c1 = cursor.getInt(cursor.getColumnIndex(game_2));
            int c2 = cursor.getInt(cursor.getColumnIndex(game_3));
            int c3 = cursor.getInt(cursor.getColumnIndex(game_4));
            int c4 = cursor.getInt(cursor.getColumnIndex(game_5));
            int c5 = cursor.getInt(cursor.getColumnIndex(game_6));
            values.put(game_1, c1);
            values.put(game_2, c2);
            values.put(game_3, c3);
            values.put(game_4, c4);
            values.put(game_5, c5);
            values.put(game_6, analysis);
        }
        db.update(TABLE_PUZZLE, values, "parentname=?", new String[]{email});
        db.close();
    }


    public void puzzle_30(String email, String name, int analysis) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        Log.d("Email ", email);
        contentValues.put("name", name);
        contentValues.put("parentname", email);
        contentValues.put("analysis", analysis);
        db.insert("records_30_puzzle", null, contentValues);
    }

    public int[] puzzle_graph(String name) {
        int[] array = new int[6];
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM puzzle_analysis WHERE parentname=?", new String[]{name});
        Log.i("data", name);
        if (cursor.moveToFirst()) {
            int c1 = cursor.getInt(cursor.getColumnIndex(game_7));
            int c2 = cursor.getInt(cursor.getColumnIndex(game_8));
            int c3 = cursor.getInt(cursor.getColumnIndex(game_9));
            int c4 = cursor.getInt(cursor.getColumnIndex(game_10));
            int c5 = cursor.getInt(cursor.getColumnIndex(game_11));
            int c6 = cursor.getInt(cursor.getColumnIndex(game_12));
            array[0] = c1;
            array[1] = c2;
            array[2] = c3;
            array[3] = c4;
            array[4] = c5;
            array[5] = c6;

        }
        return array;

    }

    // SIMON GAME
    public void simon_30(String email, String name, int score, int analysis) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        Log.d("Email ", email);
        contentValues.put("name", name);
        contentValues.put("parentname", email);
        contentValues.put("score", score);
        contentValues.put("analysis", analysis);
        db.insert("records_30_simon", null, contentValues);
    }

    public void simon_analysis(int analysis, String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        Cursor cursor = db.rawQuery("SELECT * FROM simon_analysis  WHERE parentname=?", new String[]{name});
        if (cursor.moveToFirst()) {
            int c1 = cursor.getInt(cursor.getColumnIndex(game_2));
            int c2 = cursor.getInt(cursor.getColumnIndex(game_3));
            int c3 = cursor.getInt(cursor.getColumnIndex(game_4));
            int c4 = cursor.getInt(cursor.getColumnIndex(game_5));
            int c5 = cursor.getInt(cursor.getColumnIndex(game_6));
            int c6 = cursor.getInt(cursor.getColumnIndex(game_7));
            int c7 = cursor.getInt(cursor.getColumnIndex(game_8));
            int c8 = cursor.getInt(cursor.getColumnIndex(game_9));
            int c9 = cursor.getInt(cursor.getColumnIndex(game_10));
            int c10 = cursor.getInt(cursor.getColumnIndex(game_11));
            int c11 = cursor.getInt(cursor.getColumnIndex(game_12));
            values.put(game_1, c1);
            values.put(game_2, c2);
            values.put(game_3, c3);
            values.put(game_4, c4);
            values.put(game_5, c5);
            values.put(game_6, c6);
            values.put(game_7, c7);
            values.put(game_8, c8);
            values.put(game_9, c9);
            values.put(game_10, c10);
            values.put(game_11, c11);
            values.put(game_12, analysis);
        }
        Log.i("data", "saved");
        db.update("simon_analysis", values, "parentname=?", new String[]{name});
        db.close();

    }

    public int[] simon_graph(String name) {
        int[] array = new int[6];
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM simon_analysis WHERE parentname=?", new String[]{name});
        Log.i("data", name);
        if (cursor.moveToFirst()) {
            int c1 = cursor.getInt(cursor.getColumnIndex(game_7));
            int c2 = cursor.getInt(cursor.getColumnIndex(game_8));
            int c3 = cursor.getInt(cursor.getColumnIndex(game_9));
            int c4 = cursor.getInt(cursor.getColumnIndex(game_10));
            int c5 = cursor.getInt(cursor.getColumnIndex(game_11));
            int c6 = cursor.getInt(cursor.getColumnIndex(game_12));
            array[0] = c1;
            array[1] = c2;
            array[2] = c3;
            array[3] = c4;
            array[4] = c5;
            array[5] = c6;

        }
        return array;

    }


    public void addcolorweek(String name,int weekdata) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        Cursor cursor = db.rawQuery("SELECT * FROM colorweek WHERE parentname=?", new String[]{name});
        if (cursor.moveToFirst()) {
            int c1 = cursor.getInt(cursor.getColumnIndex("week2"));
            int c2 = cursor.getInt(cursor.getColumnIndex("week3"));
            int c3 = cursor.getInt(cursor.getColumnIndex("week4"));

            values.put("week1", c1);
            values.put("week2", c2);
            values.put("week3", c3);
            values.put("week4", weekdata);

        }
        db.update("colorweek", values, "parentname=?", new String[]{name});
        db.close();


    }



    public int[] colorweek_fetch(String name) {
        int[] array = new int[4];
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM colorweek WHERE parentname=?", new String[]{name});
        //Cursor cursor=db.rawQuery("SELECT * FROM "+ TABLE_COLOR + " WHERE ID = " + id + " ",null);
        if (cursor.moveToFirst()) {
            int c1 = cursor.getInt(cursor.getColumnIndex("week1"));
            int c2 = cursor.getInt(cursor.getColumnIndex("week2"));
            int c3 = cursor.getInt(cursor.getColumnIndex("week3"));
            int c4 = cursor.getInt(cursor.getColumnIndex("week4"));

            array[0] = c1;
            array[1] = c2;
            array[2] = c3;
            array[3] = c4;

        }
        return array;

    }

    public void addfindweek(String name,int weekdata) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        Cursor cursor = db.rawQuery("SELECT * FROM findweek WHERE parentname=?", new String[]{name});
        if (cursor.moveToFirst()) {
            int c1 = cursor.getInt(cursor.getColumnIndex("week2"));
            int c2 = cursor.getInt(cursor.getColumnIndex("week3"));
            int c3 = cursor.getInt(cursor.getColumnIndex("week4"));

            values.put("week1", c1);
            values.put("week2", c2);
            values.put("week3", c3);
            values.put("week4", weekdata);

        }
        db.update("findweek", values, "parentname=?", new String[]{name});
        db.close();


    }
    public void addpuzzleweek(String name,int weekdata) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        Cursor cursor = db.rawQuery("SELECT * FROM puzzleweek WHERE parentname=?", new String[]{name});
        if (cursor.moveToFirst()) {
            int c1 = cursor.getInt(cursor.getColumnIndex("week2"));
            int c2 = cursor.getInt(cursor.getColumnIndex("week3"));
            int c3 = cursor.getInt(cursor.getColumnIndex("week4"));

            values.put("week1", c1);
            values.put("week2", c2);
            values.put("week3", c3);
            values.put("week4", weekdata);

        }
        db.update("puzzleweek", values, "parentname=?", new String[]{name});
        db.close();


    }

    public void addblinkweek(String name,int weekdata) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        Cursor cursor = db.rawQuery("SELECT * FROM blinkweek WHERE parentname=?", new String[]{name});
        if (cursor.moveToFirst()) {
            int c1 = cursor.getInt(cursor.getColumnIndex("week2"));
            int c2 = cursor.getInt(cursor.getColumnIndex("week3"));
            int c3 = cursor.getInt(cursor.getColumnIndex("week4"));

            values.put("week1", c1);
            values.put("week2", c2);
            values.put("week3", c3);
            values.put("week4", weekdata);

        }
        db.update("blinkweek", values, "parentname=?", new String[]{name});
        db.close();


    }

    public int[] findweek_fetch(String name) {
        int[] array = new int[4];
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM findweek WHERE parentname=?", new String[]{name});
        //Cursor cursor=db.rawQuery("SELECT * FROM "+ TABLE_COLOR + " WHERE ID = " + id + " ",null);
        if (cursor.moveToFirst()) {
            int c1 = cursor.getInt(cursor.getColumnIndex("week1"));
            int c2 = cursor.getInt(cursor.getColumnIndex("week2"));
            int c3 = cursor.getInt(cursor.getColumnIndex("week3"));
            int c4 = cursor.getInt(cursor.getColumnIndex("week4"));

            array[0] = c1;
            array[1] = c2;
            array[2] = c3;
            array[3] = c4;

        }
        return array;

    }
    public int[] puzzleweek_fetch(String name) {
        int[] array = new int[4];
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM puzzleweek WHERE parentname=?", new String[]{name});
        //Cursor cursor=db.rawQuery("SELECT * FROM "+ TABLE_COLOR + " WHERE ID = " + id + " ",null);
        if (cursor.moveToFirst()) {
            int c1 = cursor.getInt(cursor.getColumnIndex("week1"));
            int c2 = cursor.getInt(cursor.getColumnIndex("week2"));
            int c3 = cursor.getInt(cursor.getColumnIndex("week3"));
            int c4 = cursor.getInt(cursor.getColumnIndex("week4"));

            array[0] = c1;
            array[1] = c2;
            array[2] = c3;
            array[3] = c4;

        }
        return array;

    }

    public int[] blinkweek_fetch(String name) {
        int[] array = new int[4];
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM blinkweek WHERE parentname=?", new String[]{name});
        //Cursor cursor=db.rawQuery("SELECT * FROM "+ TABLE_COLOR + " WHERE ID = " + id + " ",null);
        if (cursor.moveToFirst()) {
            int c1 = cursor.getInt(cursor.getColumnIndex("week1"));
            int c2 = cursor.getInt(cursor.getColumnIndex("week2"));
            int c3 = cursor.getInt(cursor.getColumnIndex("week3"));
            int c4 = cursor.getInt(cursor.getColumnIndex("week4"));

            array[0] = c1;
            array[1] = c2;
            array[2] = c3;
            array[3] = c4;

        }
        return array;

    }








}