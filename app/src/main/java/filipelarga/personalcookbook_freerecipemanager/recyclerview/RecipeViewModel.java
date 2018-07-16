package filipelarga.personalcookbook_freerecipemanager.recyclerview;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import filipelarga.personalcookbook_freerecipemanager.database.RecipeEntity;
import filipelarga.personalcookbook_freerecipemanager.database.RecipeIngredientDatabase;

public class RecipeViewModel extends AndroidViewModel {

    private LiveData<List<RecipeEntity>> recipes;

    public RecipeViewModel(@NonNull Application application) {
        super(application);

        RecipeIngredientDatabase db = RecipeIngredientDatabase.getInstance(this.getApplication());
        recipes = db.recipeDao().loadAllRecipes();
    }

    public LiveData<List<RecipeEntity>> getRecipes() {
        return recipes;
    }
}
