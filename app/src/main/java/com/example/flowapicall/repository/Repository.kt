package com.example.flowapicall.repository

import com.example.flowapicall.*
import com.example.flowapicall.dto.LoginRequest
import com.example.flowapicall.dto.Response
import com.example.flowapicall.flow_utils.State
import com.example.flowapicall.repository.dao.local_dao.LocalDataSource
import com.example.flowapicall.repository.dao.remote_dao.RemoteDataSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@ExperimentalCoroutinesApi
class Repository @Inject constructor(private val remoteRepository: RemoteDataSource, private val localRepository: LocalDataSource, private val connectivity: NetworkConnectivity, private val ioDispatcher: CoroutineContext): RepositorySource {
    override suspend fun doLogin(loginRequest: LoginRequest): Flow<State<Response>> {
        return flow {
            emit(if (connectivity.isConnected()) remoteRepository.doLogin(loginRequest) else localRepository.doLogin(loginRequest))
        }.flowOn(ioDispatcher)
    }
}