package hr.from.bkoruznjak.comet.main.navigation

import android.support.v4.app.FragmentManager
import hr.from.bkoruznjak.comet.R
import hr.from.bkoruznjak.comet.allplayers.view.AllPlayerFragment
import hr.from.bkoruznjak.comet.home.view.HomeFragment
import hr.from.bkoruznjak.comet.main.fragments.CometFragment
import hr.from.bkoruznjak.comet.newplayer.view.NewPlayerFragment

class ScreenFragmentProvider {

    /**
     * Goes to the desired screen fragment
     * When adding new fragments (screens) to the app
     * make sure you also list them here and assign an ID
     */
    fun goToScreen(screenId: Int, fragmentManager: FragmentManager) {
        val screenFragment = getFragment(screenId)
        val fragmentTag = screenFragment.getNametag()
        fragmentManager.popBackStackImmediate()
        fragmentManager.beginTransaction().replace(DEFAULT_SCREEN_CONTAINER_ID, screenFragment)
            .addToBackStack(fragmentTag)
            .commit()
    }

    /**
     * Helper method that resolved a new fragment when given a valid screen ID
     */
    private fun getFragment(screenId: Int): CometFragment {
        return when (screenId) {
            HOME_SCREEN -> HomeFragment()
            LIST_ALL_PLAYERS -> AllPlayerFragment()
            NEW_PLAYER -> NewPlayerFragment()
            else -> throw IllegalArgumentException("Unknown screen ID")
        }
    }

    //Add all screens here with 0 based index values as they are added in menu_main_navigation
    companion object {
        const val DEFAULT_SCREEN_CONTAINER_ID = R.id.screen_container
        const val HOME_SCREEN = 2
        const val LIST_ALL_PLAYERS = 1
        const val NEW_PLAYER = 0
    }
}
