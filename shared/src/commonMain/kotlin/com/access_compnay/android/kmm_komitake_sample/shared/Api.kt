package com.access_compnay.android.kmm_komitake_sample.shared

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

fun HttpResponse.getNextLink(): String? {
    return headers["Link"]
        ?.split(",")?.firstOrNull { it.endsWith("rel=\"next\"") }
        ?.split(";")?.firstOrNull()
        ?.trim()?.removePrefix("<")?.removeSuffix(">")
}

class GitHubApi(private val token: String) {
    private val httpClient = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                ignoreUnknownKeys = true
            })
        }
    }

    suspend fun getOrgRepos(org: String, url: String? = null): Pair<List<RepositoryData>, String?> {
        val requestUrl = url ?: "https://api.github.com/orgs/$org/repos"
        val response = httpClient.get<HttpResponse>(requestUrl) {
            header("Authorization", "token $token")
        }

        val nextUrl: String? = response.getNextLink()

        return Pair(response.receive(), nextUrl)
    }
}
