package victor.tech.fakepostapp.mvp

interface BasePresenter<in V> {

    fun onAttachView(view: V)

    fun onDetachView()
}
