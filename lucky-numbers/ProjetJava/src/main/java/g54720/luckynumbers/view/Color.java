package g54720.luckynumbers.view;

/**
 * Enumeration for the view. CHange the color of a string.
 *
 * @author Gabriel.espinosa g54720
 *
 * recovery from the exercise td06
 */
public enum Color {
    /**
     * color black
     */
    BLACK("\033[30m"),
    /**
     * color red
     */
    RED("\033[31m"),
    /**
     * color green
     */
    GREEN("\033[32m"),
    /**
     * color yellow
     */
    YELLOW("\033[33m"),
    /**
     * color blue
     */
    BLUE("\033[34m"),
    /**
     * color magenta
     */
    MAGENTA("\033[35m"),
    /**
     * color cyan
     */
    CYAN("\033[36m"),
    /**
     * color white
     */
    WHITE("\033[37m");
    /**
     * Rest the initiate color
     */
    public static final String RESET_CODE = "\033" + "[0m";
    private final String code;

    /**
     * Contructor of Color
     *
     * @param code
     */
    Color(String code) {
        this.code = code;
    }

    /**
     * Reset the color after a string
     *
     * @param text a string
     * @return the string colored
     */
    public final String color(String text) {
        return this + text + RESET_CODE;
    }

    @Override
    public String toString() {
        return code;
    }

}
