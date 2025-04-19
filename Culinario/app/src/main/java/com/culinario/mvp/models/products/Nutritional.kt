package com.culinario.mvp.models

import kotlinx.serialization.Serializable

/**
 * Класс Nutritional представляет информацию о питательных веществах с уникальным идентификатором, количеством калорий, белков, жиров и углеводов.
 * @param Id Уникальный идентификатор информации о питательных веществах.
 * @param Calories Количество калорий.
 * @param Protein Количество белков.
 * @param Fat Количество жиров.
 * @param Carbohydrate Количество углеводов.
 */
@Serializable
class Nutritional(
    override val Id: Int,
    val Calories: Int,
    val Protein: Int,
    val Fat: Int,
    val Carbohydrate: Int,
) : IModel