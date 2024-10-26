package ngrams;

import java.util.Collection;
import java.util.TreeMap;

import edu.princeton.cs.algs4.In;

import static ngrams.TimeSeries.MAX_YEAR;
import static ngrams.TimeSeries.MIN_YEAR;
import static utils.Utils.SHORT_WORDS_FILE;

/**
 * An object that provides utility methods for making queries on the
 * Google NGrams dataset (or a subset thereof).
 *
 * An NGramMap stores pertinent data from a "words file" and a "counts
 * file". It is not a map in the strict sense, but it does provide additional
 * functionality.
 *
 * @author Josh Hug
 */
public class NGramMap {

    // TODO: Add any necessary static/instance variables.
    TreeMap<String, TimeSeries> words = new TreeMap<>();
    TimeSeries totalCounts = new TimeSeries();
    /**
     *
     *
     *
     * Constructs an NGramMap from WORDSFILENAME and COUNTSFILENAME.
     */
    public NGramMap(String wordsFilename, String countsFilename) {
        // TODO: Fill in this constructor. See the "NGramMap Tips" section of the spec for help.
        In in = new In(wordsFilename);
        TimeSeries temp = new TimeSeries();
        while (!in.isEmpty() && in.hasNextLine()) {
            String nextLine = in.readLine();
            String[] splitLine = nextLine.split("\t");
            if (words.containsKey(splitLine[0])) {
                temp = words.get(splitLine[0]);
                temp.put(Integer.parseInt(splitLine[1]), Double.parseDouble(splitLine[2]));
                words.put(splitLine[0],temp);
                //temp.clear();
            } else {
                //TimeSeries temp = new TimeSeries();
                temp.put(Integer.parseInt(splitLine[1]), Double.parseDouble(splitLine[2]));
                words.put(splitLine[0],temp);
                //temp.clear();
            }
        }
        in.close();

        In in2 = new In(countsFilename);

        while (!in2.isEmpty() && in2.hasNextLine()) {
            String nextLine = in2.readLine();
            String[] splitLine = nextLine.split(",");
            totalCounts.put(Integer.parseInt(splitLine[0]), Double.parseDouble(splitLine[1]));
        }
        in2.close();
    }

    /**
     * Provides the history of WORD between STARTYEAR and ENDYEAR, inclusive of both ends. The
     * returned TimeSeries should be a copy, not a link to this NGramMap's TimeSeries. In other
     * words, changes made to the object returned by this function should not also affect the
     * NGramMap. This is also known as a "defensive copy". If the word is not in the data files,
     * returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word, int startYear, int endYear) {
        // TODO: Fill in this method.
        TimeSeries result = new TimeSeries();
        if (!words.containsKey(word)) {
            return result;
        } else {
            result = new TimeSeries(words.get(word), startYear, endYear);
            return result;
        }
    }

    /**
     * Provides the history of WORD. The returned TimeSeries should be a copy, not a link to this
     * NGramMap's TimeSeries. In other words, changes made to the object returned by this function
     * should not also affect the NGramMap. This is also known as a "defensive copy". If the word
     * is not in the data files, returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word) {
        // TODO: Fill in this method.
        TimeSeries result = new TimeSeries();
        if (!words.containsKey(word)) {
            return result;
        } else {
            result = words.get(word);
            return result;
        }
    }

    /**
     * Returns a defensive copy of the total number of words recorded per year in all volumes.
     */
    public TimeSeries totalCountHistory() {
        // TODO: Fill in this method.
        return null;
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD between STARTYEAR
     * and ENDYEAR, inclusive of both ends. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word, int startYear, int endYear) {
        // TODO: Fill in this method.
        return null;
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD compared to all
     * words recorded in that year. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word) {
        // TODO: Fill in this method.
        return null;
    }

    /**
     * Provides the summed relative frequency per year of all words in WORDS between STARTYEAR and
     * ENDYEAR, inclusive of both ends. If a word does not exist in this time frame, ignore it
     * rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words,
                                          int startYear, int endYear) {
        // TODO: Fill in this method.
        return null;
    }

    /**
     * Returns the summed relative frequency per year of all words in WORDS. If a word does not
     * exist in this time frame, ignore it rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words) {
        // TODO: Fill in this method.
        return null;
    }

    // TODO: Add any private helper methods.
    // TODO: Remove all TODO comments before submitting.
}
