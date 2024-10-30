package main;

import ngrams.NGramMap;
import ngrams.TimeSeries;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;

import java.util.List;

public class HistoryTextHandler extends NgordnetQueryHandler {

    NGramMap history;

    public HistoryTextHandler(NGramMap map) {
        history = map;
    }

    @Override
    public String handle(NgordnetQuery q) {
        List<String> words = q.words();
        int startYear = q.startYear();
        int endYear = q.endYear();

        TimeSeries word_history;
        String response = "";

        for (String word : words) {
            word_history = history.weightHistory(word, startYear, endYear);
            response += word + ":" + " {";
            for (int year : word_history.years()) {
                if (word_history.years().indexOf(year) == word_history.years().size() - 1) {
                    response += year + "=" + word_history.get(year) + "}" + "\n";
                } else {
                    response += year + "=" + word_history.get(year) + ", ";
                }
            }
        }
        return response;
    }
}
