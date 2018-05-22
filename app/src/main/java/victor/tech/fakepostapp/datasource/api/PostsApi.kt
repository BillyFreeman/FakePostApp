package victor.tech.fakepostapp.datasource.api

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import victor.tech.fakepostapp.domain.Comment
import victor.tech.fakepostapp.domain.Post
import victor.tech.fakepostapp.domain.User


interface PostsApi {

    @GET("/posts")
    fun getPosts(): Observable<List<Post>>

    @GET("/users")
    fun getUsers(): Observable<List<User>>

    @GET("/users/{userId}")
    fun getUser(@Path("userId") userId: Int): Observable<User>

    @GET("/comments")
    fun getComments(): Observable<List<Comment>>

    @GET("/comments")
    fun getComments(@Query("postId") postId: Int): Observable<List<Comment>>
}