# Search iTunes API: Searching the iTunes store.
The project allows you to access the iTunes API and search for different media.

[![Build Status](https://travis-ci.org/smuellner/ITMS-API.svg?branch=master)](https://travis-ci.org/smuellner/ITMS-API)

### Example Search
A search basically consist of a search term, the media type, the expected response entity and the amount of returned results: 
```java
final String artistName = "Jeremy Loops";
final String trackName = "The Gypsy Opera";
final StringBuilder builder = new StringBuilder();
builder.append(artistName).append(" ").append(trackName);
final Response response = iTunesSearchService.search(builder.toString(), MediaSearch.MUSIC, ResultEntity.SONG, 5);
Optional<Result> firstResult = response.getResults().stream().findFirst();
if(firstResult.isPresent()) {
	Result result = firstResult.get();
    System.out.println("ArtistViewUrl: " + result.getArtistViewUrl());
    System.out.println("ArtworkUrl100: " + result.getArtworkUrl100());
}
```

The output for this query would be:
```html
ArtistViewUrl: https://itunes.apple.com/us/artist/jeremy-loops/id589222946?uo=4
ArtworkUrl100: http://is2.mzstatic.com/image/thumb/Music7/v4/f1/46/5d/f1465d6d-72aa-a00c-7a7c-861ab23bae2b/source/100x100bb.jpg
```

## Reference
REF 1: [https://affiliate.itunes.apple.com/resources/documentation/itunes-store-web-service-search-api/](https://affiliate.itunes.apple.com/resources/documentation/itunes-store-web-service-search-api/)


## License (MIT)

Copyright (C) 2016 Sascha Müllner

See the attached LICENSE file.