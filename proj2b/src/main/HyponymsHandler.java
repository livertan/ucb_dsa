package main;
import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import wordnet.WordGraph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class HyponymsHandler extends NgordnetQueryHandler {
    WordGraph wordnet;
    //
    public HyponymsHandler (WordGraph graph) {
        wordnet = graph;
    }
    @Override
    public String handle(NgordnetQuery q) {
        List<String> words = q.words();
        int startYear = q.startYear();
        int endYear = q.endYear();

        List<String> temp = new ArrayList<String>();
        List<String> output = new ArrayList<String>();
        String response;

        for (String word : words) {
            temp = (List<String>) wordnet.hyponyms(word);
            if (output.isEmpty()) {
                output = temp;
            } else {
                output.retainAll(temp);
            }
        }
        response = "[" + String.join(", ", output) + "]";
        return response;
    }
}
