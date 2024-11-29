package main;

import browser.NgordnetQueryHandler;
import ngrams.NGramMap;
import wordnet.WordGraph;


public class AutograderBuddy {
    /** Returns a HyponymHandler */
    public static NgordnetQueryHandler getHyponymsHandler(
            String wordFile, String countFile,
            String synsetFile, String hyponymFile) {
        NGramMap ngmap = new NGramMap(wordFile, countFile);
        WordGraph wn = new WordGraph(synsetFile, hyponymFile);
        return new HyponymsHandler(ngmap, wn);
    }
}
