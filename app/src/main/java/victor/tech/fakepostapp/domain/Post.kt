package victor.tech.fakepostapp.domain

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


open class Post (@Expose @SerializedName("id") var id: Int,
                 @Expose @SerializedName("userId") var userId: Int,
                 @Expose @SerializedName("title") var title: String,
                 @Expose @SerializedName("body") var body: String)