package hr.from.bkoruznjak.comet.newplayer.view


import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import hr.from.bkoruznjak.comet.R
import hr.from.bkoruznjak.comet.main.fragments.CometFragment
import hr.from.bkoruznjak.comet.newplayer.model.DateDTO
import kotlinx.android.synthetic.main.fragment_new_player.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.support.v4.act

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupOnClickListeners()
    }

    private fun setupOnClickListeners() {
        imageViewDateOfBirthFrom.onClick {
            showAddNewReminderDateDialog(act) {
                editTextDateOfBirthValue.setText(it.toDateString(), TextView.BufferType.EDITABLE)
            }
        }

        editTextDateOfBirthValue.onClick {
            showAddNewReminderDateDialog(act) {
                editTextDateOfBirthValue.setText(it.toDateString(), TextView.BufferType.EDITABLE)
            }
        }

        editTextDateFromValue.onClick {
            showAddNewReminderDateDialog(act) {
                editTextDateFromValue.setText(it.toDateString(), TextView.BufferType.EDITABLE)
            }
        }

        imageViewDateFrom.onClick {
            showAddNewReminderDateDialog(act) {
                editTextDateFromValue.setText(it.toDateString(), TextView.BufferType.EDITABLE)
            }
        }

        editTextDateToValue.onClick {
            showAddNewReminderDateDialog(act) {
                editTextDateToValue.setText(it.toDateString(), TextView.BufferType.EDITABLE)
            }
        }

        imageViewDateTo.onClick {
            showAddNewReminderDateDialog(act) {
                editTextDateToValue.setText(it.toDateString(), TextView.BufferType.EDITABLE)
            }
        }
    }

    private fun showAddNewReminderDateDialog(activity: Activity, caller: (DateDTO) -> Unit) {
        val datePickerDialog = AddDateDialog(activity) { selectedDate ->
            caller(selectedDate)
        }
        datePickerDialog.window.setBackgroundDrawableResource(android.R.color.transparent)
        datePickerDialog.setCancelable(false)
        datePickerDialog.show()
    }
}
