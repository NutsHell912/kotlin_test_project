package com.sample.xxx.user.posts

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sample.xxx.R
import kotlinx.android.synthetic.main.post_item.view.*

class PostsAdapter(
        private val presenter: PostsMVP.Presenter<PostsMVP.View, PostsMVP.Interactor>
) : RecyclerView.Adapter<PostsAdapter.ViewHolder>() {
    init {
        setHasStableIds(true)
    }

    override fun getItemId(position: Int): Long {
        return presenter.getUserId(position).toLong()
    }

    override fun getItemCount(): Int {
        return presenter.postsCount
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.post_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        presenter.bindItemView(holder, position)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
            PostsMVP.ItemView {
        override fun renderPostTitle(postTitle: String) {
            itemView.postTitle.text = postTitle
        }

        override fun renderPostBody(postBody: String) {
            itemView.postBody.text = postBody
        }


    }
}