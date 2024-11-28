package com.culinario.backend.interfaces

import android.content.Context

/**
 * Интерфейс, использующийся для определения класса, сохраняющего объект.
 */
interface ISaver {
    /**
     * Сохранение объекта
     *
     * @param [dataToSave] объект [Any], который требуется сохранить.
     * @param [context] контекст взаимодействия с системой.
     * @return Сериализованный в [String] объект.
     */
    fun save(dataToSave: Any, context: Context): String
}