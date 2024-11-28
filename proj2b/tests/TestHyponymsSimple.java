import org.junit.jupiter.api.Test;
import wordnet.WordGraph;

import java.util.ArrayList;
import java.util.Collection;

import java.util.List;
import java.util.Set;

import static com.google.common.truth.Truth.assertThat;

public class TestHyponymsSimple {
    @Test
    public void testHyponymsSimple() {
        WordGraph wn = new WordGraph("./data/wordnet/synsets11.txt", "./data/wordnet/hyponyms11.txt");
        assertThat(wn.hyponyms("antihistamine")).isEqualTo(List.of("actifed", "antihistamine"));
        wn = new WordGraph("./data/wordnet/synsets14.txt", "./data/wordnet/hyponyms14.txt");
        assertThat(wn.hyponyms("change")).isEqualTo(List.of("alteration", "change", "demotion", "increase", "jump",
                "leap", "modification", "saltation", "transition", "variation"));
        wn = new WordGraph("./data/wordnet/synsets16.txt", "./data/wordnet/hyponyms16.txt");
        assertThat(wn.hyponyms("change")).isEqualTo(List.of("alteration", "change", "demotion", "increase", "jump",
                "leap", "modification", "saltation", "transition", "variation"));
        //
    }

    @Test
    public void testHyponymsWordList() {
        WordGraph wn;
        List<String> input = List.of("video", "recording");
        List<String> output = new ArrayList<String>();
        List<String> temp = new ArrayList<String>();
        for (String word : input) {
            wn = new WordGraph("./data/wordnet/synsets.txt", "./data/wordnet/hyponyms.txt");
            temp = (List<String>) wn.hyponyms(word);
            if (output.isEmpty()) {
                output = temp;
            } else {
                output.containsAll(temp);
            }
        }
        assertThat(output.equals(List.of("video", "video_recording", "videocassette", "videotape")));
        //
        input = List.of("pastry", "tart");
        output = new ArrayList<String>();
        temp = new ArrayList<String>();
        for (String word : input) {
            wn = new WordGraph("./data/wordnet/synsets.txt", "./data/wordnet/hyponyms.txt");
            temp = (List<String>) wn.hyponyms(word);
            if (output.isEmpty()) {
                output = temp;
            } else {
                output.containsAll(temp);
            }
        }
        assertThat(output.equals(List.of("apple_tart", "lobster_tart", "quiche", "quiche_Lorraine", "tart", "tartlet")));
    }
}
