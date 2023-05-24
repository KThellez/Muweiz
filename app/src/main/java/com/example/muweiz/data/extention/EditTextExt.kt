package com.example.muweiz.data.extention

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

fun EditText.onTextChanged(listener: (String) -> Unit){
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(p0: CharSequence?, start: Int, before: Int, count: Int) {}
        override fun afterTextChanged(s: Editable?){
            listener(s.toString())
        }

    })
}

fun EditText.loseFocusAfterAction(action: Int){
    this.setOnEditorActionListener { v, actionID, _ ->
        if(actionID == action){
            this.dismissKeyboard()
            v.clearFocus()
        }
        return@setOnEditorActionListener false
    }
}