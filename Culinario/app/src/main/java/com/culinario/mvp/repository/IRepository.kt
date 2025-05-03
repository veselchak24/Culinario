package com.culinario.repository

import com.culinario.mvp.models.IModel

interface IRepository<TModel : IModel> {
    fun get(id: UInt): TModel

    fun add(newTModel: TModel): Boolean

    fun update(tModel: TModel): Boolean

    fun delete(id: UInt): Boolean

}