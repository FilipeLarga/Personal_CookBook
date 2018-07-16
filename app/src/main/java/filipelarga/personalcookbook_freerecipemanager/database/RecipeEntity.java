package filipelarga.personalcookbook_freerecipemanager.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.List;

@Entity(tableName = "Recipe")
public class RecipeEntity {

    @PrimaryKey
    @NonNull
    public String RecipeName;
    public List<String> categories;
    public int duration;
    public List<String> directions;
    public int difficulty;
    public int servings;

    public RecipeEntity(String RecipeName, List<String> categories, int duration, List<String> directions, int difficulty, int servings) {
        this.RecipeName = RecipeName;
        this.categories = categories;
        this.duration = duration;
        this.directions = directions;
        this.difficulty = difficulty;
        this.servings = servings;
    }

}
