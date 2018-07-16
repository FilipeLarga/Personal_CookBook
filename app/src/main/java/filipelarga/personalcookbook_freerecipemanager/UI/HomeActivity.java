package filipelarga.personalcookbook_freerecipemanager.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import filipelarga.personalcookbook_freerecipemanager.R;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Button button = findViewById(R.id.home_add_recipe_Button);
        button.setAnimation(AnimationUtils.loadAnimation(this, R.anim.down_up_animation));

        Button button2 = findViewById(R.id.home_view_recipe_Button);
        button2.setAnimation(AnimationUtils.loadAnimation(this, R.anim.down_up_animation));

        loadImage((ImageView) findViewById(R.id.home_background_ImageView));
    }

    private void loadImage(ImageView viewById) {
        Glide.with(this).load(R.drawable.love_ingredient).into(viewById);
    }

    public void startAddRecipeActivity(View view) {
        Intent intent = new Intent(this, NewRecipeActivity.class);
        startActivity(intent);
    }

    public void startListRecipesActivity(View view) {
        Intent intent = new Intent(this, ListRecipesActivity.class);
        startActivity(intent);
    }

}
