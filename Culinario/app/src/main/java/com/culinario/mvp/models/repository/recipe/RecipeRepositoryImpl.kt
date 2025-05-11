package com.culinario.mvp.models.repository.recipe

import com.culinario.R
import com.culinario.mvp.models.Difficulty
import com.culinario.mvp.models.Ingredient
import com.culinario.mvp.models.OtherInfo
import com.culinario.mvp.models.Recipe
import com.culinario.mvp.models.RecipeImageResources
import com.culinario.mvp.models.RecipeType
import com.culinario.mvp.models.Unit

class RecipeRepositoryImpl : RecipeRepository {
    private val recipes = mutableListOf (
        Recipe (
            id = "11111111",
            userId = "WaAWgH3212",
            name = "Паста с томатным соусом",
            description = "Вкусная паста с домашним томатным соусом.",
            recipeImageResources = RecipeImageResources(
                recipeBackgroundImageResources = R.drawable.recipe_page_bg,
                recipePicturesResources = arrayOf(
                    R.drawable.pasta,
                    R.drawable.pasta2,
                    R.drawable.pasta3
                )
            ),
            ingredients = listOf (
                Ingredient(name = "Паста", quantity = 200.0, unit = Unit.GRAMS),
                Ingredient(name = "Томаты", quantity = 300.0, unit = Unit.GRAMS),
                Ingredient(name = "Чеснок", quantity = 2.0, unit = Unit.PIECE)
            ),
            cookingSpeed = 30,
            steps = listOf("Сварить пасту", "Приготовить соус", "Смешать пасту с соусом"),
            recipeType = RecipeType.QUICK,
            difficulty = Difficulty.EASY,
            otherInfo = OtherInfo(106, 2)
        ),

        Recipe(
            id = "11111112",
            userId = "WaAWgH3212",
            name = "Шоколадный торт",
            description = "Нежный шоколадный торт с кремом.",
            recipeImageResources = RecipeImageResources (
                recipeBackgroundImageResources = R.drawable.chocolate_cake_2,
                recipePicturesResources = arrayOf (
                    R.drawable.chocolate_cake,
                    R.drawable.chocolate_cake_2,
                    R.drawable.chocolate_cake_3
                )
            ),
            ingredients = listOf(
                Ingredient(name = "Мука", quantity = 250.0, unit = Unit.GRAMS),
                Ingredient(name = "Шоколад", quantity = 100.0, unit = Unit.GRAMS),
                Ingredient(name = "Яйца", quantity = 3.0, unit = Unit.PIECE)
            ),
            cookingSpeed = 60,
            steps = listOf("Смешать ингредиенты", "Выпекать в духовке", "Остудить и подать"),
            recipeType = RecipeType.BAKING,
            difficulty = Difficulty.MEDIUM,
            otherInfo = OtherInfo(100, 5)
        ),

        Recipe(
            id = "11111113",
            userId = "user1",
            name = "Паста Карбонара",
            description = "Классическая итальянская паста с соусом из яиц, сыра и бекона.",
            recipeImageResources = RecipeImageResources(
                recipeBackgroundImageUri = "@drawable/ic_launcher_background",
                recipePicturesUri = arrayOf()
            ),
            ingredients = listOf(
                Ingredient("Спагетти", 400.0, Unit.GRAMS),
                Ingredient("Бекон", 150.0, Unit.GRAMS),
                Ingredient("Яйца", 3.0, Unit.PIECE),
                Ingredient("Пармезан", 100.0, Unit.GRAMS),
                Ingredient("Чеснок", 2.0, Unit.PIECE),
                Ingredient("Соль", null, null),
                Ingredient("Чёрный перец", null, null)
            ),
            cookingSpeed = 20,
            steps = listOf(
                "Отварите спагетти в подсоленной воде до состояния аль денте, согласно инструкции на упаковке.",
                "Обжарьте бекон с чесноком до хрустящей корочки.",
                "Взбейте яйца с тёртым пармезаном и чёрным перцем.",
                "Смешайте горячие спагетти с беконом, затем добавьте яичную смесь, быстро помешивая.",
                "Подавайте сразу, посыпав дополнительным пармезаном и перцем."
            ),
            recipeType = RecipeType.QUICK,
            difficulty = Difficulty.MEDIUM,
            otherInfo = OtherInfo(watches = 1500, likes = 1200)
        ),
        Recipe(
            id = "11111114",
            userId = "user2",
            name = "Веганский салат с киноа",
            description = "Питательный салат с киноа, овощами и лимонной заправкой.",
            recipeImageResources = RecipeImageResources(
                recipeBackgroundImageUri = "@drawable/ic_launcher_background",
                recipePicturesUri = arrayOf()
            ),
            ingredients = listOf(
                Ingredient("Киноа", 200.0, Unit.GRAMS),
                Ingredient("Огурец", 1.0, Unit.PIECE),
                Ingredient("Помидор", 2.0, Unit.PIECE),
                Ingredient("Авокадо", 1.0, Unit.PIECE),
                Ingredient("Лимонный сок", 30.0, Unit.GRAMS),
                Ingredient("Оливковое масло", 20.0, Unit.GRAMS),
                Ingredient("Соль", null, null),
                Ingredient("Петрушка", null, null)
            ),
            cookingSpeed = 15,
            steps = listOf(
                "Отварите киноа согласно инструкции на упаковке.",
                "Нарежьте овощи и авокадо кубиками.",
                "Смешайте киноа с овощами.",
                "Приготовьте заправку из лимонного сока, оливкового масла и соли.",
                "Полейте салат заправкой и украсьте петрушкой."
            ),
            recipeType = RecipeType.VEGAN,
            difficulty = Difficulty.EASY,
            otherInfo = OtherInfo(watches = 800, likes = 750)
        ),

        Recipe(
            id = "11111115",
            userId = "user1",
            name = "Классические блины",
            description = "Традиционные русские блины на молоке, тонкие и ароматные",
            recipeImageResources = RecipeImageResources(
                recipeBackgroundImageUri = "@drawable/ic_launcher_background",
                recipePicturesUri = arrayOf()
            ),
            ingredients = listOf(
                Ingredient("Мука", 200.0, Unit.GRAMS),
                Ingredient("Молоко", 500.0, Unit.GRAMS),
                Ingredient("Яйца", 2.0, Unit.PIECE),
                Ingredient("Сахар", 30.0, Unit.GRAMS),
                Ingredient("Соль", 5.0, Unit.GRAMS),
                Ingredient("Масло растительное", 30.0, Unit.GRAMS)
            ),
            cookingSpeed = 30,
            steps = listOf(
                "Смешайте яйца с сахаром и солью",
                "Добавьте молоко и постепенно введите муку",
                "Дайте тесту постоять 15 минут",
                "Жарьте на хорошо разогретой сковороде с обеих сторон",
                "Подавайте с вареньем или сметаной"
            ),
            recipeType = RecipeType.BAKING,
            difficulty = Difficulty.EASY,
            otherInfo = OtherInfo(watches = 2500, likes = 2100)
        ),

        Recipe(
            id = "11111116",
            userId = "user2",
            name = "Веганский салат с нутом",
            description = "Питательный салат с нутом, свежими овощами и лимонной заправкой",
            recipeImageResources = RecipeImageResources(
                recipeBackgroundImageUri = "@drawable/ic_launcher_background"
            ),
            ingredients = listOf(
                Ingredient("Нут вареный", 200.0, Unit.GRAMS),
                Ingredient("Огурец", 1.0, Unit.PIECE),
                Ingredient("Помидор", 2.0, Unit.PIECE),
                Ingredient("Красный лук", 0.5, Unit.PIECE),
                Ingredient("Петрушка", 20.0, Unit.GRAMS),
                Ingredient("Лимонный сок", 30.0, Unit.GRAMS),
                Ingredient("Оливковое масло", 20.0, Unit.GRAMS)
            ),
            cookingSpeed = 15,
            steps = listOf(
                "Нарежьте овощи кубиками",
                "Смешайте все ингредиенты в миске",
                "Приготовьте заправку из лимонного сока и оливкового масла",
                "Полейте салат заправкой и аккуратно перемешайте",
                "Украсьте петрушкой перед подачей"
            ),
            recipeType = RecipeType.VEGAN,
            difficulty = Difficulty.EASY,
            otherInfo = OtherInfo(watches = 1800, likes = 1600)
        ),

        Recipe(
            id = "11111117",
            userId = "user3",
            name = "Лазанья Болоньезе",
            description = "Классическая итальянская лазанья с мясным соусом",
            recipeImageResources = RecipeImageResources(
                recipeBackgroundImageUri = "@drawable/ic_launcher_background"
            ),
            ingredients = listOf(
                Ingredient("Листы для лазаньи", 12.0, Unit.PIECE),
                Ingredient("Фарш говяжий", 500.0, Unit.GRAMS),
                Ingredient("Лук", 1.0, Unit.PIECE),
                Ingredient("Морковь", 1.0, Unit.PIECE),
                Ingredient("Чеснок", 2.0, Unit.PIECE),
                Ingredient("Томатная паста", 100.0, Unit.GRAMS),
                Ingredient("Сыр пармезан", 150.0, Unit.GRAMS),
                Ingredient("Сыр моцарелла", 200.0, Unit.GRAMS),
                Ingredient("Молоко", 500.0, Unit.GRAMS),
                Ingredient("Мука", 50.0, Unit.GRAMS),
                Ingredient("Сливочное масло", 50.0, Unit.GRAMS)
            ),
            cookingSpeed = 90,
            steps = listOf(
                "Приготовьте мясной соус, обжарив овощи и фарш",
                "Сделайте бешамель из муки, масла и молока",
                "Соберите лазанью, чередуя листы, соус и сыр",
                "Запекайте 40 минут при 180°C",
                "Дайте настояться 10 минут перед подачей"
            ),
            recipeType = RecipeType.COMPLEX,
            difficulty = Difficulty.HARD,
            otherInfo = OtherInfo(watches = 3200, likes = 2900)
        ),

        Recipe(
            id = "11111118",
            userId = "user4",
            name = "Безглютеновые оладьи",
            description = "Пышные оладьи на гречневой муке без глютена",
            recipeImageResources = RecipeImageResources(
                recipeBackgroundImageUri = "@drawable/ic_launcher_background"
            ),
            ingredients = listOf(
                Ingredient("Гречневая мука", 250.0, Unit.GRAMS),
                Ingredient("Кефир", 300.0, Unit.GRAMS),
                Ingredient("Яйца", 2.0, Unit.PIECE),
                Ingredient("Сода", 5.0, Unit.GRAMS),
                Ingredient("Сахар", 30.0, Unit.GRAMS),
                Ingredient("Масло растительное", 20.0, Unit.GRAMS)
            ),
            cookingSpeed = 25,
            steps = listOf(
                "Смешайте кефир с яйцами и сахаром",
                "Добавьте муку и соду, тщательно перемешайте",
                "Дайте тесту постоять 10 минут",
                "Жарьте на среднем огне под крышкой",
                "Подавайте с медом или ягодами"
            ),
            recipeType = RecipeType.GLUTEN_FREE,
            difficulty = Difficulty.MEDIUM,
            otherInfo = OtherInfo(watches = 1500, likes = 1300)
        ),

        Recipe(
            id = "11111119",
            userId = "user5",
            name = "Куриный суп с лапшой",
            description = "Ароматный домашний куриный суп с лапшой и овощами",
            recipeImageResources = RecipeImageResources(
                recipeBackgroundImageUri = "@drawable/ic_launcher_background"
            ),
            ingredients = listOf(
                Ingredient("Курица (грудка)", 400.0, Unit.GRAMS),
                Ingredient("Лапша", 150.0, Unit.GRAMS),
                Ingredient("Морковь", 1.0, Unit.PIECE),
                Ingredient("Лук", 1.0, Unit.PIECE),
                Ingredient("Картофель", 3.0, Unit.PIECE),
                Ingredient("Зелень", null, null),
                Ingredient("Соль", null, null),
                Ingredient("Перец горошком", 5.0, Unit.PIECE)
            ),
            cookingSpeed = 40,
            steps = listOf(
                "Сварите куриный бульон",
                "Добавьте нарезанные овощи и варите 15 минут",
                "Положите лапшу и варите еще 5-7 минут",
                "Добавьте зелень перед подачей"
            ),
            recipeType = RecipeType.QUICK,
            difficulty = Difficulty.EASY,
            otherInfo = OtherInfo(watches = 2200, likes = 2000)
        ),

        Recipe(
            id = "11111120",
            userId = "user6",
            name = "Шоколадные маффины",
            description = "Нежные шоколадные маффины с жидкой серединкой",
            recipeImageResources = RecipeImageResources(
                recipeBackgroundImageUri = "@drawable/ic_launcher_background"
            ),
            ingredients = listOf(
                Ingredient("Мука", 200.0, Unit.GRAMS),
                Ingredient("Сахар", 150.0, Unit.GRAMS),
                Ingredient("Какао-порошок", 50.0, Unit.GRAMS),
                Ingredient("Яйца", 2.0, Unit.PIECE),
                Ingredient("Сливочное масло", 100.0, Unit.GRAMS),
                Ingredient("Шоколад", 100.0, Unit.GRAMS),
                Ingredient("Разрыхлитель", 10.0, Unit.GRAMS)
            ),
            cookingSpeed = 25,
            steps = listOf(
                "Растопите масло и шоколад на водяной бане",
                "Смешайте сухие ингредиенты",
                "Добавьте яйца и шоколадную массу",
                "Разлейте по формочкам и выпекайте 15 минут при 180°C"
            ),
            recipeType = RecipeType.BAKING,
            difficulty = Difficulty.MEDIUM,
            otherInfo = OtherInfo(watches = 2800, likes = 2600)
        ),

        Recipe(
            id = "11111121",
            userId = "user7",
            name = "Греческий салат",
            description = "Классический греческий салат с фетаксой и оливками",
            recipeImageResources = RecipeImageResources(
                recipeBackgroundImageUri = "@drawable/ic_launcher_background"
            ),
            ingredients = listOf(
                Ingredient("Помидоры", 3.0, Unit.PIECE),
                Ingredient("Огурец", 1.0, Unit.PIECE),
                Ingredient("Лук красный", 0.5, Unit.PIECE),
                Ingredient("Сыр фета", 200.0, Unit.GRAMS),
                Ingredient("Оливки", 100.0, Unit.GRAMS),
                Ingredient("Оливковое масло", 50.0, Unit.GRAMS),
                Ingredient("Лимонный сок", 20.0, Unit.GRAMS),
                Ingredient("Орегано", null, null)
            ),
            cookingSpeed = 15,
            steps = listOf(
                "Нарежьте овощи крупными кусками",
                "Добавьте оливки и кубики феты",
                "Приготовьте заправку из масла и лимонного сока",
                "Посыпьте орегано перед подачей"
            ),
            recipeType = RecipeType.QUICK,
            difficulty = Difficulty.EASY,
            otherInfo = OtherInfo(watches = 1900, likes = 1750)
        ),

        Recipe(
            id = "11111122",
            userId = "user8",
            name = "Рататуй",
            description = "Традиционное французское овощное рагу из баклажанов, кабачков и перцев",
            recipeImageResources = RecipeImageResources(
                recipeBackgroundImageUri = "@drawable/ic_launcher_background"
            ),
            ingredients = listOf(
                Ingredient("Баклажан", 1.0, Unit.PIECE),
                Ingredient("Кабачок", 1.0, Unit.PIECE),
                Ingredient("Перец болгарский", 2.0, Unit.PIECE),
                Ingredient("Помидоры", 4.0, Unit.PIECE),
                Ingredient("Лук", 1.0, Unit.PIECE),
                Ingredient("Чеснок", 3.0, Unit.PIECE),
                Ingredient("Оливковое масло", 50.0, Unit.GRAMS),
                Ingredient("Прованские травы", null, null)
            ),
            cookingSpeed = 60,
            steps = listOf(
                "Нарежьте овощи кружочками",
                "Выложите в форму для запекания, чередуя овощи",
                "Полейте оливковым маслом и посыпьте травами",
                "Запекайте 45 минут при 190°C"
            ),
            recipeType = RecipeType.VEGAN,
            difficulty = Difficulty.MEDIUM,
            otherInfo = OtherInfo(watches = 2100, likes = 1950)
        ),

        Recipe(
            id = "11111123",
            userId = "user9",
            name = "Тирамису",
            description = "Итальянский десерт с кофейным вкусом и нежным кремом",
            recipeImageResources = RecipeImageResources(
                recipeBackgroundImageUri = "@drawable/ic_launcher_background"
            ),
            ingredients = listOf(
                Ingredient("Сыр маскарпоне", 500.0, Unit.GRAMS),
                Ingredient("Печенье савоярди", 200.0, Unit.GRAMS),
                Ingredient("Яйца", 4.0, Unit.PIECE),
                Ingredient("Сахар", 100.0, Unit.GRAMS),
                Ingredient("Кофе эспрессо", 200.0, Unit.GRAMS),
                Ingredient("Какао-порошок", 30.0, Unit.GRAMS)
            ),
            cookingSpeed = 90,
            steps = listOf(
                "Приготовьте кофе и остудите",
                "Взбейте желтки с сахаром и добавьте маскарпоне",
                "Взбейте белки и аккуратно смешайте с кремом",
                "Выложите слоями: печенье, пропитанное кофе, затем крем",
                "Посыпьте какао и охладите 6 часов"
            ),
            recipeType = RecipeType.COMPLEX,
            difficulty = Difficulty.HARD,
            otherInfo = OtherInfo(watches = 3500, likes = 3300)
        ),

        Recipe(
            id = "11111124",
            userId = "user10",
            name = "Омлет с овощами",
            description = "Пышный омлет с болгарским перцем, помидорами и зеленью",
            recipeImageResources = RecipeImageResources(
                recipeBackgroundImageUri = "@drawable/ic_launcher_background"
            ),
            ingredients = listOf(
                Ingredient("Яйца", 4.0, Unit.PIECE),
                Ingredient("Молоко", 50.0, Unit.GRAMS),
                Ingredient("Помидор", 1.0, Unit.PIECE),
                Ingredient("Перец болгарский", 0.5, Unit.PIECE),
                Ingredient("Зелень", null, null),
                Ingredient("Соль", null, null),
                Ingredient("Масло растительное", 20.0, Unit.GRAMS)
            ),
            cookingSpeed = 15,
            steps = listOf(
                "Нарежьте овощи мелкими кубиками",
                "Взбейте яйца с молоком и солью",
                "Обжарьте овощи 2 минуты, залейте яичной смесью",
                "Готовьте на среднем огне под крышкой 5-7 минут",
                "Посыпьте зеленью перед подачей"
            ),
            recipeType = RecipeType.QUICK,
            difficulty = Difficulty.EASY,
            otherInfo = OtherInfo(watches = 1200, likes = 1100)
        ),

        Recipe(
            id = "11111125",
            userId = "user101",
            name = "Сырники классические",
            description = "Нежные творожные сырники с хрустящей корочкой",
            recipeImageResources = RecipeImageResources(
                recipeBackgroundImageUri = "@drawable/ic_launcher_background",
                recipePicturesUri = arrayOf()
            ),
            ingredients = listOf(
                Ingredient("Творог", 500.0, Unit.GRAMS),
                Ingredient("Яйца", 2.0, Unit.PIECE),
                Ingredient("Мука", 100.0, Unit.GRAMS),
                Ingredient("Сахар", 50.0, Unit.GRAMS),
                Ingredient("Ванильный сахар", 10.0, Unit.GRAMS),
                Ingredient("Масло растительное", 50.0, Unit.GRAMS)
            ),
            cookingSpeed = 25,
            steps = listOf(
                "Протрите творог через сито",
                "Смешайте творог с яйцами, сахаром и ванильным сахаром",
                "Добавьте муку и замесите тесто",
                "Сформируйте сырники и обжарьте на среднем огне до золотистой корочки",
                "Подавайте со сметаной или вареньем"
            ),
            recipeType = RecipeType.BAKING,
            difficulty = Difficulty.EASY,
            otherInfo = OtherInfo(watches = 3200, likes = 2900)
        ),

        Recipe(
            id = "11111126",
            userId = "user102",
            name = "Куриные крылышки BBQ",
            description = "Хрустящие куриные крылышки в барбекю соусе",
            recipeImageResources = RecipeImageResources(
                recipeBackgroundImageUri = "@drawable/ic_launcher_background"
            ),
            ingredients = listOf(
                Ingredient("Куриные крылья", 1.0, Unit.GRAMS),
                Ingredient("Соус BBQ", 150.0, Unit.GRAMS),
                Ingredient("Чеснок", 3.0, Unit.PIECE),
                Ingredient("Паприка", 10.0, Unit.GRAMS),
                Ingredient("Соль", 5.0, Unit.GRAMS),
                Ingredient("Растительное масло", 30.0, Unit.GRAMS)
            ),
            cookingSpeed = 45,
            steps = listOf(
                "Замаринуйте крылья в смеси специй и масла на 1 час",
                "Запекайте в духовке при 200°C 30 минут",
                "Обмажьте соусом BBQ и запекайте еще 10 минут",
                "Подавайте с сельдереем и соусом голубой сыр"
            ),
            recipeType = RecipeType.QUICK,
            difficulty = Difficulty.MEDIUM,
            otherInfo = OtherInfo(watches = 4100, likes = 3800)
        ),

        Recipe(
            id = "11111127",
            userId = "user103",
            name = "Веганский бургер",
            description = "Бургер с котлетой из нута и овощами",
            recipeImageResources = RecipeImageResources(
                recipeBackgroundImageUri = "@drawable/ic_launcher_background"
            ),
            ingredients = listOf(
                Ingredient("Нут вареный", 300.0, Unit.GRAMS),
                Ingredient("Булочки для бургеров", 2.0, Unit.PIECE),
                Ingredient("Авокадо", 1.0, Unit.PIECE),
                Ingredient("Помидор", 1.0, Unit.PIECE),
                Ingredient("Листья салата", 50.0, Unit.GRAMS),
                Ingredient("Чеснок", 2.0, Unit.PIECE),
                Ingredient("Нутовая мука", 50.0, Unit.GRAMS),
                Ingredient("Растительное масло", 20.0, Unit.GRAMS)
            ),
            cookingSpeed = 35,
            steps = listOf(
                "Измельчите нут в блендере с чесноком",
                "Сформируйте котлеты, обваляйте в муке",
                "Обжарьте котлеты на сковороде 5 минут с каждой стороны",
                "Соберите бургер: булочка, котлета, овощи",
                "Подавайте с картофелем фри"
            ),
            recipeType = RecipeType.VEGAN,
            difficulty = Difficulty.MEDIUM,
            otherInfo = OtherInfo(watches = 2300, likes = 2100)
        )
    )

