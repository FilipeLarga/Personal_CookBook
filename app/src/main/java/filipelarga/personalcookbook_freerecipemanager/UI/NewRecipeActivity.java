package filipelarga.personalcookbook_freerecipemanager.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import filipelarga.personalcookbook_freerecipemanager.Categories;
import filipelarga.personalcookbook_freerecipemanager.R;
import filipelarga.personalcookbook_freerecipemanager.database.RecipeEntity;
import filipelarga.personalcookbook_freerecipemanager.database.RecipeIngredientDatabase;

public class NewRecipeActivity extends AppCompatActivity {

    ArrayList<String> categories = new ArrayList<>();
    ArrayList<String> directions = new ArrayList<>();
    ExecutorService executor = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_recipe);
        categories.add(Categories.FAVOURITE);
        categories.add(Categories.FISH);
    }

    public void addRecipeToDB(View view) {
        final int r = new Random().nextInt((1000 - 1) + 1) + 1;
        executor.execute(new Runnable() {
            @Override
            public void run() {
                directions.add("12323123");
                RecipeIngredientDatabase.getInstance(getApplicationContext()).recipeDao().insertRecipe(
                        new RecipeEntity("NomeTeste" + r,
                                categories, 10, directions, 1, 1));
            }
        });
    }

    public void delete(View view) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                RecipeIngredientDatabase.getInstance(getApplicationContext()).recipeDao().deleteAll();
            }
        });
    }

    public void goBack(View view) {
        finish();
    }

}
