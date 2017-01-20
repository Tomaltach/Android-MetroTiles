package ie.tomaltach.thomasdonegan.android.widgets.metrotiles;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ie.tomaltach.thomasdonegan.android.widgets.R;
import ie.tomaltach.thomasdonegan.android.widgets.metrotiles.exception.MetroTileAttributeException;
import ie.tomaltach.thomasdonegan.android.widgets.metrotiles.exception.TileConfigException;
import ie.tomaltach.thomasdonegan.android.widgets.metrotiles.model.TileConfigModel;

/**
 * Created by thomasdonegan on 20/01/2017.
 */
public class MetroTileView extends LinearLayout {
    private Context mContext;
    private int numberOfTiles;

    //region Static Final Properties

    //region LinearLayout
    private static final int TILES_PER_ROW = 2;
    private static final int TILE_HEIGHT = 100;
    private static int BACKGROUND_COLOR;
    private static final int BORDER_COLOR = Color.BLACK;
    private static final int BORDER_SIZE = 1;
    private static final int SHOW_BORDER = MetroTile.TRUE;
    private static final int TILE_SHAPE = MetroTile.RECTANGLE;
    private static final int MARGIN = 0;
    private static final int MARGIN_LEFT = 0;
    private static final int MARGIN_RIGHT = 0;
    private static final int MARGIN_TOP = 0;
    private static final int MARGIN_BOTTOM = 0;
    private static final int PADDING = 0;
    private static final int PADDING_LEFT = 0;
    private static final int PADDING_RIGHT = 0;
    private static final int PADDING_TOP = 0;
    private static final int PADDING_BOTTOM = 0;
    private static final int ICON_TEXT_ORIENTATION = LinearLayout.HORIZONTAL;
    //endregion
    //region ImageView
    private static final float ICON_SIZE = 14.0f;
    private static final int ICON_COLOR = Color.BLACK;
    //endregion
    //region TextView
    private static final float TEXT_SIZE = 14.0f;
    private static final int TEXT_COLOR = Color.BLACK;
    //endregion

    //endregion

    //region Customizable Properties

    //region LinearLayout
    private int mTilesPerRow;
    private int mTileHeight;
    private int mBackgroundColor;
    private int mBorderColor;
    private int mBorderSize;
    private int mShowBorder;
    private int mTileShape;
    private int mMargin;
    private int mMarginLeft;
    private int mMarginRight;
    private int mMarginTop;
    private int mMarginBottom;
    private int mPadding;
    private int mPaddingLeft;
    private int mPaddingRight;
    private int mPaddingTop;
    private int mPaddingBottom;
    private int mIconTextOrientation;
    //endregion
    //region ImageView
    private Drawable[] mTilesIcon;
    private float mIconSize;
    private int mIconColor;
    //endregion
    //region TextView
    private String[] mTilesText;
    private float mTextSize;
    private int mTextColor;
    //endregion

    //endregion

    //region Constructors

