package in.confluenceoftech.android.swedsd.utils;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by Jewel on 12/26/2015.
 */
public class LoadingDialog {
    private static Context context;
    private static ProgressDialog dialog;
    private static LoadingDialog loadingDialog;

    public LoadingDialog getInstance(Context _context) {
        context = _context;
        if (loadingDialog == null)
            loadingDialog = new LoadingDialog();
        return loadingDialog;
    }

    public static void open() {
        if (dialog == null)
            dialog = new ProgressDialog(context);
        dialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
        dialog.show();
    }

    public static void close() {
        dialog.dismiss();

    }
}
