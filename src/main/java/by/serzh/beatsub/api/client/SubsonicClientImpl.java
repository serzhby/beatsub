package by.serzh.beatsub.api.client;

import by.serzh.beatsub.api.domain.License;
import by.serzh.beatsub.api.domain.exceptions.SubsonicException;
import by.serzh.beatsub.domain.Server;
import by.serzh.beatsub.utils.HashUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigInteger;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class SubsonicClientImpl implements SubsonicClient {

    private final Server server;
    private SecureRandom random = new SecureRandom();
    private static final String CLIENT_NAME = "BeatSub";
    private static final String API_VERSION = "1.14.0";
    private HttpClient client = HttpClientBuilder.create().build();

    public SubsonicClientImpl(Server server) {
        this.server = server;
    }

    @Override
    public License getLicense() throws SubsonicException, IOException {
        URI uri = createURI("getLicense.view", null);
        HttpGet request = new HttpGet(uri);
        HttpResponse response = client.execute(request);

        License license = ResponseExtractor.extract(response.getEntity().getContent(), License.class);


//        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
//        StringBuffer result = new StringBuffer();
//        String line = "";
//        while ((line = rd.readLine()) != null) {
//            result.append(line);
//        }
//        System.out.println(result.toString());
        return null;
    }

    private URI createURI(String methodPath, Object paramsObject)  {
        try {
            URIBuilder uriBuilder = new URIBuilder().setScheme("http").setHost(server.getHost()).setPort(server.getPort()).setPath("/rest/" + methodPath);
            uriBuilder.addParameters(createAuthorizationParams());
            return uriBuilder.build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private List<NameValuePair> createParams(Object paramsObject) {
        List<NameValuePair> requestParams = createAuthorizationParams();
        if(paramsObject != null) {
            addObjectParams(requestParams, paramsObject);
        }
        return requestParams;
    }

    private void addObjectParams(List<NameValuePair> list, Object object) {
        Field[] fields = object.getClass().getDeclaredFields();
        Function<Field, BasicNameValuePair> nameValuePairConverter = field -> {
            try {
                field.setAccessible(true);
                String value = Optional.ofNullable(field.get(object)).map(Object::toString).orElse(null);
                return new BasicNameValuePair(field.getName(), value);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        };
        Arrays.stream(fields)
            .filter(field -> !Modifier.isStatic(field.getModifiers()))
            .map(nameValuePairConverter)
            .forEach(list::add);
    }

    private List<NameValuePair> createAuthorizationParams() {
        List<NameValuePair> params = new ArrayList<>();
        String salt = generateSalt();
        params.add(new BasicNameValuePair("u", server.getUser()));
        params.add(new BasicNameValuePair("s", salt));
        params.add(new BasicNameValuePair("t", generateToken(salt)));
        params.add(new BasicNameValuePair("c", CLIENT_NAME));
        params.add(new BasicNameValuePair("v", API_VERSION));
        params.add(new BasicNameValuePair("f", "json"));
        return params;
    }

    private String generateSalt() {
        return new BigInteger(130, random).toString(32);
    }

    private String generateToken(String salt) {
        return HashUtils.md5(server.getPassword() + salt);
    }
}