package com.example.superheroapi.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.superheroapi.repository.Repository

@Suppress("UNCHECKED_CAST")
class SuperHeroViewModelFactory (private val repository: Repository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SuperHeroViewModel(repository) as T
    }
}