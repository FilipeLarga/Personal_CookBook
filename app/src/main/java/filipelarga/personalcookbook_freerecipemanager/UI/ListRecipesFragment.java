package filipelarga.personalcookbook_freerecipemanager.ui;

import android.app.Dialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import filipelarga.personalcookbook_freerecipemanager.Categories;
import filipelarga.personalcookbook_freerecipemanager.R;
import filipelarga.personalcookbook_freerecipemanager.database.DBExecutioner;
import filipelarga.personalcookbook_freerecipemanager.database.RecipeEntity;
import filipelarga.personalcookbook_freerecipemanager.recyclerview.CategorySection;
import filipelarga.personalcookbook_freerecipemanager.recyclerview.NormalAdapter;
import filipelarga.personalcookbook_freerecipemanager.recyclerview.RecipeViewModel;
import filipelarga.personalcookbook_freerecipemanager.recyclerview.RecyclerTouchListener;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;

public class ListRecipesFragment extends Fragment {

    private TextView tv;

    private List<RecipeEntity> recipeEntitiesList;

    private String categorySelected;
    private SectionedRecyclerViewAdapter sectionAdapter;
    private NormalAdapter normalAdapter;
    private boolean isNormalAdapterOn = false;
    private RecyclerView recyclerView;

    private CategorySection meatSection = new CategorySection(Categories.MEAT, new ArrayList<RecipeEntity>());
    private CategorySection fishSection = new CategorySection(Categories.FISH, new ArrayList<RecipeEntity>());
    private CategorySection favouriteSection = new CategorySection(Categories.FAVOURITE, new ArrayList<RecipeEntity>());

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_recipes, container, false);
        this.tv = container.findViewById(R.id.list_recipes_textview_norecipes);
        sectionAdapter = new SectionedRecyclerViewAdapter();
        normalAdapter = new NormalAdapter();

        recyclerView = view.findViewById(R.id.fragment_list_recipes_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(sectionAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerView, new RecyclerTouchListener.ClickListener() {

            @Override
            public void onClick(View view, int position) {
                RecipeEntity recipeClicked = null;
                if (isNormalAdapterOn) {
                    Toast.makeText(getContext(), "onClick " + recipeEntitiesList.get(position).RecipeName, Toast.LENGTH_SHORT).show();
                    recipeClicked = recipeEntitiesList.get(position);
                } else {
                    TextView textView = view.findViewById(R.id.tvItem);
                    if (textView != null) {
                        String name = (String) textView.getText();
                        for (RecipeEntity recipe : recipeEntitiesList) {
                            if (recipe.RecipeName.equals(name)) {
                                recipeClicked = recipe;
                                break;
                            }
                        }
                        Toast.makeText(getContext(), "onClick " + recipeClicked.RecipeName, Toast.LENGTH_SHORT).show();
                    } else return; //Clicou num header
                }
                //TODO Intent para nova activity com o recipeClicked
                startViewRecipeActivity();
            }

            @Override
            public void onLongClick(View view, int position) {
                RecipeEntity recipeClicked = null;
                if (isNormalAdapterOn) {
                    Toast.makeText(getContext(), "onLongClick " + recipeEntitiesList.get(position).RecipeName, Toast.LENGTH_SHORT).show();
                    recipeClicked = recipeEntitiesList.get(position);
                } else {
                    TextView textView = view.findViewById(R.id.tvItem);
                    if (textView != null) {
                        String name = (String) textView.getText();
                        for (RecipeEntity recipe : recipeEntitiesList) {
                            if (recipe.RecipeName.equals(name)) {
                                recipeClicked = recipe;
                                break;
                            }
                        }
                        Toast.makeText(getContext(), "onLongClick " + recipeClicked.RecipeName, Toast.LENGTH_SHORT).show();
                    } else return; //Clicou num header
                }
                showDialog(recipeClicked);
            }
        }));

        setupViewModel();
        return view;
    }

    public void setupViewModel() {
        RecipeViewModel recipeViewModel = ViewModelProviders.of(this).get(RecipeViewModel.class);
        recipeViewModel.getRecipes().observe(this, new Observer<List<RecipeEntity>>() {
            @Override
            public void onChanged(@Nullable List<RecipeEntity> recipeEntities) {
                recipeEntitiesList = recipeEntities;
                if (recipeEntities != null && !recipeEntities.isEmpty()) {
                    if (tv != null)
                        tv.setVisibility(View.INVISIBLE);
                    if (categorySelected == null || categorySelected.equalsIgnoreCase("ALL")) {
                        buildSectionedAdapter(recipeEntities);
                        isNormalAdapterOn = false;
                    } else {
                        buildNormalAdapter(recipeEntities);
                        isNormalAdapterOn = true;
                    }
                } else {
                    cleanSectionedAdapter();
                    cleanNormalAdapter();
                    if (tv != null)
                        tv.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void buildNormalAdapter(List<RecipeEntity> recipeEntityList) {
        recyclerView.setAdapter(normalAdapter);
        cleanNormalAdapter();
        for (RecipeEntity recipe : recipeEntityList) {
            boolean belongs = false;
            for (String category : recipe.categories) {
                if (category.equalsIgnoreCase(categorySelected))
                    belongs = true;
            }
            if (belongs) {
                normalAdapter.addRecipteToAdapter(recipe);
            }
        }
        normalAdapter.notifyDataSetChanged();
    }

    private void buildSectionedAdapter(List<RecipeEntity> recipeEntityList) {
        recyclerView.setAdapter(sectionAdapter);
        cleanSectionedAdapter();
        for (RecipeEntity recipe : recipeEntityList) {
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
    }

    private void cleanSectionedAdapter() {
        sectionAdapter.removeAllSections();
        favouriteSection.removeRecipesFromSection();
        fishSection.removeRecipesFromSection();
        sectionAdapter.notifyDataSetChanged();
    }

    private void cleanNormalAdapter() {
        normalAdapter.clearRecipes();
        normalAdapter.notifyDataSetChanged();
    }

    public void changeCategory(String category) {
        categorySelected = category;
        if (categorySelected.equalsIgnoreCase("ALL")) {
            buildSectionedAdapter(recipeEntitiesList);
            isNormalAdapterOn = false;
        } else {
            buildNormalAdapter(recipeEntitiesList);
            isNormalAdapterOn = true;
        }
    }

    public void showDialog(final RecipeEntity recipe) {
        final Dialog dialog = new Dialog(getContext());

//      IF THE RECIPE HAS IMAGE
        dialog.setContentView(R.layout.popup_list_recipes);
        TextView title = dialog.findViewById(R.id.popup_title_textview);
        title.setText(recipe.RecipeName);
        ImageView image = dialog.findViewById(R.id.popup_imageview);
        Glide.with(this).load(R.drawable.test_remove_later_please).into(image);
        Button viewButton = dialog.findViewById(R.id.popup_view_recipe);
        Button editButton = dialog.findViewById(R.id.popup_edit_recipe);

        Button deleteButton = dialog.findViewById(R.id.popup_remove_recipe);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBExecutioner.getInstance().deleteRecipe(recipe, getContext());
                Snackbar snackbar = Snackbar.make(getActivity().findViewById(R.id.list_recipes_coordinator),
                        recipe.RecipeName + " has been removed", Snackbar.LENGTH_LONG);
                snackbar.setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DBExecutioner.getInstance().addRecipe(recipe, getContext());
                    }
                });
                snackbar.show();
                dialog.dismiss();
            }
        });

//        IF THE RECIPE HAS NO IMAGE
//        dialog.setContentView(R.layout.popup_list_recipes_no_image);
//        TextView title = dialog.findViewById(R.id.popup_no_image_title_textview);
//        title.setText(recipe.RecipeName);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    public void startViewRecipeActivity() {
        Intent intent = new Intent(getActivity(), ViewRecipeActivity.class);
        startActivity(intent);
    }

}
