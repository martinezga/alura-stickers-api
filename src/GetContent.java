import java.util.List;
import java.util.Map;

public interface GetContent {

    List<Content> extract(String type, List<Map<String, String>> json);

}
