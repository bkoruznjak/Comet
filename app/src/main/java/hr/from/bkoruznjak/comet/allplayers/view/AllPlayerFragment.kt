package hr.from.bkoruznjak.comet.allplayers.view


import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import hr.from.bkoruznjak.comet.R
import hr.from.bkoruznjak.comet.allplayers.adapter.PlayerAdapter
import hr.from.bkoruznjak.comet.allplayers.viewmodel.AllPlayersViewModel
import hr.from.bkoruznjak.comet.main.fragments.CometFragment
import hr.from.bkoruznjak.comet.root.database.model.UserDTO
import kotlinx.android.synthetic.main.fragment_all_player.*
import org.jetbrains.anko.support.v4.act
import org.koin.android.viewmodel.ext.android.viewModel

class AllPlayerFragment : CometFragment() {

    private val allPlayersViewModel: AllPlayersViewModel by viewModel()
    private val playerAdapter = PlayerAdapter { clickedPlayer -> openPlayerDetails(clickedPlayer) }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayoutResourceId(), container, false)
    }

    override fun getLayoutResourceId(): Int {
        return R.layout.fragment_all_player
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycler()
        setupDataListeners()
        setupPlayerSearchListener()
    }

    private fun setupPlayerSearchListener() {
        editTextPlayerSearch.setText(allPlayersViewModel.filter, TextView.BufferType.EDITABLE)
        editTextPlayerSearch.setOnEditorActionListener { view, actionId, _ ->
            when (actionId) {
                EditorInfo.IME_ACTION_DONE -> {
                    searchFor(view)
                    true
                }
                else -> false
            }
        }
    }

    private fun searchFor(searchView: TextView) {
        allPlayersViewModel.filterPlayersByName(searchView.text)
    }

    private fun setupRecycler() {
        recyclerViewPlayers.layoutManager = LinearLayoutManager(act)
        recyclerViewPlayers.adapter = playerAdapter
        recyclerViewPlayers.itemAnimator = DefaultItemAnimator()
    }

    private fun openPlayerDetails(clickedPlayer: UserDTO) {
        Log.d("žžž", "opening details for : $clickedPlayer")
    }

    private fun setupDataListeners() {
        allPlayersViewModel.playerList.observe(this, Observer {
            it?.let {
                playerAdapter.reloadData(it)
            }
        })
    }

    override fun onResume() {
        super.onResume()
        allPlayersViewModel.load()
    }
}
