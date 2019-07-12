package ru.skillbranch.devintensive.extensions

import android.app.Activity
import android.graphics.Rect
import android.view.View
import android.view.ViewTreeObserver
import android.view.inputmethod.InputMethodManager

fun Activity.hideKeyboard() {
    val imm = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    var view = this.currentFocus
    if (view == null) {
        view = View(this)
    }
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

//fun Activity.isKeyboardOpen(): Boolean {
//
//    val rootView = this.window.decorView.rootView
//
//    rootView.viewTreeObserver.addOnGlobalLayoutListener {
//        val r = Rect()
//        rootView.getWindowVisibleDisplayFrame(r)
//        val heightDiff = rootView.height - r.bottom - r.top
//        if (heightDiff > 100) return true
//        else return false
//    }
//
//    val s = 5;
//
////    final View activityRootView = findViewById(R.id.activityRoot);
////    activityRootView.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
////        @Override
////        public void onGlobalLayout() {
////            Rect r = new Rect();
////            //r will be populated with the coordinates of your view that area still visible.
////            activityRootView.getWindowVisibleDisplayFrame(r);
////
////            int heightDiff = activityRootView.getRootView().getHeight() - (r.bottom - r.top);
////            if (heightDiff > 100) { // if more than 100 pixels, its probably a keyboard...
////                ... do something here
////            }
////        }
////    });
//
//
//    return false
//}