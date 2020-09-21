package com.example.alphabetboard.log

import timber.log.Timber

class MDebugTree : Timber.DebugTree() {
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        super.log(priority, tag + "_MY_TAG", message, t)
    }
}