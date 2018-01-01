package com.android.test;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.test.Adapter.DetailsAdapter;
import com.android.test.Model.MainModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;


public class DetailsActivity extends AppCompatActivity {

    private CollapsingToolbarLayout collapsingToolbar;
    private AppBarLayout appBarLayout;
    private RecyclerView recyclerView;

    private DetailsAdapter detailsAdapter;

    private Menu collapsedMenu;
    private boolean appBarExpanded = true;
    private MainModel test;
    String[] value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.anim_toolbar);
        ImageView header = (ImageView)findViewById(R.id.header);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        appBarLayout = (AppBarLayout) findViewById(R.id.appbar);

        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(getString(R.string.app_name));
        Bitmap bitmap = null;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
//            test = (MainModel)extras.getSerializable("test");

            //test = new MainModel();

            test = MainActivity.itemList.get(extras.getInt("item"));
            //test.setPhoto(extras.getString("photo"));
            value = new String[]{
                    test.getId().toString(),
                    test.getFirstName(),
                    test.getLastName(),
                    test.getEmail(),
                    test.getGender(),
                    test.getIpAddress(),
                    test.getPhoto(),
                    test.getEmployment().getName(),
                    test.getEmployment().getPosition()};
            System.out.println("------"+test.getName());



            collapsingToolbar.setTitle(test.getName());
            Glide.with(this)
                    .load(test.getPhoto())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)   // cache both original
                    .into(header);

           /* try {

                bitmap = Glide.with(this)
                        .load(test.getPhoto())
                        .asBitmap()
                        .into(-1, -1).get();

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }*/

        }
        if(bitmap==null) bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_foreground);
            Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
                @SuppressWarnings("ResourceType")
                @Override
                public void onGenerated(Palette palette) {
                    int vibrantColor = palette.getVibrantColor(R.color.primary_500);
                    collapsingToolbar.setContentScrimColor(vibrantColor);
                    collapsingToolbar.setStatusBarScrimColor(R.color.black_trans80);
                }
            });

        recyclerView = (RecyclerView) findViewById(R.id.scrollableview);
        //  Use when your list size is constant for better performance
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        detailsAdapter = new DetailsAdapter(this, value);
        recyclerView.setAdapter(detailsAdapter);

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                //Log.d(DetailsActivity.class.getSimpleName(), "onOffsetChanged: verticalOffset: " + verticalOffset);

                //  Vertical offset == 0 indicates appBar is fully expanded.
                if (Math.abs(verticalOffset) > 200) {
                    appBarExpanded = false;
                    invalidateOptionsMenu();
                } else {
                    appBarExpanded = true;
                    invalidateOptionsMenu();
                }
            }
        });

        FloatingActionButton myFab = (FloatingActionButton) findViewById(R.id.myFAB);
        myFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(DetailsActivity.this, "clicked add", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (collapsedMenu != null
                && (!appBarExpanded || collapsedMenu.size() != 1)) {
            //collapsed
            collapsedMenu.add("Add")
                    .setIcon(R.drawable.ic_action_add)
                    .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        } else {
            //expanded
        }
        return super.onPrepareOptionsMenu(collapsedMenu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        collapsedMenu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.action_settings:
                return true;
        }
        if (item.getTitle() == "Add") {
            Toast.makeText(this, "clicked add", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }
}
