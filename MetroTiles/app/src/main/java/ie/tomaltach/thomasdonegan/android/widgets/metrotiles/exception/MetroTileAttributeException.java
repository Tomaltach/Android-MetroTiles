package ie.tomaltach.thomasdonegan.android.widgets.metrotiles.exception;

/**
 * Created by thomasdonegan on 20/01/2017.
 */
public class MetroTileAttributeException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public MetroTileAttributeException(final String message) {
        super("Null found for: " + message);
    }
}
