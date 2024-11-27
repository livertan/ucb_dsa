import org.junit.jupiter.api.Test;
import wordnet.WordGraph;

import java.util.Collection;

import java.util.List;
import java.util.Set;

import static com.google.common.truth.Truth.assertThat;

public class TestHyponymsSimple {
    @Test
    public void testHyponymsSimple(){
        WordGraph wn=new WordGraph("./data/wordnet/synsets11.txt","./data/wordnet/hyponyms11.txt");
        assertThat(wn.hyponyms("antihistamine")).isEqualTo(Set.of("antihistamine","actifed"));
    }
}
