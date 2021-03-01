/*
 * Copyright (c) 2021 Olivier Patry
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the Software
 * is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
 * CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package net.opatry.adoptacat.data

import net.opatry.adoptacat.model.CatBreed
import net.opatry.adoptacat.model.CatModel
import net.opatry.adoptacat.model.Gender
import java.util.UUID
import kotlin.random.Random
import kotlin.time.Duration
import kotlin.time.ExperimentalTime
import kotlin.time.days

val catNames = listOf("Alfie", "Angel", "Bella", "Charlie", "Chloe", "Coco", "Daisy", "Felix", "Jasper", "Lily", "Lucky", "Lucy", "Max", "Millie", "Milo", "Missy", "Misty", "Molly", "Oliver", "Oscar", "Poppy", "Sam", "Shadow", "Simba", "Smokey", "Smudge", "Sooty", "Tiger",)

val fakeColors = listOf("IndianRed", "LightCoral", "DarkRed", "PapayaWhip", "RosyBrown", "Sienna", "Brown", "Maroon", "AntiqueWhite", "Linen", "DarkGray", "DimGray", "SlateGray", "Black",)

@ExperimentalTime
fun fakeCat(name: String, breed: CatBreed, pictureUrl: String, gender: Gender, age: Duration) =
    CatModel(
        uuid = UUID.randomUUID(),
        name = name,
        breed = breed,
        birthdate = System.currentTimeMillis() - age.inMilliseconds.toLong(),
        gender = gender,
        weightInGram = 0f,
        color = fakeColors[Random.nextInt(fakeColors.size)],
        pictureUrl = pictureUrl,
        adopted = false,
        description = "This $breed is a wonderful cat. Like all domestic cats, Turkish Angoras descended from the African wildcat (Felis silvestris lybica). Their ancestors were among the cats that were first domesticated in the Fertile Crescent. Cats from eastern mountainous regions of Anatolia developed into longhaired breeds like the Turkish Van and the Turkish Angora through inbreeding and natural selection. Longhaired cats were imported to Britain and France from Asia Minor, Persia and Russia as early as the late 16th century, though there are indications that they appeared in Europe as early as the 14th century due to the Crusades. The Turkish Angora was used to improve the coat on the Persian, almost to the point of extinction.",
    )

class FakeCatDataSource : CatDataSource {
    @ExperimentalTime
    override suspend fun loadCats(): List<CatModel> = listOf(
        fakeCat(
            catNames[0],
            CatBreed.AmericanCurl,
            "https://upload.wikimedia.org/wikipedia/commons/thumb/b/bd/ACL_Pointocurl_Fiorentina.jpg/220px-ACL_Pointocurl_Fiorentina.jpg",
            Gender.Male,
            50.days,
        ),
        fakeCat(
            catNames[1],
            CatBreed.Chartreux,
            "https://upload.wikimedia.org/wikipedia/commons/thumb/3/3c/IC_Blue_Melody_Flipper_CHA_male_EX1_CACIB.jpg/220px-IC_Blue_Melody_Flipper_CHA_male_EX1_CACIB.jpg",
            Gender.Female,
            3.days,
        ),
        fakeCat(
            catNames[2],
            CatBreed.EgyptianMau,
            "https://upload.wikimedia.org/wikipedia/commons/thumb/3/3b/Egyptian-mau-Face.jpg/220px-Egyptian-mau-Face.jpg",
            Gender.Male,
            10.days,
        ),
        fakeCat(
            catNames[3],
            CatBreed.JapaneseBobtail,
            "https://upload.wikimedia.org/wikipedia/commons/thumb/5/56/JapaneseBobtailBlueEyedMi-ke.JPG/220px-JapaneseBobtailBlueEyedMi-ke.JPG",
            Gender.Male,
            12.days,
        ),
        fakeCat(
            catNames[4],
            CatBreed.Lykoi,
            "https://upload.wikimedia.org/wikipedia/commons/thumb/1/1e/8-month-old_male_Lykoi.jpg/220px-8-month-old_male_Lykoi.jpg",
            Gender.Male,
            93.days,
        ),
        fakeCat(
            catNames[5],
            CatBreed.MekongBobtail,
            "https://upload.wikimedia.org/wikipedia/commons/thumb/6/65/Mekong_bobtail_%28Thai_bobtail%29._Tabby-point..jpg/220px-Mekong_bobtail_%28Thai_bobtail%29._Tabby-point..jpg",
            Gender.Female,
            40.days,
        ),
        fakeCat(
            catNames[6],
            CatBreed.PersianModernPersianCat,
            "https://upload.wikimedia.org/wikipedia/commons/thumb/1/15/White_Persian_Cat.jpg/220px-White_Persian_Cat.jpg",
            Gender.Female,
            35.days,
        ),
        fakeCat(
            catNames[7],
            CatBreed.Ragamuffin,
            "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a1/Ragamuffin_kitten-GRACIE.png/220px-Ragamuffin_kitten-GRACIE.png",
            Gender.Female,
            120.days,
        ),
        fakeCat(
            catNames[8],
            CatBreed.Savannah,
            "https://upload.wikimedia.org/wikipedia/commons/thumb/2/2c/Savannah.jpg/220px-Savannah.jpg",
            Gender.Male,
            6.days,
        ),
        fakeCat(
            catNames[9],
            CatBreed.ScottishFold,
            "https://upload.wikimedia.org/wikipedia/commons/thumb/8/83/Lilac_Scottish_Fold.jpg/220px-Lilac_Scottish_Fold.jpg",
            Gender.Male,
            360.days,
        ),
        fakeCat(
            catNames[10],
            CatBreed.Siamese,
            "https://upload.wikimedia.org/wikipedia/commons/thumb/1/14/Sealpoint-siamese-hybrid.jpg/150px-Sealpoint-siamese-hybrid.jpg",
            Gender.Female,
            8.days,
        ),
        fakeCat(
            catNames[11],
            CatBreed.Singapura,
            "https://upload.wikimedia.org/wikipedia/commons/thumb/9/9b/Raw_Singapura.jpg/220px-Raw_Singapura.jpg",
            Gender.Male,
            950.days,
        ),
        fakeCat(
            catNames[12],
            CatBreed.Sphynx,
            "https://upload.wikimedia.org/wikipedia/commons/thumb/6/60/Sphynx_cat_wearing_clothes.jpg/469px-Sphynx_cat_wearing_clothes.jpg",
            Gender.Female,
            90.days,
        ),
        fakeCat(
            catNames[13],
            CatBreed.Thai,
            "https://upload.wikimedia.org/wikipedia/commons/thumb/5/57/Mimbi3.JPG/220px-Mimbi3.JPG",
            Gender.Male,
            3.days,
        ),
        fakeCat(
            catNames[14],
            CatBreed.TurkishAngora,
            "https://upload.wikimedia.org/wikipedia/commons/thumb/d/df/TUA_Eur.Ch._Akkedi_Yuran_%2811322070486%29.jpg/220px-TUA_Eur.Ch._Akkedi_Yuran_%2811322070486%29.jpg",
            Gender.Female,
            1.days,
        ),
        fakeCat(
            "Buggy",
            CatBreed.Sokoke,
            "BROKEN",
            Gender.Male,
            0.days,
        ),
    )
}
