package hr.from.bkoruznjak.comet.newplayer.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import hr.from.bkoruznjak.comet.R
import hr.from.bkoruznjak.comet.main.fragments.CometFragment

class NewPlayerFragment : CometFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayoutResourceId(), container, false)
    }

    override fun getLayoutResourceId(): Int {
        return R.layout.fragment_new_player
    }
}