    override fun addRecipe(recipe: Recipe) {
        recipes.add(recipe)
    }

    override fun commit() {

    }

    override fun getAllRecipes(): List<Recipe> {
        return recipes
    }

    override fun getRecipeById(id: String): Recipe {
        return recipes.first { recipe ->
            recipe.id == id
        }
    }

    override fun searchRecipesByTitle(title: String): List<Recipe> {
        return recipes.filter { it.name.contains(title, ignoreCase = true) }
    }

    override fun searchRecipesByIngredients(ingredients: List<String>): List<Recipe> {
        return recipes.filter { recipe ->
            ingredients.all { ingredient ->
                recipe.ingredients.any { it.name.equals(ingredient, ignoreCase = true) }
            }
        }
    }

    override fun searchRecipesByCookingSpeed(maxSpeed: Int): List<Recipe> {
        return recipes.filter { it.cookingSpeed <= maxSpeed }
    }

    override fun searchRecipesByType(type: RecipeType): List<Recipe> {
        return recipes.filter { it.recipeType == type }
    }

    override fun searchRecipesByMealTime(mealTime: String): List<Recipe> {
        throw NotImplementedError()
//        return recipes.filter { recipe ->
//            recipe.otherCharacteristics["mealTime"]?.toString()?.equals(mealTime, ignoreCase = true) == true
//        }
    }
}