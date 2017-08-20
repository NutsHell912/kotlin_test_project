package com.sample.nutshell.user.posts

import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.widget.EditText
import com.sample.nutshell.R


class PostDialog: DialogFragment() {

    private lateinit var listener:PostDialogListener
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        // Get the layout inflater
        val inflater = activity.layoutInflater

        val viewInflated = inflater.inflate(R.layout.post_dialog, null)
        val title = viewInflated.findViewById(R.id.title) as EditText
        val body = viewInflated.findViewById(R.id.body) as EditText
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(viewInflated)
                // Add action buttons
                .setPositiveButton(R.string.create, { dialog, id ->
                    val listener = targetFragment as PostDialogListener
                    listener.onPostCreating(title.text.toString(), body.text.toString())
                })
                .setNegativeButton(R.string.cancel, { dialog, id -> this@PostDialog.dialog.cancel() })
        return builder.create()

    }
    interface PostDialogListener {
        fun onPostCreating(title: String, body: String)
    }
}