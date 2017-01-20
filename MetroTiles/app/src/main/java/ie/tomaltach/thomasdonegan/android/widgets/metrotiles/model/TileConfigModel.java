package ie.tomaltach.thomasdonegan.android.widgets.metrotiles.model;

import android.graphics.drawable.Drawable;

/**
 * Created by thomasdonegan on 20/01/2017.
 */
public class TileConfigModel {
    private String text = "Button";
    private Drawable icon = null;

    public TileConfigModel() {}
    public TileConfigModel(String text, Drawable icon) {
        this.text = text;
        this.icon = icon;
    }

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

    public Drawable getIcon() {
        return icon;
    }
    public void setIcon(Drawable icon) {
        this.icon = icon;
    }
}
