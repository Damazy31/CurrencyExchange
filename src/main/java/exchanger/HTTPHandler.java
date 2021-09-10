package exchanger;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.function.Supplier;

public class HTTPHandler {
    public ExchangeRate synchronousRequest() throws IOException, InterruptedException {
        // create a client
        var client = HttpClient.newHttpClient();

        // create a request
        var request = HttpRequest.newBuilder(
                URI.create("http://api.nbp.pl/api/exchangerates/rates/C/USD")
        ).build();

        // use the client to send the request
        HttpResponse<Supplier<ExchangeRate>> response = client.send(request, new JsonBodyHandler<>(ExchangeRate.class));

        // the response:
        return response.body().get();
    }

}
