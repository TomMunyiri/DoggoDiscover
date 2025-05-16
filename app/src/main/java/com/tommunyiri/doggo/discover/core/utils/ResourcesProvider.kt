package com.tommunyiri.doggo.discover.core.utils

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ResourcesProvider(private val context: Context) {
    fun getString(resId: Int): String {
        return context.getString(resId)
    }

    fun getDrawable(resId: Int): Drawable? {
        return ContextCompat.getDrawable(context, resId)
    }
}

object ResourcesProviderHelper : KoinComponent {
    private val resourcesProvider: ResourcesProvider by inject()

    fun getString(resId: Int): String {
        return resourcesProvider.getString(resId)
    }

    fun getDrawable(resId: Int): Drawable? {
        return resourcesProvider.getDrawable(resId)
    }
}