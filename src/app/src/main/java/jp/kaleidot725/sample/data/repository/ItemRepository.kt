package kaleidot725.sample.data.repository

import android.util.Log
import kaleidot725.sample.data.entity.Item
import kaleidot725.sample.data.entity.nullItem
import kaleidot725.sample.data.service.QiitaService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class ItemRepository(private val service: QiitaService) {
    fun getItems(): Flow<List<Item>> {
        return flow {
            try {
                emit(service.getItems().execute().body())
            } catch (e: Exception) {
                Log.e("UserRepository", "getItems error", e)
                emit(listOf(nullItem))
            }
        }
    }
}
