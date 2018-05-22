package victor.tech.fakepostapp.datasource.local

import io.reactivex.Observable
import victor.tech.fakepostapp.datasource.Repository
import victor.tech.fakepostapp.domain.Comment
import victor.tech.fakepostapp.domain.Post
import victor.tech.fakepostapp.domain.User

class LocalDAO(val postsTable: MutableMap<Int, Post>,
               val usersTable: MutableMap<Int, User>,
               val commentsTable: MutableMap<Int, List<Comment>>) : Repository {

    override fun getPosts(forceLoad: Boolean): Observable<List<Post>> {
        return Observable.just(postsTable.values.toList())
    }

    override fun getComments(forceLoad: Boolean, postId: Int): Observable<List<Comment>> {
        return if (commentsTable[postId] == null) {
            Observable.empty()
        } else {
            Observable.just(commentsTable[postId])
        }
    }

    override fun getUser(forceLoad: Boolean, postId: Int): Observable<User> {
        return Observable.just(postsTable[postId]).flatMap { post ->
            val user = usersTable[post.userId]
            return@flatMap if (user != null) {
                Observable.just(user)
            } else Observable.empty<User>()
        }
    }

    override fun updatePosts(posts: List<Post>) {
        postsTable.clear()
        posts.forEach { entry -> postsTable.put(entry.id, entry) }
    }

    override fun updateUser(user: User) {
        usersTable.put(user.id, user)
    }

    override fun updateComments(postId: Int, comments: List<Comment>) {
        commentsTable.put(postId, comments)
    }
}
