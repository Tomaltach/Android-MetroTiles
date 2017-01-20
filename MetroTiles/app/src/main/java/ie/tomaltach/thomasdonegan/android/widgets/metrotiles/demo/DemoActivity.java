package ie.tomaltach.thomasdonegan.android.widgets.metrotiles.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import ie.tomaltach.thomasdonegan.android.widgets.R;
import ie.tomaltach.thomasdonegan.android.widgets.metrotiles.IMetroTile;
import ie.tomaltach.thomasdonegan.android.widgets.metrotiles.MetroTile;
import ie.tomaltach.thomasdonegan.android.widgets.metrotiles.MetroTileView;

/**
 * Created by thomasdonegan on 20/01/2017.
 */
public class DemoActivity extends AppCompatActivity {
    private MetroTileView metroTileView, metroTileView2;
    private IMetroTile mMetroTile, mMetroTile2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_metrotiles);
        metroTileView = (MetroTileView) findViewById(R.id.tileView);
        metroTileView2 = (MetroTileView) findViewById(R.id.tileView2);
        mMetroTile = new MetroTile(metroTileView);
        mMetroTile2 = new MetroTile(metroTileView2);

        LinearLayout l1 = mMetroTile.getTile("tileButton1");
        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DemoActivity.this, DemoActivity.class);
                startActivity(intent);
            }
        });

        LinearLayout l2 = mMetroTile.getTile("tileButton2");
        l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DemoActivity.this, DemoActivity.class);
                startActivity(intent);
            }
        });

        LinearLayout l3 = mMetroTile.getTile("tileButton3");
        l3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DemoActivity.this, DemoActivity.class);
                startActivity(intent);
            }
        });
    }
}
