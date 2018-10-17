package hr.from.bkoruznjak.comet.allplayers.adapter

import android.support.annotation.MainThread
import android.support.v7.widget.RecyclerView
import android.view.HapticFeedbackConstants
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import hr.from.bkoruznjak.comet.R
import hr.from.bkoruznjak.comet.root.database.model.UserDTO
import kotlinx.android.synthetic.main.item_player_card.view.*

class PlayerAdapter(private val itemClick: (UserDTO) -> Unit) : RecyclerView.Adapter<PlayerAdapter.PlayerHolder>() {

    private val playerData: ArrayList<UserDTO> = ArrayList()

    @MainThread
    fun ViewGroup.inflate(layoutRes: Int): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, false)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerHolder {
        val view: View = parent.inflate(R.layout.item_player_card)
        return PlayerAdapter.PlayerHolder(view, itemClick)
    }

    fun reloadData(newPlayerData: List<UserDTO>) {
        playerData.clear()
        playerData.addAll(newPlayerData)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = playerData.count()

    override fun onBindViewHolder(holder: PlayerAdapter.PlayerHolder, position: Int) {
        val offerDataEntry = playerData[position]
        holder.bind(offerDataEntry)
    }

    class PlayerHolder(private val view: View,
                       private val itemClick: (UserDTO) -> Unit) :
            RecyclerView.ViewHolder(view) {

        fun bind(player: UserDTO) {
            view.textViewPlayerNameValue.text = "${player.firstName} ${player.lastName}"
            view.textViewClubNameValue.text = "${player.club}"
            view.textViewDateFromDateToValue.text = "Date from: ${player.dateFrom} Date to: ${player.dateTo}"
            view.setOnClickListener {
                it.performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP)
                itemClick(player)
            }
        }
    }
}