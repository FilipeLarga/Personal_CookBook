package filipelarga.personalcookbook_freerecipemanager.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import filipelarga.personalcookbook_freerecipemanager.R;

public class ListRecipesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_recipes);
        ImageView imageView = findViewById(R.id.list_recipes_imageview);

        Toolbar toolbar =   findViewById(R.id.list_recipes_toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        moveFabPosition();
        loadImage(imageView);
        getSupportFragmentManager().beginTransaction().add(R.id.coordinator, new ListRecipesFragment()).commit();
    }

    private void loadImage(ImageView imageView) {
        Glide.with(this).load(R.drawable.list_recipes_backdrop).into(imageView);
    }

    private void moveFabPosition() {
        AppBarLayout appBarLayout = findViewById(R.id.list_recipes_appbar);
        final FloatingActionButton fab = findViewById(R.id.list_recipes_fab_bottom);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    fab.show();
                    isShow = true;
                } else if (isShow) {
                    isShow = false;
                    fab.hide();
                }
            }
        });


    }

    public void startAddRecipeActivity(View view) {
        Intent intent = new Intent(this, NewRecipeActivity.class);
        startActivity(intent);
    }
}
