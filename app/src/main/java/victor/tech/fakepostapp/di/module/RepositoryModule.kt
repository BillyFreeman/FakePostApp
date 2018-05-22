package victor.tech.fakepostapp.di.module

import dagger.Module
import dagger.Provides
import victor.tech.fakepostapp.datasource.FakeAppRepository
import victor.tech.fakepostapp.datasource.Repository
import victor.tech.fakepostapp.datasource.api.ApiDataSource
import victor.tech.fakepostapp.datasource.api.PostsApi
import victor.tech.fakepostapp.datasource.local.LocalDAO
import victor.tech.fakepostapp.domain.Comment
import victor.tech.fakepostapp.domain.Post
import victor.tech.fakepostapp.domain.User
import javax.inject.Named
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    @Named(REPO)
    fun provideRepository(@Named(LOCAL) localRepository: Repository,
                          @Named(REMOTE) remoteRepository: Repository): Repository {
        return FakeAppRepository(localRepository, remoteRepository)
    }

    @Singleton
    @Provides
    fun providePostsTable(): MutableMap<Int, Post> {
        return mutableMapOf()
    }

    @Singleton
    @Provides
    fun provideUserTable(): MutableMap<Int, User> {
        return mutableMapOf()
    }

    @Singleton
    @Provides
    fun provideCommentsTable(): MutableMap<Int, List<Comment>> {
        return mutableMapOf()
    }

    @Provides
    @Singleton
    @Named(LOCAL)
    fun provideLocalRepository(postsTable: MutableMap<Int, Post>,
                               usersTable: MutableMap<Int, User>,
                               commentsTable: MutableMap<Int, List<Comment>>): Repository {
        return LocalDAO(postsTable, usersTable, commentsTable)
    }

    @Provides
    @Singleton
    @Named(REMOTE)
    fun provideLocalRemote(api: PostsApi): Repository {
        return ApiDataSource(api)
    }
}

const val LOCAL: String = "local"
const val REMOTE: String = "remote"
const val REPO: String = "repo"
