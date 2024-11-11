package com.culinario.backend

import com.culinario.mvp.models.Recipe

class CloudRecipeSaver : ISaver {

    override fun save(dataToSave: Recipe) {
        print("Placeholder of cloud saving :0")
    }
}