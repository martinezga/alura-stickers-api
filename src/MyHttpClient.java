import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class MyHttpClient {

    public String getData(String url) {
        try {
            URI myURI = URI.create(url);
            java.net.http.HttpClient client = java.net.http.HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder(myURI).GET().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
