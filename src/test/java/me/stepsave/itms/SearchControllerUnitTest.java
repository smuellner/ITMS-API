package me.stepsave.itms;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import me.stepsave.itms.enums.MediaSearch;
import me.stepsave.itms.enums.ResultEntity;
import me.stepsave.itms.generated.Response;
import me.stepsave.itms.generated.Result;
import org.hamcrest.text.IsEqualIgnoringCase;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author smuellner
 */
public class SearchControllerUnitTest {

    public SearchControllerUnitTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void searchArtist() {
        try {
            final String artistName = "avicii";
            final ITunesSearchService iTunesSearchService = new ITunesSearchService();
            final Response response = iTunesSearchService.search(artistName, MediaSearch.MUSIC, ResultEntity.SONG, 1);
            Assert.assertNotNull(response);
            Assert.assertTrue(response.getResultCount() > 0);
            Optional<Result> firstResult = response.getResults().stream().findFirst();
            Assert.assertTrue(firstResult.isPresent());
            Result result = firstResult.get();
            assertThat(result.getArtistName(), IsEqualIgnoringCase.equalToIgnoringCase(artistName));
        } catch (Exception ex) {
            Logger.getLogger(SearchControllerUnitTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void searchArtistAndTrack() {
        try {
            final String artistName = "avicii";
            final String trackName = "Hey Brother";
            final StringBuilder builder = new StringBuilder();
            builder.append(artistName).append(" ").append(trackName);
            final ITunesSearchService iTunesSearchService = new ITunesSearchService();
            final Response response = iTunesSearchService.search(builder.toString(), MediaSearch.MUSIC, ResultEntity.SONG, 1);
            Assert.assertNotNull(response);
            Assert.assertTrue(response.getResultCount() > 0);
            Optional<Result> firstResult = response.getResults().stream().findFirst();
            Assert.assertTrue(firstResult.isPresent());
            Result result = firstResult.get();
            assertThat(result.getArtistName(), IsEqualIgnoringCase.equalToIgnoringCase(artistName));
            assertThat(result.getTrackName(), IsEqualIgnoringCase.equalToIgnoringCase(trackName));
        } catch (Exception ex) {
            Logger.getLogger(SearchControllerUnitTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
