package  com.example.martinapi.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CharacterDao {

    @Query("SELECT * FROM characters")
    fun getAllCharacters() : LiveData<List<com.example.martinapi.data.entities.Characters>>

    @Query("SELECT * FROM characters WHERE id = :id")
    fun getCharacter(id: Int): LiveData<com.example.martinapi.data.entities.Characters>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characters: List<com.example.martinapi.data.entities.Characters>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(character: com.example.martinapi.data.entities.Characters)

}