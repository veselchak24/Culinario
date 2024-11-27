package com.culinario.mvp.models
//модель данных рецепта
    class recipe (
        val name: String,                                        //название рецепта
        val description: String,                                 //описание рецепта
        val imageUrl: String,                                    //фото
        val author: Author,                                      //автор
        val ingredients: List<Ingredient>,                       //ингредиенты
        val steps: List<String>,                                 //шаги приготовления
        val recipeType: RecipeType,                              //тип рецепта
        var otherCharacteristics: Map<String, Any> = emptyMap()  //другие характеристики
    )

//автор рецепта
        data class Author(val name: String, val email: String? = null)  //имя автора и почта
//ингридиенты
        interface Ingredient {
            val name: String        //название продукта
            val quantity: Double?   //количество
            val unit: Unit?         //единица измерения
        }
//единица измерения ингредиента
        enum class Unit {
            PIECE, GRAMS, LITERS, TEASPOONS, TABLESPOONS, CUPS   //количество в штуках, граммы, литры, сайные ложки, столовые ложки, чашки
        }
//реализует интерфейс ингридиентов
//Класс SimpleIngredient реализует интерфейс Ingredient. Он содержит те же поля, что и интерфейс, и может использоваться для создания конкретных экземпляров ингредиентов.
        data class SimpleIngredient(
            override val name: String,
            override val quantity: Double? = null,
            override val unit: Unit? = null
        ) : Ingredient
//тип рецепта
        enum class RecipeType {
            QUICK, COMPLEX, VEGAN, GLUTEN_FREE, BAKING  //быстрый, комплексный, веганский, без глютена, выпечка
        }
//уровень сложности рецепта
        enum class Difficulty {
            EASY, MEDIUM, HARD   //лёгкий, средний, сложный
        }
