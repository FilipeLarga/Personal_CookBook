package filipelarga.personalcookbook_freerecipemanager.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import filipelarga.personalcookbook_freerecipemanager.R;

public class ListRecipesActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    ListRecipesFragment listRecipesFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_recipes);

        Toolbar toolbar =   findViewById(R.id.list_recipes_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
        loadImage();
        listRecipesFragment = new ListRecipesFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.list_recipes_constraintlayout, listRecipesFragment).commit();
        setupTabListener((TabLayout) findViewById(R.id.list_recipes_tab_layout));

        mDrawerLayout = findViewById(R.id.list_recipes_drawerlayout);
    }

    private void loadImage() {
        NavigationView navigationView = findViewById(R.id.list_recipes_navigationview);
        View header = navigationView.getHeaderView(0);
        ImageView drawerHeader = header.findViewById(R.id.drawer_header_imageview);
        Glide.with(this).load(R.drawable.list_recipes_backdrop).into(drawerHeader);
    }

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                Log.v("home", "Clicked");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_list_recipes, menu);
        return true;
    }
}
