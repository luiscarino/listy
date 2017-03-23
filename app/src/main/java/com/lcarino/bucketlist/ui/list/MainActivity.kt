package com.lcarino.bucketlist.ui.list

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.lcarino.bucketlist.R
import com.lcarino.bucketlist.application.BucketListApplication
import com.lcarino.bucketlist.common.BaseFragment
import com.lcarino.bucketlist.ui.inspirations.InspirationListFragment
import com.lcarino.bucketlist.ui.list.di.ListComponent
import com.lcarino.bucketlist.ui.list.di.ListModule
import kotlinx.android.synthetic.main.main_activity_layout.*

/**
 * Main Activity.
 */
class MainActivity : AppCompatActivity(),
        InspirationListFragment.Listener,
        BaseFragment.ActivityActions {

    private var actionBarDrawerToggle: ActionBarDrawerToggle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity_layout)
        val toolbar = findViewById(R.id.my_toolbar) as Toolbar
        setSupportActionBar(toolbar)

        my_nav_view.setNavigationItemSelectedListener { item ->
            onMenuItemClicked(item)
            true
        }

        actionBarDrawerToggle = object : ActionBarDrawerToggle(this, drawer_layout,
                toolbar,
                R.string.menu_opened, /* "open drawer" description for accessibility */
                R.string.menu_closed) {

            override fun onDrawerOpened(drawerView: View?) {
                super.onDrawerOpened(drawerView)

            }

            override fun onDrawerClosed(drawerView: View?) {
                super.onDrawerClosed(drawerView)
                if (refreshDrawer) {
                    loadFragmentForId()
                    refreshDrawer = false
                }

            }
        }

        drawer_layout.addDrawerListener(actionBarDrawerToggle!!)
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }

        showList()
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        actionBarDrawerToggle?.syncState()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_bucket_list, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId
        return super.onOptionsItemSelected(item)
    }

    fun showList() {
        supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, MyListFragment.newInstance())
                .commit()
    }

    override fun setToolbarTitle(title: String) {
        // implement me
    }

    override fun getListComponent(): ListComponent {
        return (application as BucketListApplication).applicationComponent.plus(ListModule())
    }

    var selectedItemId = 0
    var refreshDrawer = false
    fun onMenuItemClicked(item: MenuItem) {
        item.isChecked = true
        drawer_layout.closeDrawers()
        selectedItemId = item.itemId
        refreshDrawer = true
    }

    fun loadFragmentForId() {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, getFragmentForMenuItem(selectedItemId))
                .commit()
    }


    fun getFragmentForMenuItem(id: Int): Fragment {
        when (id) {
            R.id.action_listly -> return MyListFragment.newInstance()
            R.id.action_archive -> return MyListFragment.newInstanceForArchive()
            R.id.action_trash -> return MyListFragment.newInstanceForTrash()
            else -> throw IllegalStateException("ID not valid. Should be declared on menu file.")
        }
    }
}
