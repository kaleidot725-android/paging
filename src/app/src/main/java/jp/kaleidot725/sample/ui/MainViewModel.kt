package jp.kaleidot725.sample.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import jp.kaleidot725.sample.data.entity.NetworkState
import jp.kaleidot725.sample.data.repository.ItemDataSource
import jp.kaleidot725.sample.data.repository.ItemDataSourceFactory
import kaleidot725.sample.data.entity.Item
import kotlinx.coroutines.Dispatchers

class MainViewModel(private val itemDataSourcefactory: ItemDataSourceFactory): ViewModel() {
    private val config = PagedList.Config.Builder().setInitialLoadSizeHint(10).setPageSize(10).build()
    val items: LiveData<PagedList<Item>> = LivePagedListBuilder(itemDataSourcefactory, config).build()
    val networkState: LiveData<NetworkState> = itemDataSourcefactory.source.networkState
}