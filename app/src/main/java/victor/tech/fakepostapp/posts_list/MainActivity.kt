package victor.tech.fakepostapp.posts_list

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import victor.tech.fakepostapp.R
import victor.tech.fakepostapp.di.Injector
import victor.tech.fakepostapp.domain.Post
import victor.tech.fakepostapp.post_info.PostActivity
import victor.tech.fakepostapp.post_info.PostInfoView
import victor.tech.fakepostapp.posts_list.adapter.PostsListAdapter
import javax.inject.Inject

class MainActivity : AppCompatActivity(), PostsListView {

    private var toast: Toast? = null
    private lateinit var adapter: PostsListAdapter

    @Inject
    lateinit var presenter: IPostsListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        injectDependencies()
        presenter.onAttachView(this)
        presenter.refresh(false)
    }


    fun initViews() {
        adapter = PostsListAdapter(inflater = LayoutInflater.from(applicationContext))
        adapter.clickListener = { post ->
            openPost(post)
        }
        postsList.adapter = adapter
        postsList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        postsList.itemAnimator = DefaultItemAnimator()

        pullToRefresh.setOnRefreshListener {
            presenter.refresh(true)
        }
    }

    private fun openPost(post: Post) {
        val intent = Intent(this, PostActivity::class.java)
        intent.putExtra(PostInfoView.KEY_POST_ID, post.id)
        startActivity(intent)
    }

    fun injectDependencies() {
        Injector.instance.plusPostsComponent()?.inject(this)
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
        if (pullToRefresh.isRefreshing && !visible) {
            pullToRefresh.isRefreshing = false
            return
        }
        pullToRefresh.isRefreshing = visible
    }

    override fun setPosts(posts: List<Post>) {
        adapter.setItems(posts)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDetachView()
        if (isFinishing) {
            Injector.instance.releasePostsComponent()
        }
    }
}
