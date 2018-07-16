package filipelarga.personalcookbook_freerecipemanager.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import filipelarga.personalcookbook_freerecipemanager.Categories;
import filipelarga.personalcookbook_freerecipemanager.R;
import filipelarga.personalcookbook_freerecipemanager.database.RecipeEntity;
import filipelarga.personalcookbook_freerecipemanager.recyclerview.CategorySection;
import filipelarga.personalcookbook_freerecipemanager.recyclerview.RecipeViewModel;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;

public class ListRecipesFragment extends Fragment {

    private TextView tv;

    private SectionedRecyclerViewAdapter sectionAdapter;

    private CategorySection meatSection = new CategorySection(Categories.MEAT, new ArrayList<RecipeEntity>());
    private CategorySection fishSection = new CategorySection(Categories.FISH, new ArrayList<RecipeEntity>());
    private CategorySection favouriteSection = new CategorySection(Categories.FAVOURITE, new ArrayList<RecipeEntity>());
    // private CategorySection

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_recipes, container, false);
        tv = container.findViewById(R.id.list_recipes_textview_norecipes);
        sectionAdapter = new SectionedRecyclerViewAdapter();
        RecyclerView recyclerView = view.findViewById(R.id.fragment_list_recipes_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(sectionAdapter);
        setupViewModel();
        return view;
    }

    public void setupViewModel() {
        RecipeViewModel recipeViewModel = ViewModelProviders.of(this).get(RecipeViewModel.class);
        recipeViewModel.getRecipes().observe(this, new Observer<List<RecipeEntity>>() {
            @Override
            public void onChanged(@Nullable List<RecipeEntity> recipeEntities) {
                if (recipeEntities != null && !recipeEntities.isEmpty()) {
                    //if (tv != null)
                    tv.setVisibility(View.INVISIBLE);
                    buildAdapter(recipeEntities);
                } else {
                    Log.v("recyc", "List was empty - Cleaning the adapter!!");
                    cleanAdapter();
                    //if (tv != null)
                    tv.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void buildAdapter(List<RecipeEntity> recipeEntityList) {
        cleanAdapter();
        Log.v("recyc", "Beginning to build adapter");
        for (RecipeEntity recipe : recipeEntityList) {
            Log.v("recyc", "NAME: " + recipe.RecipeName);
            Log.v("recyc", "Categories: " + recipe.categories.toString());
            for (String category : recipe.categories) {
                if (category.equals(Categories.FAVOURITE)) {
                    favouriteSection.addRecipeToSection(recipe);
                }
                if (category.equals(Categories.MEAT)) {
                    meatSection.addRecipeToSection(recipe);
                }
                if (category.equals(Categories.FISH)) {
                    fishSection.addRecipeToSection(recipe);
                }
            }
        }
        if (favouriteSection.getContentItemsTotal() > 0) {
            sectionAdapter.addSection(favouriteSection);
        }
        if (meatSection.getContentItemsTotal() > 0) {
            sectionAdapter.addSection(meatSection);
        }
        if (fishSection.getContentItemsTotal() > 0) {
            sectionAdapter.addSection(fishSection);
        }
        sectionAdapter.notifyDataSetChanged();
        Log.v("recyc", "Finished building adapter");
    }

    private void cleanAdapter() {
        sectionAdapter.removeAllSections();
        favouriteSection.removeRecipesFromSection();
        fishSection.removeRecipesFromSection();
        sectionAdapter.notifyDataSetChanged();
    }
}
