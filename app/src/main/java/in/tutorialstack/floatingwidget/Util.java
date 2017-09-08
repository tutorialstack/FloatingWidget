package in.tutorialstack.floatingwidget;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;

import java.util.ArrayList;
import java.util.List;

public class Util {
    Context context;
    private static Util util;

    public static Util getInstance(Context context) {
        if (util == null) {
            util = new Util(context);
        }

        return util;
    }

    public Util(Context context) {
        this.context = context;
    }

    List<String> PERMISSIONS_REQ_GRANTED = new ArrayList<String>();
    public static int PERMISSION_ALL = 1;

    // Call Permissions
    String[] Call_Permsiions = {
            Manifest.permission.CALL_PHONE
    };

    public boolean checkCallPermission(Activity activity) {
        if (!hasPermissions(Call_Permsiions)) {
            ActivityCompat.requestPermissions(activity, PERMISSIONS_REQ_GRANTED.toArray(new String[PERMISSIONS_REQ_GRANTED.size()]), PERMISSION_ALL);
            PERMISSIONS_REQ_GRANTED.clear();
            return false;
        }

        return true;
    }

    public boolean hasPermissions(String... permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    PERMISSIONS_REQ_GRANTED.add(permission);
                }
            }
        }

        if (PERMISSIONS_REQ_GRANTED.size() > 0)
            return false;

        return true;
    }
}
