package repository

import android.content.Context
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import data.CardItem
import data.ItemsAndCategories
import data.Result
import org.json.JSONArray

class ItemRepository {
    private val apiUrl = "http://192.168.1.112:3000/api/"


    private fun parseItems(itemsJsonArray: JSONArray, categoriesJson: JSONArray): ItemsAndCategories {
        val items = mutableListOf<CardItem>()
        val categories = mutableListOf<String>()

        for (i in 0 until itemsJsonArray.length()) {
            val jsonObject = itemsJsonArray.getJSONObject(i)
            val id = jsonObject.getString("id")
            val picture = jsonObject.getString("picture")
            val price = jsonObject.getJSONObject("price").getDouble("amount").toFloat()
            val title = jsonObject.getString("title")
            val stateName = jsonObject.getString("state_name")

            val item = CardItem(id, picture, price, title, stateName)
            items.add(item)
        }
        for (i in 0 until categoriesJson.length()) {
            categories.add(categoriesJson[i].toString())
        }

        return ItemsAndCategories(items, categories)
    }

    fun searchItems(query: String, context: Context, callback: (Result<ItemsAndCategories>) -> Unit) {
        val queue = Volley.newRequestQueue(context)
        val url = "${apiUrl}items?q=${query}"
        val request = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                try {
                    val itemsAndCategories = parseItems(response.getJSONArray("items"),response.getJSONArray("categories"))
                    callback(Result.Success(itemsAndCategories))
                }
                catch (e: Exception) {
                    callback(Result.Error(e))
                }
            },
            { error ->
                callback(Result.Error(error))
            }
        )
        queue.add(request)
    }
}