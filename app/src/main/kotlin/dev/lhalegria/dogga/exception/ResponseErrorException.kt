package dev.lhalegria.dogga.exception

import dev.lhalegria.dogga.DoggaApp
import dev.lhalegria.dogga.R

class ResponseErrorException(
    private val code: Int
): Exception() {

    override val message: String
        get() = String.format(DoggaApp.appResources.getString(R.string.response_error), code)
}
