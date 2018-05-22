package victor.tech.fakepostapp.post_info

import victor.tech.fakepostapp.domain.Comment
import victor.tech.fakepostapp.domain.User
import victor.tech.fakepostapp.mvp.BaseView


interface PostInfoView : BaseView {

    companion object {
        const val KEY_POST_ID = "key_post_id"
    }

    fun setUser(user: User)

    fun setComments(comments: List<Comment>)
}