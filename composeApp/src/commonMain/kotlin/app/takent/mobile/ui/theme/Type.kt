package app.takent.mobile.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.Font
import takentmobileapp.composeapp.generated.resources.GeneralSans_Bold
import takentmobileapp.composeapp.generated.resources.GeneralSans_Medium
import takentmobileapp.composeapp.generated.resources.GeneralSans_Regular
import takentmobileapp.composeapp.generated.resources.GeneralSans_Semibold
import takentmobileapp.composeapp.generated.resources.Quicksand_Bold
import takentmobileapp.composeapp.generated.resources.Quicksand_Medium
import takentmobileapp.composeapp.generated.resources.Quicksand_Regular
import takentmobileapp.composeapp.generated.resources.Quicksand_SemiBold
import takentmobileapp.composeapp.generated.resources.Res

@Composable
fun getTypography(): Typography {


    val generalSans = FontFamily(
        Font(Res.font.GeneralSans_Regular, FontWeight.Normal),
        Font(Res.font.GeneralSans_Medium, FontWeight.Medium),
        Font(Res.font.GeneralSans_Semibold, FontWeight.SemiBold),
        Font(Res.font.GeneralSans_Bold, FontWeight.Bold)
    )

    val quicksand = FontFamily(
        Font(Res.font.Quicksand_Regular, FontWeight.Normal),
        Font(Res.font.Quicksand_Medium, FontWeight.Medium),
        Font(Res.font.Quicksand_SemiBold, FontWeight.SemiBold),
        Font(Res.font.Quicksand_Bold, FontWeight.Bold)
    )

    return Typography(
        displayLarge = TextStyle(
            fontFamily = generalSans,
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
            letterSpacing = (-0.5).sp
        ),

        bodyLarge = TextStyle(
            fontFamily = quicksand,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp
        ),

        labelSmall = TextStyle(
            fontFamily = generalSans,
            fontWeight = FontWeight.Medium,
            fontSize = 8.sp
        ),

        labelLarge = TextStyle(
            fontFamily = generalSans,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp
        ),
    )
}

val Typography.PrimaryButton: TextStyle
    @Composable
    get() = TextStyle(
        fontFamily = FontFamily(Font(Res.font.GeneralSans_Medium)),
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    )