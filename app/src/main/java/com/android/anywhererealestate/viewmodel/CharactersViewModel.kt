package com.android.anywhererealestate.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.CreationExtras
import com.android.anywhererealestate.MyApplication
import com.android.anywhererealestate.model.ApiResponse
import com.android.anywhererealestate.model.RelatedTopic
import com.android.anywhererealestate.model.ResultOf
import com.android.anywhererealestate.repo.CharactersRepository
import com.android.anywhererealestate.repo.Repository
import com.android.anywhererealestate.util.ApiException
import com.android.anywhererealestate.util.NoInternetException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException

class CharactersViewModel(private val charactersRepository: CharactersRepository) : ViewModel() {
    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                val application = checkNotNull(extras[APPLICATION_KEY])
                val repo = (application as MyApplication).appContainer.charactersRepository
                return CharactersViewModel(repo) as T
            }
        }
    }

    private val mutableCharactersLiveData = MutableLiveData<ResultOf<List<RelatedTopic>>>()

    fun getCharactersLiveData(): LiveData<ResultOf<List<RelatedTopic>>> = mutableCharactersLiveData

    fun getCharactersFromNetwork(query: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = charactersRepository.getCharactersFromNetwork(query)
                response.let {
                    if (it.isSuccessful) {
                        it.body()?.let { responseBody ->
                            mutableCharactersLiveData.postValue(ResultOf.Success(responseBody.relatedTopics))
                        } ?: run {
                            throw ApiException("Response body is null")
                        }
                    } else {
                        it.errorBody()?.let { errorBody ->
                            //if the error body is not null
                            throw ApiException(errorBody.toString())
                        } ?: run {
                            //if the error body is null
                            throw ApiException("Error code: ${it.code()}, ${it.message()}")
                        }
                    }
                }
            } catch (exception: NoInternetException) {
                mutableCharactersLiveData.postValue(ResultOf.Failure(exception))
            } catch (exception: ApiException) {
                mutableCharactersLiveData.postValue(ResultOf.Failure(exception))
            } catch (exception: IOException) {
                //I'm not sure what other kind of exceptions can occur, but this is to ensure that
                //it is caught. Granted, I could wrap all of the possible exceptions under
                //"Exception" class but that may hide the type of exception that occurs and would be
                //of little help
                mutableCharactersLiveData.postValue(ResultOf.Failure(exception))
            }
        }
    }
}