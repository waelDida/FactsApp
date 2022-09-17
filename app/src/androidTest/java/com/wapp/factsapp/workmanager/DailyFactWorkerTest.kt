package com.wapp.factsapp.workmanager

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.work.ListenableWorker
import androidx.work.testing.TestListenableWorkerBuilder
import kotlinx.coroutines.runBlocking
import org.hamcrest.Matchers.`is`
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class DailyFactWorkerTest{
    private lateinit var context: Context

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp(){
        context = ApplicationProvider.getApplicationContext()
    }

    @Test
    fun testWorker(){
        val worker = TestListenableWorkerBuilder<DailyFactWorker>(context).build()
        runBlocking {
            val result = worker.doWork()
            assertThat(result,`is`(ListenableWorker.Result.success()))
        }
    }
}