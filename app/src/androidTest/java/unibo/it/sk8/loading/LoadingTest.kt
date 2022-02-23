package unibo.it.sk8.loading

/*
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import unibo.it.common.navigation.Destinations
import unibo.it.sk8.navigation.Nav

@RunWith(AndroidJUnit4ClassRunner::class)
class LoadingTest {

    @get:Rule()
    val composeTestRule = createComposeRule()

    @OptIn(InternalCoroutinesApi::class)
    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        composeTestRule.setContent {
            Nav()
        }

    }

    @Test
    fun loadingScreenIsDisplayed() {
        composeTestRule
            .onNodeWithContentDescription(Destinations.Loading)
            .assertIsDisplayed()
    }

    @Test
    fun loadingLogoSk8Test() {
        composeTestRule
            .onNodeWithContentDescription("Sk8 Logo")
            .assertExists()
    }

    @Test
    fun loadingDestinationTest() {
        */
/*runBlocking {
            withContext(Dispatchers.Main) {
                delay(Constant.LOADING_SCREEN_DURATION)
                navController.navigate(Destinations.Authentication)
            }
        }*//*


        composeTestRule
            .onNodeWithContentDescription(Destinations.Authentication)
            .assertIsDisplayed()
    }

    @Test
    fun firebaseConnectionTest() {
        // assert(auth.currentUser != null)
    }
}
*/
