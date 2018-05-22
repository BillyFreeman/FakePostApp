package victor.tech.fakepostapp.domain

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


open class User(@Expose @SerializedName("id") var id: Int,
                @Expose @SerializedName("name") var name: String,
                @Expose @SerializedName("username") var username: String,
                @Expose @SerializedName("email") var email: String,
                @Expose @SerializedName("address") var address: Address,
                @Expose @SerializedName("phone") var phone: String,
                @Expose @SerializedName("website") var website: String,
                @Expose @SerializedName("company") var company: Company)

open class Company(@Expose @SerializedName("name") var name: String,
                   @Expose @SerializedName("catchPhrase") var catchPhrase: String,
                   @Expose @SerializedName("bs") var bs: String)

open class Address(@Expose @SerializedName("street") var street: String,
                   @Expose @SerializedName("suite") var suite: String,
                   @Expose @SerializedName("city") var city: String,
                   @Expose @SerializedName("zipcode") var zipcode: String,
                   @Expose @SerializedName("geo") var geo: Geo)

open class Geo(@Expose @SerializedName("lat") var lat: String,
               @Expose @SerializedName("lng") var lng: String)