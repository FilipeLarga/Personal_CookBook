package filipelarga.personalcookbook_freerecipemanager.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import filipelarga.personalcookbook_freerecipemanager.R;
import filipelarga.personalcookbook_freerecipemanager.database.RecipeEntity;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;
import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection;

public class CategorySection extends StatelessSection {

    private String title;
    private List<RecipeEntity> list;

    public CategorySection(String title, List<RecipeEntity> list) {
        super(SectionParameters.builder()
                .itemResourceId(R.layout.item_list_recipes)
                .headerResourceId(R.layout.header_list_recipes)
                .build());
        this.title = title;
        this.list = list;
    }

    @Override
    public int getContentItemsTotal() {
        return list.size();
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        final ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        RecipeEntity recipe = list.get(position);
        itemViewHolder.getTextItem().setText(recipe.RecipeName);

    }

    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
        return new HeaderViewHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
        HeaderViewHolder headerHolder = (HeaderViewHolder) holder;
        headerHolder.setText(title);
    }

    public void addRecipeToSection(RecipeEntity recipe) {
        list.add(recipe);
    }

    public void removeRecipesFromSection() {
        list.clear();
    }
}
