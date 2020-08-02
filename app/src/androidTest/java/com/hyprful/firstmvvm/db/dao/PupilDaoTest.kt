package com.hyprful.firstmvvm.db.dao

//import androidx.arch.core.executor.testing.InstantTaskExecutorRule

import android.util.Log
import com.hyprful.firstmvvm.db.AppDatabaseTest
import com.hyprful.firstmvvm.db.entity.PupilEntity
import com.hyprful.firstmvvm.db.util.getOrAwaitValue
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*


//@RunWith(MockitoJUnitRunner::class)
//@RunWith(JUnit4::class)
open class PupilDaoTest : AppDatabaseTest(){

    @Test
    fun insertAndRead() {
        val repo = PupilEntity("custom name", "custom country", 1.123, 3.45, 2, false)
        db.pupilDao().insert(repo)
        val loaded = db.pupilDao().loadByNameAndCountry("custom name", "custom country").getOrAwaitValue()!!
        MatcherAssert.assertThat(loaded, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(loaded.name, CoreMatchers.`is`("custom name"))
        MatcherAssert.assertThat(loaded.country, CoreMatchers.`is`("custom country"))
        MatcherAssert.assertThat(loaded.latitude, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(loaded.longitude, CoreMatchers.`is`(3.45))
    }


}