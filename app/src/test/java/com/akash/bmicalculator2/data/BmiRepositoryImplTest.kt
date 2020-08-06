package com.akash.bmicalculator2.data


import com.akash.bmicalculator2.data.source.FakeBmiRepository
import com.akash.bmicalculator2.models.BMI
import kotlinx.coroutines.runBlocking
import org.hamcrest.core.IsEqual
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test

class BmiRepositoryImplTest {
    // Given
    private val bmi_1 = BMI(
        height = 168,
        weight = 76,
        date = "23.06.2020"
    )
    private val bmi_2 = BMI(
        height = 168,
        weight = 74,
        date = "24.06.2020"
    )

    private val listOfBmis = listOf(bmi_1, bmi_2).sortedBy { bmi -> bmi.bmi() }
    private lateinit var fakeBmiRepository: FakeBmiRepository

    @Before
    fun genrateFakeRepo() {
        fakeBmiRepository = FakeBmiRepository(listOfBmis.toMutableList())
    }

    @Test
    fun getAllBmisFromLocalDB() {
        runBlocking {
            val bmis = fakeBmiRepository.getListOfBmis()
            assertThat(bmis, IsEqual(listOfBmis))
        }
    }
}