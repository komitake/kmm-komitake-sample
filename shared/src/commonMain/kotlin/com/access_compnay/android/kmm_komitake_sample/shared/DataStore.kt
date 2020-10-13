package com.access_compnay.android.kmm_komitake_sample.shared

interface DataStore {
    suspend fun getRepos(): List<RepositoryData>
    suspend fun saveRepos(repos: List<RepositoryData>)
}

class ApiDataStore(private val api: GitHubApi) : DataStore {
    override suspend fun getRepos(): List<RepositoryData> {
        val allRepos = mutableListOf<RepositoryData>()

        var url: String? = null // default null
        do {
            val (res, nextUrl) = api.getOrgRepos(GITHUB_ORG, url)
            allRepos.addAll(res)
            url = nextUrl
        } while (url != null)

        return allRepos
    }

    override suspend fun saveRepos(repos: List<RepositoryData>) {
        // do nothing
    }
}

class StubDataStore() : DataStore {
    override suspend fun getRepos(): List<RepositoryData> {
        return listOf(
            RepositoryData(
                id = 0,
                nodeId = "0",
                name = "abc",
                fullName = "ABC",
                private = false,
                htmlUrl = "http://localhost",
                defaultBranch = "main",
            )
        )
    }

    override suspend fun saveRepos(repos: List<RepositoryData>) {
        // do nothing
    }
}