package victor.tech.fakepostapp.di

import android.content.Context
import victor.tech.fakepostapp.di.module.ApiModule
import victor.tech.fakepostapp.di.module.AppModule
import victor.tech.fakepostapp.di.module.RepositoryModule

class Injector private constructor() {

    private object Holder {
        val INSTANCE = Injector()
    }

    companion object {
        val instance: Injector by lazy { Holder.INSTANCE }
    }

    private lateinit var appComponent: AppComponent
    private var postInfoComponent: PostInfoComponent? = null
    private var postsListComponent: PostsListComponent? = null


    fun init(context: Context) {
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(context))
                .apiModule(ApiModule())
                .repositoryModule(RepositoryModule())
                .build()
    }

    fun plusPostComponent(): PostInfoComponent? {
        if (postInfoComponent == null) {
            postInfoComponent = appComponent.plusPostInfoComponent()
        }
        return postInfoComponent
    }

    fun releasePostComponent() {
        postInfoComponent = null
    }

    fun plusPostsComponent(): PostsListComponent? {
        if (postsListComponent == null) {
            postsListComponent = appComponent.plusPostsListComponent()
        }
        return postsListComponent
    }

    fun releasePostsComponent() {
        postsListComponent = null
    }
}