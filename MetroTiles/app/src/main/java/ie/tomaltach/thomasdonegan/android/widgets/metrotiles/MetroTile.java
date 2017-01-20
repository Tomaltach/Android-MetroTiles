package ie.tomaltach.thomasdonegan.android.widgets.metrotiles;

import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by thomasdonegan on 20/01/2017.
 */
public class MetroTile implements IMetroTile {
    //region ENUMS
    public static final int RECTANGLE = 0;
    public static final int CIRCLE = 1;

    public static final int FALSE = 0;
    public static final int TRUE = 1;
    //endregion

    private MetroTileView metroTileView;
    private List<LinearLayout> rows = new ArrayList<>();
    private Map<String, LinearLayout> tiles = new HashMap<>();

    /**
     * Pass in MetroTileView to allow access to the buttons inside.
     *
     * @param metroTileView
     */
    public MetroTile(MetroTileView metroTileView) {
        this.metroTileView = metroTileView;
        getRows();
        getTiles();
    }

    /**
     * Pull out the rows of LinearLayout into a list.
     */
    private void getRows() {
        int count = metroTileView.getChildCount();
        for (int i=0; i<count; i++) {
            LinearLayout layout = (LinearLayout) metroTileView.getChildAt(i);
            rows.add(layout);
        }
    }

    /**
     * Pull out the Buttons from the LinearLayouts list and add them to their own list.
     */
    private void getTiles() {
        for (int i=0; i<rows.size(); i++) {
            LinearLayout row = rows.get(i);
            int count = row.getChildCount();
            for (int j=0; j<count; j++) {
                LinearLayout tile = (LinearLayout) row.getChildAt(j);
                TextView textView = (TextView) tile.getChildAt(1);
                String text = (String) textView.getText();
                String id = "tile" + text.replaceAll("\\s+","");
                tiles.put(id, tile);
            }
        }
    }

    /**
     * Return Button based on the button text. Like an ID as it searchs from a Map<String, Button>.
     *
     * @param text Button text, e.g. in the DEMO the text 'devices' is used.
     * @return Button
     */
    public LinearLayout getTile(String text) {
        return tiles.get(text);
    }
}
