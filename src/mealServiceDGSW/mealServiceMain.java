package mealServiceDGSW;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class mealServiceMain {

    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar cal = Calendar.getInstance();

        String newUrls = "https://open.neis.go.kr/hub/mealServiceDietInfo?"
                + "Type=json&"
                + "ATPT_OFCDC_SC_CODE=D10&"
                + "SD_SCHUL_CODE=7240454&"
                + "MLSV_YMD=" + sdf.format(cal.getTime());

        try {
            URL url = new URL(newUrls);
            try {
                BufferedReader br = new BufferedReader( new InputStreamReader(url.openStream(), "UTF-8"));

                JSONParser jsonParser = new JSONParser();
                try {
                    JSONObject jsonObject = (JSONObject) jsonParser.parse(br.readLine());

                    JSONArray jsonArray = (JSONArray) jsonObject.get("mealServiceDietInfo");

                    JSONObject row = (JSONObject) jsonArray.get(1);
                    JSONArray row_array = (JSONArray) row.get("row");

                    for(int i = 0; i < row_array.size(); i++) {
                        JSONObject out = (JSONObject) row_array.get(i);
                        System.out.println(out.get("MMEAL_SC_NM"));
                        System.out.println(out.get(("DDISH_NM")));
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }  catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }


}
