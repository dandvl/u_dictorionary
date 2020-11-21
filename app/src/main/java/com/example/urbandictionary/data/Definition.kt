package com.example.urbandictionary.data

data class Definition(
    var definition: String,
    var permalink: String,
    var thumbs_up: Int,
    var thumbs_down: Int,
    var author: String,
    var word: String,
    var defid: String,
    var current_vote: String,
    var writte_on: String,
    var example: String,
    var sound_urls: List<String>
)