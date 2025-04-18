// camel-k: dependency=mvn:org.json:json:20240303
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.Exchange;
import org.json.JSONObject;

public class HttpCallRoute extends RouteBuilder {
  @Override
  public void configure() throws Exception {
	from("timer:test?period=5000")
	  .doTry()
		.to("https://jsonplaceholder.typicode.com/todos/1")
		.process(exchange -> {
		  String body = exchange.getIn().getBody(String.class);
		  JSONObject jsonObject = new JSONObject(body);
		  if (jsonObject.has("title")) {
			String title = jsonObject.getString("title");
			jsonObject.put("title", title.toUpperCase());
			exchange.getIn().setBody(jsonObject.toString());
		  }
		})
		.log("Transformed API Response Title: ${body}")
	  .doCatch(Exception.class)
		.log("Error calling API: ${exception.message}")
		.setBody(simple("Error occurred"))
	  .endDoTry();
  }
}