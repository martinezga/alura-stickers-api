import java.util.*;

public class GetContentImpl implements GetContent{

    @Override
    public List<Content> extract(String type, List<Map<String, String>> json) {
        List<Content> contentList = new ArrayList();

        for (Map<String, String> obj: json) {
            String title = null;
            String url = null;

            switch (type) {
                case "MOCK":
                    title = obj.get("fullTitle");
                    String urlComplete = obj.get("image");
                    url = urlComplete.split("@")[0] + "@.jpg";
                    break;
                case "IMDB":
                    break;
                case "NASA":
                    title = obj.get("title");
                    url = obj.get("url");
                    break;
                case "MARVEL":
                    break;
            }
            contentList.add(new Content(title, url));
        }

        return contentList;
    }
}
