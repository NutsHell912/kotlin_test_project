package com.sample.xxx.photo

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.bumptech.glide.Glide
import com.sample.xxx.R
import com.sample.xxx.base.BaseActivity
import kotlinx.android.synthetic.main.activity_photo.*
import javax.inject.Inject

class PhotoActivity : BaseActivity(), PhotoMVP.View {

    @Inject lateinit var presenter: PhotoMVP.Presenter<PhotoMVP.View, PhotoMVP.Interactor>


    override var albumId: Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo)

        albumId = intent.extras.getInt(ARG_ALBUM_ID)
        activityComponent.inject(this)

        swipeRefreshLayout.setOnRefreshListener { presenter.onRefresh() }
        photoList.adapter = PhotoAdapter(presenter, Glide.with(this))
        photoList.layoutManager = LinearLayoutManager(this)
    }


    override fun onResume() {
        super.onResume()
        presenter.onAttach(this)
    }

    override fun onPause() {
        presenter.onDetach()
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun renderList() {
        swipeRefreshLayout.isRefreshing = false

        photoList.adapter.notifyDataSetChanged()
    }

    override fun showError(error: String) {
        swipeRefreshLayout.isRefreshing = false
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }
    companion object {
        val ARG_ALBUM_ID = "user_id_activity"
    }

}
