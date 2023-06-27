package dev.lhalegria.dogga.exception

import dev.lhalegria.dogga.DoggaApp
import dev.lhalegria.dogga.R

class ResponseNoBodyException : Exception() {

    override val message: String
        get() = DoggaApp.appResources.getString(R.string.no_body_exception)
}
