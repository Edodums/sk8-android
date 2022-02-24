package unibo.it.controls_api.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel

abstract class ControlsViewModel(application: Application) : AndroidViewModel(application) {
    /**
     * It speaks directly with the peripheral device and tells to it to run for his life
     */
    abstract fun speedHandler(value: Float)
}