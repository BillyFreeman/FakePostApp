package victor.tech.fakepostapp.di.module

import dagger.Module
import dagger.Provides
import victor.tech.fakepostapp.datasource.Repository
import victor.tech.fakepostapp.di.scope.PostsActivityScope
import victor.tech.fakepostapp.posts_list.IPostsListPresenter
import victor.tech.fakepostapp.posts_list.PostsListPresenter
import javax.inject.Named

@Module
class PostsListModule {

    @Provides
    @PostsActivityScope
    fun providePresenter(@Named(REPO) repository: Repository): IPostsListPresenter {
        return PostsListPresenter(repository)
    }

}