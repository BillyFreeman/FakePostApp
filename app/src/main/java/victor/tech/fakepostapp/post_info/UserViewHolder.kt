package victor.tech.fakepostapp.post_info

import android.view.View
import kotlinx.android.synthetic.main.activity_post.view.*
import victor.tech.fakepostapp.domain.User

class UserViewHolder(val viewItem: View) {

    fun bind(user: User) = with(viewItem) {
        username.text = user.username
        email.text = user.email
    }
}