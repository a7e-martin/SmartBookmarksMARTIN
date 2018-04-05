package org.diiage.martin.smartbookmarksmartin;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import org.diiage.martin.smartbookmarksmartin.adapters.SpinnerBooksAdapter;
import org.diiage.martin.smartbookmarksmartin.models.Book;
import org.diiage.martin.smartbookmarksmartin.models.Comment;
import org.diiage.martin.smartbookmarksmartin.utils.DatabaseHelper;

import java.util.ArrayList;

public class AddCommentActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    ArrayList<Book> books;
    Book book;
    TextView txtPageNumber;
    TextView txtComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_comment);

        txtPageNumber = findViewById(R.id.txtPageNumber);
        txtComment = findViewById(R.id.txtComment);

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        books = DatabaseHelper.getBooks(db);
        Spinner spinnerBook = (Spinner)findViewById(R.id.spnBooks);
        SpinnerBooksAdapter adapter = new SpinnerBooksAdapter(books, this);
        spinnerBook.setAdapter(adapter);
        spinnerBook.setOnItemSelectedListener(this);

        Button btnValidComment = findViewById(R.id.btnValidComment);
        btnValidComment.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Comment comment = new Comment(book.getId(), Long.parseLong(txtPageNumber.getText().toString()), txtComment.getText().toString());
                DatabaseHelper.addComment(db, comment);
                Intent intent = new Intent(AddCommentActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        book = (Book)parent.getItemAtPosition(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
