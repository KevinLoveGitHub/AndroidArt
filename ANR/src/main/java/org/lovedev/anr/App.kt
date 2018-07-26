package org.lovedev.anr

import android.app.Application
import android.os.StrictMode
import com.github.moduth.blockcanary.BlockCanary




/**
 * @author Kevin
 * @data 2018/7/18
 */
class App : Application() {
    override fun onCreate() {
        StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build())
        StrictMode.setVmPolicy(StrictMode.VmPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .penaltyDeath()
                .build())
        super.onCreate()
        BlockCanary.install(this, AppBlockCanaryContext()).start()
    }
}