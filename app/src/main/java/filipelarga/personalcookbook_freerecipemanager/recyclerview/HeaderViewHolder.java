package filipelarga.personalcookbook_freerecipemanager.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import filipelarga.personalcookbook_freerecipemanager.R;

public class HeaderViewHolder extends RecyclerView.ViewHolder {

    private final TextView textView;


    public HeaderViewHolder(View itemView) {
        super(itemView);

        textView = itemView.findViewById(R.id.tvTitle);

    }

    public TextView getTextView() {
        return textView;
    }

    public void setText(String text) {
        textView.setText(text);
    }
}
