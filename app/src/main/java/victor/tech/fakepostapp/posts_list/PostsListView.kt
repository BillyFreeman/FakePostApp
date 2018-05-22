package victor.tech.fakepostapp.posts_list

import victor.tech.fakepostapp.domain.Post
import victor.tech.fakepostapp.mvp.BaseView

interface PostsListView : BaseView {

    fun setPosts(posts: List<Post>)

}