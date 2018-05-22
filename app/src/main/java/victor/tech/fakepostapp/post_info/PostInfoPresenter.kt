package victor.tech.fakepostapp.post_info

import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables
import victor.tech.fakepostapp.datasource.Repository


class PostInfoPresenter(val repository: Repository) : IPostInfoPresenter {

    private var view: PostInfoView? = null
    private var userSubscription: Disposable = Disposables.empty()
    private var commentsSubscription: Disposable = Disposables.empty()

    override fun refreshUser(force: Boolean, postId: Int) {
        userSubscription = repository.getUser(force, postId).subscribe({ user ->
            view?.setUser(user)
        }, { error ->
            view?.showMessage(error.message)
        })
    }

    override fun refreshComments(force: Boolean, postId: Int) {
        view?.showProgress(true)
        commentsSubscription = repository.getComments(force, postId).subscribe({ comments ->
            view?.let { v ->
                v.showProgress(false)
                v.setComments(comments)
            }
        }, { error ->
            view?.let { v ->
                v.showProgress(false)
                v.showMessage(error.message)
            }
        })
    }

    override fun onAttachView(view: PostInfoView) {
        this.view = view
    }

    override fun onDetachView() {
        this.view = null
        if (!userSubscription.isDisposed) {
            userSubscription.dispose()
        }
        if (!commentsSubscription.isDisposed) {
            commentsSubscription.dispose()
        }
    }
}