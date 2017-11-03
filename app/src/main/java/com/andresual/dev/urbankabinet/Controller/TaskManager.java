package com.andresual.dev.urbankabinet.Controller;

import android.os.AsyncTask;

/**
 * Created by andresual on 10/24/2017.
 */

public class TaskManager extends AsyncTask<Void, Void, Void> {
    @Override
    protected Void doInBackground(Void... params) {

        int i = 0;
        synchronized (this) {

            while (i < 10) {
                try {
                    wait(1500);
                    i++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

}
