package grab

import com.google.gson.Gson
import model.ItemInfo
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.util.*

class Harvester {
    private val REFRESH_TIME = 1000 * 60 * 5L

    fun launch() {
        Timer().schedule(object : TimerTask() {
            override fun run() {
                val fileName = "/res/ids.txt"
                val file = File(fileName)

                val ids  = if (!file.exists()) {
                    System.out.println("file in ${file.absolutePath} not found")
                    System.out.println("Test mode for id 1459")
                    listOf("1459")
                }else{
                    File(fileName).readLines()
                }

                for (item in ids) {
                    System.out.println("Updating item: $item")
                    val response = sendRequest("http://scrapper:6661/?id=$item")
                    val itemInfo = Gson().fromJson(response, ItemInfo::class.java)
                    Storage.updateItem(item, itemInfo)
                    System.out.println("Item: $item was updated")
                }
            }
        }, 0, REFRESH_TIME)
    }

    @Synchronized
    private fun sendRequest(url: String): String {
        val obj = URL(url)

        with(obj.openConnection() as HttpURLConnection) {
            BufferedReader(InputStreamReader(inputStream)).use {
                val response = StringBuffer()

                var inputLine = it.readLine()
                while (inputLine != null) {
                    response.append(inputLine)
                    inputLine = it.readLine()
                }
                return response.toString()
            }
        }
    }
}