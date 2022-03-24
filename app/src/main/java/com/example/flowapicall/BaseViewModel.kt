package com.example.flowapicall

import androidx.lifecycle.ViewModel
import javax.inject.Inject

abstract class BaseViewModel : ViewModel() {
    /**Inject Singleton ErrorManager
     * Use this errorManager to get the Errors
     */
    @Inject
    lateinit var errorManager: com.example.flowapicall.error_utils.ErrorManager
}
