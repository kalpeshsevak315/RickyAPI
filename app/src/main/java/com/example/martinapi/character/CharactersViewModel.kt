package com.example.martinapi.character

import androidx.lifecycle.ViewModel
import androidx.room.Insert
import com.example.martinapi.data.repository.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    repository: CharacterRepository
) : ViewModel() {

    val characters = repository.getCharacters()
}
