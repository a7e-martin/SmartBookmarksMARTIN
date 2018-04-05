package org.diiage.martin.smartbookmarksmartin.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.diiage.martin.smartbookmarksmartin.R;
import org.diiage.martin.smartbookmarksmartin.models.Book;
import org.diiage.martin.smartbookmarksmartin.models.Comment;

import java.util.ArrayList;

public class SpinnerBooksAdapter extends BaseAdapter {

    private ArrayList<Book> data;
    SpinnerItemHolder holder;
    private Activity context;

    public SpinnerBooksAdapter(ArrayList<Book> data, Activity context){
        super();
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        SpinnerItemHolder spinnerItemHolder;
        if(convertView != null){
            view = convertView;
            spinnerItemHolder = (SpinnerItemHolder)view.getTag();
        } else{
            LayoutInflater layoutInflater = this.context.getLayoutInflater();
            view = layoutInflater.inflate(R.layout.spinner_item_book, parent, false);

            TextView txtTitle = view.findViewById(R.id.txtTitle);

            spinnerItemHolder = new SpinnerItemHolder(txtTitle);

            view.setTag(spinnerItemHolder);
        }

        Book book = data.get(position);
        spinnerItemHolder.txtTitle.setText(book.getTitle());

        return view;
    }
}

class SpinnerItemHolder{
    public TextView txtTitle;

    public SpinnerItemHolder(TextView txtTitle){
        this.txtTitle = txtTitle;
    }
}
