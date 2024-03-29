package com.fedorov.andrii.andriiovych.imagesearch.data.repositories

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.fedorov.andrii.andriiovych.imagesearch.domain.repositories.SettingsPrefRepository
import com.fedorov.andrii.andriiovych.imagesearch.presentation.screens.settingsscreen.ImageColor
import com.fedorov.andrii.andriiovych.imagesearch.presentation.screens.settingsscreen.ImageOrientation
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


private const val SETTINGS_PREFERENCE_NAME = "settings_preferences"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = SETTINGS_PREFERENCE_NAME
)

class SettingsPrefRepositoryImpl @Inject constructor(@ApplicationContext val context: Context) :
    SettingsPrefRepository {

    override val imageOrientationSettings: Flow<ImageOrientation> = context.dataStore.data.map { pref ->
        pref[IMAGE_ORIENTATION_PREF] ?: IMAGE_ORIENTATION_DEFAULT
    }.map { when(it){
        ImageOrientation.LANDSCAPE.value -> ImageOrientation.LANDSCAPE
        ImageOrientation.PORTRAIT.value -> ImageOrientation.PORTRAIT
        else -> ImageOrientation.PORTRAIT
    } }

    override val imageColorSettings: Flow<ImageColor> = context.dataStore.data.map { pref ->
        pref[IMAGE_COLOR_PREF] ?: IMAGE_COLOR_DEFAULT
    }.map {
        when(it){
            ImageColor.RED.value->ImageColor.RED
            ImageColor.ORANGE.value->ImageColor.ORANGE
            ImageColor.YELLOW.value->ImageColor.YELLOW
            ImageColor.GREEN.value->ImageColor.GREEN
            ImageColor.TURQUOISE.value->ImageColor.TURQUOISE
            ImageColor.BLUE.value->ImageColor.BLUE
            ImageColor.VIOLET.value->ImageColor.VIOLET
            ImageColor.PINK.value->ImageColor.PINK
            ImageColor.BROWN.value->ImageColor.BROWN
            ImageColor.Black.value->ImageColor.Black
            ImageColor.GRAY.value->ImageColor.GRAY
            ImageColor.WHITE.value->ImageColor.WHITE
            else -> ImageColor.EMPTY
        }
    }

    override val imageSizeSettings: Flow<String> = context.dataStore.data.map { pref ->
        pref[IMAGE_SIZE_PREF] ?: IMAGE_SIZE_DEFAULT
    }

    override suspend fun saveOrientationSettings(value: String) {
        context.dataStore.edit { pref ->
            pref[IMAGE_ORIENTATION_PREF] = value
        }
    }

    override suspend fun saveColorSettings(value: String) {
        context.dataStore.edit { pref ->
            pref[IMAGE_COLOR_PREF] = value
        }
    }

    override suspend fun saveSizeSettings(value: String) {
        context.dataStore.edit { pref ->
            pref[IMAGE_SIZE_PREF] = value
        }
    }

    private companion object {
        private const val IMAGE_ORIENTATION_DEFAULT = "Portrait"
        val IMAGE_ORIENTATION_PREF = stringPreferencesKey("image_orientation")
        private const val IMAGE_COLOR_DEFAULT = "White"
        val IMAGE_COLOR_PREF = stringPreferencesKey("image_color")
        private const val IMAGE_SIZE_DEFAULT = "Medium"
        val IMAGE_SIZE_PREF = stringPreferencesKey("image_size")
    }
}