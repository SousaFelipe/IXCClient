package com.flpss.ixc;


import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Properties;


public class IXCClient {



    String host;
    String token;

    String target;

    HttpClient client;
    HttpRequest request;


    X509TrustManager manager = new X509TrustManager() {
        public X509Certificate[] getAcceptedIssuers() { return new X509Certificate[] { }; }
        public void checkClientTrusted(X509Certificate[] certs, String authType) { }
        public void checkServerTrusted(X509Certificate[] certs, String authType) { }
    };



    public IXCClient() {
        loadProperties();

        SSLContext sslContext = null;

        try {
            sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new TrustManager[]{ manager }, new SecureRandom());

        }
        catch (KeyManagementException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        this.client = HttpClient.newBuilder()
            .sslContext(sslContext)
            .build();
    }



    public void setHost(String host) {
        this.host = "https://".concat(host).concat("/webservice/v1/");
    }



    public void setToken(String token) {
        this.token = token;
    }



    public void setTarget(String target) {
        this.target = target;
    }



    private String uri() {
        return this.host.concat(this.target).concat("/");
    }



    public IXCResponse get(Arguments args) throws IOException, InterruptedException {

        this.request = HttpRequest.newBuilder()
            .POST(args.publisher())
            .header("Authorization", headerToken())
            .header("Content-type", "application/json")
            .header("ixcsoft", "listar")
            .uri(URI.create(uri()))
            .build();

        HttpResponse<String> response = this.client.send(this.request, HttpResponse.BodyHandlers.ofString());

        return new IXCResponse(response.body());
    }



    private String headerToken() {
        byte[] buffer = Base64.getEncoder().encode(this.token.getBytes(StandardCharsets.UTF_8));
        String fromBuffer = new String(buffer);
        return "Basic ".concat(fromBuffer);
    }



    private void loadProperties() {

        Properties properties = new Properties();

        try (FileInputStream fis = new FileInputStream("config.properties")) {
            properties.load(fis);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        setHost(properties.getProperty("app.host"));
        setToken(properties.getProperty("app.token"));
    }
}
