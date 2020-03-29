package jp.kaleidot725.sample.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kaleidot725.sample.data.entity.Item
import kaleidot725.sample.data.entity.User
import kaleidot725.sample.data.repository.ItemRepository
import kotlinx.coroutines.Dispatchers

class MainViewModel(private val userRepository: ItemRepository): ViewModel() {
    val items: LiveData<List<Item>> = userRepository.getItems().asLiveData(
        viewModelScope.coroutineContext + Dispatchers.IO
    )
}