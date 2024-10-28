package id.ac.polbeng.jumiatiriyana.threadrunnable

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class MyWorker(context: Context, workerParams: WorkerParameters) :
    CoroutineWorker(context, workerParams) {

    private val TAG: String = MyWorker::class.java.name

    companion object {
        const val COUNTER = "counter"
        const val PROGRESS = "progress"
        const val MESSAGE = "message"
    }

    override suspend fun doWork(): Result {
        val counter = inputData.getInt(COUNTER, 0)
        Log.d(TAG, "Start counter...")

        for (i in 1..counter) {
            Log.d(TAG, "Counter-$i")
            setProgress(workDataOf(PROGRESS to i))

            // Ganti Thread.sleep() dengan delay()
            withContext(Dispatchers.IO) {
                delay(1000) // gunakan delay() dalam coroutine
            }
        }

        Log.d(TAG, "Finish counter!")
        val outputData = workDataOf(MESSAGE to "Finish counter!")
        return Result.success(outputData)
    }
}
