package pixelpark.pages

import androidx.compose.runtime.*
import com.varabyte.kobweb.browser.api
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import kotlinx.browser.window
import kotlinx.coroutines.launch
import org.jetbrains.compose.web.attributes.placeholder
import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.H4
import org.jetbrains.compose.web.dom.TextInput
import pixelpark.components.layouts.FontStyle
import pixelpark.components.layouts.VoresPageLayout
import pixelpark.toSitePalette
import pixelpark.utils.thenIfMobile
import pixelpark.utils.thenIfNotMobile

@Page
@Composable
fun LandingPage() {
    VoresPageLayout(subtitle = null) {
        var input by remember { mutableStateOf("") }
        var response by remember { mutableStateOf("") }


        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxHeight()
                .fillMaxWidth()
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                H1 {
                    SpanText("Vores Hundeskove", Modifier.color(Color.white))
                }

                H4 {
                    SpanText("Response: $response", Modifier.color(Color.white))
                }

                val coroutineScope = rememberCoroutineScope()
                TextInput(
                    value = input,
                    attrs = Modifier
                        .then(FontStyle.toModifier())
                        .padding(topBottom = 8.px, leftRight = 16.px)
                        .minHeight(56.px)
                        .outline(
                            width = 0.px,
                            style = LineStyle.None,
                            color = Colors.Transparent
                        )
                        .backgroundColor(Color.white)
                        .borderRadius(16.px)
                        .border {
                            style(LineStyle.None)
                        }
                        .fontSize(20.px)
                        .thenIfMobile() {
                            Modifier.fillMaxWidth()
                        }
                        .thenIfNotMobile() {
                            Modifier.minWidth(66.percent)
                        }
                        .onKeyDown {
                            if (it.key.lowercase() != "enter") return@onKeyDown

                            // TODO
                        }
                        .toAttrs {
                            placeholder("Søg efter by, postnummer eller område")
                            onInput { input = it.value }
                        }
                )
            }
        }
    }
}
