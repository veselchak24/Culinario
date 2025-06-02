package com.culinario.helpers

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.culinario.mvp.models.DetailedCookingStep
import com.culinario.mvp.models.Difficulty
import com.culinario.mvp.models.Ingredient
import com.culinario.mvp.models.NutritionInfo
import com.culinario.mvp.models.OtherInfo
import com.culinario.mvp.models.Recipe
import com.culinario.mvp.models.RecipeType
import com.google.firebase.Firebase
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await

class RecipeRepositoryImpl {
	val recipes = mutableListOf (
		Recipe(
			id = "11111114",
			userId = "E5ZlpLTKquNxREkoOM8dZyEzk0i1",
			name = "Веганский салат с киноа",
			description = "Питательный салат с киноа, овощами и лимонной заправкой.",
			recipeImagesUrl = listOf(
				"https://images.gastronom.ru/FmZZAOzyawm4CWlwitR-prviBXOwD39-VXMlxyAxDug/pr:content-group-cover-image/g:ce/rs:auto:0:0:0/L2Ntcy9hbGwtaW1hZ2VzL2I4NmRjNTRkLWMyOWMtNGNhNi05MjQ5LTQ1YmFlNDE3ZmUxNy5qcGc.webp",
				"https://static.1000.menu/img/content-v2/42/f7/38318/salat-s-kinoa-i-avokado_1568554667_1_max.jpg"
			),
			ingredients = listOf(
				Ingredient("Киноа", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSrzW8lEB5GaS0mYxBquAxwYV0TL_st3dB_Pw&s", 200.0, "г.", NutritionInfo(200.0, 8.0, 4.0, 36.0)),
				Ingredient("Огурец", "https://usolie.info/userfiles/picfullsize/image-1739392867_025.jpg", 1.0, "шт.", NutritionInfo(36.0, 1.6, 0.4, 7.8)),
				Ingredient("Помидор", "https://kuban24.tv/wp-content/uploads/2023/06/photo_2023-06-20_13-18-40.jpg", 2.0, "шт.", NutritionInfo(234.0, 2.9, 21.4, 12.5)),
				Ingredient("Авокадо", "https://images.unian.net/photos/2020_04/thumb_files/1000_545_1586510267-1627.jpg", 1.0, "шт.", NutritionInfo(8.0, 0.1, 0.1, 2.5)),
				Ingredient("Лимонный сок", "https://images.gastronom.ru/BI0TG0GHGHlwieEsbbFUtQBti_qllKtfuelUzlmtcS0/pr:product-preview-image/g:ce/rs:auto:0:0:0/L2Ntcy9hbGwtaW1hZ2VzLzJlMGJjNDY0LTgxOWMtNDliMS05ZTk2LTg3ZmM4YmZkYTYyNS5qcGc.webp", 30.0, "мл.", NutritionInfo(29.0, 0.9, 0.1, 3.0)),
				Ingredient("Оливковое масло", "https://kudri-brovi.ru/wp-content/uploads/2023/08/%D0%BE%D0%BB%D0%B8%D0%B2%D0%BA%D0%BE%D0%B2%D0%BE%D0%B5-%D0%BC%D0%B0%D1%81%D0%BB%D0%BE-%D0%B0%D0%B2%D0%B0.jpg", 20.0, "мл.", NutritionInfo(900.0, 0.0, 100.0, 0.0)),
				Ingredient("Соль", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTVlpSDxpyfPf058KpXAlGqeKlLwNRjwC_DNA&s", null, "по вкусу", NutritionInfo(0.0, 0.0, 0.0, 0.0)),
				Ingredient("Петрушка", "https://organicmarket.ru/files/images/product/large_image/0/3407/petrushka_63319cb86ef34.jpg", null, "", NutritionInfo(49.0, 3.7, 0.4, 7.6))
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
			userId = "E5ZlpLTKquNxREkoOM8dZyEzk0i1",
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
			steps = listOf(
				DetailedCookingStep(
					imageUrl = "",
					title = "Смешивание ингредиентов",
					time = 300,
					description = "Смешайте яйца с сахаром и солью в глубокой миске. Добавьте молоко и постепенно введите муку, тщательно перемешивая до получения однородного теста без комочков."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Отдых теста",
					time = 900,
					description = "Дайте тесту постоять 15 минут при комнатной температуре. Это сделает блины более нежными и воздушными."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Жарка блинов",
					time = 600,
					description = "Разогрейте сковороду на среднем огне, смажьте небольшим количеством растительного масла. Наливайте тесто тонким слоем и жарьте блины по 1-2 минуты с каждой стороны до золотистого цвета."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Подача",
					time = 120,
					description = "Подавайте блины горячими с вареньем, сметаной, медом или свежими ягодами по вашему вкусу."
				)
			),
			recipeType = RecipeType.BAKING,
			difficulty = Difficulty.EASY,
			otherInfo = OtherInfo(watches = 0, likes = 0)
		),

		Recipe(
			id = "11111116",
			userId = "E5ZlpLTKquNxREkoOM8dZyEzk0i1",
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
			steps = listOf(
				DetailedCookingStep(
					imageUrl = "",
					title = "Нарезка овощей",
					time = 300,
					description = "Огурец и помидоры нарежьте кубиками среднего размера. Красный лук нарежьте тонкими полукольцами. Петрушку мелко порубите."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Смешивание ингредиентов",
					time = 180,
					description = "В большой салатнице соедините вареный нут, нарезанные овощи и петрушку. Аккуратно перемешайте."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Приготовление заправки",
					time = 120,
					description = "В небольшой миске смешайте лимонный сок, оливковое масло, щепотку соли и перца. Взбейте венчиком до однородной эмульсии."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Заправка салата",
					time = 60,
					description = "Полейте салат приготовленной заправкой и аккуратно перемешайте, чтобы все ингредиенты равномерно покрылись соусом."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Подача",
					time = 60,
					description = "Подавайте салат сразу после приготовления, украсив веточками свежей петрушки и дольками лимона."
				)
			),
			recipeType = RecipeType.VEGAN,
			difficulty = Difficulty.EASY,
			otherInfo = OtherInfo(watches = 0, likes = 0)
		),

		Recipe(
			id = "11111117",
			userId = "E5ZlpLTKquNxREkoOM8dZyEzk0i1",
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
			steps = listOf(
				DetailedCookingStep(
					imageUrl = "",
					title = "Приготовление мясного соуса",
					time = 1200,
					description = "Нарежьте лук и морковь мелкими кубиками. Обжарьте на оливковом масле до мягкости. Добавьте фарш и обжаривайте до румяного цвета. Влейте томатную пасту, разведенную в воде, и тушите на медленном огне 30 минут."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Приготовление бешамель",
					time = 600,
					description = "Растопите сливочное масло в кастрюле, добавьте муку и обжаривайте 1-2 минуты. Постепенно влейте молоко, постоянно помешивая, чтобы не было комочков. Варите соус до загустения, добавьте соль, перец и мускатный орех по вкусу."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Сборка лазаньи",
					time = 600,
					description = "В форму для запекания выложите слой мясного соуса, затем листы лазаньи, бешамель и тертый сыр. Повторите слои, закончив сыром."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Запекание",
					time = 2400,
					description = "Запекайте лазанью в предварительно разогретой до 180°C духовке 40 минут до золотистой корочки."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Подача",
					time = 600,
					description = "Дайте лазанье настояться 10 минут перед нарезкой. Подавайте горячей, украсив веточками свежего базилика."
				)
			),
			recipeType = RecipeType.COMPLEX,
			difficulty = Difficulty.HARD,
			otherInfo = OtherInfo(watches = 0, likes = 0)
		),

		Recipe(
			id = "11111118",
			userId = "E5ZlpLTKquNxREkoOM8dZyEzk0i1",
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
			steps = listOf(
				DetailedCookingStep(
					imageUrl = "",
					title = "Приготовление теста",
					time = 300,
					description = "В глубокой миске смешайте кефир с яйцами и сахаром. Добавьте гречневую муку и соду, тщательно перемешайте до получения однородного теста без комочков."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Отдых теста",
					time = 600,
					description = "Дайте тесту постоять 10 минут при комнатной температуре. Это позволит муке полностью впитать жидкость, и оладьи получатся более пышными."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Жарка оладий",
					time = 600,
					description = "Разогрейте сковороду на среднем огне, смажьте небольшим количеством растительного масла. Выкладывайте тесто ложкой, накрывайте крышкой и жарьте оладьи по 2-3 минуты с каждой стороны до золотистого цвета."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Подача",
					time = 120,
					description = "Подавайте оладьи горячими с медом, свежими ягодами или сметаной по вашему вкусу."
				)
			),
			recipeType = RecipeType.GLUTEN_FREE,
			difficulty = Difficulty.MEDIUM,
			otherInfo = OtherInfo(watches = 0, likes = 0)
		),

		Recipe(
			id = "11111119",
			userId = "E5ZlpLTKquNxREkoOM8dZyEzk0i1",
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
			steps = listOf(
				DetailedCookingStep(
					imageUrl = "",
					title = "Приготовление бульона",
					time = 1800,
					description = "Поместите куриную грудку в кастрюлю, залейте холодной водой и доведите до кипения. Снимите пену, добавьте целую луковицу, морковь и перец горошком. Варите на медленном огне 30 минут."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Подготовка овощей",
					time = 300,
					description = "Очистите картофель и нарежьте кубиками. Морковь нарежьте кружочками или соломкой."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Добавление овощей",
					time = 900,
					description = "Достаньте курицу из бульона, удалите луковицу. Добавьте в бульон нарезанные овощи и варите 15 минут до мягкости картофеля."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Добавление лапши",
					time = 420,
					description = "Разберите курицу на волокна, верните в суп. Добавьте лапшу и варите еще 5-7 минут до готовности лапши."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Подача",
					time = 60,
					description = "Перед подачей добавьте в суп мелко нарезанную зелень. Подавайте горячим с черным хлебом."
				)
			),
			recipeType = RecipeType.QUICK,
			difficulty = Difficulty.EASY,
			otherInfo = OtherInfo(watches = 0, likes = 0)
		),

		Recipe(
			id = "11111120",
			userId = "E5ZlpLTKquNxREkoOM8dZyEzk0i1",
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
			steps = listOf(
				DetailedCookingStep(
					imageUrl = "",
					title = "Растапливание шоколада",
					time = 300,
					description = "Растопите масло и шоколад на водяной бане, постоянно помешивая до получения однородной массы. Дайте немного остыть."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Смешивание сухих ингредиентов",
					time = 180,
					description = "В отдельной миске смешайте муку, какао-порошок, сахар и разрыхлитель. Тщательно перемешайте сухие ингредиенты."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Соединение ингредиентов",
					time = 240,
					description = "Добавьте яйца в шоколадную массу, хорошо взбейте. Постепенно введите сухие ингредиенты, аккуратно перемешивая до однородности."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Формирование маффинов",
					time = 180,
					description = "Разложите тесто по формочкам, заполняя их на 2/3 объема. Можно добавить кусочек шоколада в центр каждого маффина для жидкой серединки."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Выпекание",
					time = 900,
					description = "Выпекайте в предварительно разогретой до 180°C духовке 15 минут. Маффины должны немного подняться, но остаться влажными внутри."
				)
			),
			recipeType = RecipeType.BAKING,
			difficulty = Difficulty.MEDIUM,
			otherInfo = OtherInfo(watches = 0, likes = 0)
		),

		Recipe(
			id = "11111121",
			userId = "E5ZlpLTKquNxREkoOM8dZyEzk0i1",
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
			steps = listOf(
				DetailedCookingStep(
					imageUrl = "",
					title = "Нарезка овощей",
					time = 300,
					description = "Помидоры нарежьте крупными дольками, огурец - толстыми полукругами. Красный лук нарежьте тонкими полукольцами."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Подготовка сыра",
					time = 120,
					description = "Сыр фета нарежьте кубиками примерно 1,5-2 см. Оливки разрежьте пополам или оставьте целыми."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Сборка салата",
					time = 180,
					description = "В большой салатнице соедините все подготовленные ингредиенты: овощи, сыр фета и оливки."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Приготовление заправки",
					time = 120,
					description = "В небольшой миске смешайте оливковое масло, лимонный сок, щепотку соли и сушеный орегано. Взбейте вилкой до эмульсии."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Подача",
					time = 60,
					description = "Полейте салат заправкой, аккуратно перемешайте. Подавайте сразу, украсив веточками свежего орегано."
				),
			),
			recipeType = RecipeType.QUICK,
			difficulty = Difficulty.EASY,
			otherInfo = OtherInfo(watches = 0, likes = 0)
		),

		Recipe(
			id = "11111122",
			userId = "E5ZlpLTKquNxREkoOM8dZyEzk0i1",
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
			steps = listOf(
				DetailedCookingStep(
					imageUrl = "",
					title = "Подготовка овощей",
					time = 600,
					description = "Баклажаны и кабачки нарежьте кружочками толщиной около 5 мм. Помидоры нарежьте тонкими кружочками. Перец очистите от семян и нарежьте кольцами."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Приготовление основы",
					time = 300,
					description = "Лук и чеснок мелко нарежьте и обжарьте на оливковом масле до прозрачности. Добавьте половину нарезанных помидоров и тушите 5 минут."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Формирование рататуя",
					time = 300,
					description = "Выложите томатную основу в форму для запекания. Сверху красиво разложите кружочки баклажанов, кабачков, перца и оставшихся помидоров, чередуя их."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Добавление специй",
					time = 120,
					description = "Полейте овощи оливковым маслом, посыпьте прованскими травами, солью и перцем по вкусу."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Запекание",
					time = 2700,
					description = "Накройте форму фольгой и запекайте в предварительно разогретой до 190°C духовке 45 минут. Затем снимите фольгу и запекайте еще 15 минут."
				)
			),
			recipeType = RecipeType.VEGAN,
			difficulty = Difficulty.MEDIUM,
			otherInfo = OtherInfo(watches = 0, likes = 0)
		),

		Recipe(
			id = "11111123",
			userId = "E5ZlpLTKquNxREkoOM8dZyEzk0i1",
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
			steps = listOf(
				DetailedCookingStep(
					imageUrl = "",
					title = "Приготовление кофе",
					time = 300,
					description = "Сварите крепкий кофе и остудите его до комнатной температуры. Можно добавить 1 ст.л. сахара и 1 ст.л. ликера (по желанию)."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Приготовление крема",
					time = 600,
					description = "Отделите желтки от белков. Желтки взбейте с сахаром до белой пышной массы. Добавьте маскарпоне и аккуратно перемешайте до однородности."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Взбивание белков",
					time = 300,
					description = "Белки взбейте до устойчивых пиков. Осторожно введите в крем, перемешивая снизу вверх."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Сборка десерта",
					time = 600,
					description = "Кратковременно обмакивайте печенье в кофе (1-2 секунды) и выкладывайте слоем в форму. Сверху распределите половину крема. Повторите слои."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Завершение",
					time = 120,
					description = "Посыпьте поверхность какао-порошком через сито. Накройте пищевой пленкой и уберите в холодильник минимум на 6 часов, лучше на ночь."
				)
			),
			recipeType = RecipeType.COMPLEX,
			difficulty = Difficulty.HARD,
			otherInfo = OtherInfo(watches = 0, likes = 0)
		),

		Recipe(
			id = "11111124",
			userId = "E5ZlpLTKquNxREkoOM8dZyEzk0i1",
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
			steps = listOf(
				DetailedCookingStep(
					imageUrl = "",
					title = "Подготовка овощей",
					time = 180,
					description = "Помидор и болгарский перец нарежьте мелкими кубиками. Зелень мелко порубите."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Приготовление яичной смеси",
					time = 120,
					description = "В миске взбейте яйца с молоком и солью до однородности. Можно добавить немного черного перца по вкусу."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Обжарка овощей",
					time = 120,
					description = "На разогретой сковороде с маслом обжарьте перец 1 минуту, затем добавьте помидоры и жарьте еще 1 минуту."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Приготовление омлета",
					time = 300,
					description = "Залейте овощи яичной смесью, убавьте огонь до среднего, накройте крышкой и готовьте 5-7 минут до полного схватывания."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Подача",
					time = 60,
					description = "Посыпьте омлет зеленью перед подачей. Можно свернуть омлет пополам или подавать как есть."
				)
			),
			recipeType = RecipeType.QUICK,
			difficulty = Difficulty.EASY,
			otherInfo = OtherInfo(watches = 0, likes = 0)
		),

		Recipe(
			id = "11111125",
			userId = "E5ZlpLTKquNxREkoOM8dZyEzk0i1",
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
			steps = listOf(
				DetailedCookingStep(
					imageUrl = "",
					title = "Подготовка творога",
					time = 180,
					description = "Творог протрите через систо или измельчите блендером для получения однородной массы без комочков."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Приготовление теста",
					time = 300,
					description = "В миске смешайте творог с яйцами, сахаром и ванильным сахаром. Постепенно добавляйте муку, замешивая тесто. Оно должно быть мягким, но не липнуть к рукам."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Формирование сырников",
					time = 240,
					description = "Сформируйте из теста небольшие шарики, затем слегка приплюсните их, чтобы получились круглые лепешки толщиной около 1,5 см."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Жарка сырников",
					time = 600,
					description = "Разогрейте сковороду с маслом на среднем огне. Обжаривайте сырники по 3-4 минуты с каждой стороны до золотистой корочки."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Подача",
					time = 60,
					description = "Подавайте сырники горячими со сметаной, вареньем, медом или свежими ягодами по вашему вкусу."
				)
			),
			recipeType = RecipeType.BAKING,
			difficulty = Difficulty.EASY,
			otherInfo = OtherInfo(watches = 0, likes = 0)
		),

		Recipe(
			id = "11111126",
			userId = "E5ZlpLTKquNxREkoOM8dZyEzk0i1",
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
			steps = listOf(
				DetailedCookingStep(
					imageUrl = "",
					title = "Подготовка крылышек",
					time = 300,
					description = "Куриные крылышки промойте и тщательно обсушите бумажными полотенцами. Удалите остатки перьев при необходимости."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Приготовление маринада",
					time = 180,
					description = "В миске смешайте паприку, измельченный чеснок, соль и растительное масло. Добавьте половину соуса BBQ и тщательно перемешайте."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Маринование",
					time = 600,
					description = "Обмажьте крылышки маринадом со всех сторон. Накройте пищевой пленкой и оставьте мариноваться в холодильнике минимум на 1 час (лучше на 3-4 часа)."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Запекание",
					time = 1800,
					description = "Разогрейте духовку до 200°C. Выложите крылышки на решетку или противень с пергаментом. Запекайте 30 минут, перевернув один раз."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Глазирование",
					time = 600,
					description = "Достаньте крылышки, обильно смажьте оставшимся соусом BBQ и запекайте еще 10 минут до образования липкой глазури."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Подача",
					time = 60,
					description = "Подавайте горячими, украсив зеленью и дольками лимона. Идеально сочетаются с овощным салатом и свежим хлебом."
				)
			),
			recipeType = RecipeType.QUICK,
			difficulty = Difficulty.MEDIUM,
			otherInfo = OtherInfo(watches = 0, likes = 0)
		),

		Recipe(
			id = "11111127",
			userId = "E5ZlpLTKquNxREkoOM8dZyEzk0i1",
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
			steps = listOf(
				DetailedCookingStep(
					imageUrl = "",
					title = "Приготовление котлетной массы",
					time = 300,
					description = "Измельчите нут в блендере или кухонном комбайне до состояния грубой пасты. Добавьте измельченный чеснок, соль и специи по вкусу."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Формирование котлет",
					time = 240,
					description = "Добавьте нутовую муку для связки, сформируйте 2 круглые котлеты толщиной около 1,5 см. Обваляйте их в небольшом количестве муки."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Обжарка котлет",
					time = 600,
					description = "Разогрейте сковороду с маслом на среднем огне. Обжаривайте котлеты по 5 минут с каждой стороны до золотистой корочки."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Подготовка овощей",
					time = 180,
					description = "Авокадо нарежьте тонкими ломтиками. Помидор нарежьте кружочками. Листья салата промойте и обсушите."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Сборка бургера",
					time = 180,
					description = "Подрумяньте булочки на сухой сковороде. На нижнюю половину булочки выложите лист салата, котлету, ломтики авокадо и помидора. Накройте верхней половиной булочки."
				)
			),
			recipeType = RecipeType.VEGAN,
			difficulty = Difficulty.MEDIUM,
			otherInfo = OtherInfo(watches = 0, likes = 0)
		),

		Recipe(
			id = "11111128",
			userId = "E5ZlpLTKquNxREkoOM8dZyEzk0i1",
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
			steps = listOf(
				DetailedCookingStep(
					imageUrl = "",
					title = "Подготовка тыквы",
					time = 300,
					description = "Очистите тыкву от кожуры и семян. Нарежьте мякоть кубиками размером примерно 2-3 см."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Обжарка ароматных овощей",
					time = 180,
					description = "На оливковом масле обжарьте мелко нарезанный лук, чеснок и тертый имбирь до мягкости и появления аромата (около 3 минут)."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Приготовление основы",
					time = 120,
					description = "Добавьте нарезанную тыкву к обжаренным овощам, залейте водой так, чтобы она полностью покрывала тыкву. Доведите до кипения."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Варка супа",
					time = 1500,
					description = "Уменьшите огонь и варите суп 25 минут, пока тыква не станет полностью мягкой."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Приготовление пюре",
					time = 180,
					description = "Измельчите суп блендером до однородного состояния. Добавьте сливки, соль и перец по вкусу, прогрейте еще 2 минуты."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Подача",
					time = 60,
					description = "Подавайте суп горячим, украсив тыквенными семечками, каплей сливок и веточкой зелени."
				)
			),
			recipeType = RecipeType.VEGAN,
			difficulty = Difficulty.EASY,
			otherInfo = OtherInfo(watches = 0, likes = 0)
		),

		Recipe(
			id = "11111129",
			userId = "E5ZlpLTKquNxREkoOM8dZyEzk0i1",
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
			steps = listOf(
				DetailedCookingStep(
					imageUrl = "",
					title = "Подготовка основы",
					time = 300,
					description = "Разберите цветную капусту на соцветия и измельчите в блендере или кухонном комбайне до состояния мелкой крошки."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Приготовление теста",
					time = 240,
					description = "Смешайте цветную капусту с яйцами и кукурузной мукой. Добавьте соль и специи по вкусу. Замесите однородное тесто."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Формирование основы",
					time = 180,
					description = "Выложите тесто на пергамент, сформируйте круглую основу для пиццы толщиной около 0,5 см."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Выпекание основы",
					time = 1200,
					description = "Выпекайте основу в предварительно разогретой до 180°C духовке 20 минут до золотистого цвета."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Добавление начинки",
					time = 300,
					description = "На готовую основу нанесите томатный соус, посыпьте тертой моцареллой, добавьте оливки и другие начинки по желанию."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Завершающее запекание",
					time = 900,
					description = "Верните пиццу в духовку еще на 15 минут, пока сыр не расплавится и не подрумянится. Украсьте свежим базиликом перед подачей."
				)
			),
			recipeType = RecipeType.GLUTEN_FREE,
			difficulty = Difficulty.MEDIUM,
			otherInfo = OtherInfo(watches = 0, likes = 0)
		),

		Recipe(
			id = "11111130",
			userId = "E5ZlpLTKquNxREkoOM8dZyEzk0i1",
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
			steps = listOf(
				DetailedCookingStep(
					imageUrl = "",
					title = "Приготовление теста",
					time = 180,
					description = "В миске смешайте овсяные хлопья, яйца и молоко. Добавьте щепотку соли. Дайте постоять 2-3 минуты, чтобы хлопья немного набухли."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Жарка блина",
					time = 180,
					description = "Вылейте тесто на разогретую сковороду с небольшим количеством масла. Распределите равномерно по всей поверхности. Жарьте на среднем огне 3 минуты."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Добавление начинки",
					time = 120,
					description = "На одну половину блина выложите нарезанный помидор, тертый сыр и зелень. Накройте второй половиной."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Завершение приготовления",
					time = 120,
					description = "Аккуратно переверните блин и жарьте еще 2 минуты, чтобы сыр внутри расплавился."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Подача",
					time = 60,
					description = "Подавайте блин горячим, разрезав пополам. Можно добавить свежие овощи или зелень по вкусу."
				)
			),
			recipeType = RecipeType.QUICK,
			difficulty = Difficulty.EASY,
			otherInfo = OtherInfo(watches = 0, likes = 0)
		),

		Recipe(
			id = "11111131",
			userId = "E5ZlpLTKquNxREkoOM8dZyEzk0i1",
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
			steps = listOf(
				DetailedCookingStep(
					imageUrl = "",
					title = "Подготовка лосося",
					time = 180,
					description = "Филе лосося промойте и обсушите бумажными полотенцами. Удалите оставшиеся кости пинцетом. Нарежьте на порционные куски."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Приготовление соуса",
					time = 120,
					description = "В небольшой миске смешайте мед, горчицу, измельченный чеснок, сок половины лимона и оливковое масло. Тщательно перемешайте до однородности."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Маринование",
					time = 300,
					description = "Обмажьте куски лосося приготовленным соусом со всех сторон. Оставьте мариноваться на 10 минут при комнатной температуре."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Запекание",
					time = 900,
					description = "Выложите лосось на противень, застеленный пергаментом. Запекайте в предварительно разогретой до 200°C духовке 12-15 минут."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Подача",
					time = 60,
					description = "Готового лосося украсьте веточками укропа и дольками лимона. Подавайте с овощами или рисом."
				)
			),
			recipeType = RecipeType.COMPLEX,
			difficulty = Difficulty.MEDIUM,
			otherInfo = OtherInfo(watches = 0, likes = 0)
		),

		Recipe(
			id = "11111132",
			userId = "E5ZlpLTKquNxREkoOM8dZyEzk0i1",
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
			steps = listOf(
				DetailedCookingStep(
					imageUrl = "",
					title = "Растапливание шоколада",
					time = 300,
					description = "На водяной бане растопите темный шоколад вместе со сливочным маслом, помешивая до получения однородной массы."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Взбивание яиц",
					time = 240,
					description = "В отдельной миске взбейте яйца с сахаром до пышной светлой массы."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Соединение ингредиентов",
					time = 180,
					description = "Аккуратно соедините растопленный шоколад с яичной смесью, затем добавьте муку и какао-порошок, тщательно перемешайте."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Формирование фонданов",
					time = 300,
					description = "Разлейте тесто по смазанным маслом формочкам, заполняя их на 3/4 объема."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Выпекание",
					time = 600,
					description = "Выпекайте в разогретой до 180°C духовке 10-12 минут, пока края не затвердеют, а середина останется жидкой."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Подача",
					time = 120,
					description = "Дайте фонданам немного остыть, затем аккуратно переверните на тарелки. Подавайте теплыми, посыпав какао-порошком или сахарной пудрой."
				)
			),
			recipeType = RecipeType.BAKING,
			difficulty = Difficulty.HARD,
			otherInfo = OtherInfo(watches = 0, likes = 0)
		),

		Recipe(
			id = "11111133",
			userId = "E5ZlpLTKquNxREkoOM8dZyEzk0i1",
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
			steps = listOf(
				DetailedCookingStep(
					imageUrl = "",
					title = "Подготовка грибов",
					time = 180,
					description = "Грибы очистите от мусора, промойте и нарежьте ломтиками средней толщины. Если используете лесные грибы, предварительно отварите их 15 минут."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Обжарка овощей",
					time = 240,
					description = "Лук нарежьте кубиками, морковь натрите на крупной терке. Обжарьте на сливочном масле до мягкости 5 минут, затем добавьте измельченный чеснок."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Обжарка грибов",
					time = 300,
					description = "Добавьте грибы к овощам, жарьте на среднем огне 7-10 минут, пока грибы не подрумянятся и не уменьшатся в объеме."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Добавление гречки",
					time = 120,
					description = "Гречку переберите, промойте и добавьте к грибам и овощам. Перемешайте и прогрейте 1-2 минуты."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Варка каши",
					time = 1200,
					description = "Залейте смесь кипятком (в пропорции 1:2), посолите, уменьшите огонь до минимума и варите под крышкой 15-20 минут, пока гречка не впитает всю воду."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Подача",
					time = 60,
					description = "Добавьте сливочное масло, перемешайте и дайте настояться 5 минут под крышкой. Подавайте горячей, посыпав свежей зеленью."
				)
			),
			recipeType = RecipeType.QUICK,
			difficulty = Difficulty.EASY,
			otherInfo = OtherInfo(watches = 0, likes = 0)
		),

		Recipe(
			id = "11111134",
			userId = "E5ZlpLTKquNxREkoOM8dZyEzk0i1",
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
			steps = listOf(
				DetailedCookingStep(
					imageUrl = "",
					title = "Подготовка грибов",
					time = 240,
					description = "Шампиньоны очистите, промойте и нарежьте пластинками. Оставьте несколько красивых грибов для украшения."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Обжарка лука и чеснока",
					time = 180,
					description = "На сливочном масле обжарьте мелко нарезанный лук и чеснок до прозрачности и появления аромата (около 3 минут)."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Обжарка грибов",
					time = 300,
					description = "Добавьте нарезанные шампиньоны и жарьте 7-10 минут, пока грибы не подрумянятся и не уменьшатся в объеме."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Добавление картофеля",
					time = 120,
					description = "Картофель очистите и нарежьте кубиками. Добавьте к грибам, влейте 1 литр воды или бульона, добавьте тимьян."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Варка супа",
					time = 900,
					description = "Доведите до кипения, уменьшите огонь и варите 15 минут, пока картофель не станет мягким."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Приготовление пюре",
					time = 180,
					description = "Измельчите суп блендером до однородной кремовой консистенции. Добавьте сливки, прогрейте 2 минуты, не доводя до кипения."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Подача",
					time = 120,
					description = "Подавайте суп горячим, украсив обжаренными целыми шампиньонами, веточками тимьяна и гренками."
				)
			),
			recipeType = RecipeType.QUICK,
			difficulty = Difficulty.MEDIUM,
			otherInfo = OtherInfo(watches = 0, likes = 0)
		),

		Recipe(
			id = "11111135",
			userId = "E5ZlpLTKquNxREkoOM8dZyEzk0i1",
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
			steps = listOf(
				DetailedCookingStep(
					imageUrl = "",
					title = "Подготовка крылышек",
					time = 300,
					description = "Куриные крылышки промойте и тщательно обсушите бумажными полотенцами. Удалите остатки перьев при необходимости."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Приготовление маринада",
					time = 180,
					description = "В миске смешайте паприку, измельченный чеснок, соль и растительное масло. Добавьте половину соуса BBQ и тщательно перемешайте."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Маринование",
					time = 600,
					description = "Обмажьте крылышки маринадом со всех сторон. Накройте пищевой пленкой и оставьте мариноваться в холодильнике минимум на 1 час (лучше на 3-4 часа)."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Запекание",
					time = 1800,
					description = "Разогрейте духовку до 200°C. Выложите крылышки на решетку или противень с пергаментом. Запекайте 30 минут, перевернув один раз."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Глазирование",
					time = 600,
					description = "Достаньте крылышки, обильно смажьте оставшимся соусом BBQ и запекайте еще 10 минут до образования липкой глазури."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Подача",
					time = 60,
					description = "Подавайте горячими, украсив зеленью и дольками лимона. Идеально сочетаются с овощным салатом и свежим хлебом."
				)
			),
			recipeType = RecipeType.VEGAN,
			difficulty = Difficulty.HARD,
			otherInfo = OtherInfo(watches = 0, likes = 0)
		),

		Recipe(
			id = "11111136",
			userId = "E5ZlpLTKquNxREkoOM8dZyEzk0i1",
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
			steps = listOf(
				DetailedCookingStep(
					imageUrl = "",
					title = "Подготовка бананов",
					time = 120,
					description = "Очистите бананы и разомните вилкой в пюре в глубокой миске. Оставьте несколько кусочков для украшения."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Приготовление теста",
					time = 180,
					description = "Добавьте к бананам яйца, молоко и тщательно перемешайте. Всыпьте миндальную муку и разрыхлитель, перемешайте до однородности."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Подготовка сковороды",
					time = 120,
					description = "Разогрейте антипригарную сковороду на среднем огне. Смажьте минимальным количеством масла или используйте кулинарный спрей."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Жарка панкейков",
					time = 600,
					description = "Выливайте тесто небольшими порциями (около 1/4 стакана на панкейк). Жарьте 2-3 минуты до появления пузырьков, затем переверните и жарьте еще 1-2 минуты."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Сборка стопки",
					time = 120,
					description = "Готовые панкейки складывайте в стопку, сохраняя их теплыми под крышкой или в духовке при 100°C."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Подача",
					time = 60,
					description = "Подавайте с кленовым сиропом, кусочками банана и свежими ягодами. Можно добавить греческий йогурт или орехи."
				)
			),
			recipeType = RecipeType.GLUTEN_FREE,
			difficulty = Difficulty.EASY,
			otherInfo = OtherInfo(watches = 0, likes = 0)
		),

		Recipe(
			id = "11111137",
			userId = "E5ZlpLTKquNxREkoOM8dZyEzk0i1",
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
			steps = listOf(
				DetailedCookingStep(
					imageUrl = "",
					title = "Подготовка грудки",
					time = 240,
					description = "Куриные грудки промойте, обсушите и слегка отбейте через пищевую пленку до равномерной толщины (1,5-2 см). Посолите и поперчите."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Приготовление соуса",
					time = 180,
					description = "В миске смешайте мед, горчицу, измельченный чеснок, лимонный сок и оливковое масло. Взбейте венчиком до однородной эмульсии."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Маринование",
					time = 600,
					description = "Обмажьте куриные грудки соусом со всех сторон. Оставьте мариноваться при комнатной температуре на 15 минут (или в холодильнике 1 час)."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Обжарка",
					time = 300,
					description = "Разогрейте сковороду с небольшим количеством масла на среднем огне. Обжарьте грудки по 3-4 минуты с каждой стороны до золотистой корочки."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Запекание",
					time = 600,
					description = "Переложите грудки в форму для запекания, полейте оставшимся соусом и запекайте в разогретой до 180°C духовке 10 минут."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Подача",
					time = 60,
					description = "Подавайте горячими с овощным гарниром или салатом. Полейте соком, образовавшимся при запекании."
				)
			),
			recipeType = RecipeType.QUICK,
			difficulty = Difficulty.MEDIUM,
			otherInfo = OtherInfo(watches = 0, likes = 0)
		),

		Recipe(
			id = "11111138",
			userId = "E5ZlpLTKquNxREkoOM8dZyEzk0i1",
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
			steps = listOf(
				DetailedCookingStep(
					imageUrl = "",
					title = "Растапливание шоколада",
					time = 300,
					description = "Шоколад поломайте на кусочки и растопите на водяной бане, постоянно помешивая. Дайте немного остыть."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Подготовка яиц",
					time = 120,
					description = "Аккуратно отделите желтки от белков. Белки уберите в холодильник до использования."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Приготовление яичной смеси",
					time = 240,
					description = "Желтки взбейте с сахаром и ванилью до светлой пышной массы. Аккуратно вмешайте растопленный шоколад."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Взбивание сливок",
					time = 300,
					description = "Сливки взбейте до устойчивых пиков. Охлажденные белки взбейте отдельно до мягких пиков."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Соединение ингредиентов",
					time = 180,
					description = "Аккуратно вмешайте сначала сливки, затем белки в шоколадную массу, сохраняя воздушность."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Охлаждение",
					time = 2400,
					description = "Разлейте мусс по креманкам или стаканам. Уберите в холодильник минимум на 4 часа, лучше на ночь."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Подача",
					time = 60,
					description = "Перед подачей украсьте тертым шоколадом, ягодами или мятой. Подавайте охлажденным."
				)
			),
			recipeType = RecipeType.BAKING,
			difficulty = Difficulty.MEDIUM,
			otherInfo = OtherInfo(watches = 0, likes = 0)
		),

		Recipe(
			id = "11111139",
			userId = "E5ZlpLTKquNxREkoOM8dZyEzk0i1",
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
			steps = listOf(
				DetailedCookingStep(
					imageUrl = "",
					title = "Подготовка моркови",
					time = 240,
					description = "Морковь очистите и натрите на мелкой терке. Грецкие орехи измельчите в крошку, оставив несколько половинок для украшения."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Смешивание сухих ингредиентов",
					time = 180,
					description = "В большой миске смешайте муку, сахар, корицу и соду. Добавьте щепотку соли."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Приготовление жидкой основы",
					time = 180,
					description = "В другой миске взбейте яйца с растительным маслом до однородности."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "",
					time = 240,
					description = "Добавьте жидкую смесь к сухим ингредиентам, перемешайте. Введите тертую морковь и орехи, аккуратно перемешайте лопаткой."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Подготовка формы",
					time = 120,
					description = "Форму для выпечки смажьте маслом и выстелите пергаментом. Вылейте тесто, разровняйте поверхность."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Выпекание",
					time = 3000,
					description = "Выпекайте в разогретой до 180°C духовке 50 минут. Проверьте готовность деревянной палочкой - она должна выходить сухой."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Охлаждение",
					time = 1800,
					description = "Дайте коржу полностью остыть на решетке перед нанесением крема."
				),
				DetailedCookingStep(
					imageUrl = "",
					title = "Подача",
					time = 120,
					description = "Подавайте торт, украсив орехами и посыпав корицей. Можно разрезать на 2 коржа и промазать кремом."
				)
			),
			recipeType = RecipeType.BAKING,
			difficulty = Difficulty.HARD,
			otherInfo = OtherInfo(watches = 0, likes = 0)
		)
	)

	@Composable
	fun sendRecipesToDb() {
		val recipes = RecipeRepositoryImpl()

		LaunchedEffect(Unit) {
			for (recipe in recipes.recipes) {
				Firebase.firestore.collection(RECIPE_COLLECTION).document(recipe.id).set(recipe, SetOptions.merge()).await()
			}
		}
	}
}