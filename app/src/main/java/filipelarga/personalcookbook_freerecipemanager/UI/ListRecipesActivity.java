package filipelarga.personalcookbook_freerecipemanager.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import filipelarga.personalcookbook_freerecipemanager.R;

public class ListRecipesActivity extends AppCompatActivity {

    ListRecipesFragment listRecipesFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_recipes);

        Toolbar toolbar =   findViewById(R.id.list_recipes_toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //moveFabPosition();
        loadImage();
        listRecipesFragment = new ListRecipesFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.list_recipes_constraintlayout, listRecipesFragment).commit();
        setupTabListener((TabLayout) findViewById(R.id.list_recipes_tab_layout));
    }

    private void loadImage() {
        NavigationView navigationView = findViewById(R.id.list_recipes_navigationview);
        View header = navigationView.getHeaderView(0);
        ImageView drawerHeader = header.findViewById(R.id.drawer_header_imageview);
        Glide.with(this).load(R.drawable.list_recipes_backdrop).into(drawerHeader);
    }

    /*private void moveFabPosition() {
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


    }*/

    private void setupTabListener(TabLayout tabLayout) {
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                listRecipesFragment.changeCategory(tab.getText().toString());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    public void startAddRecipeActivity(View view) {
        Intent intent = new Intent(this, NewRecipeActivity.class);
        startActivity(intent);
    }
}
