package com.sample.xxx.user.albums

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sample.xxx.R
import kotlinx.android.synthetic.main.album_item.view.*

class AlbumAdapter(
        private val presenter: AlbumMVP.Presenter<AlbumMVP.View, AlbumMVP.Interactor>
) : RecyclerView.Adapter<AlbumAdapter.ViewHolder>() {
    init {
        setHasStableIds(true)
    }

    override fun getItemId(position: Int): Long {
        return presenter.getUserId(position).toLong()
    }

    override fun getItemCount(): Int {
        return presenter.albumCount
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.album_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        presenter.bindItemView(holder, position)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
            AlbumMVP.ItemView {
        init {
            itemView.setOnClickListener {
                val adapterPosition = adapterPosition
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    presenter.onAlbumClicked(adapterPosition)
                }
            }
        }

        override fun renderAlbumTitle(albumTitle: String) {
            itemView.albumTitle.text = albumTitle
        }
    }
}