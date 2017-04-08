package com.lcarino.bucketlist.ui.list.dialog


import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.ContextThemeWrapper
import com.lcarino.bucketlist.R


/**
 * Created by luiscarino on 4/6/17.
 */
class ConfirmDialog : DialogFragment() {

    interface ConfirmDialogActions {
        fun onCancel()
        fun onConfirm()
    }

    var actions: ConfirmDialogActions? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(ContextThemeWrapper(context, R.style.AlertDialogCustom))
                .setIcon(R.drawable.ic_warning)
                .setTitle(R.string.confirm_dialog_title)
                .setPositiveButton(R.string.confirm_dialog_yes_btn,
                        DialogInterface.OnClickListener { dialog, whichButton ->
                            actions?.onConfirm()
                            dismiss()
                        }
                )
                .setNegativeButton(R.string.confirm_dialog_cancel_btn,
                        DialogInterface.OnClickListener { dialog, whichButton -> dismiss() }
                )
                .create()
    }

    fun addDialogActions(actions: ConfirmDialogActions) {
        this.actions = actions
    }


}