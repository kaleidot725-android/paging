package jp.kaleidot725.sample.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import jp.kaleidot725.sample.data.entity.NetworkState
import kaleidot725.sample.data.service.QiitaService
import kaleidot725.sample.data.entity.Item
import java.lang.Exception

class ItemDataSource(private val service: QiitaService) : PageKeyedDataSource<Int, Item>() {
    private val _networkState = MutableLiveData<NetworkState>()
    val networkState = _networkState

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Item>) {

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Item>) {
        Log.v("TAG", "${params.key}")
        callAPI(params.key, params.requestedLoadSize) { items, next ->
            callback.onResult(items, next)
        }
    }

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Item>) {
        callAPI(1, params.requestedLoadSize) { items, next ->
            callback.onResult(items, null, next)
        }
    }

    private fun callAPI(page: Int, perPage: Int, callback: (items: List<Item>, next: Int?) -> Unit) {
        _networkState.postValue(NetworkState.RUNNING)
        try {
            val response = service.getItems(page, perPage).execute().body()
            callback(response, page + 1)
            _networkState.postValue(NetworkState.SUCCESS)
        } catch (e: Exception) {
            _networkState.postValue(NetworkState.FAILED)
        }
    }
}