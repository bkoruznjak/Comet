package hr.from.bkoruznjak.comet.main.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.design.internal.NavigationMenu
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import hr.from.bkoruznjak.comet.R
import hr.from.bkoruznjak.comet.main.navigation.ScreenFragmentProvider
import hr.from.bkoruznjak.comet.main.navigation.ScreenFragmentProvider.Companion.HOME_SCREEN
import hr.from.bkoruznjak.comet.main.navigation.ScreenFragmentProvider.Companion.LIST_ALL_PLAYERS
import hr.from.bkoruznjak.comet.main.navigation.ScreenFragmentProvider.Companion.NEW_PLAYER
import hr.from.bkoruznjak.comet.main.viewmodel.MainViewModel
import hr.from.bkoruznjak.comet.newplayer.repository.UserEdit
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {


    private val mainViewModel: MainViewModel by viewModel()
    private val screenProvider: ScreenFragmentProvider by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        setupToolbar()
        setupNavigationView()
        setupUserScreen()
    }

    private fun setupToolbar() {
        setSupportActionBar(main_toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_menu)
            setDisplayShowTitleEnabled(false)
        }
    }

    private fun setupUserScreen() {
        screenProvider.goToScreen(mainViewModel.currentScreenId, supportFragmentManager)
    }

    @SuppressLint("RestrictedApi")
    private fun setupNavigationView() {
        main_navigation.setNavigationItemSelectedListener { menuItem ->
            menuItem.isChecked = true
            main_drawer.closeDrawers()
            mainViewModel.currentScreenId = (main_navigation.menu as NavigationMenu).findItemIndex(menuItem.itemId)
            screenProvider.goToScreen(mainViewModel.currentScreenId, supportFragmentManager)
            text_view_title.text = getString(screenProvider.getScreenTitle(mainViewModel.currentScreenId))
            true
        }
        if (mainViewModel.currentScreenId != HOME_SCREEN) {
            main_navigation.menu.getItem(mainViewModel.currentScreenId).isChecked = true
        }

        text_view_title.text = getString(screenProvider.getScreenTitle(mainViewModel.currentScreenId))
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

    override fun onResume() {
        super.onResume()
        mainViewModel.load()
    }

    override fun onBackPressed() {
        var fragment: Fragment? = supportFragmentManager.findFragmentById(R.id.screen_container)
        if (UserEdit.clickedPlayer != null) {
            backToAllPlayers()
        } else if (null == fragment) {
            super.onBackPressed()
        } else {
            if (!fragment.childFragmentManager.popBackStackImmediate()) {
                super.onBackPressed()
                finish()
            }
        }
    }

    @SuppressLint("RestrictedApi")
    fun goToPlayerDetails() {
        main_drawer.closeDrawers()
        mainViewModel.currentScreenId = NEW_PLAYER
        screenProvider.goToScreen(mainViewModel.currentScreenId, supportFragmentManager)
        text_view_title.text = "COMET Player / Edit Player"
        main_navigation.menu.getItem(mainViewModel.currentScreenId).isChecked = true
    }

    @SuppressLint("RestrictedApi")
    fun backToAllPlayers() {
        UserEdit.clickedPlayer = null
        main_drawer.closeDrawers()
        mainViewModel.currentScreenId = LIST_ALL_PLAYERS
        screenProvider.goToScreen(mainViewModel.currentScreenId, supportFragmentManager)
        text_view_title.text = getString(screenProvider.getScreenTitle(mainViewModel.currentScreenId))
        main_navigation.menu.getItem(mainViewModel.currentScreenId).isChecked = true
    }

}
