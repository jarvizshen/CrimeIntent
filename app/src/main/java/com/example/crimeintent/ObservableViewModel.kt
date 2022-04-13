package com.example.crimeintent
import androidx.databinding.Observable
import androidx.databinding.Observable.OnPropertyChangedCallback
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.ViewModel

open class ObservableViewModel(private val callbacks: PropertyChangeRegistry = PropertyChangeRegistry())
    : ViewModel(), Observable {
    override fun addOnPropertyChangedCallback(callback: OnPropertyChangedCallback) =
            callbacks.add(callback)

    override fun removeOnPropertyChangedCallback(callback: OnPropertyChangedCallback) =
            callbacks.remove(callback)

    /**
     * Notifies listeners that all properties of this instance have changed.
     */
    fun notifyChange() = callbacks.notifyCallbacks(this, 0, null)

    /**
     * Notifies listeners that a specific property has changed. The getter for the property
     * that changes should be marked with [Bindable] to generate a field in
     * `BR` to be used as `fieldId`.
     *
     * @param fieldId The generated BR id for the Bindable field.
     */
    fun notifyPropertyChanged(fieldId: Int) = callbacks.notifyCallbacks(this, fieldId, null)
}
