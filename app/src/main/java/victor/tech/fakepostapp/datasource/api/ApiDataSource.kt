package victor.tech.fakepostapp.datasource.api

import io.reactivex.Observable
import victor.tech.fakepostapp.datasource.Repository
import victor.tech.fakepostapp.domain.Comment
import victor.tech.fakepostapp.domain.Post
import victor.tech.fakepostapp.domain.User


class ApiDataSource(val api: PostsApi) : Repository {

    override fun getPosts(forceLoad: Boolean): Observable<List<Post>> {
        return api.getPosts()
    }

    override fun getComments(forceLoad: Boolean, postId: Int): Observable<List<Comment>> {
        return api.getComments(postId)
    }

    override fun getUser(forceLoad: Boolean, postId: Int): Observable<User> {
        return api.getUser(postId)
    }

    override fun updatePosts(posts: List<Post>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateUser(user: User) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateComments(postId: Int, comments: List<Comment>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}