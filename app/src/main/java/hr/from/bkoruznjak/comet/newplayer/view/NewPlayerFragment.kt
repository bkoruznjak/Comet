package hr.from.bkoruznjak.comet.newplayer.view


import android.app.Activity
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.view.HapticFeedbackConstants
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

import hr.from.bkoruznjak.comet.R
import hr.from.bkoruznjak.comet.main.fragments.CometFragment
import hr.from.bkoruznjak.comet.newplayer.model.DateDTO
import hr.from.bkoruznjak.comet.newplayer.repository.GeoRepository
import hr.from.bkoruznjak.comet.newplayer.repository.UserEdit
import hr.from.bkoruznjak.comet.newplayer.viewmodel.NewPlayerViewModel
import kotlinx.android.synthetic.main.fragment_new_player.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.support.v4.act
import org.jetbrains.anko.support.v4.toast
import org.koin.android.viewmodel.ext.android.viewModel

class NewPlayerFragment : CometFragment() {

    private val newPlayerViewModel: NewPlayerViewModel  by viewModel()
    private var overrideBackPress: Boolean = false

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
        setupLiveDataListeners()
    }

    private fun setupLiveDataListeners() {
        newPlayerViewModel.clubs.observe(this, Observer {
            it?.let {
                val clubAdapter = ArrayAdapter<String>(act, android.R.layout.simple_spinner_item, it)
                spinnerClubSelector.adapter = clubAdapter
            }
        })

        newPlayerViewModel.countries.observe(this, Observer {
            it?.let {
                val countryAdapter = ArrayAdapter<String>(act, android.R.layout.simple_spinner_item, it)
                spinnerCountrySelector.adapter = countryAdapter
            }
        })

        newPlayerViewModel.newPlayer.observe(this, Observer {
            it?.let {
                //unlock edit button
                toast("player ${it.firstName} ${it.lastName} saved")
                editTextIdValue.setText("${it.id}", TextView.BufferType.EDITABLE)
                buttonSave.isEnabled = false
                buttonEdit.isEnabled = true
            }
        })

        newPlayerViewModel.existingPlayer.observe(this, Observer {
            it?.let {
                toast("player ${it.firstName} ${it.lastName} updated")
                editTextIdValue.setText("${it.id}", TextView.BufferType.EDITABLE)
            }
        })

        newPlayerViewModel.playerForView.observe(this, Observer {
            it?.let {
                editTextIdValue.setText("${it.id}", TextView.BufferType.EDITABLE)
                editTextUniqueIdValue.setText("${it.uniqueId}", TextView.BufferType.EDITABLE)
                editTextFirstNameValue.setText("${it.firstName}", TextView.BufferType.EDITABLE)
                editTextLastNameValue.setText("${it.lastName}", TextView.BufferType.EDITABLE)
                editTextDateOfBirthValue.setText("${it.dateOfBirth}", TextView.BufferType.EDITABLE)
                editTextPlaceOfBirthValue.setText("${it.placeOfBirth}", TextView.BufferType.EDITABLE)
                spinnerCountrySelector.setSelection(GeoRepository.countryList.indexOf(it.country))
                spinnerClubSelector.setSelection(GeoRepository.countryList.indexOf(it.club))
                editTextDateFromValue.setText("${it.dateFrom}", TextView.BufferType.EDITABLE)
                editTextDateToValue.setText("${it.dateTo}", TextView.BufferType.EDITABLE)
                buttonSave.isEnabled = false
                buttonEdit.isEnabled = true
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        UserEdit.clickedPlayer?.let {
            overrideBackPress = true
        }
    }

    override fun onResume() {
        super.onResume()
        newPlayerViewModel.load()
        UserEdit.clickedPlayer?.let {
            newPlayerViewModel.loadExistingPlayer(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        overrideBackPress = false
    }

    private fun setupOnClickListeners() {
        buttonEdit.isEnabled = false
        imageViewDateOfBirthFrom.onClick {
            it?.performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP)
            showAddNewReminderDateDialog(act) {
                editTextDateOfBirthValue.setText(it.toDateString(), TextView.BufferType.EDITABLE)
            }
        }

        editTextDateOfBirthValue.onClick {
            it?.performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP)
            showAddNewReminderDateDialog(act) {
                editTextDateOfBirthValue.setText(it.toDateString(), TextView.BufferType.EDITABLE)
            }
        }

        editTextDateFromValue.onClick {
            it?.performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP)
            showAddNewReminderDateDialog(act) {
                editTextDateFromValue.setText(it.toDateString(), TextView.BufferType.EDITABLE)
            }
        }

        imageViewDateFrom.onClick {
            it?.performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP)
            showAddNewReminderDateDialog(act) {
                editTextDateFromValue.setText(it.toDateString(), TextView.BufferType.EDITABLE)
            }
        }

        editTextDateToValue.onClick {
            it?.performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP)
            showAddNewReminderDateDialog(act) {
                editTextDateToValue.setText(it.toDateString(), TextView.BufferType.EDITABLE)
            }
        }

        imageViewDateTo.onClick {
            it?.performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP)
            showAddNewReminderDateDialog(act) {
                editTextDateToValue.setText(it.toDateString(), TextView.BufferType.EDITABLE)
            }
        }

        buttonSave.onClick {
            it?.performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP)
            tryToSavePlayer()
        }

        buttonEdit.onClick {
            it?.performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP)
            updatePlayer()
        }
    }

    private fun tryToSavePlayer() {
        if (hasAllFieldsAssigned()) {
            newPlayerViewModel.registerNewPlayer(
                    editTextUniqueIdValue.text.toString(),
                    editTextFirstNameValue.text.toString(),
                    editTextLastNameValue.text.toString(),
                    editTextDateOfBirthValue.text.toString(),
                    editTextPlaceOfBirthValue.text.toString(),
                    spinnerCountrySelector.selectedItem.toString(),
                    spinnerClubSelector.selectedItem.toString(),
                    editTextDateFromValue.text.toString(),
                    editTextDateToValue.text.toString()
            )
        }
    }

    private fun updatePlayer() {
        if (hasAllFieldsAssigned()) {
            newPlayerViewModel.updateExistingPlayer(
                    editTextIdValue.text.toString(),
                    editTextUniqueIdValue.text.toString(),
                    editTextFirstNameValue.text.toString(),
                    editTextLastNameValue.text.toString(),
                    editTextDateOfBirthValue.text.toString(),
                    editTextPlaceOfBirthValue.text.toString(),
                    spinnerCountrySelector.selectedItem.toString(),
                    spinnerClubSelector.selectedItem.toString(),
                    editTextDateFromValue.text.toString(),
                    editTextDateToValue.text.toString()
            )
        }
    }

    private fun hasAllFieldsAssigned(): Boolean {
        //EXTRACT STRINGS TO RESOURCES
        when {
            editTextUniqueIdValue.text.isNullOrBlank() -> toast("You need to assign an id")
            editTextFirstNameValue.text.isNullOrBlank() -> toast("You need to assign a first name")
            editTextLastNameValue.text.isNullOrBlank() -> toast("You need to assign a last name")
            editTextDateOfBirthValue.text.isNullOrBlank() -> toast("You need to assign a date of birth")
            editTextPlaceOfBirthValue.text.isNullOrBlank() -> toast("You need to assign a place of birth")
            editTextDateFromValue.text.isNullOrBlank() -> toast("You need to assign a date from")
            editTextDateToValue.text.isNullOrBlank() -> toast("You need to assign a date to")
            else -> return true
        }
        return false
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
