package ipp

import de.gmuth.ipp.client.*
import de.gmuth.ipp.client.IppTemplateAttributes.documentFormat
import de.gmuth.ipp.client.IppTemplateAttributes.printerResolution
import de.gmuth.ipp.core.IppResolution.Unit.DPI
import de.gmuth.log.Logging
import ipp.Printers.laser

fun main() {
    val log = Logging.getLogger {}
    val ippPrinter = IppPrinter(laser)

    val ippValidationResponse = try {
        ippPrinter.validateJob(
            documentFormat("application/pdf"),
            printerResolution(600, DPI),
            IppColorMode.Color,
            IppSides.TwoSidedLongEdge,
            IppMedia.Collection(source = "manual")
        )

    } catch (ippExchangeException: IppExchangeException) {
        //log.error(ippExchangeException) {"failed to validate job"}
        log.logWithCauseMessages(ippExchangeException)
        ippExchangeException.response
    }

    ippValidationResponse?.run {
        if (unsupportedGroup.size > 0) {
            for (ippAttribute in unsupportedGroup.values) {
                log.info { ippAttribute }
            }
        }
    }

//    status: client-error-attributes-or-values-not-supported
//    unsupported attributes or values:
//    media-col (collection) = {media-source=manual}

}