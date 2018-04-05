package org.diiage.martin.smartbookmarksmartin;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.diiage.martin.smartbookmarksmartin.models.Book;
import org.diiage.martin.smartbookmarksmartin.models.Comment;
import org.diiage.martin.smartbookmarksmartin.utils.DatabaseHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnComments = (Button)findViewById(R.id.btnComments);
        Button btnAddComment = (Button)findViewById(R.id.btnAddComment);

        btnComments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CommentsActivity.class);
                startActivity(intent);
            }
        });

        btnAddComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddCommentActivity.class);
                startActivity(intent);
            }
        });


        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Comment> comments = DatabaseHelper.getComments(db);
        ArrayList<Book> books = DatabaseHelper.getBooks(db);
        Float commentsPerBook = DatabaseHelper.getAverageCommentsPerBook(db);

        TextView tvStats = findViewById(R.id.txtStats);
        tvStats.setText("Il y a " + books.size() + " livre(s), " + comments.size() + " commentaire(s), et une moyenne de " + commentsPerBook + " commentaires par livre");
    }
}
