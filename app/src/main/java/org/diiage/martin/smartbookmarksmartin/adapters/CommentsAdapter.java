package org.diiage.martin.smartbookmarksmartin.adapters;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.util.JsonReader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import org.diiage.martin.smartbookmarksmartin.R;
import org.diiage.martin.smartbookmarksmartin.models.Comment;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class CommentsAdapter extends BaseAdapter {

    ArrayList<Comment> _comments;
    Activity _context;

    public CommentsAdapter(Activity context, ArrayList<Comment> comments){
        this._comments = comments;
        this._context = context;
    }

    @Override
    public int getCount() {
        return _comments.size();
    }

    @Override
    public Object getItem(int i) {
        return _comments.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;

    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        View view;
        CommentsViewHolder commentsViewHolder;
        if(convertView != null){
            view = convertView;
            commentsViewHolder = (CommentsViewHolder)view.getTag();
        } else{
            LayoutInflater layoutInflater = this._context.getLayoutInflater();
            view = layoutInflater.inflate(R.layout.item_comment, parent, false);

            TextView txtComment = view.findViewById(R.id.txtComment);
            TextView txtBookTitle = view.findViewById(R.id.txtBookTitle);
            TextView txtPageNumber = view.findViewById(R.id.txtPageNumber);
            TextView txtDateComment = view.findViewById(R.id.txtDateComment);

            commentsViewHolder = new CommentsViewHolder(txtComment, txtBookTitle, txtPageNumber, txtDateComment);

            view.setTag(commentsViewHolder);
        }

        Comment comment = _comments.get(i);
        commentsViewHolder.txtComment.setText(comment.getComment());
        commentsViewHolder.txtBookTitle.setText(comment.getBook().getTitle());
        commentsViewHolder.txtPageNumber.setText(comment.getPageNumber().toString());
        commentsViewHolder.txtDateComment.setText(comment.getDate());

        return view;
    }
}