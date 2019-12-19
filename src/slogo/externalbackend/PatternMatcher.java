package slogo.externalbackend;

import java.util.*;
import java.util.regex.Pattern;
import java.util.Map.Entry;

/**
 * This class was mostly adapted from code in class. It is used to map our regex syntax to the correct token types.
 *
 * @author Robert Duvall
 * @author Jerry Wang
 */
public class PatternMatcher {

    private List<Map.Entry<String, Pattern>> mySymbols;
    private ResourceBundle RESOURCES_PACKAGE;

    /**
     * Create an empty parser
     */
    public PatternMatcher(ResourceBundle resources) {
        mySymbols = new ArrayList<>();
        RESOURCES_PACKAGE = resources;
    }

    /**
     * Get the patterns from the package
     */
    public List<Entry<String, Pattern>> getPatterns(){
        addPatterns(RESOURCES_PACKAGE);
        return mySymbols;
    }

    /**
     * Adds the given resource file to this language's recognized types
     */
    //Adds patterns into the program parser
    public void addPatterns(ResourceBundle syntax) {
        ResourceBundle resources = syntax;
        for (String key : Collections.list(resources.getKeys())) {
            String regex = resources.getString(key);
            mySymbols.add(new AbstractMap.SimpleEntry<>(key, Pattern.compile(regex, Pattern.CASE_INSENSITIVE)));
        }
    }

    /**
     * Returns language's type associated with the given text if one exists
     */
    public String getSymbol (String text) {
        final String ERROR = "ERROR: No Match";
        for (Map.Entry<String, Pattern> e : mySymbols) {
            if (match(text, e.getValue())) {
                return e.getKey();
            }
        }
        throw new IllegalArgumentException(ERROR);
    }

    /**
     * @param text
     * @param regex
     * @return whether a pattern matches the given text
     */
    public boolean match (String text, Pattern regex) {
        return regex.matcher(text).matches();
    }

}
