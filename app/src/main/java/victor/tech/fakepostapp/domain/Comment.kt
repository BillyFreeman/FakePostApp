package victor.tech.fakepostapp.domain

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

open class Comment(@Expose @SerializedName("id") var id: Int,
                   @Expose @SerializedName("postId") var postId: Int,
                   @Expose @SerializedName("name") var name: String,
                   @Expose @SerializedName("email") var email: String,
                   @Expose @SerializedName("body") var body: String)
