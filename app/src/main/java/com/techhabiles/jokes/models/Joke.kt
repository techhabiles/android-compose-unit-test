package com.techhabiles.jokes.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
/**
 *  Model Represents a Single joke received from Server
 *  @author krish@techhabiles.com
 */
class Joke {

    @SerializedName("id")
    @Expose
    val id:Int? = null
    @SerializedName("type")
    @Expose
    val type:String? = null
    @SerializedName("setup")
    @Expose
    val setup:String? = null
    @SerializedName("punchline")
    @Expose
    val punchline:String? = null

}