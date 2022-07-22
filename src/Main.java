import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {

        while (true) {
            Scanner input = new Scanner(System.in);
            int menuOption = 1;
            Map<Integer, String> menu = new HashMap<>();
            menu.put(0, "Exit");

            // Get user input
            System.out.println("Choose an API");
            System.out.println(" Insert a number and press enter");

            for (APIEnum api: APIEnum.values()) {
                System.out.println(" " + menuOption + ". " + api);
                menu.put(menuOption, api.name());
                menuOption += 1;
            }
            System.out.println(" 0. Exit");
            System.out.print("> ");
            Integer menuNumberSelected = input.nextInt();

            if(menuNumberSelected.equals(0)) {
                System.out.println("Bye!");
                break;
            } else {
                System.out.print("Choose images quantity: ");
                int imageQty = input.nextInt();

                // Get data
                String apiName = menu.get(menuNumberSelected);
                String apiUrl = APIEnum.valueOf(apiName).getApiUrl();
                String data = new MyHttpClient().getData(apiUrl);

                if (apiName.equals("MARVEL")) {
                    data = "{[" + data.split("\\[")[1];
                    System.out.println("Implementation in progress");
                    break;
                }

                // Parse data from String to List of Map objects
                JsonParser parser = new JsonParser();
                List<Map<String, String>> dataList = parser.parse(data);

                // Extract title and url image depending on API selected
                List<Content> contentList = new GetContentImpl().extract(apiName, dataList);

                // Create sticker
                StickerGenerator sticker = new StickerGenerator();

                // Create stickers
                System.out.println("Sticker(s) name:");
                for (int i = 0; i < imageQty; i++) {
                    Content dataItem = contentList.get(i);

                    try {
                        sticker.create(dataItem.getUrlImage(), dataItem.getTitle());
                        System.out.println(" - " + dataItem.getTitle());
                    } catch (Exception e) {
                        continue;
                    }
                }
                System.out.println("\nSticker(s) done!\n");
            }
        }
    }
}