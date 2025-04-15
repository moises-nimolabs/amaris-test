// camel-k: dependency=mvn:org.json:json:20230227
import org.apache.camel.builder.RouteBuilder;
import org.json.JSONObject;

public class HttpCallSecureTokenRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("timer:secure-route?period=10000")
                .to("https://jsonplaceholder.typicode.com/todos/1")
                .convertBodyTo(String.class)
                .process(exchange -> {
                    JSONObject obj = new JSONObject(exchange.getIn().getBody(String.class));
                    obj.put("title", obj.getString("title").toUpperCase());
                    exchange.getMessage().setBody(obj.toString());
                })
                .setHeader("Authorization", simple("Bearer ${env:BEARER_TOKEN}"))
                .to("https://webhook.site/destination-endpoint")
                .log("✅ JSON enviado com token do Secret");
    }
}
