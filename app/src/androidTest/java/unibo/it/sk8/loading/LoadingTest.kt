package unibo.it.sk8.loading

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import unibo.it.sk8.navigation.Destinations
import unibo.it.sk8.navigation.Nav
import unibo.it.sk8.utils.Constant

@RunWith(AndroidJUnit4ClassRunner::class)
class LoadingTest {

    @get:Rule()
    val composeTestRule = createComposeRule()
    private lateinit var navController: NavHostController

   // lateinit var auth: FirebaseAuth

    @Before
    fun setUp() {
        composeTestRule.setContent {
            navController = rememberNavController()
            Nav(navController = navController)
        }

    }

    @Test
    fun loadingScreenIsDisplayed() {
        composeTestRule
            .onNodeWithContentDescription(Destinations.loading.destination)
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
        runBlocking {
            withContext(Dispatchers.Main) {
                delay(Constant.LOADING_SCREEN_DURATION)
                navController.navigate(Destinations.authentication.destination)
            }
        }

        composeTestRule
            .onNodeWithContentDescription(Destinations.authentication.destination)
            .assertIsDisplayed()
    }

    @Test
    fun firebaseConnectionTest() {
        // assert(auth.currentUser != null)
    }
}