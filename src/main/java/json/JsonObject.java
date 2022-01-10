package json;

import java.util.HashMap;
import java.util.stream.Collectors;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class JsonObject extends Json {
    private final HashMap<String, JsonPair> jsonPairs = new HashMap<>();

    public JsonObject(JsonPair... jsonPairs) {
        for (JsonPair jsonPair: jsonPairs) {
            this.jsonPairs.remove(jsonPair.key);
            this.jsonPairs.put(jsonPair.key, jsonPair);
        }
    }

    @Override
    public String toJson() {
        return "{" +
                jsonPairs.values().stream().map(
                        JsonPair::toString
                ).collect(Collectors.joining(", ")) +
                "}";
    }

    public void add(JsonPair jsonPair) {
        jsonPairs.remove(jsonPair.key);
        jsonPairs.put(jsonPair.key, jsonPair);
    }

    public boolean contains(String name) {
        return jsonPairs.containsKey(name);
    }

    public Json find(String name) {
        if (!jsonPairs.containsKey(name)) {
            return null;
        }
        return jsonPairs.get(name).value;
    }

    public JsonObject projection(String... names) {
        JsonObject jsonObject = new JsonObject();
        for (String name: names) {
            if (this.jsonPairs.containsKey(name)) {
                jsonObject.add(this.jsonPairs.get(name));
            }
        }
        return jsonObject;
    }
}
