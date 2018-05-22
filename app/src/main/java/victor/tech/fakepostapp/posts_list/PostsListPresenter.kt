package victor.tech.fakepostapp.posts_list

import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables
import victor.tech.fakepostapp.datasource.Repository

class PostsListPresenter(val repository: Repository) : IPostsListPresenter {

    private var view: PostsListView? = null
    private var subscription: Disposable = Disposables.empty()

    override fun onAttachView(view: PostsListView) {
        this.view = view
    }

    override fun refresh(force: Boolean) {
        view?.showProgress(true)
        subscription = repository.getPosts(force).subscribe({ list ->
            view?.let { v ->
                v.showProgress(false)
                v.setPosts(list)
            }
        }, { error ->
            view?.let { v ->
                v.showProgress(false)
                v.showMessage(error.message)
            }
        })
    }

    override fun onDetachView() {
        this.view = null
        if (!subscription.isDisposed) {
            subscription.dispose()
        }
    }
}
