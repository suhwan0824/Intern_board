package com.example.bulletin.json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonReader {

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String jsonText = readAll(rd);
            JSONObject result = new JSONObject(jsonText);
            return result;
        } finally {
            is.close();
        }
    }

    /*public static void main(String[] args) throws IOException, JSONException {
        JSONObject all = readJsonFromUrl("https://nzin.nongshim.com/api/nz_webzine.php?rows=300&type=json");
        System.out.println(all.toString());
        JSONArray next = (JSONArray) all.get("array");
        for (int i = 0; i < next.length(); i++){
            JSONObject object = (JSONObject) next.get(i);
            System.out.println(i + "번째 항목");
            System.out.println("제목 : " + object.get("title"));
            System.out.println("사진링크 : " + object.get("thumbLink"));
        }
    }*/

}