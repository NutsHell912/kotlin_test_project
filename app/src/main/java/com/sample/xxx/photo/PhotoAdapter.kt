package com.sample.xxx.photo

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import com.sample.xxx.R
import kotlinx.android.synthetic.main.photo_item.view.*

class PhotoAdapter(
        private val presenter: PhotoMVP.Presenter<PhotoMVP.View, PhotoMVP.Interactor>,
        private val glide: RequestManager
) : RecyclerView.Adapter<PhotoAdapter.ViewHolder>() {
    init {
        setHasStableIds(true)
    }

    override fun getItemId(position: Int): Long {
        return presenter.getUserId(position).toLong()
    }

    override fun getItemCount(): Int {
        return presenter.photoCount
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.photo_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        presenter.bindItemView(holder, position)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
            PhotoMVP.ItemView {

        override fun renderPhotoTitle(photoTitle: String) {
            itemView.photoTitle.text = photoTitle
        }

        override fun renderPhoto(photoUrl: String) {
            glide.load(photoUrl)
                    .into(itemView.photoImage)
        }
    }
}