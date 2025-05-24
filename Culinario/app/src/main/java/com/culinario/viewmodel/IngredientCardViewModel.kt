package com.culinario.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.culinario.helpers.INGREDIENT_COLLECTION
import com.culinario.mvp.models.Ingredient
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class IngredientCardViewModel(
    val ingredientId: String
) : ViewModel() {
    private val ingredientsCollection = Firebase.firestore.collection(INGREDIENT_COLLECTION)

    private val ingredientState = MutableStateFlow(Ingredient())
    val ingredient = ingredientState.asStateFlow()
    val isLoaded = mutableStateOf(false)

    init {
        viewModelScope.launch {
            ingredientState.value = ingredientsCollection
                .document(ingredientId)
                .get()
                .await()
                .toObject<Ingredient>() ?: Ingredient()

            isLoaded.value = true
        }
    }
}