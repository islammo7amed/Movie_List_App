package com.example.movielistapp;

import static android.content.Context.CONNECTIVITY_SERVICE;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import androidx.appcompat.app.AlertDialog;

public class NetworkInformation {

    public static boolean isNetworkAvailable(Context context){

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            Network network = connectivityManager.getActiveNetwork();
            if (network == null){
                return false;
            }
            NetworkCapabilities capabilities = connectivityManager.getNetworkCapabilities(network);
            return capabilities != null || (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) || (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) || (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH);
        }else {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            return networkInfo != null && networkInfo.isConnected();
        }
    }

    public static void networkErrorDialog(Activity activity){
        AlertDialog alertDialog = null;
        if (alertDialog == null){
            alertDialog = new AlertDialog.Builder(activity)
                    .setTitle(activity.getString(R.string.main_alert_dialog_title))
                    .setIcon(R.drawable.ic_baseline_error)
                    .setMessage(activity.getString(R.string.main_alert_dialog_message))
                    .setCancelable(false)
                    .setPositiveButton(activity.getString(R.string.main_alert_dialog_button_text), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            activity.finish();
                        }
                    })
                    .create();
        }
        alertDialog.show();
    }

}
