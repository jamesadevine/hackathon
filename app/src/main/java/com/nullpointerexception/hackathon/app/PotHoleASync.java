package com.nullpointerexception.hackathon.app;

import android.os.AsyncTask;
import android.renderscript.Script;
import android.util.Log;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import java.util.ArrayList;

/**
 * Created by James on 06/03/2014.
 */
public class PotHoleASync extends AsyncTask<Void, Integer, String> {

        private Script serverScript = null;
        private HttpClient serverClient;
        private AsyncPotholeCallback multipleCallback;


        public PotHoleASync(AsyncPotholeCallback asyncPotholeCallback) {
            this.multipleCallback = asyncPotholeCallback;
        }

        @Override
        protected String doInBackground(Void... voids) {

            ArrayList result = new ArrayList();
            HttpClient httpClient = new DefaultHttpClient();

            HttpGet getData = new HttpGet("http://148.88.32.47/smarthack/getPotHoleData.php");


            String responseText="";
            try {
                ResponseHandler<String> responseHandler = new BasicResponseHandler();
                responseText = httpClient.execute(getData, responseHandler);

            } catch (Exception e){
                Log.e("LUFELF API", Log.getStackTraceString(e));
            }
            Log.e("CRAP","HELLO");
            return responseText;
        }

        @Override
        protected void onPostExecute(String data) {
            Log.e("crap",String.valueOf(JSONDecoder.decodeJSON(data).size()));
            multipleCallback.potholeCallback(JSONDecoder.decodeJSON(data));
        }
}
