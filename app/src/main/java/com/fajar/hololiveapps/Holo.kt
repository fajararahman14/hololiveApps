package com.fajar.hololiveapps

import android.os.Parcelable

@kotlinx.parcelize.Parcelize
data class Holo (
    var name : String,
    var desc : String,
    var photo : Int,
    var detail : String
    ) :Parcelable