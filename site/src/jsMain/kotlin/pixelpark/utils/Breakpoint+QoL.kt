package pixelpark.utils

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.thenIf
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.theme.breakpoint.rememberBreakpoint

fun Breakpoint.isMobile(): Boolean {
    return this <= Breakpoint.ZERO
}

fun Breakpoint.isNotMobile(): Boolean {
    return this > Breakpoint.ZERO
}

@Composable
inline fun Modifier.thenIfMobile(lazyProduce: () -> Modifier): Modifier {
    val breakpoint = rememberBreakpoint()
    return this.thenIf(breakpoint.isMobile(), lazyProduce)
}

@Composable
inline fun Modifier.thenIfNotMobile(lazyProduce: () -> Modifier): Modifier {
    val breakpoint = rememberBreakpoint()
    return this.thenIf(breakpoint.isNotMobile(), lazyProduce)
}