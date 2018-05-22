package victor.tech.fakepostapp.di

import dagger.Subcomponent
import victor.tech.fakepostapp.di.module.PostInfoModule
import victor.tech.fakepostapp.di.scope.PostActivityScope
import victor.tech.fakepostapp.post_info.PostActivity

@PostActivityScope
@Subcomponent(modules = arrayOf(PostInfoModule::class))
interface PostInfoComponent {

    fun inject(activity: PostActivity)

}