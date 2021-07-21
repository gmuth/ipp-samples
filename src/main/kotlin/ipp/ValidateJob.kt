package ipp

import de.gmuth.ipp.client.*
import de.gmuth.ipp.client.IppTemplateAttributes.documentFormat
import de.gmuth.ipp.client.IppTemplateAttributes.printerResolution
import de.gmuth.ipp.core.IppResolution.Unit.DPI

fun main() {
    val ippPrinter = IppPrinter("ipp://localhost:8632/printers/laser")

    val ippValidationResponse = try {
        ippPrinter.validateJob(
                documentFormat("application/pdf"),
                printerResolution(600, DPI),
                IppColorMode.Color,
                IppSides.TwoSidedLongEdge,
                IppMedia.Collection(source = "manual")
        )

    } catch (ippExchangeException: IppExchangeException) {
        println(ippExchangeException)
        //ippExchangeException.ippResponse
        ippExchangeException.response
    }

    if (ippValidationResponse != null) {
        with(ippValidationResponse) {
            println("status: $status")
            if (unsupportedGroup.size > 0) {
                println("unsupported attributes or values:")
                for (ippAttribute in unsupportedGroup.values) {
                    println(ippAttribute)
                }
            }
        }
    }

//    status: client-error-attributes-or-values-not-supported
//    unsupported attributes or values:
//    media-col (collection) = {media-source=manual}

}