package grab

import model.Item
import org.w3c.dom.Element
import org.xml.sax.InputSource
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.StringReader
import java.net.HttpURLConnection
import java.net.URL
import javax.xml.parsers.DocumentBuilderFactory

class Grabber{

    val items = listOf(Item.STONE_OF_PURITY)

    fun updateItem(item : Item){
            val response = sendRequest("http://l2on.net/?c=market&a=item&id=${item.id}&setworld=1089")

            val dbFactory = DocumentBuilderFactory.newInstance()
            dbFactory.isValidating = false
            val dBuilder = dbFactory.newDocumentBuilder()
            val xmlInput = InputSource(StringReader(response))
            val doc = dBuilder.parse(xmlInput)

            val saleTable = doc.getElementById("group_sell")
            val buyTable = doc.getElementById("group_buy")

            parseTable(saleTable)
            parseTable(buyTable)
    }

    private fun parseTable(saleTable: Element){
    }

    private fun sendRequest(url : String): String {
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