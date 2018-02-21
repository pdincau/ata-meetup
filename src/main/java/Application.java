import com.google.gson.Gson;
import com.spotify.apollo.Environment;
import com.spotify.apollo.RequestContext;
import com.spotify.apollo.Response;
import com.spotify.apollo.httpservice.HttpService;
import com.spotify.apollo.httpservice.LoadingException;
import com.spotify.apollo.route.Route;
import infrastructure.InMemoryLocationRepository;
import zip.LocationRepository;
import zip.ZipInfo;
import zip.ZipService;

import static com.spotify.apollo.Status.NOT_FOUND;
import static com.spotify.apollo.Status.OK;

public class Application {

    private static final GreetingService greetingService = new GreetingService();
    private static final InMemoryLocationRepository repository = new InMemoryLocationRepository();
    private static final ZipService zipService = new ZipService(repository);

    public static void main(String... args) throws LoadingException {
        HttpService.boot(Application::init, "ata-meetup", args);
    }

    static void init(Environment environment) {
        repository.setup();
        environment.routingEngine()
                .registerAutoRoute(Route.sync("GET", "/hello", Application::greet))
                .registerAutoRoute(Route.sync("GET", "/zip/<country>/<code>", Application::zipInfo))
                .registerAutoRoute(Route.sync("GET", "/ping", rc -> "pong"));
    }

    private static Response greet(RequestContext context) {
        String name = nameToGreetFrom(context);
        String message = greetingService.greet(name);
        return Response.of(OK, message);
    }

    private static String nameToGreetFrom(RequestContext context) {
        return context.request().parameter("name").orElse("");
    }

    private static Response zipInfo(RequestContext context) {
        String zipCode = context.pathArgs().get("code");
        String countryCode = context.pathArgs().get("country");
        ZipInfo zipInfo = zipService.dataFor(countryCode, zipCode);
        if (zipInfo != null) {
            return Response.of(OK, new Gson().toJson(zipInfo)).withHeader("Content-Type", "application/json");
        } else {
            return Response.of(NOT_FOUND, "tuple (country, zip code) invalid.");
        }
    }
}
