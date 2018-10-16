package hr.from.bkoruznjak.comet

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.internal.NavigationMenu
import android.support.v4.view.GravityCompat
import android.view.MenuItem
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init(){
        setupToolbar()
        setupNavigationView()
    }

    private fun setupToolbar() {
        setSupportActionBar(main_toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_menu)
            setDisplayShowTitleEnabled(false)
        }
    }

    @SuppressLint("RestrictedApi")
    private fun setupNavigationView() {
        main_navigation.setNavigationItemSelectedListener { menuItem ->
            menuItem.isChecked = true
            main_drawer.closeDrawers()
            //mainViewModel.currentScreenId = (main_navigation.menu as NavigationMenu).findItemIndex(menuItem.itemId)
            //screenProvider.goToScreen(mainViewModel.currentScreenId, supportFragmentManager)
            true
        }
        //main_navigation.menu.getItem(mainViewModel.currentScreenId).isChecked = true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                main_drawer.openDrawer(GravityCompat.START)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}
