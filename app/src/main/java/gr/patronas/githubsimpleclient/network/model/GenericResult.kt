package gr.patronas.githubsimpleclient.network.model

sealed class GenericResult<Data> {

    class Success<Data>(var data: Data) : GenericResult<Data>()

    class Error<Data> : GenericResult<Data>()
}