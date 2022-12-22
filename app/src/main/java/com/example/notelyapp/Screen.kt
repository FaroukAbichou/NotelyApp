package com.example.notelyapp

sealed class Screen(val route:String) {
    object Home : Screen(route = "home_screen")
    object NotesScreen : Screen(route = "NotesScreen_screen")
    object CreateNote : Screen(route="CreateNote_screen")
    object CreateNoteWA : Screen(route="CreateNote_screen")

    fun withArgs( vararg args: Int?):String{
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
