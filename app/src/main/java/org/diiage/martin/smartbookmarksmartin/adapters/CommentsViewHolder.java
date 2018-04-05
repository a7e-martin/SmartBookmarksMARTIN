package org.diiage.martin.smartbookmarksmartin.adapters;

import android.widget.TextView;

public class CommentsViewHolder {
    public TextView txtComment;
    public TextView txtBookTitle;
    public TextView txtPageNumber;
    public TextView txtDateComment;

    public CommentsViewHolder(TextView txtComment, TextView txtBookTitle, TextView txtPageNumber, TextView txtDateComment) {
        this.txtComment = txtComment;
        this.txtBookTitle = txtBookTitle;
        this.txtPageNumber = txtPageNumber;
        this.txtDateComment = txtDateComment;
    }
}
