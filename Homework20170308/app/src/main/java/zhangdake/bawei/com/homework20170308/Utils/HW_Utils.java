package zhangdake.bawei.com.homework20170308.Utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Zhangdake on 2017/3/8.
 */
public class HW_Utils {

    public static final int time_out = 5000;
    public static final String method = "GET";
    public static final String char_set = "UTF-8";

    public static String getJson(String http_url){

        try {
            URL url = new URL(http_url);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod(method);
            connection.setReadTimeout(time_out);
            connection.setConnectTimeout(time_out);

            if(connection.getResponseCode()==200){
                InputStream is = connection.getInputStream();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();

                byte[] b = new byte[1024];
                int count = 0;

                while((count = is.read(b))!=-1){
                    stream.write(b,0,count);
                }

                is.close();
                stream.close();

                return stream.toString(char_set);

            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
