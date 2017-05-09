package cn.edu.pku.app.familylibrary.ui.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import cn.edu.pku.app.familylibrary.R;

/**
 * Created by jeanboy on 2017/5/9.
 */

public class DialogUtil {

    public static void showOperateDialog(Context context, String[] items,
                                         DialogInterface.OnClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.dialog_operate);
        builder.setItems(items, listener);
        builder.show();
    }

    public static void showWarningDialog(Context context, int resId, DialogInterface.OnClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.dialog_warning);
        builder.setMessage(resId);
        builder.setPositiveButton(R.string.ok, listener);
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }
}
