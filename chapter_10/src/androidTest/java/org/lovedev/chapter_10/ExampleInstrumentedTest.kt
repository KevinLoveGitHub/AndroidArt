package org.lovedev.chapter_10

import android.os.Handler
import android.support.test.InstrumentationRegistry
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.eq
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.stubbing.Answer


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(MockitoJUnitRunner::class)
class ExampleInstrumentedTest {

    @Test
    fun useAppContext() {

        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("org.lovedev.chapter_10", appContext.packageName)
    }

    @Test
    fun test1() {
        println(100001)
    }



    @Test
    fun test() {
        val handler = mock(Handler::class.java)
        Mockito.`when`(handler.sendEmptyMessage(eq(anyInt()))).thenAnswer(Answer<Int> {
            val runnable = it.arguments[0]
            println(runnable)
            return@Answer null
        })
        println(10001)
        handler.sendEmptyMessage(1001)
//        handler?.post(Runnable {
//            val i = 10
//        })
    }
}
