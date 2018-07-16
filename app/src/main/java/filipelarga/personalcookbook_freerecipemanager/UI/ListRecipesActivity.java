package filipelarga.personalcookbook_freerecipemanager.UI;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import filipelarga.personalcookbook_freerecipemanager.R;
import filipelarga.personalcookbook_freerecipemanager.recyclerview.ListRecipesFragment;

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
        //initCollapsingToolbar();
        loadImage(imageView);

        getSupportFragmentManager().beginTransaction().add(R.id.coordinator, new ListRecipesFragment()).commit();
    }

    private void loadImage(ImageView imageView) {
        Glide.with(this).load(R.drawable.list_recipes_backdrop).into(imageView);
    }

    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar = findViewById(R.id.list_recipes_collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = findViewById(R.id.list_recipes_appbar);
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(getString(R.string.Recipes));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }

}
