package com.example.naviassignment.repository

import com.example.naviassignment.model.PullRequest
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers


private val BASE_URL =
    "https://api.github.com/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()


interface PullRequestRepository {

    @Headers(
        "Authorization: token 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",
        "Accept: application/vnd.github+json"
    )
    @GET("repos/octocat/Hello-World/pulls?state=closed")
    suspend fun getClosedPullRequest(): List<PullRequest>

    @GET
    suspend fun getImage()

}

object PullRequestApi {
    val pullRequestRepository:PullRequestRepository by lazy { retrofit.create(PullRequestRepository::class.java) }
}
