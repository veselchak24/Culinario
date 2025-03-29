package com.culinario.mvp.models

class Product(
    var id: Int,
    var name: String,
    val description: String,
    val nutritional: String,
    val categoryId: ProductCategory
) {

}