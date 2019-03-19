package com.script972.currencyrate.managers;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ConnectivitySelfManager {

    private Context context;

    private ConnectivityManager cm;
    private NetworkInfo[] netInfo;

    public ConnectivitySelfManager(Context context) {
        this.cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        assert this.cm != null;
        this.netInfo = cm.getAllNetworkInfo();
        this.context = context;
    }

    /**
     * Check if some type of connection available
     *
     * @return
     */
    public boolean isNetworkAvailable() {
        return isNetworkAvailableMobile() || isNetworkAvailableWifi();
    }

    /**
     * Check available wifi connection
     *
     * @return
     */
    public boolean isNetworkAvailableWifi() {
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    return true;
        }
        return false;
    }

    /**
     * Check available mobile connection
     *
     * @return
     */
    public boolean isNetworkAvailableMobile() {
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                return ni.isConnected();
        }
        return false;
    }


}
