public enum APIEnum {
    MOCK("https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060"),
    IMDB("https://imdb-api.com/en/API/Top250Movies/"
            + System.getenv("API_KEY_IMDB")),
    NASA("https://api.nasa.gov/planetary/apod?api_key="
            + System.getenv("API_KEY_NASA")
            + "&start_date=2022-06-12&end_date=2022-06-14"),
    MARVEL("https://gateway.marvel.com/v1/public/characters?ts=1&apikey="
            + System.getenv("API_KEY_MARVEL")
            + "&hash=" + System.getenv("MARVEL_HASH"));

    private String apiUrl;

    APIEnum(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public String getApiUrl() {
        return apiUrl;
    }
}
