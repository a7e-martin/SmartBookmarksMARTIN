package org.diiage.martin.smartbookmarksmartin;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import org.diiage.martin.smartbookmarksmartin.adapters.CommentsAdapter;
import org.diiage.martin.smartbookmarksmartin.models.Comment;
import org.diiage.martin.smartbookmarksmartin.utils.DatabaseHelper;

import java.util.ArrayList;

public class CommentsActivity extends AppCompatActivity {

    ListView lvComments;
    ArrayList<Comment> comments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        comments = DatabaseHelper.getComments(db);

        lvComments = findViewById(R.id.lvComments);
        CommentsAdapter adapter = new CommentsAdapter(this, comments);
        lvComments.setAdapter(adapter);

    }
}
