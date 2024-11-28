package main;

import browser.NgordnetQueryHandler;
import wordnet.WordGraph;


public class AutograderBuddy {
    static WordGraph wn;
    /** Returns a HyponymHandler */
    public static NgordnetQueryHandler getHyponymsHandler(
            String wordFile, String countFile,
            String synsetFile, String hyponymFile) {
        wn = new WordGraph(synsetFile, hyponymFile);
        return new HyponymsHandler(wn);
    }
}
