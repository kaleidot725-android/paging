package kaleidot725.sample.data.service

import kaleidot725.sample.data.entity.Item
import kaleidot725.sample.data.entity.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface QiitaService {
    @GET("/api/v2/items")
    fun getItems(@Query("page")page: Int, @Query("per_page") perPage: Int): Call<List<Item>>
}