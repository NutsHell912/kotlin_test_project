package com.sample.xxx.start

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.sample.xxx.R
import com.sample.xxx.base.BaseActivity
import com.sample.xxx.user.UserActivity
import kotlinx.android.synthetic.main.activity_start.*
import javax.inject.Inject

class StartActivity : BaseActivity(), StartMVP.View {

    @Inject lateinit var presenter: StartMVP.Presenter<StartMVP.View, StartMVP.Interactor>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        activityComponent.inject(this)

        swipeRefreshLayout.setOnRefreshListener { presenter.onRefresh() }
        userList.adapter = UserAdapter(presenter)
        userList.layoutManager = LinearLayoutManager(this)
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

        userList.adapter.notifyDataSetChanged()
    }

    override fun showError(error: String) {
        swipeRefreshLayout.isRefreshing = false
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }

    override fun navigateToUserActivity(userId: Int) {
        val b = Bundle()
        b.putInt(UserActivity.ARG_USER_ID, userId)
        val intent = Intent(this, UserActivity::class.java)
        intent.putExtras(b)
        startActivity(intent)
    }
}
