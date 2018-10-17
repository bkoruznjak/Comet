package hr.from.bkoruznjak.comet.newplayer.view

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import android.view.HapticFeedbackConstants
import hr.from.bkoruznjak.comet.R
import hr.from.bkoruznjak.comet.newplayer.model.DateDTO
import kotlinx.android.synthetic.main.layout_add_date_dialog.*

class AddDateDialog(activity: Activity, private val selectedDate: (DateDTO) -> Unit) : Dialog(activity) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_add_date_dialog)
        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        image_view_add_date_close.setOnClickListener {
            it.performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP)
            dismiss()
        }

        image_view_add_date_confirm.setOnClickListener {
            it.performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP)
            selectedDate(DateDTO(
                    date_picker_add.dayOfMonth,
                    date_picker_add.month,
                    date_picker_add.year))
            dismiss()
        }
    }
}