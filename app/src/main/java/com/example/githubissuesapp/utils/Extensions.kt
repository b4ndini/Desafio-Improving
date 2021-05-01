package com.example.githubissuesapp.utils



fun String.formatDateToPtBr(): String{
    return "at ${this[8]}${this[9]}/${this[5]}${this[6]}/${this[0]}${this[1]}${this[2]}${this[3]}"
}