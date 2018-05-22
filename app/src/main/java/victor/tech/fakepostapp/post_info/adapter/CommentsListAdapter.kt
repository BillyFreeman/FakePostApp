package victor.tech.fakepostapp.post_info.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.view_post.view.*
import victor.tech.fakepostapp.R
import victor.tech.fakepostapp.domain.Comment


class CommentsListAdapter(val inflater: LayoutInflater) : RecyclerView.Adapter<CommentsListAdapter.CommentViewHolder>() {

    private val items: MutableList<Comment> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val view = inflater.inflate(R.layout.view_post, parent, false)
        return CommentViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun setItems(newItems: List<Comment>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    class CommentViewHolder(viewItem: View) : RecyclerView.ViewHolder(viewItem) {

        fun bind(comment: Comment) = with(itemView) {
            postTitle.text = comment.name
            postBody.text = comment.body
        }
    }
}