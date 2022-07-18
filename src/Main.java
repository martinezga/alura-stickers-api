import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        String API_KEY = System.getenv("API_KEY");
        String URL = "https://imdb-api.com/en/API/Top250Movies/" + API_KEY;
        String MOCK = "https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060";

        // Get top 250 movies
        URI myURI = URI.create(MOCK);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(myURI).GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String body = response.body();

        // Parse data from String to List of Map objects
        JsonParser parser = new JsonParser();
        List<Map<String, String>> dataList = parser.parse(body);

        // Show info: title, image, rank
        for (Map<String, String> film : dataList) {
            System.out.println(film.get("fullTitle"));
            System.out.println(film.get("image"));
            System.out.println(film.get("imDbRating"));
        }
    }
}