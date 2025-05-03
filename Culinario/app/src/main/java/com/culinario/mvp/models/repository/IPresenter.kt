package com.culinario.mvp.presenters

import com.culinario.mvp.models.IModel
import com.culinario.mvp.repository.IRepository
import com.culinario.mvp.views.IView

interface IPresenter<TModel : IModel> {
    val repository: IRepository<TModel>
    val view: IView

    fun loadData(id: UInt);
    fun saveData(data: TModel);
}