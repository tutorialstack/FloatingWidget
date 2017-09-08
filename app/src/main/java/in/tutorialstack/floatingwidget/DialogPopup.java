package in.tutorialstack.floatingwidget;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class DialogPopup {
    Context mContext;
    android.app.Dialog dialog;
    TextView txtTitle;
    TextView txtMessage;
    Button cancel, ok;

    public DialogPopup(Context context, String title, String message) {
        mContext = context.getApplicationContext();

        dialog = new android.app.Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custome_dailog);
        dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);

        txtTitle = (TextView) dialog.findViewById(R.id.txt_custome_dailog_title);
        txtMessage = (TextView) dialog.findViewById(R.id.txt_custome_dailog_message);
        cancel = (Button) dialog.findViewById(R.id.btn_dailog_custome_cancel);
        ok = (Button) dialog.findViewById(R.id.btn_dailog_custome_set);

        setText();

        txtTitle.setText(title);
        txtMessage.setText(message);
        cancel.setOnClickListener(onClickListener);
        ok.setOnClickListener(onClickListener);

        dialog.show();
    }

    private void setText() {
        cancel.setText("Not now");
        ok.setText("Call");
    }

    public OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_dailog_custome_cancel:
                    dialog.dismiss();
                    break;
                case R.id.btn_dailog_custome_set:
                    if (Util.getInstance(mContext).checkCallPermission(MainActivity.activity)) {
                        Intent callIntent = new Intent(Intent.ACTION_CALL);
                        callIntent.setData(Uri.parse("tel:" + "xxxxxxxxxx"));
                        MainActivity.activity.startActivity(callIntent);
                        dialog.dismiss();
                    }
                    break;
            }
        }
    };
}