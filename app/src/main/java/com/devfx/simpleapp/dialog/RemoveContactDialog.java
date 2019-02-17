package com.devfx.simpleapp.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import com.devfx.simpleapp.R;

public class RemoveContactDialog extends Dialog {
    OnRemoveContact listener;
    String value;

    public RemoveContactDialog(Context context, OnRemoveContact listener, String value) {
        super(context, R.style.DefaultDialog);
        this.listener = listener;
        this.value = value;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_remove);

        findViewById(R.id.btnCancel).setOnClickListener(v -> dismiss());
        findViewById(R.id.btnRemove).setOnClickListener(v -> {
            dismiss();
            listener.onRemove(value);
        });
    }


    public interface OnRemoveContact {
        void onRemove(String value);
    }
}
