package ua.cn.stu.foundation.utils

import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class ViewModelCreatorFactory(
    private val creator: () -> ViewModel?
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return creator() as T
    }
}

/**
 * Create view-model directly by calling its constructor.
 */
inline fun <reified VM : ViewModel> ComponentActivity.viewModelCreator(noinline creator: () -> ViewModel?): Lazy<VM> {
    return viewModels { ViewModelCreatorFactory(creator) }
}