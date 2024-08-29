package ru.vlados.shorturl.model

data class ErrorResponse(
    val error: ApiError
)

//@ApiModel
data class ApiError(
//    @ApiModelProperty(
//        value = "Строковое описание ошибки",
//        example = "Внутренняя ошибка при обработке запроса",
//        required = true
//    )
    val message: String,
//    @ApiModelProperty(
//        value = "Описание внутренних ошибок сервиса",
//        example = "\"[InnerError1,InnerError2]\""
//    )
    val innerErrors: Collection<InnerError> = emptyList()
)

//@ApiModel
data class InnerError(
    val message: String,
    val cause: String,
    val source: String
)