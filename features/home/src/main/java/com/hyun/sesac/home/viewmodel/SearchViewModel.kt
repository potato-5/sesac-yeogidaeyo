package com.hyun.sesac.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hyun.sesac.home.ui.SearchUiState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

sealed interface SearchUiEffect{
    data class NavigateToDetail(val value: String) : SearchUiEffect
    data class ShowToast(val message: String) : SearchUiEffect
}
class SearchViewModel : ViewModel(){
    // 내부 수정용 state flow
    private val _uiState = MutableStateFlow(SearchUiState())
    //외부 노출용 불변 StateFlow
    val uiState = _uiState.asStateFlow()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = SearchUiState()
        )

    // 일회성 이벤트 채널
    private val _effect = Channel<SearchUiEffect>()
    val effect = _effect.receiveAsFlow()

    init{
        loadRecentSearch()
        // 추후 use case로 변경
    }

    private fun loadRecentSearch(){
        _uiState.update{
            it.copy(
                recentSearch = listOf(
                    "청년취업사관학교 동대문캠퍼스",
                    "서울시청 본청사 주차장",
                    "하이디라오 명동점",
                    "동대문 공영주차장(시)"
                )
            )
        }
    }

    //사용자 입력 처리
    fun onQueryChange(newQuery: String){
        _uiState.update { it.copy(query = newQuery) }
    }

    private fun sendEffect(effect: SearchUiEffect){
        viewModelScope.launch {
            _effect.send(effect)
        }
    }

    // 검색 실행 ( 엔터 / 버튼 )
    fun onSearch(){
        val currentQuery = _uiState.value.query
        if(currentQuery.isBlank()){
            sendEffect(SearchUiEffect.ShowToast("검색어를 입력해주세요."))
            return
        }
        // TODO : 검색 로직 수행 OR DB 저장 ( EX . 최근 검색어 추가 및 입력창 비우기 )
        sendEffect(SearchUiEffect.NavigateToDetail(currentQuery))
    }

    // 최근 검색어 삭제
    fun onDeleteRecentSearch(target: String){
        _uiState.update { currentState ->
            currentState.copy(
                recentSearch = currentState.recentSearch.filter { it != target }
            )
        }
    }

    // 검색어 전체 삭제 ( x 버튼 )
    fun onClearQuery(){
        _uiState.update { it.copy(query = "") }
    }

}