    public MetroTileView(final Context context) {
        this(context, null);
    }
    public MetroTileView(final Context context, final AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public MetroTileView(final Context context, final AttributeSet attrs, final int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
        BACKGROUND_COLOR = mContext.getResources().getColor(R.color.default_background);
        readAttrs(context, attrs, defStyle);
        init();
    }

    //endregion

    /**
     * Used to read the XML attributes.
     *
     * @param context
     * @param attrs
     * @param defStyle
     */
    private void readAttrs(final Context context, final AttributeSet attrs, final int defStyle) {
        final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MetroTileView, defStyle, 0);

        //region LinearLayout
        mTilesPerRow = a.getInt(R.styleable.MetroTileView_tilesPerRow, TILES_PER_ROW);
        mTileHeight = a.getInt(R.styleable.MetroTileView_tileHeight, TILE_HEIGHT);
        mBackgroundColor = a.getColor(R.styleable.MetroTileView_backgroundColor, BACKGROUND_COLOR);
        mBorderColor = a.getColor(R.styleable.MetroTileView_borderColor, BORDER_COLOR);
        mBorderSize = a.getInt(R.styleable.MetroTileView_borderSize, BORDER_SIZE);
        mShowBorder = a.getInt(R.styleable.MetroTileView_showTileBorder, SHOW_BORDER);
        mTileShape = a.getInt(R.styleable.MetroTileView_tileShape, TILE_SHAPE);

        mIconTextOrientation = a.getInt(R.styleable.MetroTileView_iconTextOrientation, ICON_TEXT_ORIENTATION);

        mMargin = a.getInteger(R.styleable.MetroTileView_margin, MARGIN);
        if (mMargin > 0) {
            mMarginLeft = mMargin;
            mMarginRight = mMargin;
            mMarginTop = mMargin;
            mMarginBottom = mMargin;
        } else {
            mMarginLeft = a.getInteger(R.styleable.MetroTileView_marginLeft, MARGIN_LEFT);
            mMarginRight = a.getInteger(R.styleable.MetroTileView_marginRight, MARGIN_RIGHT);
            mMarginTop = a.getInteger(R.styleable.MetroTileView_marginTop, MARGIN_TOP);
            mMarginBottom = a.getInteger(R.styleable.MetroTileView_marginRight, MARGIN_BOTTOM);
        }

        mPadding = a.getInteger(R.styleable.MetroTileView_padding, PADDING);
        if (mPadding > 0) {
            mPaddingLeft = mPadding;
            mPaddingRight = mPadding;
            mPaddingTop = mPadding;
            mPaddingBottom = mPadding;
        } else {
            mPaddingLeft = a.getInteger(R.styleable.MetroTileView_paddingLeft, PADDING_LEFT);
            mPaddingRight = a.getInteger(R.styleable.MetroTileView_paddingRight, PADDING_RIGHT);
            mPaddingTop = a.getInteger(R.styleable.MetroTileView_paddingTop, PADDING_TOP);
            mPaddingBottom = a.getInteger(R.styleable.MetroTileView_paddingRight, PADDING_BOTTOM);
        }
        //endregion
        //region ImageView
        mIconSize = a.getFloat(R.styleable.MetroTileView_iconSize, ICON_SIZE);
        mIconColor = a.getColor(R.styleable.MetroTileView_iconColor, ICON_COLOR);
        int icons = a.getTextArray(R.styleable.MetroTileView_tileIcon).length;
        Drawable[] tilesIcon = new Drawable[icons];
        CharSequence[] tilesI = a.getTextArray(R.styleable.MetroTileView_tileIcon);
        for (int i=0; i<icons; i++) {
            String fileName = tilesI[i].toString();
            tilesIcon[i] = getMyDrawable(fileName);
        }
        if (tilesIcon == null) {
            throw new MetroTileAttributeException("tilesIcon is required!");
        }
        mTilesIcon = tilesIcon;
        //endregion
        //region TextView
        mTextSize = a.getFloat(R.styleable.MetroTileView_textSize, TEXT_SIZE);
        mTextColor = a.getColor(R.styleable.MetroTileView_textColor, TEXT_COLOR);
        CharSequence[] tilesText = a.getTextArray(R.styleable.MetroTileView_tileText);
        if (tilesText == null) {
            throw new MetroTileAttributeException("tilesText is required!");
        }
        readValues(tilesText);
        //endregion

        List<TileConfigModel> tiles = getTileInfo();
        createMetroTiles(tiles);
        this.setOrientation(LinearLayout.VERTICAL);
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        this.setWeightSum(1f);
        this.setLayoutParams(layoutParams);

        a.recycle();
    }

    private void readValues(final CharSequence[] tilesText) {
        int rangeTilesLength = tilesText.length;

        final int length = rangeTilesLength;
        if (tilesText != null) {
            mTilesText = new String[length];
            for (int i = 0; i < length; i++) {
                mTilesText[i] = tilesText[i].toString();
            }
        }
    }

    @TargetApi(15)
    private void init() {
        // TODO: Why isn't this working with HA layer?
        // The needle is not displayed although the onDraw() is being triggered by invalidate() calls.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
            setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    }

    /**
     * Create the number of rows needed to show all of the tiles.
     *
     * @param tiles
     */
    private void createMetroTiles(List<TileConfigModel> tiles) {
        numberOfTiles = tiles.size();
        for (int i=0; i<numberOfTiles; i+=mTilesPerRow) {
            LinearLayout ll = addRow(tiles, i);
            this.addView(ll);
        }
    }

