package org.lovedev.chapter_10

import org.junit.Test
import kotlin.concurrent.thread

/**
 * @author Kevin
 * @data 2018/7/16
 */
class MultiThreadTest {


    @Test()
    fun test() {
        val thread = ThreadTest()

        thread {
            thread.test01()
        }
    }

    class ThreadTest {

        @Synchronized
        fun test01() {
            println("test01")
            Thread.sleep(100)
            println("test02")
        }

        @Synchronized
        fun test02() {
            println("test02")
        }
    }
}