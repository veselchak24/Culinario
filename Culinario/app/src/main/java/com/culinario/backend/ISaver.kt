package com.culinario.backend

import com.culinario.mvp.models.Recipe

interface ISaver {
    fun save(dataToSave: Recipe)
}