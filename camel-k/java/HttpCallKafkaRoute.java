// camel-k: dependency=mvn:org.apache.camel:camel-kafka
// camel-k: dependency=mvn:org.json:json:20230227
import org.apache.camel.builder.RouteBuilder;
import org.json.JSONObject;

public class HttpCallKafkaRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("timer:kafka-auth?period=10000")
                .to("https://jsonplaceholder.typicode.com/todos/1")
                .convertBodyTo(String.class)
                .process(exchange -> {
                    JSONObject obj = new JSONObject(exchange.getIn().getBody(String.class));
                    obj.put("title", obj.getString("title").toUpperCase());
                    exchange.getMessage().setBody(obj.toString());
                })
                .toD("kafka:meu-topico" +
                        "?brokers=${env:KAFKA_BROKER}" +
                        "&securityProtocol=SASL_PLAINTEXT" +
                        "&saslMechanism=PLAIN" +
                        "&saslUsername=${env:KAFKA_USERNAME}" +
                        "&saslPassword=${env:KAFKA_PASSWORD}")
                .log("📦 Enviado para Kafka com autenticação: ${body}");
    }
}
