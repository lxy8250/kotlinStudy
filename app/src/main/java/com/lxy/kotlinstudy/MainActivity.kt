package com.lxy.kotlinstudy

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.lxy.kotlinstudy.ui.fragment.HomeFragment
import com.lxy.kotlinstudy.ui.fragment.KnowledgeSystemFragment
import kotlinx.android.synthetic.main.activity_bottom.*
import kotlinx.android.synthetic.main.app_bar_bottom.*
import kotlinx.android.synthetic.main.content_bottom.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    var knowFrag : KnowledgeSystemFragment? = null
    lateinit var homeFrag : HomeFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom)
        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.title_home)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
        main_navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        homeFrag = HomeFragment()
        addFragment(homeFrag)
    }


    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.bottom, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                toolbar.setTitle(R.string.title_home)
                addFragment(homeFrag)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                toolbar.setTitle(R.string.title_knowledge_system)
                if (knowFrag == null){
                    knowFrag = KnowledgeSystemFragment()
                }
                addFragment(knowFrag!!)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                toolbar.setTitle(R.string.title_project)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun addFragment(fragment: Fragment){

        if (!supportFragmentManager.fragments.contains(fragment)) {
            supportFragmentManager.beginTransaction()
                    .add(R.id.main_fragment, fragment)
                    .commit()
        }
        var transaction = supportFragmentManager.beginTransaction()
        for (frag in supportFragmentManager.fragments){
            if (frag.equals(fragment)){
                transaction.show(fragment)
            }else{
                transaction.hide(frag)
            }
        }
        transaction.commit()

    }

}
