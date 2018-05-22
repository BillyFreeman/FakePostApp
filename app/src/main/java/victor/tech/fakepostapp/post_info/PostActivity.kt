package victor.tech.fakepostapp.post_info

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_post.*
import victor.tech.fakepostapp.R
import victor.tech.fakepostapp.di.Injector
import victor.tech.fakepostapp.domain.Comment
import victor.tech.fakepostapp.domain.User
import victor.tech.fakepostapp.post_info.adapter.CommentsListAdapter
import javax.inject.Inject


class PostActivity : AppCompatActivity(), PostInfoView {

    private var toast: Toast? = null
    @Inject
    lateinit var presenter: IPostInfoPresenter
    lateinit var userHolder: UserViewHolder
    lateinit var adapter: CommentsListAdapter
    val postId: Int by lazy { intent.getIntExtra(PostInfoView.KEY_POST_ID, -1) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)
        initViews()
        injectDependencies()
        presenter.onAttachView(this)
        presenter.refreshUser(false, postId)
        presenter.refreshComments(false, postId)
    }

    fun initViews() {
        adapter = CommentsListAdapter(inflater = LayoutInflater.from(applicationContext))
        commentsList.adapter = adapter
        commentsList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        commentsList.itemAnimator = DefaultItemAnimator()
        userHolder = UserViewHolder(userInfoContainer)
        pullToRefreshUser.setOnRefreshListener {
            presenter.refreshUser(true, postId)
            presenter.refreshComments(true, postId)
        }

    }

    fun injectDependencies() {
        Injector.instance.plusPostComponent()?.inject(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDetachView()
        if (isFinishing) {
            Injector.instance.releasePostComponent()
        }
    }

    override fun setUser(user: User) {
        userHolder.bind(user)
    }

    override fun setComments(commetes: List<Comment>) {
        adapter.setItems(commetes)
    }

    override fun showMessage(message: String?) {
        toast?.cancel()
        toast = Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT)
        toast?.show()
    }

    override fun showMessage(messageId: Int) {
        showMessage(getString(messageId))
    }

    override fun showProgress(visible: Boolean) {
        if (pullToRefreshUser.isRefreshing && !visible) {
            pullToRefreshUser.isRefreshing = false
            return
        }
        pullToRefreshUser.isRefreshing = visible
    }
}