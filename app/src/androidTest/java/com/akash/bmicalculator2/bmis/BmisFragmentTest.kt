package com.akash.bmicalculator2.bmis

import androidx.fragment.app.testing.FragmentScenario
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.akash.bmicalculator2.R
import org.hamcrest.Matchers
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

private const val TAG = "BmisFragmentTest"

@RunWith(AndroidJUnit4::class)
class BmisFragmentTest {

    private lateinit var navController: TestNavHostController
    private lateinit var bmiScenario: FragmentScenario<BmisFragment>

    /** Before every test, the fragment has to be started **/
    @Before
    fun startBmisFragmentWithNavigation() {
        navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )
        navController.setGraph(R.navigation.nav_graph)
        navController.setCurrentDestination(R.id.bmisFragment)

        /**
         * Create a graphical FragmentScenario for the BmiFragment
         * Supplying the theme is necessary because fragments usually get their theming from their parent activity.
         * When using FragmentScenario, your fragment is launched inside a generic empty activity so that it's properly
         * isolated from activity code (you are just testing the fragment code, not the associated activity).
         * The theme parameter allows you to supply the correct theme.
         * **/

        bmiScenario = FragmentScenario.launchInContainer(
            BmisFragment::class.java, null,
            R.style.AppTheme, null
        )

        /** Set the NavConroller property on the fragment **/
        bmiScenario.onFragment { _fragment ->
            Navigation.setViewNavController(_fragment.requireView(), navController)
        }
    }

    /** Testing navigation with FAB**/
    @Test
    fun testNavigationToAddBmiFragment() {
        /** Verify that performing a click changes the NavController's state **/
        Espresso.onView(ViewMatchers.withId(R.id.fab_calculateNewBmi))
            .perform(ViewActions.click())
        Assert.assertThat(
            navController.currentDestination?.id,
            Matchers.`is`(R.id.addNewBmiFragment)
        )
    }

    /** Testcase for RecyclerView - item longclick **/
    @Test
    fun testRecyclerView_longClickOnItem() {
        Espresso.onView(ViewMatchers.withId(R.id.recyclerView))
            .perform(
                RecyclerViewActions
                    .actionOnItemAtPosition<BmiViewHolder>(
                        2,
                        ViewActions.longClick()
                    )
            )
        Espresso.onView(Matchers.allOf(ViewMatchers.withId(R.id.textView)))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            .check(ViewAssertions.matches(ViewMatchers.withText(R.string.delete_bmi_are_you_sure)))
        Espresso.onView(Matchers.allOf(ViewMatchers.withId(R.id.fragment_history_bottom_sheet_delete)))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}