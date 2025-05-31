package com.culinario.helpers

import com.culinario.mvp.models.DetailedCookingStep
import com.culinario.mvp.models.Difficulty
import com.culinario.mvp.models.Ingredient
import com.culinario.mvp.models.NutritionInfo
import com.culinario.mvp.models.OtherInfo
import com.culinario.mvp.models.Recipe
import com.culinario.mvp.models.RecipeType

class RecipeRepositoryImpl {
	val recipes = mutableListOf (
		Recipe(
			id = "11111114",
			userId = "EOmncsE5uZMsGl8HEJ1NYcmcQTw1",
			name = "Веганский салат с киноа",
			description = "Питательный салат с киноа, овощами и лимонной заправкой.",
			recipeImagesUrl = listOf(
				"https://images.gastronom.ru/FmZZAOzyawm4CWlwitR-prviBXOwD39-VXMlxyAxDug/pr:content-group-cover-image/g:ce/rs:auto:0:0:0/L2Ntcy9hbGwtaW1hZ2VzL2I4NmRjNTRkLWMyOWMtNGNhNi05MjQ5LTQ1YmFlNDE3ZmUxNy5qcGc.webp",
				"https://static.1000.menu/img/content-v2/42/f7/38318/salat-s-kinoa-i-avokado_1568554667_1_max.jpg"
			),
			ingredients = listOf(
				Ingredient("Киноа", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSrzW8lEB5GaS0mYxBquAxwYV0TL_st3dB_Pw&s", 200.0, "г.", NutritionInfo(200.0, 8.0, 4.0, 36.0)),
				Ingredient("Огурец", "https://usolie.info/userfiles/picfullsize/image-1739392867_025.jpg", 1.0, "шт.", NutritionInfo(36.0, 1.6, 0.4, 7.8)),
			),
			cookingSpeed = 15,
			steps = listOf(
				DetailedCookingStep(
					imageUrl = "https://cdn.viqeo.tv/storage/e3/42/32e8c0fb2746baeb4ce8f5f0b5442778.jpg",
					title = "Приготовление киноа",
					time = 900,
					description = "Тщательно промойте киноа под проточной водой. В кастрюле доведите до кипения 400 мл воды, добавьте киноа и варите на медленном огне 15 минут, пока вся вода не впитается."
				),
				DetailedCookingStep(
					imageUrl = "https://di-so.ru/wp-content/themes/yootheme/cache/03/nutrimun-blog-51-03b9aa9f.jpeg",
					title = "Нарезка овощей",
					time = 300,
					description = "Огурец и помидоры нарежьте кубиками среднего размера. Авокадо очистите от кожуры, удалите косточку и нарежьте ломтиками."
				),
				DetailedCookingStep(
					imageUrl = "https://eda.ru/images/RecipeStep/434x295/pravilnaya-zapravka-dlya-salata-cezar_174035_step_1.jpg",
					title = "Приготовление заправки",
					time = 120,
					description = "В небольшой миске смешайте лимонный сок, оливковое масло, щепотку соли. Взбейте венчиком до однородной эмульсии."
				),
				DetailedCookingStep(
					imageUrl = "https://img.freepik.com/free-photo/mid-shot-chef-mixing-salad-ingredients_23-2148794093.jpg",
					title = "Смешивание ингредиентов",
					time = 180,
					description = "В большой салатнице соедините охлажденную киноа, нарезанные овощи и авокадо. Аккуратно перемешайте."
				),
				DetailedCookingStep(
					imageUrl = "https://img.freepik.com/premium-photo/process-preparing-sauce-salad-kitchen-woman-pouring-sauce-into-glass-bowl-with-salad-ingredients_392895-494680.jpg",
					title = "Заправка салата",
					time = 60,
					description = "Полейте салат приготовленной лимонно-оливковой заправкой. Еще раз аккуратно перемешайте."
				),
				DetailedCookingStep(
					imageUrl = "https://images.gastronom.ru/FmZZAOzyawm4CWlwitR-prviBXOwD39-VXMlxyAxDug/pr:content-group-cover-image/g:ce/rs:auto:0:0:0/L2Ntcy9hbGwtaW1hZ2VzL2I4NmRjNTRkLWMyOWMtNGNhNi05MjQ5LTQ1YmFlNDE3ZmUxNy5qcGc.webp",
					title = "Подача",
					time = 120,
					description = "Перед подачей украсьте салат веточками петрушки. Можно посыпать кунжутом или тыквенными семечками для дополнительного хруста."
				)
			),
			nutritionInfo = NutritionInfo(
				calories = 340.0,
				proteins = 32.0,
				fats = 8.0,
				carbohydrates = 12.0
			),
			recipeType = RecipeType.VEGAN,
			difficulty = Difficulty.EASY,
			otherInfo = OtherInfo(watches = 0, likes = 0)
		),

		Recipe(
			id = "11111115",
			userId = "EOmncsE5uZMsGl8HEJ1NYcmcQTw1",
			name = "Классические блины",
			recipeImagesUrl = listOf(
				"https://povarenok.by/uploads/images/recepty/big/klassicheskiy-recept-blinov.jpg",
				"https://marr.ru/upload/resize_cache/webp/iblock/5e1/ev08ik5jqntd5i3z25i15hiog4job9jp.webp"
			),
			description = "Традиционные русские блины на молоке, тонкие и ароматные",
			ingredients = listOf(
				Ingredient("Мука", "https://tsx.x5static.net/i/800x800-fit/xdelivery/files/eb/1b/5a2d4687c78d8d7bc41032d7c50c.jpg", 200.0, "г.", NutritionInfo(364.0, 10.3, 1.0, 76.3)),
				Ingredient("Молоко", "https://kozalavka.ru/a/kozadereza/files//import/iiko_img_afbf2332-ab1d-445e-aab5-b761e69868e4_f6b9d346-f6ce-4241-9bfa-b008c55ef529.jpg", 500.0, "мл.", NutritionInfo(60.0, 3.2, 3.6, 4.8)),
				Ingredient("Яйца", "https://efspb.ru/upload/iblock/209/ig78ehdky1d5p28ujt7nyund94ex797x.jpg", 2.0, "шт.", NutritionInfo(143.0, 12.6, 9.5, 0.7)),
				Ingredient("Сахар", "https://www.agroinvestor.ru/upload/iblock/b51/b51e3524ea6be6464b92b4599fceb25d.jpg", 30.0, "г.", NutritionInfo(387.0, 0.0, 0.0, 100.0)),
				Ingredient("Соль", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTVlpSDxpyfPf058KpXAlGqeKlLwNRjwC_DNA&s", 5.0, "г.", NutritionInfo(0.0, 0.0, 0.0, 0.0)),
				Ingredient("Масло растительное", "https://www.rbc.ua/static/img/b/1/b117cff8_4169_49ec_8076_a7c45b0886d2_650x410.jpg", 30.0, "мл.", NutritionInfo(884.0, 0.0, 100.0, 0.0))
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
			otherInfo = OtherInfo(watches = 0, likes = 0)
		),

		Recipe(
			id = "11111116",
			userId = "EOmncsE5uZMsGl8HEJ1NYcmcQTw1",
			name = "Веганский салат с нутом",
			description = "Питательный салат с нутом, свежими овощами и лимонной заправкой",
			recipeImagesUrl = listOf(
				"https://eda.ru/images/RecipePhoto/4x3/salat-s-nutom-i-svezhimi-ovoshhami_53998_photo_64819.jpg",
				"https://images.gastronom.ru/enUVdy4bP6CU4Y6snO1NusF2zY-c1-5ZGSyAU6ak1Bo/pr:recipe-preview-image/g:ce/rs:auto:0:0:0/L2Ntcy9hbGwtaW1hZ2VzLzg5OWViN2UxLWI4MWUtNDBlZi05MWYzLTA1Y2U2MTZmZWNmNi5qcGc"
			),
			ingredients = listOf(
				Ingredient("Нут вареный", "https://fort.crimea.com/catering/uploads/fotos/8025c67d-9d5e-11e9-9c72-54a05051519a_1.jpg", 200.0, "г.", NutritionInfo(164.0, 8.9, 2.6, 27.4)),
				Ingredient("Огурец", "https://usolie.info/userfiles/picfullsize/image-1739392867_025.jpg", 1.0, "шт.", NutritionInfo(16.0, 0.7, 0.1, 3.6)),
				Ingredient("Помидор", "https://kuban24.tv/wp-content/uploads/2023/06/photo_2023-06-20_13-18-40.jpg", 2.0, "шт.", NutritionInfo(22.0, 1.1, 0.2, 4.8)),
				Ingredient("Красный лук", "https://kuban24.tv/wp-content/uploads/2024/04/photo_2024-04-21_16-53-12.jpg", 1.0, "шт.", NutritionInfo(40.0, 1.1, 0.1, 9.3)),
				Ingredient("Петрушка", "https://organicmarket.ru/files/images/product/large_image/0/3407/petrushka_63319cb86ef34.jpg", 20.0, "г.", NutritionInfo(36.0, 3.0, 0.8, 6.3)),
				Ingredient("Лимонный сок", "https://images.gastronom.ru/BI0TG0GHGHlwieEsbbFUtQBti_qllKtfuelUzlmtcS0/pr:product-preview-image/g:ce/rs:auto:0:0:0/L2Ntcy9hbGwtaW1hZ2VzLzJlMGJjNDY0LTgxOWMtNDliMS05ZTk2LTg3ZmM4YmZkYTYyNS5qcGc.webp", 30.0, "мл.", NutritionInfo(6.0, 0.1, 0.0, 2.0)),
				Ingredient("Оливковое масло", "https://kudri-brovi.ru/wp-content/uploads/2023/08/%D0%BE%D0%BB%D0%B8%D0%B2%D0%BA%D0%BE%D0%B2%D0%BE%D0%B5-%D0%BC%D0%B0%D1%81%D0%BB%D0%BE-%D0%B0%D0%B2%D0%B0.jpg", 20.0, "мл.", NutritionInfo(884.0, 0.0, 100.0, 0.0))
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
			otherInfo = OtherInfo(watches = 0, likes = 0)
		),

		Recipe(
			id = "11111117",
			userId = "EOmncsE5uZMsGl8HEJ1NYcmcQTw1",
			name = "Лазанья Болоньезе",
			description = "Классическая итальянская лазанья с мясным соусом",
			recipeImagesUrl = listOf(
				"https://barilla.ru/wp-content/uploads/2023/12/lazanja-boloneze-min_11zon.webp",
				"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQuJEfMc1jDCenbN7Xvcigysy6FCC02TUXKyg&s"
			),
			ingredients = listOf(
				Ingredient("Листы для лазаньи", "https://www.patee.ru/r/x6/10/00/00/960m.jpg", 12.0, "шт.", NutritionInfo(131.0, 5.1, 0.6, 25.0)),
				Ingredient("Фарш говяжий", "https://мясной-хуторок.рф/image/catalog/polufabrikati/1-1559.jpg", 500.0, "г.",  NutritionInfo(250.0, 26.0, 15.0, 0.0)),Ingredient("Лук", "", 1.0, "шт.",  NutritionInfo(40.0, 1.1, 0.1, 9.3)),
				Ingredient("Морковь", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQFUXsVcPr9mslgzSrcGB-j0yUCocYzIikQ-Q&s", 1.0, "шт.", NutritionInfo(41.0, 0.9, 0.2, 9.6)),
				Ingredient("Чеснок", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQw-Dz7thX_MzHRwrzaI7nO-PUWkq9cbWbk7w&s", 2.0, "шт.", NutritionInfo(149.0, 6.4, 0.5, 33.1)),
				Ingredient("Томатная паста", "https://ru.s.bekhost.com/uploads/files/lukovichnye/dom_pasta_04.jpg", 100.0, "гр.", NutritionInfo(82.0, 4.3, 0.5, 18.9)),
				Ingredient("Сыр пармезан", "https://gastronauta.ru/wp-content/uploads/2022/02/%D0%BF%D0%B0%D1%80%D0%BC%D0%B8%D0%B4%D0%B67-%E2%80%94-%D0%BA%D0%BE%D0%BF%D0%B8%D1%8F.webp", 150.0, "гр.", NutritionInfo(392.0, 35.8, 25.8, 3.2)),
				Ingredient("Сыр моцарелла", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSlZ4zOGAVmcvJsolP3-pDipxzQJK90aZPaYg&s", 200.0, "гр.", NutritionInfo(280.0, 28.0, 17.0, 3.1)),
				Ingredient("Молоко", "https://kozalavka.ru/a/kozadereza/files//import/iiko_img_afbf2332-ab1d-445e-aab5-b761e69868e4_f6b9d346-f6ce-4241-9bfa-b008c55ef529.jpg", 500.0, "мл.", NutritionInfo(60.0, 3.2, 3.6, 4.8)),
				Ingredient("Мука", "https://tsx.x5static.net/i/800x800-fit/xdelivery/files/eb/1b/5a2d4687c78d8d7bc41032d7c50c.jpg", 50.0, "гр.", NutritionInfo(364.0, 10.3, 1.0, 76.3)),
				Ingredient("Сливочное масло", "https://www.mosregfermer.ru/upload/iblock/32a/32a2a99e6ddd6f1d7c205d60539802e1.jpg", 50.0, "гр.", NutritionInfo(717.0, 0.9, 81.1, 0.1))
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
			otherInfo = OtherInfo(watches = 0, likes = 0)
		),

		Recipe(
			id = "11111118",
			userId = "EOmncsE5uZMsGl8HEJ1NYcmcQTw1",
			name = "Безглютеновые оладьи",
			description = "Пышные оладьи на гречневой муке без глютена",
			recipeImagesUrl = listOf(
				"https://menunedeli.ru/wp-content/uploads/2022/02/Oladi-s-jablokom-bez-gljutena-gotovy_opt-1200x800.jpg",
				"https://garnec.com/upload/iblock/1f2/8ft1atfps41q3e289pphqv9z96rzej6l/Screenshot_3.jpg"
			),
			ingredients = listOf(
				Ingredient("Гречневая мука", "https://mukomoloff.ru/upload/resize_cache/webp/iblock/57f/hsg4d35qzklpk6i622y205de01yg6crr.webp", 250.0, "гр.", NutritionInfo(343.0, 13.3, 3.4, 71.5)),
				Ingredient("Кефир", "https://optimatest.ru/wp-content/uploads/2021/03/shutterstock_1420656311.jpeg", 300.0, "мл.", NutritionInfo(56.0, 3.4, 2.0, 4.7)),
				Ingredient("Яйца", "https://efspb.ru/upload/iblock/209/ig78ehdky1d5p28ujt7nyund94ex797x.jpg", 2.0, "шт.", NutritionInfo(143.0, 12.6, 9.5, 0.7)),
				Ingredient("Сода", "https://s3.coolclever.tech/img/0000000090025352/1000/15916.webp", 5.0, "гр.", NutritionInfo(0.0, 0.0, 0.0, 0.0)),
				Ingredient("Сахар", "https://www.agroinvestor.ru/upload/iblock/b51/b51e3524ea6be6464b92b4599fceb25d.jpg", 30.0, "гр.", NutritionInfo(387.0, 0.0, 0.0, 100.0)),
				Ingredient("Масло растительное", "https://www.rbc.ua/static/img/b/1/b117cff8_4169_49ec_8076_a7c45b0886d2_650x410.jpg", 20.0, "мл.", NutritionInfo(884.0, 0.0, 100.0, 0.0))
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
			otherInfo = OtherInfo(watches = 0, likes = 0)
		),

		Recipe(
			id = "11111119",
			userId = "EOmncsE5uZMsGl8HEJ1NYcmcQTw1",
			name = "Куриный суп с лапшой",
			description = "Ароматный домашний куриный суп с лапшой и овощами",
			recipeImagesUrl = listOf(
				"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTFBpjC2BtVdmIZyaAHJ3_KZU_x2boapqylbg&s",
				"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSCklqt3TExLLJE3jxYzhFASn2Fr6VqGChzsw&s"
			),
			ingredients = listOf(
				Ingredient("Курица (грудка)", "https://primemeat.ru/upload/iblock/e8b/iipdtp2zal50vkuvnxbcp44c8h3r9vma/grudka_kurinaya_bez_kozhi_na_kosti.jpg", 400.0, "гр.", NutritionInfo(165.0, 31.0, 3.6, 0.0)),
				Ingredient("Лапша", "https://grandkulinar.ru/uploads/posts/2014-10/1412772681_kitajskaya-lapsha-v-domashnix-usloviyax.jpg", 150.0, "гр.", NutritionInfo(131.0, 5.1, 0.6, 25.0)),
				Ingredient("Морковь", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQFUXsVcPr9mslgzSrcGB-j0yUCocYzIikQ-Q&s", 1.0, "шт.", NutritionInfo(41.0, 0.9, 0.2, 9.6)),
				Ingredient("Лук", "https://turgor.net/wp-content/uploads/2025/05/types_onion.webp", 1.0, "шт.", NutritionInfo(40.0, 1.1, 0.1, 9.3)),
				Ingredient("Картофель", "", 3.0, "шт.", NutritionInfo(77.0, 2.0, 0.1, 17.0)),
				Ingredient("Зелень", "https://здоровое-питание.рф/upload/iblock/b95/xc1iusq3rlr74fw21kinrddc2tip04dm/004_2.jpg", null, "", NutritionInfo(49.0, 3.7, 0.4, 7.6)),
				Ingredient("Соль", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTVlpSDxpyfPf058KpXAlGqeKlLwNRjwC_DNA&s", null, "", NutritionInfo(0.0, 0.0, 0.0, 0.0)),
				Ingredient("Перец горошком", "", 5.0, "шт.", NutritionInfo(251.0, 10.4, 3.3, 64.8))
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
			otherInfo = OtherInfo(watches = 0, likes = 0)
		),

		Recipe(
			id = "11111120",
			userId = "EOmncsE5uZMsGl8HEJ1NYcmcQTw1",
			name = "Шоколадные маффины",
			description = "Нежные шоколадные маффины с жидкой серединкой",
			recipeImagesUrl = listOf(
				"https://i2.wp.com/foxychef.ru/wp-content/uploads/2016/08/MG_2027.jpg?fit=900%2C600",
				"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTyNw8-GQv82s5LMhfMWO6hzuyTmSjoaFlMLw&s"
			),
			ingredients = listOf(
				Ingredient("Мука", "https://tsx.x5static.net/i/800x800-fit/xdelivery/files/eb/1b/5a2d4687c78d8d7bc41032d7c50c.jpg", 200.0, "гр.", NutritionInfo(364.0, 10.3, 1.0, 76.3)),
				Ingredient("Сахар", "https://www.agroinvestor.ru/upload/iblock/b51/b51e3524ea6be6464b92b4599fceb25d.jpg", 150.0, "гр.", NutritionInfo(387.0, 0.0, 0.0, 100.0)),
				Ingredient("Какао-порошок", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSHZaHicO6hi_CkNUKQEC-1D9BiUCXMYNmNWA&s", 50.0, "гр.", NutritionInfo(228.0, 19.6, 13.7, 57.9)),
				Ingredient("Яйца", "https://efspb.ru/upload/iblock/209/ig78ehdky1d5p28ujt7nyund94ex797x.jpg", 2.0, "шт.", NutritionInfo(143.0, 12.6, 9.5, 0.7)),
				Ingredient("Сливочное масло", "https://www.mosregfermer.ru/upload/iblock/32a/32a2a99e6ddd6f1d7c205d60539802e1.jpg", 100.0, "гр.", NutritionInfo(717.0, 0.9, 81.1, 0.1)),
				Ingredient("Шоколад", "https://78centr.ru/wp-content/uploads/2024/07/1674503298_catherineasquithgallery-com-p-fon-gorkii-shokolad-30.jpg", 100.0, "гр.", NutritionInfo(546.0, 4.9, 31.3, 61.2)),
				Ingredient("Разрыхлитель", "https://candy-chef.ru/image/cache/catalog/ingredienty/razryhliteldljatestadr.oetker10gr-min-500x500.jpg", 10.0, "гр.", NutritionInfo(0.0, 0.0, 0.0, 0.0))
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
			otherInfo = OtherInfo(watches = 0, likes = 0)
		),

		Recipe(
			id = "11111121",
			userId = "EOmncsE5uZMsGl8HEJ1NYcmcQTw1",
			name = "Греческий салат",
			description = "Классический греческий салат с фетаксой и оливками",
			recipeImagesUrl = listOf(
				"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQvSNe3lqfZfNDxBNVhMKkqeB_QbLMAZHfY7w&s",
				"https://cookhelp.ru/upload/recipes/salat_grecheskiy_klassicheskiy.jpg"
			),
			ingredients = listOf(
				Ingredient("Помидоры", "https://kuban24.tv/wp-content/uploads/2023/06/photo_2023-06-20_13-18-40.jpg", 3.0, "шт.", NutritionInfo(22.0, 1.1, 0.2, 4.8)),
				Ingredient("Огурец", "https://usolie.info/userfiles/picfullsize/image-1739392867_025.jpg", 1.0, "шт.", NutritionInfo(16.0, 0.7, 0.1, 3.6)),
				Ingredient("Лук красный", "https://kuban24.tv/wp-content/uploads/2024/04/photo_2024-04-21_16-53-12.jpg", 1.0, "шт.", NutritionInfo(40.0, 1.1, 0.1, 9.3)),
				Ingredient("Сыр фета", "https://cheezu.ru/wp-content/uploads/2020/02/cheese-feta.jpg", 200.0, "гр.", NutritionInfo(264.0, 14.2, 21.3, 4.1)),
				Ingredient("Оливки", "https://s3.coolclever.tech/img/0000000090027471/1000/21413.webp", 100.0, "гр.", NutritionInfo(115.0, 0.8, 10.7, 6.3)),
				Ingredient("Оливковое масло", "https://kudri-brovi.ru/wp-content/uploads/2023/08/%D0%BE%D0%BB%D0%B8%D0%B2%D0%BA%D0%BE%D0%B2%D0%BE%D0%B5-%D0%BC%D0%B0%D1%81%D0%BB%D0%BE-%D0%B0%D0%B2%D0%B0.jpg", 50.0, "мл.", NutritionInfo(884.0, 0.0, 100.0, 0.0)),
				Ingredient("Лимонный сок", "https://images.gastronom.ru/BI0TG0GHGHlwieEsbbFUtQBti_qllKtfuelUzlmtcS0/pr:product-preview-image/g:ce/rs:auto:0:0:0/L2Ntcy9hbGwtaW1hZ2VzLzJlMGJjNDY0LTgxOWMtNDliMS05ZTk2LTg3ZmM4YmZkYTYyNS5qcGc.webp", 20.0, "мл.", NutritionInfo(6.0, 0.1, 0.0, 2.0)),
				Ingredient("Орегано", "https://img.sunfruits.ru/images/products/1/4629/152932885/577.750x0.jpg", null, "", NutritionInfo(306.0, 11.0, 10.3, 64.4))
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
			otherInfo = OtherInfo(watches = 0, likes = 0)
		),

		Recipe(
			id = "11111122",
			userId = "EOmncsE5uZMsGl8HEJ1NYcmcQTw1",
			name = "Рататуй",
			description = "Традиционное французское овощное рагу из баклажанов, кабачков и перцев",
			recipeImagesUrl = listOf(
				"https://eda.ru/images/RecipeOpenGraph/1200x630/ratatuy-s-sousom_51981_ogimage.jpg",
				"https://fire-house.ru/wp-content/uploads/2023/02/kkepk.png"
			),
			ingredients = listOf(
				Ingredient("Баклажан", "https://www.chefmarket.ru/blog/wp-content/uploads/2020/08/heap-of-fresh-eggplants-2000x1200.jpg", 1.0, "шт.", NutritionInfo(25.0, 1.0, 0.2, 6.0)),
				Ingredient("Кабачок", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTtsUZvNrhhwR_OwOjD7aAHooHMPkAGH9a6NA&s", 1.0, "шт.", NutritionInfo(17.0, 1.2, 0.3, 3.1)),
				Ingredient("Перец болгарский", "https://s7.stc.all.kpcdn.net/family/wp-content/uploads/2022/02/bolgarskij-perecz-polza-i-vred-dlya-organizma-960x540.jpg", 2.0, "шт.", NutritionInfo(31.0, 1.0, 0.3, 6.0)),
				Ingredient("Помидоры", "https://kuban24.tv/wp-content/uploads/2023/06/photo_2023-06-20_13-18-40.jpg", 4.0, "шт.", NutritionInfo(22.0, 1.1, 0.2, 4.8)),
				Ingredient("Лук", "https://turgor.net/wp-content/uploads/2025/05/types_onion.webp", 1.0, "шт.", NutritionInfo(40.0, 1.1, 0.1, 9.3)),
				Ingredient("Чеснок", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQw-Dz7thX_MzHRwrzaI7nO-PUWkq9cbWbk7w&s", 3.0, "шт.", NutritionInfo(149.0, 6.4, 0.5, 33.1)),
				Ingredient("Оливковое масло", "https://kudri-brovi.ru/wp-content/uploads/2023/08/%D0%BE%D0%BB%D0%B8%D0%B2%D0%BA%D0%BE%D0%B2%D0%BE%D0%B5-%D0%BC%D0%B0%D1%81%D0%BB%D0%BE-%D0%B0%D0%B2%D0%B0.jpg", 50.0, "мл.", NutritionInfo(884.0, 0.0, 100.0, 0.0)),
				Ingredient("Прованские травы", "https://kuban24.tv/wp-content/uploads/2023/11/tguyi.jpg", null, "гр.", NutritionInfo(306.0, 11.0, 10.3, 64.4))
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
			otherInfo = OtherInfo(watches = 0, likes = 0)
		),

		Recipe(
			id = "11111123",
			userId = "EOmncsE5uZMsGl8HEJ1NYcmcQTw1",
			name = "Тирамису",
			description = "Итальянский десерт с кофейным вкусом и нежным кремом",
			recipeImagesUrl = listOf(
				"https://i0.wp.com/jennysmile83.com/wp-content/uploads/2024/11/91c8b3d1-a574-489b-8de6-e07fed81a5b5.jpeg?ssl=1",
				"https://upload.wikimedia.org/wikipedia/commons/thumb/e/e7/Classic_Italian_Tiramisu-3_%2829989504485%29.jpg/960px-Classic_Italian_Tiramisu-3_%2829989504485%29.jpg"
			),
			ingredients = listOf(
				Ingredient("Сыр маскарпоне", "https://www.italioni.ru/upload/iblock/1a2/vmcrc6xxvnd396hxwj0869m2er8imjnq.jpg", 500.0, "гр.", NutritionInfo(429.0, 7.1, 46.4, 2.6)),
				Ingredient("Печенье савоярди", "https://cookhelp.ru/upload/recipes/pechenye_savoyardi.jpg", 200.0, "гр.", NutritionInfo(386.0, 10.6, 9.1, 66.7)),
				Ingredient("Яйца", "https://efspb.ru/upload/iblock/209/ig78ehdky1d5p28ujt7nyund94ex797x.jpg", 4.0, "шт.", NutritionInfo(143.0, 12.6, 9.5, 0.7)),
				Ingredient("Сахар", "https://www.agroinvestor.ru/upload/iblock/b51/b51e3524ea6be6464b92b4599fceb25d.jpg", 100.0, "гр.", NutritionInfo(387.0, 0.0, 0.0, 100.0)),
				Ingredient("Кофе espresso macchiato", "https://upload.wikimedia.org/wikipedia/commons/4/40/Espresso_Macchiato_%28Tommy_Cash_song%29.jpg", 200.0, "мл.", NutritionInfo(1.0, 0.1, 0.0, 0.0)),
				Ingredient("Какао-порошок", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSHZaHicO6hi_CkNUKQEC-1D9BiUCXMYNmNWA&s", 30.0, "гр.", NutritionInfo(228.0, 19.6, 13.7, 57.9))
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
			otherInfo = OtherInfo(watches = 0, likes = 0)
		),

		Recipe(
			id = "11111124",
			userId = "EOmncsE5uZMsGl8HEJ1NYcmcQTw1",
			name = "Омлет с овощами",
			description = "Пышный омлет с болгарским перцем, помидорами и зеленью",
			recipeImagesUrl = listOf(
				"https://static.1000.menu/img/content-v2/4d/d2/6308/omlet-s-ovoshchami-i-syrom-na-skovorode_1619073092_18_max.jpg",
				"https://img.iamcook.ru/old/upl/recipes/cat/u5846-1bcd338712a2f42f827d7b51cc234882.JPG"
			),
			ingredients = listOf(
				Ingredient("Яйца", "https://efspb.ru/upload/iblock/209/ig78ehdky1d5p28ujt7nyund94ex797x.jpg", 4.0, "шт.", NutritionInfo(143.0, 12.6, 9.5, 0.7)),
				Ingredient("Молоко", "https://kozalavka.ru/a/kozadereza/files//import/iiko_img_afbf2332-ab1d-445e-aab5-b761e69868e4_f6b9d346-f6ce-4241-9bfa-b008c55ef529.jpg", 50.0, "мл.", NutritionInfo(60.0, 3.2, 3.6, 4.8)),
				Ingredient("Помидор", "https://kuban24.tv/wp-content/uploads/2023/06/photo_2023-06-20_13-18-40.jpg", 1.0, "шт.", NutritionInfo(22.0, 1.1, 0.2, 4.8)),
				Ingredient("Перец болгарский", "https://s7.stc.all.kpcdn.net/family/wp-content/uploads/2022/02/bolgarskij-perecz-polza-i-vred-dlya-organizma-960x540.jpg", 1.0, "шт.", NutritionInfo(31.0, 1.0, 0.3, 6.0)),
				Ingredient("Зелень", "https://здоровое-питание.рф/upload/iblock/b95/xc1iusq3rlr74fw21kinrddc2tip04dm/004_2.jpg", null, "", NutritionInfo(49.0, 3.7, 0.4, 7.6)),
				Ingredient("Соль", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTVlpSDxpyfPf058KpXAlGqeKlLwNRjwC_DNA&s", null, "по вкусу", NutritionInfo(0.0, 0.0, 0.0, 0.0)),
				Ingredient("Масло растительное", "https://www.rbc.ua/static/img/b/1/b117cff8_4169_49ec_8076_a7c45b0886d2_650x410.jpg", 20.0, "мл.", NutritionInfo(884.0, 0.0, 100.0, 0.0))
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
			otherInfo = OtherInfo(watches = 0, likes = 0)
		),

		Recipe(
			id = "11111125",
			userId = "EOmncsE5uZMsGl8HEJ1NYcmcQTw1",
			name = "Сырники классические",
			description = "Нежные творожные сырники с хрустящей корочкой",
			recipeImagesUrl = listOf(
				"https://s1.eda.ru/StaticContent/Photos/0/20/020aee5b808f46a18684da8abe8743a4.jpg",
				"https://baking-academy.ru/upload/iblock/d58/d58f029b2bd9cf8bd49714d02b29b6a1.jpeg"
			),
			ingredients = listOf(
				Ingredient("Творог", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTNWg5JK1__yO4fUsPJw3K_2H9i78Uv-ZF4AQ&s", 500.0, "гр.", NutritionInfo(159.0, 17.2, 5.0, 3.4)),
				Ingredient("Яйца", "https://efspb.ru/upload/iblock/209/ig78ehdky1d5p28ujt7nyund94ex797x.jpg", 2.0, "шт.", NutritionInfo(143.0, 12.6, 9.5, 0.7)),
				Ingredient("Мука", "https://tsx.x5static.net/i/800x800-fit/xdelivery/files/eb/1b/5a2d4687c78d8d7bc41032d7c50c.jpg", 100.0, "гр.", NutritionInfo(364.0, 10.3, 1.0, 76.3)),
				Ingredient("Сахар", "https://www.agroinvestor.ru/upload/iblock/b51/b51e3524ea6be6464b92b4599fceb25d.jpg", 50.0, "гр.", NutritionInfo(387.0, 0.0, 0.0, 100.0)),
				Ingredient("Ванильный сахар", "https://upload.wikimedia.org/wikipedia/commons/0/07/Vanillezucker.jpg", 10.0, "гр.", NutritionInfo(394.0, 0.0, 0.0, 98.0)),
				Ingredient("Масло растительное", "https://www.rbc.ua/static/img/b/1/b117cff8_4169_49ec_8076_a7c45b0886d2_650x410.jpg", 50.0, "мл.", NutritionInfo(884.0, 0.0, 100.0, 0.0))
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
			otherInfo = OtherInfo(watches = 0, likes = 0)
		),

		Recipe(
			id = "11111126",
			userId = "EOmncsE5uZMsGl8HEJ1NYcmcQTw1",
			name = "Куриные крылышки BBQ",
			description = "Хрустящие куриные крылышки в барбекю соусе",
			recipeImagesUrl = listOf(
				"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQiyzGJawj0DoTWAOJEpzr-FQnqbcr3fG61vg&s",
				"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQTl5fNI9Sch6x0AjUzXg-FytKSgWWhjbc2rw&s"
			),
			ingredients = listOf(
				Ingredient("Куриные крылья", "https://pticaopt.ru/image/cache/catalog/image/myaso/kurica/krilo%20zoloto-900x675.jpg", 1.0, "шт.", NutritionInfo(203.0, 30.5, 8.1, 0.0)),
				Ingredient("Соус BBQ", "https://images.gastronom.ru/i6jSU5xcjWkE26svDthGr2Js3nPDV8I37SBk2Lz1S4Y/pr:recipe-cover-image/g:ce/rs:auto:0:0:0/L2Ntcy9hbGwtaW1hZ2VzLzE3MDJiMDIzLTllYjYtNDRmNi05ZWY2LTJmYWU0NjI0ZmMzYy5qcGc.webp", 150.0, "мл.", NutritionInfo(172.0, 1.3, 0.6, 41.3)),
				Ingredient("Чеснок", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQw-Dz7thX_MzHRwrzaI7nO-PUWkq9cbWbk7w&s", 3.0, "шт.", NutritionInfo(149.0, 6.4, 0.5, 33.1)),
				Ingredient("Паприка", "https://upload.wikimedia.org/wikipedia/commons/c/c2/Pimenton-ahumado-candeleda.jpg", 10.0, "гр.", NutritionInfo(282.0, 14.1, 12.8, 53.9)),
				Ingredient("Соль", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTVlpSDxpyfPf058KpXAlGqeKlLwNRjwC_DNA&s", 5.0, "гр.", NutritionInfo(0.0, 0.0, 0.0, 0.0)),
				Ingredient("Растительное масло", "https://xaviar.ru/uploads/product/100/175/zolotaya-semechka-1_2022-03-14_14-11-17.png", 30.0, "мл.", NutritionInfo(884.0, 0.0, 100.0, 0.0))
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
			otherInfo = OtherInfo(watches = 0, likes = 0)
		),

		Recipe(
			id = "11111127",
			userId = "EOmncsE5uZMsGl8HEJ1NYcmcQTw1",
			name = "Веганский бургер",
			description = "Бургер с котлетой из нута и овощами",
			recipeImagesUrl = listOf(
				"https://img.freepik.com/premium-photo/vegetable-burger-with-chickpea-cutlet-two-burgers-black-plate_126267-10281.jpg"
			),
			ingredients = listOf(
				Ingredient("Нут вареный", "https://fort.crimea.com/catering/uploads/fotos/8025c67d-9d5e-11e9-9c72-54a05051519a_1.jpg", 300.0, "гр.", NutritionInfo(164.0, 8.9, 2.6, 27.4)),
				Ingredient("Булочки для бургеров", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSlPs3raZqZQpJSTEIUBtYx24JtGcmmA9LGkQ&s", 2.0, "шт.", NutritionInfo(263.0, 9.0, 3.2, 49.0)),
				Ingredient("Авокадо", "https://images.unian.net/photos/2020_04/thumb_files/1000_545_1586510267-1627.jpg", 1.0, "шт.", NutritionInfo(160.0, 2.0, 15.0, 9.0)),
				Ingredient("Помидор", "https://kuban24.tv/wp-content/uploads/2023/06/photo_2023-06-20_13-18-40.jpg", 1.0, "шт.", NutritionInfo(22.0, 1.1, 0.2, 4.8)),
				Ingredient("Листья салата", "https://glycemic-index.net/images/zK7RYlFq6l.webp", 50.0, "гр.", NutritionInfo(15.0, 1.4, 0.2, 2.9)),
				Ingredient("Чеснок", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQw-Dz7thX_MzHRwrzaI7nO-PUWkq9cbWbk7w&s", 2.0, "шт.", NutritionInfo(149.0, 6.4, 0.5, 33.1)),
				Ingredient("Нутовая мука", "https://i2.storeland.net/1/6133/61326676/afacdb/muka-nutovaya-jpg.jpg", 50.0, "гр.", NutritionInfo(364.0, 10.3, 1.0, 76.3)),
				Ingredient("Растительное масло", "https://xaviar.ru/uploads/product/100/175/zolotaya-semechka-1_2022-03-14_14-11-17.png", 20.0, "гр.", NutritionInfo(884.0, 0.0, 100.0, 0.0))
			),
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
			otherInfo = OtherInfo(watches = 0, likes = 0)
		),

		Recipe(
			id = "11111128",
			userId = "EOmncsE5uZMsGl8HEJ1NYcmcQTw1",
			name = "Тыквенный суп-пюре",
			description = "Нежный крем-суп из тыквы с имбирем и сливками",
			recipeImagesUrl = listOf(
				"https://static.1000.menu/img/content/30273/sup-pure-iz-tykvy-klassicheskii_1545215004_1_max.jpg",
				"https://resizer.mail.ru/p/2d7c09fc-e8c5-525a-9d43-1355e682070e/AQAF_R621nb9v34-idqT1_Ar6brDWBZhe0QJC5dgHiTziK2QV3jlEh9msIDfAWqBBNlL1uayhSTgei2NjjD5R5YQsPQ.jpg"
			),
			ingredients = listOf(
				Ingredient("Тыква", "https://static.tildacdn.com/tild3932-3438-4934-b535-643830323536/tykva.jpg", 800.0, "гр.", NutritionInfo(26.0, 1.0, 0.1, 6.5)),
				Ingredient("Лук", "https://turgor.net/wp-content/uploads/2025/05/types_onion.webp", 1.0, "шт.", NutritionInfo(40.0, 1.1, 0.1, 9.3)),
				Ingredient("Чеснок", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQw-Dz7thX_MzHRwrzaI7nO-PUWkq9cbWbk7w&s", 2.0, "шт.", NutritionInfo(149.0, 6.4, 0.5, 33.1)),
				Ingredient("Имбирь", "https://s10.stc.all.kpcdn.net/family/wp-content/uploads/2022/01/imbir-polza-i-vred-960-960x540.jpg", 20.0, "гр.", NutritionInfo(80.0, 1.8, 0.8, 17.8)),
				Ingredient("Сливки", "https://tsx.x5static.net/i/800x800-fit/xdelivery/files/6e/a1/66f430885a1e844ab9a3df37aaaa.jpg", 200.0, "мл.", NutritionInfo(340.0, 2.5, 36.0, 2.8)),
				Ingredient("Оливковое масло", "https://kudri-brovi.ru/wp-content/uploads/2023/08/%D0%BE%D0%BB%D0%B8%D0%B2%D0%BA%D0%BE%D0%B2%D0%BE%D0%B5-%D0%BC%D0%B0%D1%81%D0%BB%D0%BE-%D0%B0%D0%B2%D0%B0.jpg", 30.0, "мл.", NutritionInfo(884.0, 0.0, 100.0, 0.0)),
				Ingredient("Соль", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTVlpSDxpyfPf058KpXAlGqeKlLwNRjwC_DNA&s", null, "по вкусу", NutritionInfo(0.0, 0.0, 0.0, 0.0)),
				Ingredient("Перец", "https://daoofhealth.ru/upload/iblock/e7b/e7b220b23d6f5e9ed65eee0034fcae25.jpg", null, "по вкусу", NutritionInfo(251.0, 10.4, 3.3, 64.8))
			),
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
			otherInfo = OtherInfo(watches = 0, likes = 0)
		),

		Recipe(
			id = "11111129",
			userId = "EOmncsE5uZMsGl8HEJ1NYcmcQTw1",
			name = "Безглютеновая пицца",
			description = "Пицца на основе цветной капусты без глютена",
			recipeImagesUrl = listOf(
				"https://grandkulinar.ru/uploads/posts/2020-03/1583253501_bezglyutenovaya-picca-iz-kukuruznoj-muki.jpg",
				"https://media.ovkuse.ru/images/recipes/97fb06fd-cb24-406f-9dd3-e64693a56b6a/97fb06fd-cb24-406f-9dd3-e64693a56b6a_420_420.webp"
			),
			ingredients = listOf(
				Ingredient("Цветная капуста", "https://ss.sport-express.ru/userfiles/materials/195/1953687/volga.jpg", 500.0, "гр.", NutritionInfo(25.0, 2.0, 0.3, 5.0)),
				Ingredient("Яйца", "https://efspb.ru/upload/iblock/209/ig78ehdky1d5p28ujt7nyund94ex797x.jpg", 2.0, "шт.", NutritionInfo(143.0, 12.6, 9.5, 0.7)),
				Ingredient("Сыр моцарелла", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSlZ4zOGAVmcvJsolP3-pDipxzQJK90aZPaYg&s", 200.0, "гр.", NutritionInfo(280.0, 28.0, 17.0, 3.1)),
				Ingredient("Томатный соус", "https://eda.ru/images/RecipePhoto/390x390/tomatnij-sous-iz-svezhih-pomidorov-balzamicheskogo-uksusa_20977_photo_5457.jpg", 100.0, "гр.", NutritionInfo(34.0, 1.7, 0.3, 7.6)),
				Ingredient("Оливки", "https://s3.coolclever.tech/img/0000000090027471/1000/21413.webp", 50.0, "гр.", NutritionInfo(115.0, 0.8, 10.7, 6.3)),
				Ingredient("Базилик", "https://kdr-sad.ru/634-medium_bazilikfioletovyj/bazilik-fioletovyi.jpg", 10.0, "гр.", NutritionInfo(23.0, 3.2, 0.6, 2.7)),
				Ingredient("Кукурузная мука", "https://www.moi-hleb.ru/image/catalog/Blog/kukuruznaia-muka-polza-y-prymenenye.jpg", 50.0, "гр.", NutritionInfo(364.0, 10.3, 1.0, 76.3))
			),
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
			otherInfo = OtherInfo(watches = 0, likes = 0)
		),

		Recipe(
			id = "11111130",
			userId = "EOmncsE5uZMsGl8HEJ1NYcmcQTw1",
			name = "Овсянный блин",
			description = "Полезный завтрак из овсянки с начинкой",
			recipeImagesUrl = listOf(
				"https://www.chefmarket.ru/blog/wp-content/uploads/2025/01/d7d79fb6-9d7f-4fda-a159-7fed8ead3d18-933x700-e1736773581114.jpeg"
			),
			ingredients = listOf(
				Ingredient("Овсяные хлопья", "https://vyborg.tv/wp-content/uploads/2024/01/imtsdx7von0xpip6zxeefvt635i0lkx8.jpg", 50.0, "гр.", NutritionInfo(389.0, 16.9, 6.9, 66.3)),
				Ingredient("Яйца", "https://efspb.ru/upload/iblock/209/ig78ehdky1d5p28ujt7nyund94ex797x.jpg", 2.0, "шт.", NutritionInfo(143.0, 12.6, 9.5, 0.7)),
				Ingredient("Молоко", "https://kozalavka.ru/a/kozadereza/files//import/iiko_img_afbf2332-ab1d-445e-aab5-b761e69868e4_f6b9d346-f6ce-4241-9bfa-b008c55ef529.jpg", 100.0, "мл.", NutritionInfo(60.0, 3.2, 3.6, 4.8)),
				Ingredient("Сыр", "https://syromaniya.ru/upload/iblock/5ad/zpzj4dhqdg2f9f6yk3nuunb0wnhtvzpf/%D0%91%D0%B5%D0%B7%20%D0%B8%D0%BC%D0%B5%D0%BD%D0%B8-1.png", 50.0, "гр.", NutritionInfo(402.0, 25.0, 33.0, 1.3)),
				Ingredient("Помидор", "https://kuban24.tv/wp-content/uploads/2023/06/photo_2023-06-20_13-18-40.jpg", 1.0, "шт.", NutritionInfo(22.0, 1.1, 0.2, 4.8)),
				Ingredient("Зелень", "https://здоровое-питание.рф/upload/iblock/b95/xc1iusq3rlr74fw21kinrddc2tip04dm/004_2.jpg", null, "по вкусу", NutritionInfo(49.0, 3.7, 0.4, 7.6)),
				Ingredient("Соль", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTVlpSDxpyfPf058KpXAlGqeKlLwNRjwC_DNA&s", null, "по вкусу", NutritionInfo(0.0, 0.0, 0.0, 0.0))
			),
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
			otherInfo = OtherInfo(watches = 0, likes = 0)
		),

		Recipe(
			id = "11111131",
			userId = "EOmncsE5uZMsGl8HEJ1NYcmcQTw1",
			name = "Лосось в медово-горчичном соусе",
			description = "Нежное филе лосося с хрустящей корочкой",
			recipeImagesUrl = listOf(
				"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTvZhb3zjmFEoYaKZi-S-aOMOB6-xcGrig6BA&s"
			),
			ingredients = listOf(
				Ingredient("Филе лосося", "https://www.bbaum.ru/img/catalog/m425.jpg", 600.0, "гр.", NutritionInfo(208.0, 20.4, 13.4, 0.0)),
				Ingredient("Мед", "https://www.newsler.ru/data/content/2020/94676/a492f467b51601c77f2f9e8016d76e37.jpg", 30.0, "мл.", NutritionInfo(304.0, 0.3, 0.0, 82.4)),
				Ingredient("Горчица", "https://img.povar.ru/uploads/67/4a/9b/08/7f75fa6b7bfdfb010a14bf7428448b8a.jpeg", 20.0, "гр.", NutritionInfo(66.0, 4.4, 3.7, 5.8)),
				Ingredient("Лимон", "https://images.gastronom.ru/K_fcpBkamd6HNMixcHgGp1-Nxv5ygcpGiwYfV0vkpF0/pr:product-cover-image/g:ce/rs:auto:0:0:0/L2Ntcy9hbGwtaW1hZ2VzLzcxNWU0MDk3LWE3M2MtNGFhMS1hZTI2LWU5YzkxNGZlNTNhZC5qcGc.webp", 1.0, "шт.", NutritionInfo(29.0, 1.1, 0.3, 9.3)),
				Ingredient("Чеснок", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQw-Dz7thX_MzHRwrzaI7nO-PUWkq9cbWbk7w&s", 2.0, "шт.", NutritionInfo(149.0, 6.4, 0.5, 33.1)),
				Ingredient("Укроп", "https://gavrishprof.ru/sites/default/files/images/pub/gavrishprof_ukrop_dlya_efirnykh_masel.jpg", 10.0, "гр.", NutritionInfo(43.0, 3.5, 1.1, 7.0)),
				Ingredient("Оливковое масло", "https://kudri-brovi.ru/wp-content/uploads/2023/08/%D0%BE%D0%BB%D0%B8%D0%B2%D0%BA%D0%BE%D0%B2%D0%BE%D0%B5-%D0%BC%D0%B0%D1%81%D0%BB%D0%BE-%D0%B0%D0%B2%D0%B0.jpg", 20.0, "гр.", NutritionInfo(884.0, 0.0, 100.0, 0.0))
			),
			cookingSpeed = 25,
			recipeType = RecipeType.COMPLEX,
			difficulty = Difficulty.MEDIUM,
			otherInfo = OtherInfo(watches = 0, likes = 0)
		),

		Recipe(
			id = "11111132",
			userId = "EOmncsE5uZMsGl8HEJ1NYcmcQTw1",
			name = "Шоколадный фондан",
			description = "Десерт с жидкой шоколадной серединкой",
			recipeImagesUrl = listOf(
				"https://cdn1.ozonusercontent.com/s3/club-storage/images/article_image_1632x1000/56/b7507201-0dcb-4a6c-8104-139fd499c6ca.jpeg",
				"https://images.gastronom.ru/QZjNDMi08krAk2WTLHGE4kkd3DkAduZHy6iJgLI_9Ug/pr:recipe-cover-image/g:ce/rs:auto:0:0:0/L2Ntcy9hbGwtaW1hZ2VzL2RkMjQxYmYyLTExYzMtNDBmYi04OGNhLWRiNTI5MDFiZjM3Zi5qcGc.webp"
			),
			ingredients = listOf(
				Ingredient("Темный шоколад", "https://78centr.ru/wp-content/uploads/2024/07/1674503298_catherineasquithgallery-com-p-fon-gorkii-shokolad-30.jpg", 200.0, "гр.", NutritionInfo(546.0, 4.9, 31.3, 61.2)),
				Ingredient("Сливочное масло", "https://www.mosregfermer.ru/upload/iblock/32a/32a2a99e6ddd6f1d7c205d60539802e1.jpg", 100.0, "гр.", NutritionInfo(717.0, 0.9, 81.1, 0.1)),
				Ingredient("Яйца", "https://efspb.ru/upload/iblock/209/ig78ehdky1d5p28ujt7nyund94ex797x.jpg", 4.0, "шт.", NutritionInfo(143.0, 12.6, 9.5, 0.7)),
				Ingredient("Сахар", "https://www.agroinvestor.ru/upload/iblock/b51/b51e3524ea6be6464b92b4599fceb25d.jpg", 100.0, "гр.", NutritionInfo(387.0, 0.0, 0.0, 100.0)),
				Ingredient("Мука", "https://tsx.x5static.net/i/800x800-fit/xdelivery/files/eb/1b/5a2d4687c78d8d7bc41032d7c50c.jpg", 60.0, "гр.", NutritionInfo(364.0, 10.3, 1.0, 76.3)),
				Ingredient("Какао-порошок", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSHZaHicO6hi_CkNUKQEC-1D9BiUCXMYNmNWA&s", 20.0, "гр.", NutritionInfo(228.0, 19.6, 13.7, 57.9))
			),
			cookingSpeed = 30,
//			steps = listOf(
//				"Смешайте мед, горчицу и измельченный чеснок",
//				"Обмажьте лосось соусом",
//				"Запекайте 15 минут при 200°C",
//				"Подавайте с дольками лимона и укропом"
//			),
			recipeType = RecipeType.BAKING,
			difficulty = Difficulty.HARD,
			otherInfo = OtherInfo(watches = 0, likes = 0)
		),

		Recipe(
			id = "11111133",
			userId = "EOmncsE5uZMsGl8HEJ1NYcmcQTw1",
			name = "Гречка с грибами",
			description = "Ароматная гречневая каша с лесными грибами",
			recipeImagesUrl = listOf(
				"https://gipfel.ru/upload/iblock/6a3/0h4yv2q51p0y6md8a1w4c5zjfsuc3dod.jpg"
			),
			ingredients = listOf(
				Ingredient("Гречка", "https://patisson.shop/wp-content/uploads/2019/09/%D0%93%D1%80%D0%B5%D1%87%D0%BA%D0%B0.jpeg", 300.0, "гр.", NutritionInfo(343.0, 13.3, 3.4, 71.5)),
				Ingredient("Грибы", "https://www.agroinvestor.ru/upload/iblock/5b8/5b8283a92b650cdeed030202d28b5b36.jpg", 200.0, "гр.", NutritionInfo(22.0, 3.1, 0.3, 3.3)),
				Ingredient("Лук", "https://turgor.net/wp-content/uploads/2025/05/types_onion.webp", 1.0, "шт.", NutritionInfo(40.0, 1.1, 0.1, 9.3)),
				Ingredient("Морковь", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQFUXsVcPr9mslgzSrcGB-j0yUCocYzIikQ-Q&s", 1.0, "шт.", NutritionInfo(41.0, 0.9, 0.2, 9.6)),
				Ingredient("Чеснок", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQw-Dz7thX_MzHRwrzaI7nO-PUWkq9cbWbk7w&s", 2.0, "шт.", NutritionInfo(149.0, 6.4, 0.5, 33.0)),
				Ingredient("Зелень", "https://здоровое-питание.рф/upload/iblock/b95/xc1iusq3rlr74fw21kinrddc2tip04dm/004_2.jpg", null, "по вкусу", NutritionInfo(49.0, 3.7, 0.4, 7.6)),
				Ingredient("Сливочное масло", "https://www.mosregfermer.ru/upload/iblock/32a/32a2a99e6ddd6f1d7c205d60539802e1.jpg", 30.0, "гр.", NutritionInfo(717.0, 0.9, 81.1, 0.1))
			),
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
			otherInfo = OtherInfo(watches = 0, likes = 0)
		),

		Recipe(
			id = "11111134",
			userId = "EOmncsE5uZMsGl8HEJ1NYcmcQTw1",
			name = "Крем-суп из шампиньонов",
			description = "Нежный грибной суп с ароматными травами и сливками",
			recipeImagesUrl = listOf(
				"https://cdn.lifehacker.ru/wp-content/uploads/2020/07/Depositphotos_127213602_xl-2015-1_1594636073.jpg"
			),
			ingredients = listOf(
				Ingredient("Шампиньоны", "https://www.agroinvestor.ru/upload/iblock/bba/bba30a41d58bd0605f7ea032f1fa45f0.jpg", 500.0, "гр.", NutritionInfo(22.0, 3.1, 0.3, 3.3)),
				Ingredient("Лук репчатый", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQRLRM0sOVjyMyW7sDJqys-Sgmvs6HZYT0-Pw&s", 1.0, "шт.", NutritionInfo(40.0, 1.1, 0.1, 9.3)),
				Ingredient("Сливки 20%", "https://tsx.x5static.net/i/800x800-fit/xdelivery/files/6e/a1/66f430885a1e844ab9a3df37aaaa.jpg", 200.0, "мл.", NutritionInfo(206.0, 2.8, 20.0, 3.7)),
				Ingredient("Картофель", "https://www.agroinvestor.ru/upload/iblock/aa4/aa406841e1c1f447353f969b5fbd10ee.jpeg", 2.0, "шт.", NutritionInfo(77.0, 2.0, 0.1, 17.0)),
				Ingredient("Чеснок", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQw-Dz7thX_MzHRwrzaI7nO-PUWkq9cbWbk7w&s", 2.0, "шт.", NutritionInfo(149.0, 6.4, 0.5, 33.1)),
				Ingredient("Тимьян", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTaRHfMuA2GC5-cLWFYHoo072TA22J7YwUKsQ&s", 5.0, "гр.", NutritionInfo(101.0, 5.6, 1.7, 24.5)),
				Ingredient("Масло сливочное", "https://www.agroinvestor.ru/upload/iblock/e11/e112cf6c8e56b45193c582e57f31204a.jpg", 30.0, "гр.", NutritionInfo(717.0, 0.9, 81.1, 0.1))
			),
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
			otherInfo = OtherInfo(watches = 0, likes = 0)
		),

		Recipe(
			id = "11111135",
			userId = "EOmncsE5uZMsGl8HEJ1NYcmcQTw1",
			name = "Лазанья со шпинатом и рикоттой",
			description = "Вегетарианская лазанья с нежной начинкой из шпината",
			recipeImagesUrl = listOf(
				"https://foodmood.ru/upload/iblock/3f0/3f0768727a901225a5246bad8d5f539e.jpg"
			),
			ingredients = listOf(
				Ingredient("Листы лазаньи", "https://www.patee.ru/r/x6/10/00/00/960m.jpg", 12.0, "шт.", NutritionInfo(131.0, 5.1, 0.6, 25.0)),
				Ingredient("Шпинат", "https://vkusvill.ru/upload/resize/506257/506257_440x9999x85.webp", 400.0, "гр.", NutritionInfo(23.0, 2.9, 0.4, 3.6)),
				Ingredient("Сыр рикотта", "https://clubcheese.ru/upload/pictures/2111181757328163_big.jpg", 250.0, "гр.", NutritionInfo(174.0, 11.3, 13.0, 3.0)),
				Ingredient("Сыр пармезан", "https://gastronauta.ru/wp-content/uploads/2022/02/%D0%BF%D0%B0%D1%80%D0%BC%D0%B8%D0%B4%D0%B67-%E2%80%94-%D0%BA%D0%BE%D0%BF%D0%B8%D1%8F.webp", 100.0, "гр.", NutritionInfo(392.0, 35.8, 25.8, 3.2)),
				Ingredient("Чеснок", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQw-Dz7thX_MzHRwrzaI7nO-PUWkq9cbWbk7w&s", 3.0, "шт.", NutritionInfo(149.0, 6.4, 0.5, 33.1)),
				Ingredient("Молоко", "https://kozalavka.ru/a/kozadereza/files//import/iiko_img_afbf2332-ab1d-445e-aab5-b761e69868e4_f6b9d346-f6ce-4241-9bfa-b008c55ef529.jpg", 500.0, "мл.", NutritionInfo(60.0, 3.2, 3.6, 4.8)),
				Ingredient("Мука", "https://tsx.x5static.net/i/800x800-fit/xdelivery/files/eb/1b/5a2d4687c78d8d7bc41032d7c50c.jpg", 50.0, "гр.", NutritionInfo(364.0, 10.3, 1.0, 76.3)),
				Ingredient("Мускатный орех", "https://oreh-dom.ru/image/cache/catalog/demo/tovary/specii/oreh-muskatnyj2-700x700.jpg", 2.0, "шт.", NutritionInfo(525.0, 5.8, 36.3, 49.3))
			),
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
			otherInfo = OtherInfo(watches = 0, likes = 0)
		),

		/*Recipe(
			id = "11111136",
			userId = "EOmncsE5uZMsGl8HEJ1NYcmcQTw1",
			name = "Безглютеновые банановые панкейки",
			description = "Пышные панкейки на миндальной муке с бананом",
			ingredients = listOf(
				Ingredient("Миндальная мука", "https://tsx.x5static.net/i/800x800-fit/xdelivery/files/eb/1b/5a2d4687c78d8d7bc41032d7c50c.jpg", 200.0, "гр.", NutritionInfo(579.0, 21.2, 49.9, 21.6)),
				Ingredient("Бананы", "", 2.0, "шт.", NutritionInfo(89.0, 1.1, 0.3, 22.8)),
				Ingredient("Яйца", "https://efspb.ru/upload/iblock/209/ig78ehdky1d5p28ujt7nyund94ex797x.jpg", 3.0, "шт.", NutritionInfo(143.0, 12.6, 9.5, 0.7)),
				Ingredient("Молоко", "https://kozalavka.ru/a/kozadereza/files//import/iiko_img_afbf2332-ab1d-445e-aab5-b761e69868e4_f6b9d346-f6ce-4241-9bfa-b008c55ef529.jpg", 100.0, "мл.", NutritionInfo(60.0, 3.2, 3.6, 4.8)),
				Ingredient("Разрыхлитель", "https://candy-chef.ru/image/cache/catalog/ingredienty/razryhliteldljatestadr.oetker10gr-min-500x500.jpg", 10.0, "гр.", NutritionInfo(0.0, 0.0, 0.0, 0.0)),
				Ingredient("Кленовый сироп", "", 50.0, "мл.", NutritionInfo(260.0, 0.0, 0.0, 67.0))
			),
			cookingSpeed = 20,
//			steps = listOf(
//				"Разомните бананы вилкой",
//				"Смешайте все ингредиенты до однородной массы",
//				"Жарьте на среднем огне по 2 минуты с каждой стороны",
//				"Подавайте с кленовым сиропом и свежими ягодами"
//			),
			recipeType = RecipeType.GLUTEN_FREE,
			difficulty = Difficulty.EASY,
			otherInfo = OtherInfo(watches = 0, likes = 0)
		),

		Recipe(
			id = "11111137",
			userId = "EOmncsE5uZMsGl8HEJ1NYcmcQTw1",
			name = "Куриные грудки в медово-горчичном соусе",
			description = "Нежные куриные грудки с хрустящей корочкой",
			ingredients = listOf(
				Ingredient("Куриная грудка", "https://primemeat.ru/upload/iblock/e8b/iipdtp2zal50vkuvnxbcp44c8h3r9vma/grudka_kurinaya_bez_kozhi_na_kosti.jpg", 600.0, "гр.", NutritionInfo(165.0, 31.0, 3.6, 0.0)),
				Ingredient("Мед", "", 50.0, "мл.", NutritionInfo(304.0, 0.3, 0.0, 82.4)),
				Ingredient("Горчица", "", 30.0, "гр.", NutritionInfo(66.0, 4.4, 3.7, 5.8)),
				Ingredient("Чеснок", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQw-Dz7thX_MzHRwrzaI7nO-PUWkq9cbWbk7w&s", 3.0, "шт.", NutritionInfo(149.0, 6.4, 0.5, 33.1)),
				Ingredient("Лимонный сок", "https://images.gastronom.ru/BI0TG0GHGHlwieEsbbFUtQBti_qllKtfuelUzlmtcS0/pr:product-preview-image/g:ce/rs:auto:0:0:0/L2Ntcy9hbGwtaW1hZ2VzLzJlMGJjNDY0LTgxOWMtNDliMS05ZTk2LTg3ZmM4YmZkYTYyNS5qcGc.webp", 20.0, "мл.", NutritionInfo(6.0, 0.1, 0.0, 2.0)),
				Ingredient("Оливковое масло", "https://kudri-brovi.ru/wp-content/uploads/2023/08/%D0%BE%D0%BB%D0%B8%D0%B2%D0%BA%D0%BE%D0%B2%D0%BE%D0%B5-%D0%BC%D0%B0%D1%81%D0%BB%D0%BE-%D0%B0%D0%B2%D0%B0.jpg", 30.0, "гр.", NutritionInfo(884.0, 0.0, 100.0, 0.0))
			),
			cookingSpeed = 30,
//			steps = listOf(
//				"Смешайте мед, горчицу, чеснок и лимонный сок",
//				"Замаринуйте курицу в соусе на 1 час",
//				"Обжарьте на сковороде по 5 минут с каждой стороны",
//				"Доведите до готовности в духовке 10 минут при 180°C"
//			),
			recipeType = RecipeType.QUICK,
			difficulty = Difficulty.MEDIUM,
			otherInfo = OtherInfo(watches = 0, likes = 0)
		),

		Recipe(
			id = "11111138",
			userId = "EOmncsE5uZMsGl8HEJ1NYcmcQTw1",
			name = "Шоколадный мусс",
			description = "Воздушный шоколадный десерт с насыщенным вкусом",
			ingredients = listOf(
				Ingredient("Темный шоколад", "https://78centr.ru/wp-content/uploads/2024/07/1674503298_catherineasquithgallery-com-p-fon-gorkii-shokolad-30.jpg", 200.0, "гр.", NutritionInfo(546.0, 4.9, 31.3, 61.2)),
				Ingredient("Сливки 33%", "https://tsx.x5static.net/i/800x800-fit/xdelivery/files/76/d2/083d82d150a9dd3222113f62ca7d.jpg", 300.0, "мл.", NutritionInfo(340.0, 2.5, 36.0, 2.8)),
				Ingredient("Яйца", "https://efspb.ru/upload/iblock/209/ig78ehdky1d5p28ujt7nyund94ex797x.jpg", 3.0, "шт.", NutritionInfo(143.0, 12.6, 9.5, 0.7)),
				Ingredient("Сахар", "https://www.agroinvestor.ru/upload/iblock/b51/b51e3524ea6be6464b92b4599fceb25d.jpg", 50.0, "гр.", NutritionInfo(387.0, 0.0, 0.0, 100.0)),
				Ingredient("Ванильный экстракт", "", 5.0, "гр.", NutritionInfo(288.0, 0.1, 0.1, 12.7))
			),
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
			otherInfo = OtherInfo(watches = 0, likes = 0)
		),

		Recipe(
			id = "11111139",
			userId = "EOmncsE5uZMsGl8HEJ1NYcmcQTw1",
			name = "Морковный торт",
			description = "Влажный пряный торт с морковью и грецкими орехами",
			ingredients = listOf(
				Ingredient("Морковь", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQFUXsVcPr9mslgzSrcGB-j0yUCocYzIikQ-Q&s", 300.0, "гр.", NutritionInfo(41.0, 0.9, 0.2, 9.6)),
				Ingredient("Мука", "https://tsx.x5static.net/i/800x800-fit/xdelivery/files/eb/1b/5a2d4687c78d8d7bc41032d7c50c.jpg", 250.0, "гр.", NutritionInfo(364.0, 10.3, 1.0, 76.3)),
				Ingredient("Яйца", "https://efspb.ru/upload/iblock/209/ig78ehdky1d5p28ujt7nyund94ex797x.jpg", 3.0, "шт.", NutritionInfo(143.0, 12.6, 9.5, 0.7)),
				Ingredient("Сахар", "https://www.agroinvestor.ru/upload/iblock/b51/b51e3524ea6be6464b92b4599fceb25d.jpg", 200.0, "гр.", NutritionInfo(387.0, 0.0, 0.0, 100.0)),
				Ingredient("Грецкие орехи", "", 100.0, "гр.", NutritionInfo(654.0, 15.2, 65.2, 13.7)),
				Ingredient("Растительное масло", "https://xaviar.ru/uploads/product/100/175/zolotaya-semechka-1_2022-03-14_14-11-17.png", 150.0, "мл.", NutritionInfo(884.0, 0.0, 100.0, 0.0)),
				Ingredient("Корица", "", 10.0, "гр.", NutritionInfo(247.0, 4.0, 1.2, 80.6)),
				Ingredient("Сода", "https://s3.coolclever.tech/img/0000000090025352/1000/15916.webp", 5.0, "гр.", NutritionInfo(0.0, 0.0, 0.0, 0.0))
			),
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
			otherInfo = OtherInfo(watches = 0, likes = 0)
		)*/
	)
}