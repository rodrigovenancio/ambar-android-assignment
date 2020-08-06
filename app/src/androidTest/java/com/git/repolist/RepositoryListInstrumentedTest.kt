package com.git.repolist

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.git.repolist.ui.repository.view.RepositoryListActivity
import com.git.repolist.util.TestUtils.withRecyclerView
import io.appflate.restmock.RESTMockServer
import io.appflate.restmock.utils.RequestMatchers
import io.appflate.restmock.utils.RequestMatchers.pathContains
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

@RunWith(AndroidJUnit4::class)
class RepositoryListInstrumentedTest {

    @get:Rule
    var activityTestRule = ActivityTestRule(RepositoryListActivity::class.java)

    @Test
    fun testProductList() {
        RESTMockServer.whenGET(pathContains("repositories"))
            .thenReturnFile(200, "repository/repositories.json")

        Espresso.onView(
            withRecyclerView(R.id.recyclerView)
                .atPositionOnView(0, R.id.textViewRepositoryName)
        )
            .check(ViewAssertions.matches(ViewMatchers.withText("grit")))

        Espresso.onView(
            withRecyclerView(R.id.recyclerView)
                .atPositionOnView(0, R.id.textViewRepositoryOwner)
        )
            .check(ViewAssertions.matches(ViewMatchers.withText("mojombo")))

        Espresso.onView(
            withRecyclerView(R.id.recyclerView)
                .atPositionOnView(1, R.id.textViewRepositoryName)
        )
            .check(ViewAssertions.matches(ViewMatchers.withText("merb-core")))

        Espresso.onView(
            withRecyclerView(R.id.recyclerView)
                .atPositionOnView(1, R.id.textViewRepositoryOwner)
        )
            .check(ViewAssertions.matches(ViewMatchers.withText("wycats")))

        Espresso.onView(
            withRecyclerView(R.id.recyclerView)
                .atPositionOnView(2, R.id.textViewRepositoryName)
        )
            .check(ViewAssertions.matches(ViewMatchers.withText("rubinius")))

        Espresso.onView(
            withRecyclerView(R.id.recyclerView)
                .atPositionOnView(2, R.id.textViewRepositoryOwner)
        )
            .check(ViewAssertions.matches(ViewMatchers.withText("rubinius")))
    }

    @Test
    fun testProductDetailErrorAndRetry() {
        RESTMockServer.whenGET(pathContains("repositories"))
            .thenReturnFile(500)

        Espresso.onView(withId(R.id.textViewErrorTitle))
            .check(ViewAssertions.matches(ViewMatchers.withText("Cheque sua conexão.")))

        RESTMockServer.whenGET(pathContains("repositories"))
            .thenReturnFile(200, "repository/repositories.json")

        Espresso.onView(withId(R.id.buttonRetry)).perform(ViewActions.click())

        Espresso.onView(withRecyclerView(R.id.recyclerView)
                .atPositionOnView(0, R.id.textViewRepositoryName))
            .check(ViewAssertions.matches(ViewMatchers.withText("grit")))
    }

}
