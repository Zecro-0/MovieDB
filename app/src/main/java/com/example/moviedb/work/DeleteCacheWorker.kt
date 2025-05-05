package com.example.moviedb.work

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.moviedb.MovieDBApplication

class DeleteCacheWorker(appContext: Context, workerParams: WorkerParameters):
    CoroutineWorker(appContext, workerParams) {
    override suspend fun doWork(): Result {
        try {
            val application = applicationContext as MovieDBApplication
            application.db.clearAllTables()
            Log.i("DeleteCacheWorker", "Cache cleared")
            return Result.success()
        } catch (e: Exception) {
            Log.e("DeleteCacheWorker", "Error clearing cache", e)
            return Result.failure()
        }
    }
}