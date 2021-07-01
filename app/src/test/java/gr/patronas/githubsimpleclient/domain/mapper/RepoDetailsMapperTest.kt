package gr.patronas.githubsimpleclient.domain.mapper

import gr.patronas.githubsimpleclient.TestConstants.dummyApiDate
import gr.patronas.githubsimpleclient.TestConstants.dummyAuthorName
import gr.patronas.githubsimpleclient.TestConstants.dummyFormatedDate
import gr.patronas.githubsimpleclient.TestConstants.dummyMessage
import gr.patronas.githubsimpleclient.TestConstants.dummySha
import gr.patronas.githubsimpleclient.domain.model.CommitDetailsDomainModel
import gr.patronas.githubsimpleclient.network.model.BaseApiResponseList
import gr.patronas.githubsimpleclient.network.model.fetch_commits.Author
import gr.patronas.githubsimpleclient.network.model.fetch_commits.Commit
import gr.patronas.githubsimpleclient.network.model.fetch_commits.FetchRepoCommitsApiResponse
import org.junit.Before
import org.junit.Test

class RepoDetailsMapperTest {

    private val repoDetailsMapper = RepoDetailsMapper()

    @Before
    fun init() {

    }

    @Test
    fun `given an api response verify mapper returns correct domain model`() {
        val dummyApiResponse = BaseApiResponseList(
            listOf(
                FetchRepoCommitsApiResponse(
                    sha = dummySha,
                    commit = Commit(
                        author = Author(
                            name = dummyAuthorName,
                            date = dummyApiDate
                        ),
                        message = dummyMessage
                    )
                )
            )
        )

        val expectedDomainResponse = listOf(
            CommitDetailsDomainModel(
                sha = dummySha,
                message = dummyMessage,
                author = dummyAuthorName,
                date = dummyFormatedDate
            )
        )

        assert(repoDetailsMapper.mapCommitsToDomain(apiResponse = dummyApiResponse) == expectedDomainResponse)

    }

}