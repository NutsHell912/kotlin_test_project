package com.sample.xxx.user.albums

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.sample.xxx.R
import com.sample.xxx.base.BaseFragment
import com.sample.xxx.photo.PhotoActivity
import kotlinx.android.synthetic.main.fragment_user_albums.*
import javax.inject.Inject

class AlbumFragment : BaseFragment(), AlbumMVP.View {

    @Inject lateinit var presenter: AlbumMVP.Presenter<AlbumMVP.View, AlbumMVP.Interactor>


    override val userId: Int
        get() = arguments.getInt(ARG_USER_ID)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentComponent.inject(this)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_user_albums, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        albumList.adapter = AlbumAdapter(presenter)
        albumList.layoutManager = LinearLayoutManager(context)
        swipeRefreshLayout.setOnRefreshListener { presenter.onRefresh() }

    }

    override fun navigateToPhotoActivity(albumId: Int) {
        val b = Bundle()
        b.putInt(PhotoActivity.ARG_ALBUM_ID, albumId)
        val intent = Intent(context, PhotoActivity::class.java)
        intent.putExtras(b)
        startActivity(intent)
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

        albumList.adapter.notifyDataSetChanged()
    }

    override fun showError(error: String) {
        swipeRefreshLayout.isRefreshing = false
        Toast.makeText(context, error, Toast.LENGTH_LONG).show()
    }

    companion object {
        private val ARG_USER_ID = "user_id"

        fun newInstance(userId: Int): AlbumFragment {
            val fragment = AlbumFragment()
            val args = Bundle()
            args.putInt(ARG_USER_ID, userId)
            fragment.arguments = args
            return fragment
        }
    }
}
