package main;

import browser.NgordnetQueryHandler;
import ngrams.NGramMap;
import wordnet.WordGraph;


public class AutograderBuddy {
    static WordGraph wn;
    /** Returns a HyponymHandler */
    public static NgordnetQueryHandler getHyponymsHandler(
            String wordFile, String countFile,
            String synsetFile, String hyponymFile) {
        NGramMap history = new NGramMap(wordFile, countFile);
        wn = new WordGraph(synsetFile, hyponymFile);
        return new HyponymsHandler(history, wn);
    }
}
