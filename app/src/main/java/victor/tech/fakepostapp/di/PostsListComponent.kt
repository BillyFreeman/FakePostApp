package victor.tech.fakepostapp.di

import dagger.Subcomponent
import victor.tech.fakepostapp.di.module.PostsListModule
import victor.tech.fakepostapp.di.scope.PostsActivityScope
import victor.tech.fakepostapp.posts_list.MainActivity

@PostsActivityScope
@Subcomponent(modules = arrayOf(PostsListModule::class))
interface PostsListComponent {

    fun inject(activity: MainActivity)
}
