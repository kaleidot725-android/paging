package jp.kaleidot725.sample.data.repository

import androidx.paging.DataSource
import kaleidot725.sample.data.service.QiitaService
import kaleidot725.sample.data.entity.Item

class ItemDataSourceFactory(service: QiitaService) : DataSource.Factory<Int, Item>() {
    val source = ItemDataSource(service)

    override fun create(): DataSource<Int, Item> {
        return source
    }
}