package com.puxiansheng.logic.data.event

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.lang.Thread.sleep

class EventWorker(context: Context, params: WorkerParameters) :Worker(context, params) {

    override fun doWork(): Result {
        sleep(10)
        return Result.success()
    }
}