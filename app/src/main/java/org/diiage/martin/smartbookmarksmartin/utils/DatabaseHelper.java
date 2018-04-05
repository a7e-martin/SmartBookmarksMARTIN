package org.diiage.martin.smartbookmarksmartin.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.diiage.martin.smartbookmarksmartin.models.Book;
import org.diiage.martin.smartbookmarksmartin.models.Comment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String SMARTBOOKMARKS_DB = "smartbookmarks.db";
    public static final int VERSION = 2;

    public DatabaseHelper(Context context){
        super(context, SMARTBOOKMARKS_DB, null, VERSION);
    }
    //Appelé une seule fois dans le cycle de vie de l'app, lors du premier appel à getReadableDatabase() ou getWritableDatabase()
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE \"Books\" (\"id\" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL , \"title\" TEXT NOT NULL , \"author\" TEXT NOT NULL , \"genre\"TEXT NOT NULL ); ");
        db.execSQL("CREATE TABLE \"Comments\" (\"id\"  PRIMARY KEY AUTOINCREMENT NOT NULL , \"bookId\" INTEGER  NOT NULL, \"page\" INTEGER NOT NULL , \"comment\"  NOT NULL , \"date\" DATETIME NOT NULL ); ");
        db.execSQL("INSERT INTO \"Books\" VALUES(1,'Les fleurs du mal','Charles Baudelaire','Poèmes');");
        db.execSQL("INSERT INTO \"Books\" VALUES(2,'Germinal','Emile Zola','Roman'); ");
        db.execSQL("INSERT INTO \"Books\" VALUES(3,'Les misérables','Victor Hugo','Roman'); ");
        db.execSQL("INSERT INTO \"Books\" VALUES(4,'1984','George Orwell','Science-Fiction'); ");
        db.execSQL("INSERT INTO \"Books\" VALUES(5,'Le Meilleur des mondes','Aldous Huxley','Science-Fiction'); ");
        db.execSQL("INSERT INTO \"Books\" VALUES(6,'Vingt mille lieues sous les mers','Jules Verne','Aventure'); ");
        db.execSQL("INSERT INTO \"Books\" VALUES(7,'Les Trois Mousquetaires','Alexandre Dumas','Aventure'); ");
    }

    /*Méthode appelée lorsqu'il est nécessaire de mettre à jour la structure de la base de données.
    Le but de cette méthode est de :
    - Modifier la structure (ajout de table, ajout de champ, suppression de table ou de champ, ...)

    */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion > oldVersion){
            db.execSQL("DROP TABLE 'Books'");
            db.execSQL("DROP TABLE 'Comments'");

            db.execSQL("CREATE TABLE \"Books\" (\"id\" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL , \"title\" TEXT NOT NULL , \"author\" TEXT NOT NULL , \"genre\"TEXT NOT NULL ); ");
            db.execSQL("CREATE TABLE \"Comments\" (\"id\"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL , \"bookId\" INTEGER  NOT NULL, \"page\" INTEGER NOT NULL , \"comment\"  NOT NULL , \"date\" DATETIME NOT NULL ); ");
            db.execSQL("INSERT INTO \"Books\" VALUES(1,'Les fleurs du mal','Charles Baudelaire','Poèmes');");
            db.execSQL("INSERT INTO \"Books\" VALUES(2,'Germinal','Emile Zola','Roman'); ");
            db.execSQL("INSERT INTO \"Books\" VALUES(3,'Les misérables','Victor Hugo','Roman'); ");
            db.execSQL("INSERT INTO \"Books\" VALUES(4,'1984','George Orwell','Science-Fiction'); ");
            db.execSQL("INSERT INTO \"Books\" VALUES(5,'Le Meilleur des mondes','Aldous Huxley','Science-Fiction'); ");
            db.execSQL("INSERT INTO \"Books\" VALUES(6,'Vingt mille lieues sous les mers','Jules Verne','Aventure'); ");
            db.execSQL("INSERT INTO \"Books\" VALUES(7,'Les Trois Mousquetaires','Alexandre Dumas','Aventure'); ");
        }
    }

    public static ArrayList<Comment> getComments(SQLiteDatabase db){
        ArrayList<Comment> result = new ArrayList<Comment>();

        Cursor cursor = db.query(
                "Comments",
                new String[]{"id", "bookId", "page", "comment", "date"},
                "",
                null,
                null,
                null,
                null
        );

        while(cursor.moveToNext()){
            long id = cursor.getLong(0);
            long bookId = cursor.getLong(1);
            long page = cursor.getLong(2);
            String comment = cursor.getString(3);
            String date = cursor.getString(4);

            Comment c = new Comment(id, bookId, page, comment, date);
            Book b = getBook(db, bookId);
            c.setBook(b);
            result.add(c);
        }

        return result;
    }

    public static ArrayList<Book> getBooks(SQLiteDatabase db){
        ArrayList<Book> result = new ArrayList<Book>();

        Cursor cursor = db.query(
                "Books",
                new String[]{"id", "title", "author", "genre"},
                "",
                null,
                null,
                null,
                null
        );

        while(cursor.moveToNext()){
            long id = cursor.getLong(0);
            String title = cursor.getString(1);
            String author = cursor.getString(2);
            String genre = cursor.getString(3);
            result.add(new Book(id, title, author, genre));
        }

        return result;
    }

    public static Book getBook(SQLiteDatabase db, long bookId){
        Book result = new Book();

        Cursor cursor = db.query(
                "Books",
                new String[]{"id", "title", "author", "genre"},
                "id = ?",
                new String[]{String.valueOf(bookId)},
                null,
                null,
                null
        );

        while(cursor.moveToNext()){
            long id = cursor.getLong(0);
            String title = cursor.getString(1);
            String author = cursor.getString(2);
            String genre = cursor.getString(3);

            result = new Book(id, title, author, genre);
        }

        return result;
    }

    public static Float getAverageCommentsPerBook(SQLiteDatabase db){
        return 0f;
    }

    public static void addComment(SQLiteDatabase db, Comment comment) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("bookId", comment.getBookId());
        contentValues.put("page", comment.getPageNumber());
        contentValues.put("comment", comment.getComment());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(new Date());
        contentValues.put("date", date);

        long idInserted = db.insert("Comments", null, contentValues);
    }
}
