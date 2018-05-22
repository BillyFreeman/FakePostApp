package victor.tech.fakepostapp.datasource

import io.reactivex.Observable
import victor.tech.fakepostapp.domain.Comment
import victor.tech.fakepostapp.domain.Post
import victor.tech.fakepostapp.domain.User


interface Repository {

    fun getPosts(forceLoad: Boolean): Observable<List<Post>>

    fun getComments(forceLoad: Boolean, postId: Int): Observable<List<Comment>>

    fun getUser(forceLoad: Boolean, postId: Int): Observable<User>

    fun updatePosts(posts: List<Post>)

    fun updateUser(user: User)

    fun updateComments(postId: Int, comments: List<Comment>)
}