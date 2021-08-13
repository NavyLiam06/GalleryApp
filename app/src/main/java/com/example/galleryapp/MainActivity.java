package com.example.galleryapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    BottomNavigationView bottomNavigationView;
    ItemFragment itemFragment;
    AlbumFragment albumFragment;

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.MANAGE_EXTERNAL_STORAGE}, 1);
//        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
//        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 3);
//        int MyVersion = Build.VERSION.SDK_INT;
//        if (MyVersion > Build.VERSION_CODES.LOLLIPOP_MR1) {
//            if (!Environment.isExternalStorageManager()){
//                Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
//                Uri uri = Uri.fromParts("package", getPackageName(), null);
//                intent.setData(uri);
//                startActivity(intent);
//            }
//        }

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bot_navBar);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        itemFragment = new ItemFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                itemFragment).commit();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_image:
                itemFragment = new ItemFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        itemFragment).commit();
                break;
            case R.id.nav_album:
                albumFragment = new AlbumFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        albumFragment).commit();
                break;
            case R.id.nav_more:
                Toast.makeText(this, "Show more...", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}