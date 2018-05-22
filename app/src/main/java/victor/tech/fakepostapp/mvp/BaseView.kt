package victor.tech.fakepostapp.mvp

interface BaseView {

    fun showMessage(message: String?)

    fun showMessage(messageId: Int)

    fun showProgress(visible: Boolean)
}
