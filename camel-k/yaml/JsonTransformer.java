// File: JsonTransformer.java
// camel-k: dependency=mvn:org.apache.camel:camel-core
// camel-k: dependency=mvn:org.json:json:20230227
import org.apache.camel.BindToRegistry;
import org.json.JSONObject;

@BindToRegistry("jsonTransformer")
public class JsonTransformer {
    public String transform(String json) {
        JSONObject obj = new JSONObject(json);
        String title = obj.optString("title", "").toUpperCase();
        obj.put("title", title);
        return obj.toString();
    }
}
