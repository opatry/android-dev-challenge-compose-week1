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

package net.opatry.adoptacat.ui

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import net.opatry.adoptacat.R
import net.opatry.adoptacat.model.CatBreed
import net.opatry.adoptacat.model.CatModel

val CatModel.pillColor: Color
    get() = when (color) {
        "IndianRed" -> Color(0xFFCD5C5C)
        "LightCoral" -> Color(0xFFF08080)
        "DarkRed" -> Color(0xFF8B0000)
        "PapayaWhip" -> Color(0xFFFFEFD5)
        "RosyBrown" -> Color(0xFFBC8F8F)
        "Sienna" -> Color(0xFFA0522D)
        "Brown" -> Color(0xFFA52A2A)
        "Maroon" -> Color(0xFF800000)
        "AntiqueWhite" -> Color(0xFFFAEBD7)
        "Linen" -> Color(0xFFFAF0E6)
        "DarkGray" -> Color(0xFFA9A9A9)
        "DimGray" -> Color(0xFF696969)
        "SlateGray" -> Color(0xFF708090)
        "Black" -> Color(0xFF000000)
        else -> Color.Magenta
    }

@get:StringRes
val CatBreed.stringRes
    get() = when (this) {
        CatBreed.Abyssinian -> R.string.cat_breed_abyssinian
        CatBreed.Aegean -> R.string.cat_breed_aegean
        CatBreed.AmericanBobtail -> R.string.cat_breed_american_bobtail
        CatBreed.AmericanCurl -> R.string.cat_breed_american_curl
        CatBreed.AmericanShorthair -> R.string.cat_breed_american_shorthair
        CatBreed.AmericanWirehair -> R.string.cat_breed_american_wirehair
        CatBreed.ArabianMau -> R.string.cat_breed_arabian_mau
        CatBreed.Asian -> R.string.cat_breed_asian
        CatBreed.AsianSemiLonghair -> R.string.cat_breed_asian_semi_longhair
        CatBreed.AustralianMist -> R.string.cat_breed_australian_mist
        CatBreed.Balinese -> R.string.cat_breed_balinese
        CatBreed.Bambino -> R.string.cat_breed_bambino
        CatBreed.Bengal -> R.string.cat_breed_bengal
        CatBreed.Birman -> R.string.cat_breed_birman
        CatBreed.Bombay -> R.string.cat_breed_bombay
        CatBreed.BrazilianShorthair -> R.string.cat_breed_brazilian_shorthair
        CatBreed.BritishLonghair -> R.string.cat_breed_british_longhair
        CatBreed.BritishSemipiLonghair -> R.string.cat_breed_british_semipi_longhair
        CatBreed.BritishShorthair -> R.string.cat_breed_british_shorthair
        CatBreed.Burmese -> R.string.cat_breed_burmese
        CatBreed.Burmilla -> R.string.cat_breed_burmilla
        CatBreed.CaliforniaSpangled -> R.string.cat_breed_california_spangled
        CatBreed.ChantillyTiffany -> R.string.cat_breed_chantilly_tiffany
        CatBreed.Chartreux -> R.string.cat_breed_chartreux
        CatBreed.Chausie -> R.string.cat_breed_chausie
        CatBreed.Cheetoh -> R.string.cat_breed_cheetoh
        CatBreed.ColorpointShorthair -> R.string.cat_breed_colorpoint_shorthair
        CatBreed.CornishRex -> R.string.cat_breed_cornish_rex
        CatBreed.CymricManxLonghair -> R.string.cat_breed_cymric_or_manx_longhair
        CatBreed.Cyprus -> R.string.cat_breed_cyprus
        CatBreed.DevonRex -> R.string.cat_breed_devon_rex
        CatBreed.DonskoyDonSphynx -> R.string.cat_breed_donskoy_or_don_sphynx
        CatBreed.DragonLi -> R.string.cat_breed_dragon_li
        CatBreed.DwarfcatDwelf -> R.string.cat_breed_dwarf_cat_or_dwelf
        CatBreed.EgyptianMau -> R.string.cat_breed_egyptian_mau
        CatBreed.EuropeanShorthair -> R.string.cat_breed_european_shorthair
        CatBreed.ExoticShorthair -> R.string.cat_breed_exotic_shorthair
        CatBreed.FoldexCat -> R.string.cat_breed_foldex_cat
        CatBreed.GermanRex -> R.string.cat_breed_german_rex
        CatBreed.HavanaBrown -> R.string.cat_breed_havana_brown
        CatBreed.Highlander -> R.string.cat_breed_highlander
        CatBreed.HimalayanColorpointPersian -> R.string.cat_breed_himalayan_or_colorpoint_persian
        CatBreed.JapaneseBobtail -> R.string.cat_breed_japanese_bobtail
        CatBreed.Javanese -> R.string.cat_breed_javanese
        CatBreed.KhaoManee -> R.string.cat_breed_khao_manee
        CatBreed.Korat -> R.string.cat_breed_korat
        CatBreed.KoreanBobtail -> R.string.cat_breed_korean_bobtail
        CatBreed.KornJa -> R.string.cat_breed_korn_ja
        CatBreed.KurilianBobtail -> R.string.cat_breed_kurilian_bobtail
        CatBreed.KurilianBobtailKurilIslandsBobtail -> R.string.cat_breed_kurilian_bobtail_or_kuril_islands_bobtail
        CatBreed.LaPerm -> R.string.cat_breed_laperm
        CatBreed.Lykoi -> R.string.cat_breed_lykoi
        CatBreed.MaineCoon -> R.string.cat_breed_maine_coon
        CatBreed.Manx -> R.string.cat_breed_manx
        CatBreed.MekongBobtail -> R.string.cat_breed_mekong_bobtail
        CatBreed.Minskin -> R.string.cat_breed_minskin
        CatBreed.Munchkin -> R.string.cat_breed_munchkin
        CatBreed.Napoleon -> R.string.cat_breed_napoleon
        CatBreed.Nebelung -> R.string.cat_breed_nebelung
        CatBreed.NorwegianForestCat -> R.string.cat_breed_norwegian_forest_cat
        CatBreed.Ocicat -> R.string.cat_breed_ocicat
        CatBreed.OjosAzules -> R.string.cat_breed_ojos_azules
        CatBreed.OregonRex -> R.string.cat_breed_oregon_rex
        CatBreed.OrientalBicolor -> R.string.cat_breed_oriental_bicolor
        CatBreed.OrientalLonghair -> R.string.cat_breed_oriental_longhair
        CatBreed.OrientalShorthair -> R.string.cat_breed_oriental_shorthair
        CatBreed.PerFoldCat -> R.string.cat_breed_perfold_cat
        CatBreed.PersianModernPersianCat -> R.string.cat_breed_persian_modern_persian_cat
        CatBreed.PersianTraditionalPersianCat -> R.string.cat_breed_persian_traditional_persian_cat
        CatBreed.Peterbald -> R.string.cat_breed_peterbald
        CatBreed.Pixiebob -> R.string.cat_breed_pixie_bob
        CatBreed.Raas -> R.string.cat_breed_raas
        CatBreed.Ragamuffin -> R.string.cat_breed_ragamuffin
        CatBreed.Ragdoll -> R.string.cat_breed_ragdoll
        CatBreed.RussianBlue -> R.string.cat_breed_russian_blue
        CatBreed.RussianWhiteBlackTabby -> R.string.cat_breed_russian_white_black_and_tabby
        CatBreed.SamSawet -> R.string.cat_breed_sam_sawet
        CatBreed.Savannah -> R.string.cat_breed_savannah
        CatBreed.ScottishFold -> R.string.cat_breed_scottish_fold
        CatBreed.SelkirkRex -> R.string.cat_breed_selkirk_rex
        CatBreed.Serengeti -> R.string.cat_breed_serengeti
        CatBreed.Serradepetit -> R.string.cat_breed_serrade_petit
        CatBreed.Siamese -> R.string.cat_breed_siamese
        CatBreed.Siberian -> R.string.cat_breed_siberian
        CatBreed.Singapura -> R.string.cat_breed_singapura
        CatBreed.Snowshoe -> R.string.cat_breed_snowshoe
        CatBreed.Sokoke -> R.string.cat_breed_sokoke
        CatBreed.Somali -> R.string.cat_breed_somali
        CatBreed.Sphynx -> R.string.cat_breed_sphynx
        CatBreed.Suphalak -> R.string.cat_breed_suphalak
        CatBreed.Thai -> R.string.cat_breed_thai
        CatBreed.Tonkinese -> R.string.cat_breed_tonkinese
        CatBreed.Toyger -> R.string.cat_breed_toyger
        CatBreed.TurkishAngora -> R.string.cat_breed_turkish_angora
        CatBreed.TurkishVan -> R.string.cat_breed_turkish_van
        CatBreed.UkrainianLevkoy -> R.string.cat_breed_ukrainian_levkoy
    }
