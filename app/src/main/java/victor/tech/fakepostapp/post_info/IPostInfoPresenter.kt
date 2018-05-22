package victor.tech.fakepostapp.post_info

import victor.tech.fakepostapp.mvp.BasePresenter

interface IPostInfoPresenter : BasePresenter<PostInfoView> {

    fun refreshUser(force: Boolean, postId: Int)

    fun refreshComments(force: Boolean, postId: Int)

}