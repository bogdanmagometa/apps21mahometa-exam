import json.*;

public class Main {
    public static void main(String[] args) {
        JsonObject jsonObject = new JsonObject(
                new JsonPair("name", new JsonString("Anna")),
                new JsonPair("age", new JsonNumber(18)),
                new JsonPair("status", new JsonNull()),
                new JsonPair("age", new JsonNumber(20))
        );
        System.out.println(jsonObject.toJson());
    }
}
