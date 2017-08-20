package com.sample.xxx.start

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sample.xxx.R
import kotlinx.android.synthetic.main.user_item.view.*

class UserAdapter(
        private val presenter: StartMVP.Presenter<StartMVP.View, StartMVP.Interactor>
) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    init {
        setHasStableIds(true)
    }

    override fun getItemId(position: Int): Long {
        return presenter.getUserId(position).toLong()
    }

    override fun getItemCount(): Int {
        return presenter.usersCount
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.user_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        presenter.bindItemView(holder, position)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
            StartMVP.ItemView {
        init {
            itemView.setOnClickListener {
                val adapterPosition = adapterPosition
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    presenter.onUserClicked(adapterPosition)
                }
            }
        }

        override fun renderUserName(userName: String) {
            itemView.userName.text = userName
        }

        override fun renderUserEmail(userEmail: String) {
            itemView.userEmail.text = userEmail
        }

        override fun renderUserAddress(userAddress: String) {
            itemView.userAddress.text = userAddress
        }
    }
}