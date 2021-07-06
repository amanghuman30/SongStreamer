package com.songstreamer.app

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.work.ListenableWorker
import androidx.work.WorkInfo
import androidx.work.testing.TestListenableWorkerBuilder
import com.google.common.truth.Truth.assertThat
import com.songstreamer.app.background.LogTrackerWorker
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TestWorker {

    @Test
    fun testCheckWorker() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext

        /*val worker = TestListenableWorkerBuilder<LogTrackerWorker>(appContext)
            .setRunAttemptCount(2)
            .build()
        val result = worker.startWork().get()
        assertThat(result).isEqualTo(ListenableWorker.Result.Success())*/
    }

}