package org.lovedev.chapter_10

import org.junit.Test
import kotlin.concurrent.thread

/**
 * @author Kevin
 * @data 2018/7/13
 */
class ThreadLocalTest {
    @Test
    fun test() {
        val threadLocal: ThreadLocal<String> = ThreadLocal()
        threadLocal.set("main")
        println(Thread.currentThread().name + " threadLocal: " + threadLocal.get())
        thread(name = "thread_001") {
            threadLocal.set("thread_001")
            println(Thread.currentThread().name + " threadLocal: " + threadLocal.get())
        }

        thread(name = "thread_002") {
            println(Thread.currentThread().name + " threadLocal: " + threadLocal.get())
        }
    }
}