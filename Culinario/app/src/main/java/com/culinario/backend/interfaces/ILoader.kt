package com.culinario.backend.interfaces

import android.content.Context

/**
 * Интерфейс, использующийся для определения класса загрузчика объекта в runtime.
 */
interface ILoader {
    /**
     * Загрузка объекта
     *
     * @param [path] путь до файла или (возможно) ссылка на БД.
     * @param [context] контекст взаимодействия с системой.
     * @return Десериализованный объект типа [Any]
     */
    fun load(path: String, context: Context): Any
}