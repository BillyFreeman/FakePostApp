package victor.tech.fakepostapp.posts_list.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.view_post.view.*
import victor.tech.fakepostapp.R
import victor.tech.fakepostapp.domain.Post

class PostsListAdapter(val inflater: LayoutInflater) : RecyclerView.Adapter<PostsListAdapter.PostViewHolder>() {

    private val items: MutableList<Post> = mutableListOf()
    lateinit var clickListener: (post: Post) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = inflater.inflate(R.layout.view_post, parent, false)
        return PostViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(items[position], clickListener)
    }

    fun setItems(newItems: List<Post>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    class PostViewHolder(viewItem: View) : RecyclerView.ViewHolder(viewItem) {

        fun bind(post: Post, clickListener: (post: Post) -> Unit) = with(itemView) {
            setOnClickListener { clickListener.invoke(post) }
            postTitle.text = post.title
            postBody.text = post.body
        }
    }
}