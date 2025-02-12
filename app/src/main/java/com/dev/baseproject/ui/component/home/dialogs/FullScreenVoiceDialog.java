package com.dev.baseproject.ui.component.home.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AnimationUtils;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;

import com.dev.baseproject.R;

public final class FullScreenVoiceDialog extends DialogFragment {
    private FullScreenClapDialogListener listener;
    public void setListener( FullScreenClapDialogListener listener) {
        this.listener = listener;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Window window;
        View inflate = layoutInflater.inflate(R.layout.dialog_fullscreen_voice, viewGroup, false);
        Dialog dialog = getDialog();
        if (!(dialog == null || (window = dialog.getWindow()) == null)) {
            window.setBackgroundDrawable(requireContext().getDrawable(R.drawable.bg_rectangle_16));
        }
        View findViewById = inflate.findViewById(R.id.gotIt);
        findViewById.setOnClickListener(new FullScreenVoiceDialogCall(this));
        return inflate;
    }

    
    public static void FullScreenVoiceDialogCall1(FullScreenVoiceDialog fullScreenVoiceDialog, View view) {
        FullScreenClapDialogListener fullScreenClapDialogListener = fullScreenVoiceDialog.listener;
        if (fullScreenClapDialogListener != null) {
            fullScreenClapDialogListener.onImageViewClicked();
        }
        Dialog dialog = fullScreenVoiceDialog.getDialog();
        if (dialog != null) {
            dialog.dismiss();
        }
    }
}
