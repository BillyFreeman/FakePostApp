package victor.tech.fakepostapp.datasource

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import victor.tech.fakepostapp.domain.Comment
import victor.tech.fakepostapp.domain.Post
import victor.tech.fakepostapp.domain.User


class FakeAppRepository(val local: Repository, val remote: Repository) : Repository {

    override fun getPosts(forceLoad: Boolean): Observable<List<Post>> {
        val remotePosts = remote.getPosts(forceLoad).doOnNext { posts ->
            local.updatePosts(posts)
        }
        val localPosts = local.getPosts(forceLoad).flatMap { posts ->
            return@flatMap if (posts.isEmpty()) remotePosts else Observable.just(posts)
        }
        val observable = if (forceLoad) remotePosts else localPosts
        return observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }


    override fun getComments(forceLoad: Boolean, postId: Int): Observable<List<Comment>> {
        val remoteComments = remote.getComments(forceLoad, postId).doOnNext { comments ->
            local.updateComments(postId, comments)
        }
        val localComments = local.getComments(forceLoad, postId).switchIfEmpty(remoteComments)
        val observable = if (forceLoad) remoteComments else localComments
        return observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getUser(forceLoad: Boolean, postId: Int): Observable<User> {

        val remoteUser = local.getPosts(forceLoad)
                .flatMap { list -> Observable.fromIterable(list) }
                .filter { post -> post.id == postId }
                .take(1)
                .flatMap { post -> remote.getUser(forceLoad, post.userId) }
                .doOnNext { user -> local.updateUser(user) }

        val localUser = local.getUser(forceLoad, postId).switchIfEmpty(remoteUser)
        val observable = if (forceLoad) remoteUser else localUser
        return observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun updateUser(user: User) {
        local.updateUser(user)
    }

    override fun updatePosts(posts: List<Post>) {
        local.updatePosts(posts)
    }

    override fun updateComments(postId: Int, comments: List<Comment>) {
        local.updateComments(postId, comments)
    }
}
