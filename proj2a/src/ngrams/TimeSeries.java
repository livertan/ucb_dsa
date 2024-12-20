package ngrams;

import java.util.*;

/**
 * An object for mapping a year number (e.g. 1996) to numerical data. Provides
 * utility methods useful for data analysis.
 *
 * @author Josh Hug
 */
public class TimeSeries extends TreeMap<Integer, Double> {

    /** If it helps speed up your code, you can assume year arguments to your NGramMap
     * are between 1400 and 2100. We've stored these values as the constants
     * MIN_YEAR and MAX_YEAR here. */
    public static final int MIN_YEAR = 1400;
    public static final int MAX_YEAR = 2100;

    /**
     * Constructs a new empty TimeSeries.
     */
    public TimeSeries() {
        super();
    }

    /**
     * Creates a copy of TS, but only between STARTYEAR and ENDYEAR,
     * inclusive of both end points.
     */
    public TimeSeries(TimeSeries ts, int startYear, int endYear) {
        super();
        // TODO: Fill in this constructor.
        SortedMap<Integer, Double> result = new TimeSeries();
        result = ts.subMap(startYear, endYear + 1);
        this.putAll(result);
    }

    /**
     * Returns all years for this TimeSeries (in any order).
     */
    public List<Integer> years() {
        // TODO: Fill in this method.
        List<Integer> result = new ArrayList<>();
        Set<Integer> years = this.keySet();
        if (this.size() == 0) {
            return result;
        } else {
            result.addAll(years);
        }
        return result;
    }

    /**
     * Returns all data for this TimeSeries (in any order).
     * Must be in the same order as years().
     */
    public List<Double> data() {
        // TODO: Fill in this method.
        List<Double> result = new ArrayList<>();
        Set<Integer> years = this.keySet();
        if (this.size() == 0) {
            return result;
        } else {
            for (int i : years) {
                result.add(get(i));
            }
        }
        return result;
    }

    /**
     * Returns the year-wise sum of this TimeSeries with the given TS. In other words, for
     * each year, sum the data from this TimeSeries with the data from TS. Should return a
     * new TimeSeries (does not modify this TimeSeries).
     *
     * If both TimeSeries don't contain any years, return an empty TimeSeries.
     * If one TimeSeries contains a year that the other one doesn't, the returned TimeSeries
     * should store the value from the TimeSeries that contains that year.
     */
    public TimeSeries plus(TimeSeries ts) {
        // TODO: Fill in this method.
        TimeSeries sumSeries = new TimeSeries();
        if (this.size() == 0 && ts.size() == 0) {
            return sumSeries;
        } else if (this.size() != 0 && ts.size() == 0) {
            return this;
        } else if (this.size() == 0 && ts.size() != 0) {
            return ts;
        } else {
            for (int i : this.years()) {
                if (ts.containsKey(i)) {
                    sumSeries.put(i, this.get(i) + ts.get(i));
                    ts.remove(i);
                } else {
                    sumSeries.put(i, this.get(i));
                }
            }
            for (int i: ts.years()) {
                sumSeries.put(i, ts.get(i));
            }
            return sumSeries;
        }
    }

    /**
     * Returns the quotient of the value for each year this TimeSeries divided by the
     * value for the same year in TS. Should return a new TimeSeries (does not modify this
     * TimeSeries).
     *
     * If TS is missing a year that exists in this TimeSeries, throw an
     * IllegalArgumentException.
     * If TS has a year that is not in this TimeSeries, ignore it.
     */
    public TimeSeries dividedBy(TimeSeries ts) {
        // TODO: Fill in this method.
        TimeSeries ratios = new TimeSeries();
        if (this.size() == 0 && ts.size() == 0) {
            return ratios;
        } else if (this.size() != 0 && ts.size() == 0) {
            throw new IllegalArgumentException("input series is empty");
        } else if (this.size() == 0 && ts.size() != 0) {
            return ratios;
        } else {
            for (int i : this.years()) {
                if (ts.containsKey(i)) {
                    ratios.put(i, this.get(i) / ts.get(i));
                } else {
                    throw new IllegalArgumentException("input series missing years");
                }
            }
            return ratios;
        }
    }

    // TODO: Add any private helper methods.
    // TODO: Remove all TODO comments before submitting.
}