    /**
     * Create a new LinearLayout for each row needed.
     *
     * @param tiles
     * @param pos
     * @return
     */
    private LinearLayout addRow(List<TileConfigModel> tiles, int pos) {
        LinearLayout linearLayout = new LinearLayout(mContext.getApplicationContext());
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);

        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, mTileHeight);
        linearLayout.setWeightSum(1f);
        linearLayout.setLayoutParams(layoutParams);

        for (int i=0; i<mTilesPerRow; i++) {
            if ((pos+i) < tiles.size()) {
                LinearLayout tile = addTile(tiles.get((pos + i)));
                linearLayout.addView(tile);
            }
        }

        return linearLayout;
    }

    /**
     * Create a new tile with the user config settings.
     *
     * @param config
     * @return
     */
    private LinearLayout addTile(TileConfigModel config) {
        LinearLayout linearLayout = new LinearLayout(mContext);
        if (mTileShape == MetroTile.RECTANGLE)
            linearLayout.setBackgroundResource(R.drawable.metrotile_rectangle);
        if (mTileShape == MetroTile.CIRCLE)
            linearLayout.setBackgroundResource(R.drawable.metrotile_oval);
        // Get color from drawable and change it.
        GradientDrawable bgShape = (GradientDrawable) linearLayout.getBackground();
        bgShape.setColor(mBackgroundColor);
        if (mShowBorder == MetroTile.TRUE)
            bgShape.setStroke(mBorderSize, mBorderColor);
        else if (mShowBorder == MetroTile.FALSE)
            bgShape.setStroke(mBorderSize, mBackgroundColor);
        linearLayout.setOrientation(mIconTextOrientation);
        linearLayout.setGravity(Gravity.CENTER);
        linearLayout.setPadding(mPaddingLeft, mPaddingTop, mPaddingRight, mPaddingBottom);
        LayoutParams llParams = new LayoutParams(0, LayoutParams.MATCH_PARENT);
        llParams.setMargins(mMarginLeft, mMarginTop, mMarginRight, mMarginBottom);
        float weight = (float) 1 / mTilesPerRow;
        llParams.weight = weight;
        linearLayout.setLayoutParams(llParams);

        ImageView imageView = new ImageView(mContext);
        imageView.setImageDrawable(config.getIcon());
        imageView.setColorFilter(mIconColor);
        if (mIconTextOrientation == LinearLayout.HORIZONTAL)
            imageView.setPadding(0, 0, 10, 0);

        TextView textView = new TextView(mContext);
        textView.setText(config.getText());
        textView.setTextSize(mTextSize);
        textView.setTextColor(mTextColor);
        if (mIconTextOrientation == LinearLayout.HORIZONTAL)
            textView.setPadding(10, 0, 0, 0);
        textView.setGravity(Gravity.CENTER);

        linearLayout.addView(imageView);
        linearLayout.addView(textView);

        return linearLayout;
    }

    /**
     * Create Tiles from the XML list.
     *
     * @return
     */
    private List<TileConfigModel> getTileInfo() {
        List<TileConfigModel> tiles = new ArrayList<>();

        if (mTilesText.length != mTilesIcon.length)
            throw new TileConfigException("Icon and Title arrays don't match lengths!");

        for (int i=0; i<mTilesIcon.length; i++) {
            TileConfigModel tile = new TileConfigModel();
            try {
                tile.setText(mTilesText[i]);
                tile.setIcon(mTilesIcon[i]);
            } catch (ArrayIndexOutOfBoundsException e) {
                tile.setText("Tile " + i);
            }
            tiles.add(tile);
        }

        return tiles;
    }

    private Drawable getMyDrawable(String imageName) {
        String file = imageName.substring(imageName.lastIndexOf("/") + 1).trim();
        String noExt = file.substring(0, file.indexOf("."));
        int resourceId = mContext.getResources().getIdentifier(noExt, "drawable", mContext.getPackageName());
        return mContext.getResources().getDrawable(resourceId);
    }
}
