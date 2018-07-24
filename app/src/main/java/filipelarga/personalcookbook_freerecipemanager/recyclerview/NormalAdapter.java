package filipelarga.personalcookbook_freerecipemanager.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import filipelarga.personalcookbook_freerecipemanager.R;
import filipelarga.personalcookbook_freerecipemanager.database.RecipeEntity;

public class NormalAdapter extends RecyclerView.Adapter<NormalAdapter.MyViewHolder> {

    private List<RecipeEntity> list;

    public NormalAdapter() {
        list = new ArrayList<>();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_recipes, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.title.setText(list.get(position).RecipeName);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addRecipteToAdapter(RecipeEntity recipe) {
        list.add(recipe);
    }

    public void clearRecipes() {
        list.clear();
    }

    public RecipeEntity getRecipeEntity(int pos) {
        return list.get(pos);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;

        public MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.tvItem);
        }
    }
}
