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
package net.opatry.adoptacat.model

import java.util.UUID

// source: https://github.com/DiUS/java-faker/blob/master/src/main/resources/en/cat.yml
enum class CatBreed {
    Abyssinian,
    Aegean,
    AmericanBobtail,
    AmericanCurl,
    AmericanShorthair,
    AmericanWirehair,
    ArabianMau,
    Asian,
    AsianSemiLonghair,
    AustralianMist,
    Balinese,
    Bambino,
    Bengal,
    Birman,
    Bombay,
    BrazilianShorthair,
    BritishLonghair,
    BritishSemipiLonghair,
    BritishShorthair,
    Burmese,
    Burmilla,
    CaliforniaSpangled,
    ChantillyTiffany,
    Chartreux,
    Chausie,
    Cheetoh,
    ColorpointShorthair,
    CornishRex,
    CymricManxLonghair,
    Cyprus,
    DevonRex,
    DonskoyDonSphynx,
    DragonLi,
    DwarfcatDwelf,
    EgyptianMau,
    EuropeanShorthair,
    ExoticShorthair,
    FoldexCat,
    GermanRex,
    HavanaBrown,
    Highlander,
    HimalayanColorpointPersian,
    JapaneseBobtail,
    Javanese,
    KhaoManee,
    Korat,
    KoreanBobtail,
    KornJa,
    KurilianBobtail,
    KurilianBobtailKurilIslandsBobtail,
    LaPerm,
    Lykoi,
    MaineCoon,
    Manx,
    MekongBobtail,
    Minskin,
    Munchkin,
    Napoleon,
    Nebelung,
    NorwegianForestCat,
    Ocicat,
    OjosAzules,
    OregonRex,
    OrientalBicolor,
    OrientalLonghair,
    OrientalShorthair,
    PerFoldCat,
    PersianModernPersianCat,
    PersianTraditionalPersianCat,
    Peterbald,
    Pixiebob,
    Raas,
    Ragamuffin,
    Ragdoll,
    RussianBlue,
    RussianWhiteBlackTabby,
    SamSawet,
    Savannah,
    ScottishFold,
    SelkirkRex,
    Serengeti,
    Serradepetit,
    Siamese,
    Siberian,
    Singapura,
    Snowshoe,
    Sokoke,
    Somali,
    Sphynx,
    Suphalak,
    Thai,
    Tonkinese,
    Toyger,
    TurkishAngora,
    TurkishVan,
    UkrainianLevkoy
}

enum class Gender {
    Male,
    Female,
}

data class CatModel(
    val uuid: UUID,
    val name: String,
    val breed: CatBreed,
    val birthdate: Long,
    val pictureUrl: String,
    val gender: Gender,
    val weightInGram: Int,
    val color: String,
    val description: String = "",
    val adopted: Boolean = false,
)
