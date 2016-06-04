package me.stepsave.itms;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.List;
import me.stepsave.itms.enums.MediaSearch;
import me.stepsave.itms.enums.ResultEntity;
import me.stepsave.itms.generated.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author smuellner
 */
@Service
public class ITunesSearchService {

    private final Logger logger = LoggerFactory.getLogger(ITunesSearchService.class);
    
    public static String ITMS_REST_URL = "https://itunes.apple.com/search";

    /**
     * 
     * @param term the serach term
     * @param mediaSearch the media type to search for (e.g. music, video, ...)
     * @param resultEntity the entity for the result (e.g. music, video, ...)
     * @param limit the amount of returned items
     * @return 
     */
    public Response search(final String term, final MediaSearch mediaSearch, final ResultEntity resultEntity, final int limit) {
        final RestTemplate restTemplate = new RestTemplate();
        final HttpHeaders headers = new HttpHeaders();
        final UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(ITMS_REST_URL);
        builder.queryParam("term", term);
        builder.queryParam("media", mediaSearch.toString());
        builder.queryParam("entity", resultEntity.toString());
        builder.queryParam("limit", limit);
        final HttpEntity<?> entity = new HttpEntity<>(headers);
        // CORRECT: that would be the correct headers
        // headers.set("Accept", MediaType.APPLICATION_JSON_VALUE); 
        // FIXME: Workaround wrong MIME type!
        final List<HttpMessageConverter<?>> converters = restTemplate.getMessageConverters();
        converters.stream().filter((converter) -> (converter instanceof MappingJackson2HttpMessageConverter)).map((converter) -> (MappingJackson2HttpMessageConverter) converter).map((jsonConverter) -> {
            jsonConverter.setObjectMapper(new ObjectMapper());
            return jsonConverter;
        }).forEach((jsonConverter) -> {
            final MediaType types[] = new MediaType[]{new MediaType("text", "javascript", MappingJackson2HttpMessageConverter.DEFAULT_CHARSET)};
            headers.setAccept(Arrays.asList(types));
            jsonConverter.setSupportedMediaTypes(Arrays.asList(types));
        });
        try {
            // Now deserialize/map the json data to our defined POJOs
            final ResponseEntity<Response> searchResult
                    = restTemplate.exchange(builder.build().encode().toUri(),
                            HttpMethod.GET, entity, Response.class);
            return searchResult.getBody();
        } catch (Exception ex) {
           logger.error(ex.getLocalizedMessage());
        }
        return null;
    }
    
}
