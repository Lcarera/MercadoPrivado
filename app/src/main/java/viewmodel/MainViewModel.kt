package viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import data.ItemsAndCategories
import data.Result
import repository.ItemRepository

class MainViewModel : ViewModel() {
    private val repository = ItemRepository()
    private val _itemsAndCategories = MutableLiveData<List<ItemsAndCategories>>()
    val itemsAndCategories: MutableLiveData<List<ItemsAndCategories>> = _itemsAndCategories


    fun searchItems(query: String, context: Context) {
        repository.searchItems(query, context) { result ->
            when (result) {
                is Result.Success -> _itemsAndCategories.value = listOf(result.data)
                is Result.Error -> println("Error fetching data: ${result.exception.message}")
            }
        }
    }
}