package com.sample.nutshell.user.posts

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.sample.nutshell.R
import com.sample.nutshell.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_posts.*
import javax.inject.Inject

class PostsFragment : BaseFragment(), PostsMVP.View, PostDialog.PostDialogListener {

    @Inject lateinit var presenter: PostsMVP.Presenter<PostsMVP.View, PostsMVP.Interactor>


    override val userId: Int
        get() = arguments.getInt(ARG_USER_ID)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentComponent.inject(this)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_posts, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postList.adapter = PostsAdapter(presenter)
        postList.layoutManager = LinearLayoutManager(context)
        swipeRefreshLayout.setOnRefreshListener { presenter.onRefresh() }
        fab.setOnClickListener {
            val dialog = PostDialog()
            dialog.setTargetFragment(this, 42)
            dialog.show(childFragmentManager, "dialog!")
        }
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

        postList.adapter.notifyDataSetChanged()
    }

    override fun showError(error: String) {
        swipeRefreshLayout.isRefreshing = false
        Toast.makeText(context, error, Toast.LENGTH_LONG).show()
    }

    override fun onPostCreating(title: String, body: String) {
        presenter. onPostCreating(title, body)
    }

    override fun saveSuccess() {
        Toast.makeText(context, "Post is creating successful", Toast.LENGTH_LONG).show()
    }

    companion object {
        private val ARG_USER_ID = "user_id"

        fun newInstance(userId: Int): PostsFragment {
            val fragment = PostsFragment()
            val args = Bundle()
            args.putInt(ARG_USER_ID, userId)
            fragment.arguments = args
            return fragment
        }
    }
}
