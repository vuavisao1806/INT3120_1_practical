/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.racetracker

import com.example.racetracker.ui.RaceParticipant
import com.example.racetracker.ui.progressFactor
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class RaceParticipantTest {
    private val raceParticipant = RaceParticipant(
        name = "Test",
        maxProgress = 100,
        progressDelayMillis = 500L,
        initialProgress = 0,
        progressIncrement = 1
    )

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun raceParticipant_RaceStarted_ProgressUpdated() = runTest {
        val expectedProgress = 1
        launch { raceParticipant.run() }
        advanceTimeBy(raceParticipant.progressDelayMillis)
        runCurrent()
        assertEquals(expectedProgress, raceParticipant.currentProgress)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun raceParticipant_RaceFinished_ProgressUpdated() = runTest {
        val expectedProgress = 100
        launch { raceParticipant.run() }
        advanceTimeBy(raceParticipant.maxProgress * raceParticipant.progressDelayMillis)
        runCurrent()
        assertEquals(expectedProgress, raceParticipant.currentProgress)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun raceParticipant_RaceHalfAndPause_ProgressUpdated() = runTest {
        val expectedProgress = 50
        val expectedProgressFactor = 0.5F
        launch {
            raceParticipant.run()
            raceParticipant.reset()
            raceParticipant.run()
        }
        advanceTimeBy(raceParticipant.maxProgress * raceParticipant.progressDelayMillis / 2)
        runCurrent()
        assertEquals(expectedProgressFactor, raceParticipant.progressFactor, 0.00001F)
        assertEquals(expectedProgress, raceParticipant.currentProgress)
        raceParticipant.reset()
        assertEquals(0, raceParticipant.currentProgress)

    }
}
