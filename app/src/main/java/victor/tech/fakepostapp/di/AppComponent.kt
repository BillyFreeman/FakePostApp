package victor.tech.fakepostapp.di

import dagger.Component
import victor.tech.fakepostapp.di.module.ApiModule
import victor.tech.fakepostapp.di.module.AppModule
import victor.tech.fakepostapp.di.module.RepositoryModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class, ApiModule::class, RepositoryModule::class))
interface AppComponent {

    fun plusPostInfoComponent(): PostInfoComponent

    fun plusPostsListComponent(): PostsListComponent
}