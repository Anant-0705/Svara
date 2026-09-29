package com.dhwani.app.sign

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class SignCapturePolicyTest {
    @Test
    fun keepsCapturingAtTargetDurationWhenFramesAreSparse() {
        assertFalse(SignCapturePolicy.shouldFinish(3_600L, 7, 2))
        assertFalse(SignCapturePolicy.shouldFinish(3_600L, 12, 0))
        assertTrue(SignCapturePolicy.shouldFinish(3_600L, 12, 4))
    }

    @Test
    fun stopsAfterMaximumDurationWithoutEnoughInput() {
        assertTrue(SignCapturePolicy.shouldFinish(8_000L, 4, 0))
        assertFalse(SignCapturePolicy.canInfer(4, 0))
    }

    @Test
    fun permitsModelInferenceAfterEnoughFramesAndHands() {
        assertTrue(SignCapturePolicy.canInfer(10, 4))
        assertFalse(SignCapturePolicy.canInfer(9, 4))
        assertFalse(SignCapturePolicy.canInfer(10, 3))
    }
}
