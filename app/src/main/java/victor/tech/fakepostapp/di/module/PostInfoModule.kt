package victor.tech.fakepostapp.di.module

import dagger.Module
import dagger.Provides
import victor.tech.fakepostapp.datasource.Repository
import victor.tech.fakepostapp.di.scope.PostActivityScope
import victor.tech.fakepostapp.post_info.IPostInfoPresenter
import victor.tech.fakepostapp.post_info.PostInfoPresenter
import javax.inject.Named

@Module
class PostInfoModule {

    @Provides
    @PostActivityScope
    fun providePresenter(@Named(REPO) repository: Repository): IPostInfoPresenter {
        return PostInfoPresenter(repository)
    }
}