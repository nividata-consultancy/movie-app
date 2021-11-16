package com.nividata.movie_time.domain.core.preference

import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow

/**
 * Preference Manager for the application.
 * Currently it just keeps UI mode preference such as Light mode or dark mode.
 */
@Singleton
interface PreferenceManager {
    val uiModeFlow: Flow<Boolean>

    /**
     * Updates the preference for UI mode.
     *
     * @param enable Sets Dark Mode if true otherwise Light mode.
     */
    suspend fun setDarkMode(enable: Boolean)
}
