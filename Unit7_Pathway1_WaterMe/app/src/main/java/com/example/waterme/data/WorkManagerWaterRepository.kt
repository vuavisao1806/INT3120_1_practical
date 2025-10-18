package com.example.waterme.data

import android.content.Context
import androidx.work.Data
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.waterme.model.Plant
import com.example.waterme.worker.WaterReminderWorker
import java.util.concurrent.TimeUnit

class WorkManagerWaterRepository(context: Context) : WaterRepository {
    private val workManager = WorkManager.getInstance(context)

    override val plants: List<Plant>
        get() = DataSource.plants

    override fun scheduleReminder(duration: Long, unit: TimeUnit, plantName: String) {
        val data = Data.Builder().putString(
            key = WaterReminderWorker.nameKey,
            value = "$plantName after $duration ${unit.toString().lowercase()} waiting!"
        ).build()
        val waterReminderBuilder = OneTimeWorkRequestBuilder<WaterReminderWorker>()
            .setInputData(
                inputData = data
            )
            .setInitialDelay(
                duration = duration,
                timeUnit = unit
            )
            .build()
        workManager.enqueueUniqueWork(
            uniqueWorkName = "$plantName $duration ${unit.toString().lowercase()}",
            existingWorkPolicy = ExistingWorkPolicy.REPLACE,
            request = waterReminderBuilder
        )
    }
}
