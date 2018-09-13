package com.google.firebase.example.testlab.kotlin

import android.provider.Settings
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.google.android.libraries.cloudtesting.screenshots.ScreenShotter
import java.io.FileNotFoundException

class MainActivity : AppCompatActivity() {

    companion object {
        private val TAG = "MainActivity"
    }

    fun checkEnvironment() {
        // [START ftl_check_env]
        val testLabSetting = Settings.System.getString(contentResolver, "firebase.test.lab")
        if ("true" == testLabSetting) {
            // Do something when running in Test Lab
            // ...
        }
        // [END ftl_check_env]
    }

    fun gameCheckIntent() {
        // [START ftl_game_check_intent]
        val launchIntent = intent
        if (launchIntent.action == "com.google.intent.action.TEST_LOOP") {
            val scenario = launchIntent.getIntExtra("scenario", 0)
            // Code to handle your game loop here
        }
        // [END ftl_game_check_intent]
    }

    fun gameFinish() {
        val yourActivity = this
        // [START ftl_game_finish]
        yourActivity.finish()
        // [END ftl_game_finish]
    }

    fun gameOutputFile() {
        // [START ftl_game_output_file]
        val launchIntent = intent
        val logFile = launchIntent.data
        if (logFile != null) {
            Log.i(TAG, "Log file " + logFile.encodedPath!!)
            // ...
        }
        // [END ftl_game_output_file]
    }

    @Throws(FileNotFoundException::class)
    fun gameOutputFileDescriptor() {
        // [START ftl_game_output_fd]
        val launchIntent = intent
        val logFile = launchIntent.data
        var fd = -1
        if (logFile != null) {
            Log.i(TAG, "Log file " + logFile.encodedPath!!)
            try {
                fd = contentResolver
                        .openAssetFileDescriptor(logFile, "w")!!
                        .parcelFileDescriptor
                        .fd
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
                fd = -1
            } catch (e: NullPointerException) {
                e.printStackTrace()
                fd = -1
            }

        }

        // C++ code invoked here.
        // native_function(fd);
        // [END ftl_game_output_fd]
    }

    fun gameScenario() {
        // [START ftl_game_scenario]
        val launchIntent = intent
        val scenario = launchIntent.getIntExtra("scenario", 0)
        // [END ftl_game_scenario]
    }

    fun takeScreenshot() {
        // [START ftl_take_screenshot]
        ScreenShotter.takeScreenshot("main_screen_2", this /* activity */)
        // [END ftl_take_screenshot]
    }
}
