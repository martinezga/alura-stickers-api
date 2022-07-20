import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {
        String API_KEY = System.getenv("API_KEY");
        String URL = "https://imdb-api.com/en/API/Top250Movies/" + API_KEY;
        String MOCK = "https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060";
        String bold = "\u001b[1m";
        String notBold = "\u001b[0m";
        String colored = "\u001b[30m\u001b[45m";
        String title;
        String[] urlPoster;
        String urlPosterComplete;
        String rating;

        // Get top 250 movies
        URI myURI = URI.create(MOCK);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(myURI).GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String body = response.body();

        // Parse data from String to List of Map objects
        JsonParser parser = new JsonParser();
        List<Map<String, String>> dataList = parser.parse(body);

        // Show info: title, image, rank and create sticker
        StickerGenerator sticker = new StickerGenerator();

        for (Map<String, String> film : dataList) {
            rating = film.get("imDbRating");
            title = film.get("fullTitle");
            urlPosterComplete = film.get("image");
            urlPoster = urlPosterComplete.split("@");

            try {
                sticker.create(urlPoster[0] + "@", title);
            } catch (Exception e) {
                continue;
            }
        }
    }
}