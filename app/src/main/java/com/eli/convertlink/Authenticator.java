package com.eli.convertlink;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

final class Authenticator {
    private static final String CLIENT_ID = "2b4c3500c6424cf9bdd34d8d7228996c";
    private static final String CLIENT_SECRET = "f216d863af1c4a93955a5f94e6c14a3e";
    private static final String AUTH_ENDPOINT = "https://accounts.spotify.com/api/token";

    private static final FormBody FORM_BODY = new FormBody.Builder()
            .add("grant_type", "client_credentials")
            .add("client_id", CLIENT_ID)
            .add("client_secret", CLIENT_SECRET).build();

    private static final Request request = new Request.Builder()
            .url(AUTH_ENDPOINT)
            .post(FORM_BODY).build();

    private final static OkHttpClient client = new OkHttpClient();
    private static Token token;
    private Authenticator() {}

    public static Token generateToken()  {
        long time = System.currentTimeMillis();
        try {
            Response response = client.newCall(request).execute();
            token = parseToken(response, time);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return token;

    }
    private static Token parseToken(Response response, long time) throws IOException {
        Gson gson = new Gson();
        Token token = gson.fromJson(response.body().string(), Token.class);
        token.setExpiration(time);
        return token;
    }
    private static String printRequest(final RequestBody request){
        try {
            final RequestBody copy = request;
            final Buffer buffer = new Buffer();
            copy.writeTo(buffer);
            return buffer.readUtf8();
        }
        catch (final IOException e) {
            return "did not work";
        }
    }
}
