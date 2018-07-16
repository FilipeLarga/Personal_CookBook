package filipelarga.personalcookbook_freerecipemanager.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import filipelarga.personalcookbook_freerecipemanager.R;

public class ItemViewHolder extends RecyclerView.ViewHolder {

    private final View rootView;
    private final ImageView imageItem;
    private final TextView textItem;
    private final TextView textItem2;


    public ItemViewHolder(View itemView) {
        super(itemView);

        rootView = itemView;
        imageItem = itemView.findViewById(R.id.imgItem);
        textItem = itemView.findViewById(R.id.tvItem);
        textItem2 = itemView.findViewById(R.id.tvItem2);
    }

    public ImageView getImageItem() {
        return imageItem;
    }

    public TextView getTextItem() {
        return textItem;
    }
}
