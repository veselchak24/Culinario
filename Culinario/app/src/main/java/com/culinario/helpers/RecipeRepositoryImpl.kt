package com.culinario.helpers

import com.culinario.mvp.models.Difficulty
import com.culinario.mvp.models.Ingredient
import com.culinario.mvp.models.NutritionInfo
import com.culinario.mvp.models.OtherInfo
import com.culinario.mvp.models.Recipe
import com.culinario.mvp.models.RecipeDescription
import com.culinario.mvp.models.RecipeType
import com.culinario.mvp.models.DetailedCookingStep

class RecipeRepositoryImpl {
	val recipes = mutableListOf (
		Recipe(
			id = "11111114",
			userId = "85t6ir7f12v",
			name = "Веганский салат с киноа",
			description = "Питательный салат с киноа, овощами и лимонной заправкой.",
			ingredients = listOf(
				Ingredient("Киноа", "", 200.0, NutritionInfo(200.0, 8.0, 4.0, 36.0)),
				Ingredient("Огурец", "", 1.0, NutritionInfo(36.0, 1.6, 0.4, 7.8)),
				Ingredient("Помидор", "", 2.0, NutritionInfo(234.0, 2.9, 21.4, 12.5)),
				Ingredient("Авокадо", "", 1.0, NutritionInfo(8.0, 0.1, 0.1, 2.5)),
				Ingredient("Лимонный сок", "", 30.0, NutritionInfo(29.0, 0.9, 0.1, 3.0)),
				Ingredient("Оливковое масло", "", 20.0, NutritionInfo(900.0, 0.0, 100.0, 0.0)),
				Ingredient("Соль", "", null, NutritionInfo(0.0, 0.0, 0.0, 0.0)),
				Ingredient("Петрушка", "", null, NutritionInfo(49.0, 3.7, 0.4, 7.6))
			),
			cookingSpeed = 15,
//			steps = listOf(
//				"Отварите киноа согласно инструкции на упаковке.",
//				"Нарежьте овощи и авокадо кубиками.",
//				"Смешайте киноа с овощами.",
//				"Приготовьте заправку из лимонного сока, оливкового масла и соли.",
//				"Полейте салат заправкой и украсьте петрушкой."
//			),
			recipeType = RecipeType.VEGAN,
			difficulty = Difficulty.EASY,
			otherInfo = OtherInfo(watches = 800, likes = 750)
		),

		Recipe(
			id = "11111115",
			userId = "85t6ir7f12v",
			name = "Классические блины",
			description = "Традиционные русские блины на молоке, тонкие и ароматные",
			ingredients = listOf(
				Ingredient("Мука", "", 200.0, NutritionInfo(364.0, 10.3, 1.0, 76.3)),
				Ingredient("Молоко", "", 500.0, NutritionInfo(60.0, 3.2, 3.6, 4.8)),
				Ingredient("Яйца", "", 2.0, NutritionInfo(143.0, 12.6, 9.5, 0.7)),
				Ingredient("Сахар", "", 30.0, NutritionInfo(387.0, 0.0, 0.0, 100.0)),
				Ingredient("Соль", "", 5.0, NutritionInfo(0.0, 0.0, 0.0, 0.0)),
				Ingredient("Масло растительное", "", 30.0, NutritionInfo(884.0, 0.0, 100.0, 0.0))
			),
			cookingSpeed = 30,
//			steps = listOf(
//				"Смешайте яйца с сахаром и солью",
//				"Добавьте молоко и постепенно введите муку",
//				"Дайте тесту постоять 15 минут",
//				"Жарьте на хорошо разогретой сковороде с обеих сторон",
//				"Подавайте с вареньем или сметаной"
//			),
			recipeType = RecipeType.BAKING,
			difficulty = Difficulty.EASY,
			otherInfo = OtherInfo(watches = 2500, likes = 2100)
		),

		Recipe(
			id = "11111116",
			userId = "85t6ir7f12v",
			name = "Веганский салат с нутом",
			description = "Питательный салат с нутом, свежими овощами и лимонной заправкой",
			ingredients = listOf(
				Ingredient("Нут вареный", "", 200.0, NutritionInfo(164.0, 8.9, 2.6, 27.4)),
				Ingredient("Огурец", "", 1.0, NutritionInfo(16.0, 0.7, 0.1, 3.6)),
				Ingredient("Помидор", "", 2.0, NutritionInfo(22.0, 1.1, 0.2, 4.8)),
				Ingredient("Красный лук", "", 0.5, NutritionInfo(40.0, 1.1, 0.1, 9.3)),
				Ingredient("Петрушка", "", 20.0, NutritionInfo(36.0, 3.0, 0.8, 6.3)),
				Ingredient("Лимонный сок", "", 30.0, NutritionInfo(6.0, 0.1, 0.0, 2.0)),
				Ingredient("Оливковое масло", "", 20.0, NutritionInfo(884.0, 0.0, 100.0, 0.0))
			),
			cookingSpeed = 15,
//			steps = listOf(
//				"Нарежьте овощи кубиками",
//				"Смешайте все ингредиенты в миске",
//				"Приготовьте заправку из лимонного сока и оливкового масла",
//				"Полейте салат заправкой и аккуратно перемешайте",
//				"Украсьте петрушкой перед подачей"
//			),
			recipeType = RecipeType.VEGAN,
			difficulty = Difficulty.EASY,
			otherInfo = OtherInfo(watches = 1800, likes = 1600)
		),

		Recipe(
			id = "11111117",
			userId = "WaAWgH3212",
			name = "Лазанья Болоньезе",
			description = "Классическая итальянская лазанья с мясным соусом",
			ingredients = listOf(
				Ingredient("Листы для лазаньи", "", 12.0, NutritionInfo(131.0, 5.1, 0.6, 25.0)),
				Ingredient("Фарш говяжий", "", 500.0, NutritionInfo(250.0, 26.0, 15.0, 0.0)),Ingredient("Лук", "", 1.0, NutritionInfo(40.0, 1.1, 0.1, 9.3)),
				Ingredient("Морковь", "", 1.0, NutritionInfo(41.0, 0.9, 0.2, 9.6)),
				Ingredient("Чеснок", "", 2.0, NutritionInfo(149.0, 6.4, 0.5, 33.1)),
				Ingredient("Томатная паста", "", 100.0, NutritionInfo(82.0, 4.3, 0.5, 18.9)),
				Ingredient("Сыр пармезан", "", 150.0, NutritionInfo(392.0, 35.8, 25.8, 3.2)),
				Ingredient("Сыр моцарелла", "", 200.0, NutritionInfo(280.0, 28.0, 17.0, 3.1)),
				Ingredient("Молоко", "", 500.0, NutritionInfo(60.0, 3.2, 3.6, 4.8)),
				Ingredient("Мука", "", 50.0, NutritionInfo(364.0, 10.3, 1.0, 76.3)),
				Ingredient("Сливочное масло", "", 50.0, NutritionInfo(717.0, 0.9, 81.1, 0.1))
			),
			cookingSpeed = 90,
//			steps = listOf(
//				"Приготовьте мясной соус, обжарив овощи и фарш",
//				"Сделайте бешамель из муки, масла и молока",
//				"Соберите лазанью, чередуя листы, соус и сыр",
//				"Запекайте 40 минут при 180°C",
//				"Дайте настояться 10 минут перед подачей"
//			),
			recipeType = RecipeType.COMPLEX,
			difficulty = Difficulty.HARD,
			otherInfo = OtherInfo(watches = 3200, likes = 2900)
		),

		Recipe(
			id = "11111118",
			userId = "WaAWgH3212",
			name = "Безглютеновые оладьи",
			description = "Пышные оладьи на гречневой муке без глютена",
			ingredients = listOf(
				Ingredient("Гречневая мука", "", 250.0, NutritionInfo(343.0, 13.3, 3.4, 71.5)),
				Ingredient("Кефир", "", 300.0, NutritionInfo(56.0, 3.4, 2.0, 4.7)),
				Ingredient("Яйца", "", 2.0, NutritionInfo(143.0, 12.6, 9.5, 0.7)),
				Ingredient("Сода", "", 5.0, NutritionInfo(0.0, 0.0, 0.0, 0.0)),
				Ingredient("Сахар", "", 30.0, NutritionInfo(387.0, 0.0, 0.0, 100.0)),
				Ingredient("Масло растительное", "", 20.0, NutritionInfo(884.0, 0.0, 100.0, 0.0))
			),
			cookingSpeed = 25,
//			steps = listOf(
//				"Смешайте кефир с яйцами и сахаром",
//				"Добавьте муку и соду, тщательно перемешайте",
//				"Дайте тесту постоять 10 минут",
//				"Жарьте на среднем огне под крышкой",
//				"Подавайте с медом или ягодами"
//			),
			recipeType = RecipeType.GLUTEN_FREE,
			difficulty = Difficulty.MEDIUM,
			otherInfo = OtherInfo(watches = 1500, likes = 1300)
		),

		Recipe(
			id = "11111119",
			userId = "WaAWgH3212",
			name = "Куриный суп с лапшой",
			description = "Ароматный домашний куриный суп с лапшой и овощами",
			ingredients = listOf(
				Ingredient("Курица (грудка)", "", 400.0, NutritionInfo(165.0, 31.0, 3.6, 0.0)),
				Ingredient("Лапша", "", 150.0, NutritionInfo(131.0, 5.1, 0.6, 25.0)),
				Ingredient("Морковь", "", 1.0, NutritionInfo(41.0, 0.9, 0.2, 9.6)),
				Ingredient("Лук", "", 1.0, NutritionInfo(40.0, 1.1, 0.1, 9.3)),
				Ingredient("Картофель", "", 3.0, NutritionInfo(77.0, 2.0, 0.1, 17.0)),
				Ingredient("Зелень", "", null, NutritionInfo(49.0, 3.7, 0.4, 7.6)),
				Ingredient("Соль", "", null, NutritionInfo(0.0, 0.0, 0.0, 0.0)),
				Ingredient("Перец горошком", "", 5.0, NutritionInfo(251.0, 10.4, 3.3, 64.8))
			),
			cookingSpeed = 40,
//			steps = listOf(
//				"Сварите куриный бульон",
//				"Добавьте нарезанные овощи и варите 15 минут",
//				"Положите лапшу и варите еще 5-7 минут",
//				"Добавьте зелень перед подачей"
//			),
			recipeType = RecipeType.QUICK,
			difficulty = Difficulty.EASY,
			otherInfo = OtherInfo(watches = 2200, likes = 2000)
		),

		Recipe(
			id = "11111120",
			userId = "WaAWgH3212",
			name = "Шоколадные маффины",
			description = "Нежные шоколадные маффины с жидкой серединкой",
			ingredients = listOf(
				Ingredient("Мука", "", 200.0, NutritionInfo(364.0, 10.3, 1.0, 76.3)),
				Ingredient("Сахар", "", 150.0, NutritionInfo(387.0, 0.0, 0.0, 100.0)),
				Ingredient("Какао-порошок", "", 50.0, NutritionInfo(228.0, 19.6, 13.7, 57.9)),
				Ingredient("Яйца", "", 2.0, NutritionInfo(143.0, 12.6, 9.5, 0.7)),
				Ingredient("Сливочное масло", "", 100.0, NutritionInfo(717.0, 0.9, 81.1, 0.1)),
				Ingredient("Шоколад", "", 100.0, NutritionInfo(546.0, 4.9, 31.3, 61.2)),
				Ingredient("Разрыхлитель", "", 10.0, NutritionInfo(0.0, 0.0, 0.0, 0.0))
			),
			cookingSpeed = 25,
//			steps = listOf(
//				"Растопите масло и шоколад на водяной бане",
//				"Смешайте сухие ингредиенты",
//				"Добавьте яйца и шоколадную массу",
//				"Разлейте по формочкам и выпекайте 15 минут при 180°C"
//			),
			recipeType = RecipeType.BAKING,
			difficulty = Difficulty.MEDIUM,
			otherInfo = OtherInfo(watches = 2800, likes = 2600)
		),

		Recipe(
			id = "11111121",
			userId = "WaAWgH3212",
			name = "Греческий салат",
			description = "Классический греческий салат с фетаксой и оливками",
			ingredients = listOf(
				Ingredient("Помидоры", "", 3.0, NutritionInfo(22.0, 1.1, 0.2, 4.8)),
				Ingredient("Огурец", "", 1.0, NutritionInfo(16.0, 0.7, 0.1, 3.6)),
				Ingredient("Лук красный", "", 0.5, NutritionInfo(40.0, 1.1, 0.1, 9.3)),
				Ingredient("Сыр фета", "", 200.0, NutritionInfo(264.0, 14.2, 21.3, 4.1)),
				Ingredient("Оливки", "", 100.0, NutritionInfo(115.0, 0.8, 10.7, 6.3)),
				Ingredient("Оливковое масло", "", 50.0, NutritionInfo(884.0, 0.0, 100.0, 0.0)),
				Ingredient("Лимонный сок", "", 20.0, NutritionInfo(6.0, 0.1, 0.0, 2.0)),
				Ingredient("Орегано", "", null, NutritionInfo(306.0, 11.0, 10.3, 64.4))
			),
			cookingSpeed = 15,
//			steps = listOf(
//				"Нарежьте овощи крупными кусками",
//				"Добавьте оливки и кубики феты",
//				"Приготовьте заправку из масла и лимонного сока",
//				"Посыпьте орегано перед подачей"
//			),
			recipeType = RecipeType.QUICK,
			difficulty = Difficulty.EASY,
			otherInfo = OtherInfo(watches = 1900, likes = 1750)
		),

		Recipe(
			id = "11111122",
			userId = "WaAWgH3212",
			name = "Рататуй",
			description = "Традиционное французское овощное рагу из баклажанов, кабачков и перцев",
			ingredients = listOf(
				Ingredient("Баклажан", "", 1.0, NutritionInfo(25.0, 1.0, 0.2, 6.0)),
				Ingredient("Кабачок", "", 1.0, NutritionInfo(17.0, 1.2, 0.3, 3.1)),
				Ingredient("Перец болгарский", "", 2.0, NutritionInfo(31.0, 1.0, 0.3, 6.0)),
				Ingredient("Помидоры", "", 4.0, NutritionInfo(22.0, 1.1, 0.2, 4.8)),
				Ingredient("Лук", "", 1.0, NutritionInfo(40.0, 1.1, 0.1, 9.3)),
				Ingredient("Чеснок", "", 3.0, NutritionInfo(149.0, 6.4, 0.5, 33.1)),
				Ingredient("Оливковое масло", "", 50.0, NutritionInfo(884.0, 0.0, 100.0, 0.0)),
				Ingredient("Прованские травы", "", null, NutritionInfo(306.0, 11.0, 10.3, 64.4))
			),
			cookingSpeed = 60,
//			steps = listOf(
//				"Нарежьте овощи кружочками",
//				"Выложите в форму для запекания, чередуя овощи",
//				"Полейте оливковым маслом и посыпьте травами",
//				"Запекайте 45 минут при 190°C"
//			),
			recipeType = RecipeType.VEGAN,
			difficulty = Difficulty.MEDIUM,
			otherInfo = OtherInfo(watches = 2100, likes = 1950)
		),

		Recipe(
			id = "11111123",
			userId = "WaAWgH3212",
			name = "Тирамису",
			description = "Итальянский десерт с кофейным вкусом и нежным кремом",
			ingredients = listOf(
				Ingredient("Сыр маскарпоне", "", 500.0, NutritionInfo(429.0, 7.1, 46.4, 2.6)),
				Ingredient("Печенье савоярди", "", 200.0, NutritionInfo(386.0, 10.6, 9.1, 66.7)),
				Ingredient("Яйца", "", 4.0, NutritionInfo(143.0, 12.6, 9.5, 0.7)),
				Ingredient("Сахар", "", 100.0, NutritionInfo(387.0, 0.0, 0.0, 100.0)),
				Ingredient("Кофе эспрессо", "", 200.0, NutritionInfo(1.0, 0.1, 0.0, 0.0)),
				Ingredient("Какао-порошок", "", 30.0, NutritionInfo(228.0, 19.6, 13.7, 57.9))
			),
			cookingSpeed = 90,
//			steps = listOf(
//				"Приготовьте кофе и остудите",
//				"Взбейте желтки с сахаром и добавьте маскарпоне",
//				"Взбейте белки и аккуратно смешайте с кремом",
//				"Выложите слоями: печенье, пропитанное кофе, затем крем",
//				"Посыпьте какао и охладите 6 часов"
//			),
			recipeType = RecipeType.COMPLEX,
			difficulty = Difficulty.HARD,
			otherInfo = OtherInfo(watches = 3500, likes = 3300)
		),

		Recipe(
			id = "11111124",
			userId = "85t6ir7f12v",
			name = "Омлет с овощами",
			description = "Пышный омлет с болгарским перцем, помидорами и зеленью",
			ingredients = listOf(
				Ingredient("Яйца", "", 4.0, NutritionInfo(143.0, 12.6, 9.5, 0.7)),
				Ingredient("Молоко", "", 50.0, NutritionInfo(60.0, 3.2, 3.6, 4.8)),
				Ingredient("Помидор", "", 1.0, NutritionInfo(22.0, 1.1, 0.2, 4.8)),
				Ingredient("Перец болгарский", "", 0.5, NutritionInfo(31.0, 1.0, 0.3, 6.0)),
				Ingredient("Зелень", "", null, NutritionInfo(49.0, 3.7, 0.4, 7.6)),
				Ingredient("Соль", "", null, NutritionInfo(0.0, 0.0, 0.0, 0.0)),
				Ingredient("Масло растительное", "", 20.0, NutritionInfo(884.0, 0.0, 100.0, 0.0))
			),
			cookingSpeed = 15,
//			steps = listOf(
//				"Нарежьте овощи мелкими кубиками",
//				"Взбейте яйца с молоком и солью",
//				"Обжарьте овощи 2 минуты, залейте яичной смесью",
//				"Готовьте на среднем огне под крышкой 5-7 минут",
//				"Посыпьте зеленью перед подачей"
//			),
			recipeType = RecipeType.QUICK,
			difficulty = Difficulty.EASY,
			otherInfo = OtherInfo(watches = 1200, likes = 1100)
		),

		Recipe(
			id = "11111125",
			userId = "85t6ir7f12v",
			name = "Сырники классические",
			description = "Нежные творожные сырники с хрустящей корочкой",
			ingredients = listOf(
				Ingredient("Творог", "", 500.0, NutritionInfo(159.0, 17.2, 5.0, 3.4)),
				Ingredient("Яйца", "", 2.0, NutritionInfo(143.0, 12.6, 9.5, 0.7)),
				Ingredient("Мука", "", 100.0, NutritionInfo(364.0, 10.3, 1.0, 76.3)),
				Ingredient("Сахар", "", 50.0, NutritionInfo(387.0, 0.0, 0.0, 100.0)),
				Ingredient("Ванильный сахар", "", 10.0, NutritionInfo(394.0, 0.0, 0.0, 98.0)),
				Ingredient("Масло растительное", "", 50.0, NutritionInfo(884.0, 0.0, 100.0, 0.0))
			),
			cookingSpeed = 25,
//			steps = listOf(
//				"Протрите творог через сито",
//				"Смешайте творог с яйцами, сахаром и ванильным сахаром",
//				"Добавьте муку и замесите тесто",
//				"Сформируйте сырники и обжарьте на среднем огне до золотистой корочки",
//				"Подавайте со сметаной или вареньем"
//			),
			recipeType = RecipeType.BAKING,
			difficulty = Difficulty.EASY,
			otherInfo = OtherInfo(watches = 3200, likes = 2900)
		),

		Recipe(
			id = "11111126",
			userId = "85t6ir7f12v",
			name = "Куриные крылышки BBQ",
			description = "Хрустящие куриные крылышки в барбекю соусе",
			ingredients = listOf(
				Ingredient("Куриные крылья", "", 1.0, NutritionInfo(203.0, 30.5, 8.1, 0.0)),
				Ingredient("Соус BBQ", "", 150.0, NutritionInfo(172.0, 1.3, 0.6, 41.3)),
				Ingredient("Чеснок", "", 3.0, NutritionInfo(149.0, 6.4, 0.5, 33.1)),
				Ingredient("Паприка", "", 10.0, NutritionInfo(282.0, 14.1, 12.8, 53.9)),
				Ingredient("Соль", "", 5.0, NutritionInfo(0.0, 0.0, 0.0, 0.0)),
				Ingredient("Растительное масло", "", 30.0, NutritionInfo(884.0, 0.0, 100.0, 0.0))
			),
			cookingSpeed = 45,
//			steps = listOf(
//				"Замаринуйте крылья в смеси специй и масла на 1 час",
//				"Запекайте в духовке при 200°C 30 минут",
//				"Обмажьте соусом BBQ и запекайте еще 10 минут",
//				"Подавайте с сельдереем и соусом голубой сыр"
//			),
			recipeType = RecipeType.QUICK,
			difficulty = Difficulty.MEDIUM,
			otherInfo = OtherInfo(watches = 4100, likes = 3800)
		),

		

		Recipe(
			id = "11111127",
			userId = "85t6ir7f12v",
			name = "Веганский бургер",
			description = "Бургер с котлетой из нута и овощами",
			recipeImageBackgroundUrl = "https://magazine.tabris.ru/wp-content/uploads/2020/09/burger-feat.jpg",
//			ingredients = listOf(
//				Ingredient("Нут вареный", 300.0, Unit.GRAMS),
//				Ingredient("Булочки для бургеров", 2.0, Unit.PIECE),
//				Ingredient("Авокадо", 1.0, Unit.PIECE),
//				Ingredient("Помидор", 1.0, Unit.PIECE),
//				Ingredient("Листья салата", 50.0, Unit.GRAMS),
//				Ingredient("Чеснок", 2.0, Unit.PIECE),
//				Ingredient("Нутовая мука", 50.0, Unit.GRAMS),
//				Ingredient("Растительное масло", 20.0, Unit.GRAMS)
//			),
			cookingSpeed = 35,
//			steps = listOf(
//				"Измельчите нут в блендере с чесноком",
//				"Сформируйте котлеты, обваляйте в муке",
//				"Обжарьте котлеты на сковороде 5 минут с каждой стороны",
//				"Соберите бургер: булочка, котлета, овощи",
//				"Подавайте с картофелем фри"
//			),
			recipeType = RecipeType.VEGAN,
			difficulty = Difficulty.MEDIUM,
			otherInfo = OtherInfo(watches = 2300, likes = 2100)
		),

		Recipe(
			id = "11111128",
			userId = "85t6ir7f12v",
			name = "Тыквенный суп-пюре",
			description = "Нежный крем-суп из тыквы с имбирем и сливками",
			recipeImageBackgroundUrl = "https://resizer.mail.ru/p/2d7c09fc-e8c5-525a-9d43-1355e682070e/AQAF_R621nb9v34-idqT1_Ar6brDWBZhe0QJC5dgHiTziK2QV3jlEh9msIDfAWqBBNlL1uayhSTgei2NjjD5R5YQsPQ.jpg",
//			ingredients = listOf(
//				Ingredient("Тыква", 800.0, Unit.GRAMS),
//				Ingredient("Лук", 1.0, Unit.PIECE),
//				Ingredient("Чеснок", 2.0, Unit.PIECE),
//				Ingredient("Имбирь", 20.0, Unit.GRAMS),
//				Ingredient("Сливки", 0.2, Unit.LITERS),
//				Ingredient("Оливковое масло", 30.0, Unit.GRAMS),
//				Ingredient("Соль", null, null),
//				Ingredient("Перец", null, null)
//			),
			cookingSpeed = 40,
//			steps = listOf(
//				"Нарежьте тыкву кубиками",
//				"Обжарьте лук, чеснок и имбирь",
//				"Добавьте тыкву и воду, варите 25 минут",
//				"Измельчите блендером в пюре",
//				"Добавьте сливки и прогрейте",
//				"Подавайте с тыквенными семечками"
//			),
			recipeType = RecipeType.VEGAN,
			difficulty = Difficulty.EASY,
			otherInfo = OtherInfo(watches = 1800, likes = 1700)
		),

		Recipe(
			id = "11111129",
			userId = "85t6ir7f12v",
			name = "Безглютеновая пицца",
			description = "Пицца на основе цветной капусты без глютена",
			recipeImageBackgroundUrl = "https://grandkulinar.ru/uploads/posts/2020-03/1583253501_bezglyutenovaya-picca-iz-kukuruznoj-muki.jpg",
//			ingredients = listOf(
//				Ingredient("Цветная капуста", 500.0, Unit.GRAMS),
//				Ingredient("Яйца", 2.0, Unit.PIECE),
//				Ingredient("Сыр моцарелла", 200.0, Unit.GRAMS),
//				Ingredient("Томатный соус", 0.1, Unit.LITERS),
//				Ingredient("Оливки", 50.0, Unit.GRAMS),
//				Ingredient("Базилик", 10.0, Unit.GRAMS),
//				Ingredient("Кукурузная мука", 50.0, Unit.GRAMS)
//			),
			cookingSpeed = 50,
//			steps = listOf(
//				"Измельчите цветную капусту в блендере",
//				"Смешайте с яйцами и мукой, сформируйте основу",
//				"Выпекайте основу 20 минут при 180°C",
//				"Добавьте соус и начинку, запекайте еще 15 минут",
//				"Украсьте базиликом"
//			),
			recipeType = RecipeType.GLUTEN_FREE,
			difficulty = Difficulty.MEDIUM,
			otherInfo = OtherInfo(watches = 2700, likes = 2500)
		),

		Recipe(
			id = "11111130",
			userId = "WaAWgH3212",
			name = "Овсянный блин",
			description = "Полезный завтрак из овсянки с начинкой",
			recipeImageBackgroundUrl = "https://www.makfa.ru/upload/resize_cache/iblock/db7/450_450_1/mxqrmk7sv4qbehoi30l82xti7uw7l80v.jpg",
//			ingredients = listOf(
//				Ingredient("Овсяные хлопья", 50.0, Unit.GRAMS),
//				Ingredient("Яйца", 2.0, Unit.PIECE),
//				Ingredient("Молоко", 0.1, Unit.LITERS),
//				Ingredient("Сыр", 50.0, Unit.GRAMS),
//				Ingredient("Помидор", 1.0, Unit.PIECE),
//				Ingredient("Зелень", null, null),
//				Ingredient("Соль", null, null)
//			),
			cookingSpeed = 15,
//			steps = listOf(
//				"Смешайте овсянку, яйца и молоко",
//				"Вылейте на сковороду, жарьте 3 минуты",
//				"Переверните, добавьте начинку",
//				"Жарьте еще 2 минуты",
//				"Сверните пополам и подавайте"
//			),
			recipeType = RecipeType.QUICK,
			difficulty = Difficulty.EASY,
			otherInfo = OtherInfo(watches = 1900, likes = 1800)
		),

		Recipe(
			id = "11111131",
			userId = "WaAWgH3212",
			name = "Лосось в медово-горчичном соусе",
			description = "Нежное филе лосося с хрустящей корочкой",
			recipeImageBackgroundUrl = "https://static.vkusnyblog.com/full/uploads/2014/10/losos-v-medovo-gorchichnom-souse.jpg",
//			ingredients = listOf(
//				Ingredient("Филе лосося", 600.0, Unit.GRAMS),
//				Ingredient("Мед", 30.0, Unit.GRAMS),
//				Ingredient("Горчица", 20.0, Unit.GRAMS),
//				Ingredient("Лимон", 1.0, Unit.PIECE),
//				Ingredient("Чеснок", 2.0, Unit.PIECE),
//				Ingredient("Укроп", 10.0, Unit.GRAMS),
//				Ingredient("Оливковое масло", 20.0, Unit.GRAMS)
//			),
			cookingSpeed = 25,
//			steps = listOf(
//				"Смешайте мед, горчицу и измельченный чеснок",
//				"Обмажьте лосось соусом",
//				"Запекайте 15 минут при 200°C",
//				"Подавайте с дольками лимона и укропом"
//			),
			recipeType = RecipeType.COMPLEX,
			difficulty = Difficulty.MEDIUM,
			otherInfo = OtherInfo(watches = 3100, likes = 2900)
		),

		Recipe(
			id = "11111132",
			userId = "WaAWgH3212",
			name = "Шоколадный фондан",
			description = "Десерт с жидкой шоколадной серединкой",
			recipeImageBackgroundUrl = "https://eda.ru/images/RecipePhoto/390x390/shokoladnij-fondan_49755_photo_53437.jpg",
//			ingredients = listOf(
//				Ingredient("Темный шоколад", 200.0, Unit.GRAMS),
//				Ingredient("Сливочное масло", 100.0, Unit.GRAMS),
//				Ingredient("Яйца", 4.0, Unit.PIECE),
//				Ingredient("Сахар", 100.0, Unit.GRAMS),
//				Ingredient("Мука", 60.0, Unit.GRAMS),
//				Ingredient("Какао-порошок", 20.0, Unit.GRAMS)
//			),
			cookingSpeed = 30,
//			steps = listOf(
//				"Растопите шоколад с маслом",
//				"Взбейте яйца с сахаром",
//				"Смешайте все ингредиенты",
//				"Разлейте по формочкам, выпекайте 10 минут при 200°C",
//				"Подавайте сразу с мороженым"
//			),
			recipeType = RecipeType.BAKING,
			difficulty = Difficulty.HARD,
			otherInfo = OtherInfo(watches = 3800, likes = 3600)
		),

		Recipe(
			id = "11111133",
			userId = "WaAWgH3212",
			name = "Гречка с грибами",
			description = "Ароматная гречневая каша с лесными грибами",
			recipeImageBackgroundUrl = "https://cdn.lifehacker.ru/wp-content/uploads/2024/11/103_1732278919.jpg",
//			ingredients = listOf(
//				Ingredient("Гречка", 300.0, Unit.GRAMS),
//				Ingredient("Грибы", 200.0, Unit.GRAMS),
//				Ingredient("Лук", 1.0, Unit.PIECE),
//				Ingredient("Морковь", 1.0, Unit.PIECE),
//				Ingredient("Чеснок", 2.0, Unit.PIECE),
//				Ingredient("Зелень", null, null),
//				Ingredient("Сливочное масло", 30.0, Unit.GRAMS)
//			),
			cookingSpeed = 35,
//			steps = listOf(
//				"Обжарьте лук, морковь и грибы",
//				"Добавьте гречку и воду (2:1)",
//				"Варите под крышкой 20 минут",
//				"Добавьте чеснок и масло",
//				"Подавайте с зеленью"
//			),
			recipeType = RecipeType.QUICK,
			difficulty = Difficulty.EASY,
			otherInfo = OtherInfo(watches = 1500, likes = 1400)
		),

		Recipe(
			id = "11111134",
			userId = "WaAWgH3212",
			name = "Крем-суп из шампиньонов",
			description = "Нежный грибной суп с ароматными травами и сливками",
			recipeImageBackgroundUrl = "https://cdn.lifehacker.ru/wp-content/uploads/2020/04/shutterstock_562737943_1588005524-e1588005568972-scaled.jpg",
//			ingredients = listOf(
//				Ingredient("Шампиньоны", 500.0, Unit.GRAMS),
//				Ingredient("Лук репчатый", 1.0, Unit.PIECE),
//				Ingredient("Сливки 20%", 0.2, Unit.LITERS),
//				Ingredient("Картофель", 2.0, Unit.PIECE),
//				Ingredient("Чеснок", 2.0, Unit.PIECE),
//				Ingredient("Тимьян", 5.0, Unit.GRAMS),
//				Ingredient("Масло сливочное", 30.0, Unit.GRAMS)
//			),
			cookingSpeed = 35,
//			steps = listOf(
//				"Нарежьте грибы и овощи кубиками",
//				"Обжарьте лук и чеснок на сливочном масле до прозрачности",
//				"Добавьте грибы и обжаривайте 5 минут",
//				"Влейте 1 литр воды, добавьте картофель и варите 15 минут",
//				"Измельчите суп блендером, добавьте сливки и тимьян",
//				"Подавайте с гренками"
//			),
			recipeType = RecipeType.QUICK,
			difficulty = Difficulty.MEDIUM,
			otherInfo = OtherInfo(watches = 4200, likes = 3900)
		),

		Recipe(
			id = "11111135",
			userId = "85t6ir7f12v",
			name = "Лазанья со шпинатом и рикоттой",
			description = "Вегетарианская лазанья с нежной начинкой из шпината",
			recipeImageBackgroundUrl = "https://foodmood.ru/upload/iblock/3f0/3f0768727a901225a5246bad8d5f539e.jpg",
//			ingredients = listOf(
//				Ingredient("Листы лазаньи", 12.0, Unit.PIECE),
//				Ingredient("Шпинат", 400.0, Unit.GRAMS),
//				Ingredient("Сыр рикотта", 250.0, Unit.GRAMS),
//				Ingredient("Сыр пармезан", 100.0, Unit.GRAMS),
//				Ingredient("Чеснок", 3.0, Unit.PIECE),
//				Ingredient("Молоко", 0.5, Unit.LITERS),
//				Ingredient("Мука", 50.0, Unit.GRAMS),
//				Ingredient("Мускатный орех", 2.0, Unit.TEASPOONS)
//			),
			cookingSpeed = 75,
//			steps = listOf(
//				"Приготовьте бешамель: обжарьте муку, добавьте молоко и мускатный орех",
//				"Обжарьте шпинат с чесноком",
//				"Смешайте шпинат с рикоттой",
//				"Соберите лазанью, чередуя листы, соус и начинку",
//				"Посыпьте пармезаном и запекайте 40 минут при 180°C"
//			),
			recipeType = RecipeType.VEGAN,
			difficulty = Difficulty.HARD,
			otherInfo = OtherInfo(watches = 3800, likes = 3500)
		),

		Recipe(
			id = "11111136",
			userId = "85t6ir7f12v",
			name = "Безглютеновые банановые панкейки",
			description = "Пышные панкейки на миндальной муке с бананом",
			recipeImageBackgroundUrl =  "https://menunedeli.ru/wp-content/uploads/2023/04/E651A110-DF7F-44A8-8124-5B526BB1DAC6-933x700.jpeg",
//			ingredients = listOf(
//				Ingredient("Миндальная мука", 200.0, Unit.GRAMS),
//				Ingredient("Бананы", 2.0, Unit.PIECE),
//				Ingredient("Яйца", 3.0, Unit.PIECE),
//				Ingredient("Молоко", 100.0, Unit.GRAMS),
//				Ingredient("Разрыхлитель", 10.0, Unit.GRAMS),
//				Ingredient("Кленовый сироп", 50.0, Unit.GRAMS)
//			),
			cookingSpeed = 20,
//			steps = listOf(
//				"Разомните бананы вилкой",
//				"Смешайте все ингредиенты до однородной массы",
//				"Жарьте на среднем огне по 2 минуты с каждой стороны",
//				"Подавайте с кленовым сиропом и свежими ягодами"
//			),
			recipeType = RecipeType.GLUTEN_FREE,
			difficulty = Difficulty.EASY,
			otherInfo = OtherInfo(watches = 2900, likes = 2700)
		),

		Recipe(
			id = "11111137",
			userId = "85t6ir7f12v",
			name = "Куриные грудки в медово-горчичном соусе",
			description = "Нежные куриные грудки с хрустящей корочкой",
			recipeImageBackgroundUrl =  "https://img.iamcook.ru/2023/upl/recipes/cat/u-cfdce98cff5c8eef490159b1bfabea53.JPG",
//			ingredients = listOf(
//				Ingredient("Куриная грудка", 600.0, Unit.GRAMS),
//				Ingredient("Мед", 50.0, Unit.GRAMS),
//				Ingredient("Горчица", 30.0, Unit.GRAMS),
//				Ingredient("Чеснок", 3.0, Unit.PIECE),
//				Ingredient("Лимонный сок", 20.0, Unit.GRAMS),
//				Ingredient("Оливковое масло", 30.0, Unit.GRAMS)
//			),
			cookingSpeed = 30,
//			steps = listOf(
//				"Смешайте мед, горчицу, чеснок и лимонный сок",
//				"Замаринуйте курицу в соусе на 1 час",
//				"Обжарьте на сковороде по 5 минут с каждой стороны",
//				"Доведите до готовности в духовке 10 минут при 180°C"
//			),
			recipeType = RecipeType.QUICK,
			difficulty = Difficulty.MEDIUM,
			otherInfo = OtherInfo(watches = 3700, likes = 3400)
		),

		Recipe(
			id = "11111138",
			userId = "85t6ir7f12v",
			name = "Шоколадный мусс",
			description = "Воздушный шоколадный десерт с насыщенным вкусом",
			recipeImageBackgroundUrl = "https://burninghut.ru/wp-content/uploads/2024/09/poleznyj-shokoladnyj-muss.jpg",
//			ingredients = listOf(
//				Ingredient("Темный шоколад", 200.0, Unit.GRAMS),
//				Ingredient("Сливки 33%", 0.3, Unit.LITERS),
//				Ingredient("Яйца", 3.0, Unit.PIECE),
//				Ingredient("Сахар", 50.0, Unit.GRAMS),
//				Ingredient("Ванильный экстракт", 5.0, Unit.GRAMS)
//			),
			cookingSpeed = 45,
//			steps = listOf(
//				"Растопите шоколад на водяной бане",
//				"Взбейте желтки с сахаром и ванилью",
//				"Взбейте сливки до пиков",
//				"Аккуратно соедините все компоненты",
//				"Разложите по креманкам и охладите 4 часа"
//			),
			recipeType = RecipeType.BAKING,
			difficulty = Difficulty.MEDIUM,
			otherInfo = OtherInfo(watches = 3300, likes = 3100)
		),

		Recipe(
			id = "11111139",
			userId = "WaAWgH3212",
			name = "Морковный торт",
			description = "Влажный пряный торт с морковью и грецкими орехами",
			recipeImageBackgroundUrl = "https://eu-images.contentstack.com/v3/assets/bltf51e50af165afed2/blt7a8e91b8961ca7c2/65c47b7f48289b040a1e3800/%D0%BC%D0%BE%D1%80%D0%BA%D0%BE%D0%B2%D0%BD%D1%8B%D0%B9_%D1%82%D0%BE%D1%80%D1%82.jpeg",
//			ingredients = listOf(
//				Ingredient("Морковь", 300.0, Unit.GRAMS),
//				Ingredient("Мука", 250.0, Unit.GRAMS),
//				Ingredient("Яйца", 3.0, Unit.PIECE),
//				Ingredient("Сахар", 200.0, Unit.GRAMS),
//				Ingredient("Грецкие орехи", 100.0, Unit.GRAMS),
//				Ingredient("Растительное масло", 150.0, Unit.GRAMS),
//				Ingredient("Корица", 10.0, Unit.GRAMS),
//				Ingredient("Сода", 5.0, Unit.GRAMS)
//			),
			cookingSpeed = 80,
//			steps = listOf(
//				"Натрите морковь на мелкой терке",
//				"Смешайте сухие ингредиенты",
//				"Добавьте яйца, масло и морковь",
//				"Выпекайте 50 минут при 180°C",
//				"Подавайте со сливочным кремом"
//			),
			recipeType = RecipeType.BAKING,
			difficulty = Difficulty.HARD,
			otherInfo = OtherInfo(watches = 2700, likes = 2500)
		)
	)
}