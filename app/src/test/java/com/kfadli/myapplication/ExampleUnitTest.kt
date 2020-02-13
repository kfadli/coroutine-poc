package com.kfadli.myapplication

import kotlinx.coroutines.*
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    var controlledRunner = ControlledRunner<String>()

    private suspend fun myAsyncJob(params: String): String {
        println("Executing Request, params: $params")
        delay(2000)
        return "-----> Response{}, with params: $params"
    }

    suspend fun fetchApi(params: String): String {
        // if there's already a request running, return the result from the
        // existing request. If not, start a new request by running the block.
        return controlledRunner.joinPreviousOrRun {
            val result = myAsyncJob(params)
            result
        }
    }


    private fun launch(id: String, params: String) {
        GlobalScope.launch(Dispatchers.Default, CoroutineStart.DEFAULT, {
            println("Request $id Finished")
        }, {
            println("Start Request $id")

            val response = fetchApi(params)
            println("Response $id -> $response")
        })
    }

    @Test
    fun test1() {
        launch("1", "Test")
        launch("2", "Test1")
        launch("3", "Test2")
        launch("4", "Test3")
        launch("5", "Test")
        launch("6", "Test1")

        Thread.sleep(3000)
    }
}
