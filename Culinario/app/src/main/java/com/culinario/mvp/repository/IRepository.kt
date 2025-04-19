package com.culinario.mvp.repository

import com.culinario.mvp.models.IModel
import okhttp3.Response

interface IRepository<TModel : IModel> {
    fun get(id: UInt, outTModel: TModel): Response

    fun add(newTModel: TModel): Response

    fun update(tModel: TModel): Response

    fun delete(id: UInt): Response

}