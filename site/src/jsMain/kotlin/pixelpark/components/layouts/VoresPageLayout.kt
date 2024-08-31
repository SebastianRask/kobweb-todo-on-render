package pixelpark.components.layouts

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import pixelpark.components.sections.Footer
import pixelpark.components.sections.NavHeader
import pixelpark.toSitePalette
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.ColumnScope
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.style.vars.color.ColorVar
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import kotlinx.browser.document
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px

val FontStyle = CssStyle {
    base {
        Modifier.fontFamily("OpenSans")
    }
}
val VoresPageContentStyle = CssStyle {
    base {
        Modifier
            .fillMaxSize()
            .padding(leftRight = 16.px, top = 4.px, bottom = 16.px)
    }
    Breakpoint.MD { Modifier.maxWidth(60.cssRem) }
}

@Composable
fun VoresPageLayout(subtitle: String?, content: @Composable ColumnScope.() -> Unit) {
    val sitePalette = ColorMode.current.toSitePalette()

    LaunchedEffect(subtitle) {
        document.title = listOfNotNull("Vores Hundeskove", subtitle).joinToString(separator = " - ")
    }

    Box(
        Modifier
            .fillMaxWidth()
            .minHeight(100.percent)
            // Create a box with two rows: the main content (fills as much space as it can) and the footer (which reserves
            // space at the bottom). "min-content" means the use the height of the row, which we use for the footer.
            // Since this box is set to *at least* 100%, the footer will always appear at least on the bottom but can be
            // pushed further down if the first row grows beyond the page.
            // Grids are powerful but have a bit of a learning curve. For more info, see:
            // https://css-tricks.com/snippets/css/complete-guide-grid/
//            .gridTemplateRows { size(1.fr); size(minContent) }
            .backgroundColor(sitePalette.background)
            .color(sitePalette.onBackground)
            .setVariable(ColorVar, sitePalette.onBackground)
            .then(FontStyle.toModifier()),
        contentAlignment = Alignment.Center
    ) {
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            NavHeader()

            Column(
                VoresPageContentStyle.toModifier(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                content()
            }

            // Associate the footer with the row that will get pushed off the bottom of the page if it can't fit.
            Footer(Modifier.fillMaxWidth())
        }
    }
}
