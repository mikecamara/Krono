package com.example.kronosstopwatch

import android.os.Bundle
import android.text.InputType
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.EditTextPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager

class SettingsActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.settings, SettingsFragment())
            .commit()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    class SettingsFragment : PreferenceFragmentCompat() {

        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {

            setPreferencesFromResource(R.xml.root_preferences, rootKey)

            val editTextPreference: EditTextPreference? =
                preferenceManager.findPreference<EditTextPreference>("minutes")
            if (editTextPreference != null) {
                editTextPreference.setOnBindEditTextListener { editText ->
                    editText.inputType = InputType.TYPE_CLASS_NUMBER
                }
            }

            val editTextPreferenceSeconds: EditTextPreference? =
                preferenceManager.findPreference<EditTextPreference>("seconds")
            if (editTextPreferenceSeconds != null) {
                editTextPreferenceSeconds.setOnBindEditTextListener { editText ->
                    editText.inputType = InputType.TYPE_CLASS_NUMBER
                }
            }

            val editTextPreferenceMinutesBreak: EditTextPreference? =
                preferenceManager.findPreference<EditTextPreference>("minutesBreak")
            if (editTextPreferenceMinutesBreak != null) {
                editTextPreferenceMinutesBreak.setOnBindEditTextListener { editText ->
                    editText.inputType = InputType.TYPE_CLASS_NUMBER
                }
            }

            val editTextPreferenceSecondsBreak: EditTextPreference? =
                preferenceManager.findPreference<EditTextPreference>("secondsBreak")
            if (editTextPreferenceSecondsBreak != null) {
                editTextPreferenceSecondsBreak.setOnBindEditTextListener { editText ->
                    editText.inputType = InputType.TYPE_CLASS_NUMBER
                }
            }

        }



        override fun onPreferenceTreeClick(preference: Preference): Boolean {
            return when (preference.key) {
                "restart" -> {
//                    (preference as SwitchPreferenceCompat).isChecked = false
//
//                    activity?.let {
//                        AlertDialog.Builder(it)
//                            .setTitle("Coming Soon")
//                            .setMessage("This feature shall be available in coming releases.")
//                            .setPositiveButton("Support Us") { dialog, whichButton ->
//
//                            }
//                            .setNegativeButton(android.R.string.cancel, null)
//                            .show()
//                    }
                true
                }
                else -> {
                    super.onPreferenceTreeClick(preference)
                }
            }
        }


    }



}