package pixelpark.components.sections

import androidx.compose.runtime.Composable
import pixelpark.toSitePalette
import pixelpark.utils.thenIfMobile
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.foundation.layout.*
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.navigation.AlwaysUnderlinedLinkVariant
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.components.navigation.UncoloredLinkVariant
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.base
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.style.vars.color.ColorVar
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import org.jetbrains.compose.web.css.FlexWrap
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Br
import org.jetbrains.compose.web.dom.H4
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text

val FooterStyle = CssStyle.base {
    val onContainer = colorMode.toSitePalette().onSurfaceVariant
    Modifier
        .backgroundColor(colorMode.toSitePalette().surfaceVariant)
        .color(onContainer)
        .setVariable(ColorVar, onContainer)
        .padding(topBottom = 16.px)
}

val PixelMemoryStyle = CssStyle.base {
    val onContainer = colorMode.toSitePalette().onSecondaryContainer
    Modifier
        .backgroundColor(colorMode.toSitePalette().secondaryContainer)
        .color(onContainer)
        .setVariable(ColorVar, onContainer)
        .padding(topBottom = 8.px, leftRight = 16.px)
}

@Composable
private fun FooterSection(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    val sitePalette = ColorMode.current.toSitePalette()

    Column(
        modifier
            .thenIfMobile { Modifier.fillMaxWidth() }
            .padding(bottom = 32.px, left = 16.px, right = 16.px),
        verticalArrangement = Arrangement.spacedBy(8.px)
    ) {
        H4(Modifier.marginBlock().toAttrs()) {
            SpanText(title)
        }

        content()
    }

}

@Composable
fun Footer(modifier: Modifier = Modifier) {
    Column(modifier) {
        Box(FooterStyle.toModifier().fillMaxWidth(), contentAlignment = Alignment.Center) {
            val sitePalette = ColorMode.current.toSitePalette()
            val linkVariant = AlwaysUnderlinedLinkVariant.then(UncoloredLinkVariant)
            Row(
                modifier = Modifier
                    .flexWrap(FlexWrap.Wrap)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
            ) {

                FooterSection("Vores Hundeskove", modifier = Modifier.weight(1f)) {
                    P(Modifier.marginBlock().toAttrs()) {
                        """ 
                        Sebastian Rask Jepsen
                        Kirketoften 6A
                        8260, Viby J
                    """
                            .trim()
                            .lines()
                            .forEach {
                                Text(it)
                                Br()
                            }
                    }
                }

                FooterSection("Links", modifier = Modifier.weight(1f)) {
                    Link("/about", "Om Vores Hundeskove", variant = linkVariant)
                    Link(
                        "/definition",
                        "Hvad er en hundeskov?",
                        variant = linkVariant
                    )
                    Link("/rules", "Regler i hundeskove", variant = linkVariant)
                    Link("/contact", "Kontakt os", variant = linkVariant)

                    // Be careful with changing the privacy path
                    Link("/privacy", "Privatlivspolitik", variant = linkVariant)
                }

                FooterSection("Apps", modifier = Modifier.weight(1f)) {
                    Link("/privacy", "Privatlivspolitik", variant = linkVariant)
                }
            }
        }

        Box(
            PixelMemoryStyle.toModifier().fillMaxWidth().fontSize(0.8.cssRem).fontWeight(FontWeight.Light),
            contentAlignment = Alignment.Center
        ) {
            SpanText("Vores Hundeskove er udviklet til minde om mine mange eventyr med Pixel")
        }
    }

}
