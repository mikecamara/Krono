package com.example.kronosstopwatch

import android.content.res.Configuration
import android.media.MediaPlayer
import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.widget.Chronometer
import android.widget.Chronometer.OnChronometerTickListener
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_first.*


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    var lastPause: Long = 0
    var counter: Int = 0
    var isWorking = false
    var isOnBreak = false


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

//        // Checks the orientation of the screen
//        if (newConfig.orientation === Configuration.ORIENTATION_LANDSCAPE) {
//            Toast.makeText(this.context, "landscape", Toast.LENGTH_SHORT).show()
//        } else if (newConfig.orientation === Configuration.ORIENTATION_PORTRAIT) {
//            Toast.makeText(this.context, "portrait", Toast.LENGTH_SHORT).show()
//        }
    }

    fun startCrono(chronometer: Chronometer){
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.context /* Activity context */)
        val minutes = sharedPreferences.getString("minutes", "")
        val seconds = sharedPreferences.getString("seconds", "")

        val minutesBreak = sharedPreferences.getString("minutesBreak", "")
        val secondsBreak = sharedPreferences.getString("secondsBreak", "")


        var restart =  sharedPreferences.getBoolean ("restart", false)

        var cronoBreak =  sharedPreferences.getBoolean("break", false)
        var mediaPlayer: MediaPlayer? = MediaPlayer.create(context, R.raw.alarm)

        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.start()
        isWorking = true
        chronometer.setOnChronometerTickListener(OnChronometerTickListener { chronometer ->
            if (restart){
                val time = chronometer.text.toString()
                val timeString = minutes + ":" + seconds

                val breakTimeString = minutesBreak + ":" + secondsBreak

                if (isOnBreak){
                    if (time == breakTimeString) {
                        mediaPlayer?.start()
                        counter = 0
                        chronometer.setOnChronometerTickListener(null);
                        chronometer.stop();
                        chronometer.setBase(SystemClock.elapsedRealtime());
                        println("Yes it is 11")
                        if (cronoBreak){
                            isOnBreak = false
                            startCrono(chronometer)
                        }
                    }
                } else {
                    if (time == timeString) {
                        mediaPlayer?.start()
                        counter = 0
                        chronometer.setOnChronometerTickListener(null);
                        chronometer.stop();
                        chronometer.setBase(SystemClock.elapsedRealtime());
                        println("Yes it is 11")
                        if (cronoBreak){
                            isOnBreak = true
                            startCrono(chronometer)
                        }
                    }
                }
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.context /* Activity context */)
        val minutes = sharedPreferences.getString("minutes", "")
        val seconds = sharedPreferences.getString("seconds", "")
        val minutesBreak = sharedPreferences.getString("minutesBreak", "")
        val secondsBreak = sharedPreferences.getString("secondsBreak", "")
        var restart =  sharedPreferences.getBoolean ("restart", false)
        var cronoBreak =  sharedPreferences.getBoolean("break", false)
        var mediaPlayer: MediaPlayer? = MediaPlayer.create(context, R.raw.alarm)
        val meter = c_meter

        fab.setOnClickListener { view ->



            if (isWorking){
                pauseImage.animate().alpha(0.4f).setDuration(300).setInterpolator(AccelerateInterpolator()).withEndAction { pauseImage.animate().alpha(0f).setDuration(300).setInterpolator(
                    AccelerateInterpolator()
                ).start()}.start()
                fab.setImageResource(android.R.drawable.ic_media_play)


            } else {
                playImage.animate().alpha(0.4f).setDuration(300).setInterpolator(AccelerateInterpolator()).withEndAction { playImage.animate().alpha(0f).setDuration(300).setInterpolator(
                    AccelerateInterpolator()
                ).start()}.start()
                fab.setImageResource(android.R.drawable.ic_media_pause)
            }


            if (!isWorking) {
                if (counter == 0){
                    meter.setBase(SystemClock.elapsedRealtime());
                    meter.start()
                } else {
                    meter.setBase(meter.getBase() + SystemClock.elapsedRealtime() - lastPause);
                    meter.start();
                }
                isWorking = true
            } else {
                lastPause = SystemClock.elapsedRealtime();
                meter.stop();
                isWorking = false
            }
            counter++
//            view.animate().alpha(1f).setDuration(1000).setInterpolator(AccelerateInterpolator()).start()


//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
////                    .setAction("Action", null).show()
        }

        meter.setOnChronometerTickListener(OnChronometerTickListener { chronometer ->
            if (restart){
                val time = chronometer.text.toString()
                val timeString = minutes + ":" + seconds
                val breakTimeString = minutesBreak + ":" + secondsBreak
                if (isOnBreak){
                    if (time == breakTimeString) {
                        mediaPlayer?.start()
                        counter = 0
                        chronometer.setOnChronometerTickListener(null);
                        chronometer.stop();
                        chronometer.setBase(SystemClock.elapsedRealtime());
                        println("Yes it is 11")
                        if (cronoBreak){
                            isOnBreak = false
                            startCrono(chronometer)
                        }
                    }
                } else {
                    if (time == timeString) {
                        mediaPlayer?.start()
                        counter = 0
                        chronometer.setOnChronometerTickListener(null);
                        chronometer.stop();
                        chronometer.setBase(SystemClock.elapsedRealtime());
                        println("Yes it is 11")
                        if (cronoBreak){
                            isOnBreak = true
                            startCrono(chronometer)
                        }
                    }
                }
            }
        })

        view.findViewById<ConstraintLayout>(R.id.constraintLayoutMain).setOnClickListener {

        if (isWorking){
            pauseImage.animate().alpha(0.4f).setDuration(300).setInterpolator(AccelerateInterpolator()).withEndAction { pauseImage.animate().alpha(0f).setDuration(300).setInterpolator(
                AccelerateInterpolator()
            ).start()}.start()
            fab.setImageResource(android.R.drawable.ic_media_play)


        } else {
            playImage.animate().alpha(0.4f).setDuration(300).setInterpolator(AccelerateInterpolator()).withEndAction { playImage.animate().alpha(0f).setDuration(300).setInterpolator(
                AccelerateInterpolator()
            ).start()}.start()
            fab.setImageResource(android.R.drawable.ic_media_pause)
        }


            if (!isWorking) {
                if (counter == 0){
                    meter.setBase(SystemClock.elapsedRealtime());
                    meter.start()
                } else {
                    meter.setBase(meter.getBase() + SystemClock.elapsedRealtime() - lastPause);
                    meter.start();
                }
                isWorking = true
            } else {
                lastPause = SystemClock.elapsedRealtime();
                meter.stop();
                isWorking = false
            }
            counter++

        }

        view.setOnLongClickListener(object: View.OnLongClickListener {
            override fun onLongClick(v: View?): Boolean {

                stopImage.animate().alpha(0.4f).setDuration(300).setInterpolator(AccelerateInterpolator()).withEndAction { stopImage.animate().alpha(0f).setDuration(300).setInterpolator(
                    AccelerateInterpolator()
                ).start()}.start()
                fab.setImageResource(android.R.drawable.ic_media_play)
                counter = 0
                meter.stop()
                meter.setBase(SystemClock.elapsedRealtime())
                return true
            }
        })
    }
}
