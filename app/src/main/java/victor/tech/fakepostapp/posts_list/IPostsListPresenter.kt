package victor.tech.fakepostapp.posts_list

import victor.tech.fakepostapp.mvp.BasePresenter


interface IPostsListPresenter : BasePresenter<PostsListView> {

    fun refresh(force: Boolean)

}