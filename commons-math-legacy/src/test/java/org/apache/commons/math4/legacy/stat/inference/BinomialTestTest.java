/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.math4.legacy.stat.inference;

import org.apache.commons.math4.legacy.exception.MathIllegalArgumentException;
import org.apache.commons.math4.legacy.exception.NotPositiveException;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test cases for the BinomialTest class.
 */
public class BinomialTestTest {

    protected BinomialTest testStatistic = new BinomialTest();

    private static int successes = 51;
    private static int trials = 235;
    private static double probability = 1.0 / 6.0;

    @Test
    public void testBinomialTestPValues() {
        Assert.assertEquals(0.04375, testStatistic.binomialTest(
            trials, successes, probability, AlternativeHypothesis.TWO_SIDED), 1E-4);
        Assert.assertEquals(0.02654, testStatistic.binomialTest(
            trials, successes, probability, AlternativeHypothesis.GREATER_THAN), 1E-4);
        Assert.assertEquals(0.982, testStatistic.binomialTest(
            trials, successes, probability, AlternativeHypothesis.LESS_THAN), 1E-4);

        // for special boundary conditions
        Assert.assertEquals(1, testStatistic.binomialTest(
            3, 3, 1, AlternativeHypothesis.TWO_SIDED), 1E-4);
        Assert.assertEquals(1, testStatistic.binomialTest(
            3, 3, 0.9, AlternativeHypothesis.TWO_SIDED), 1E-4);
        Assert.assertEquals(1, testStatistic.binomialTest(
            3, 3, 0.8, AlternativeHypothesis.TWO_SIDED), 1E-4);
        Assert.assertEquals(0.559, testStatistic.binomialTest(
            3, 3, 0.7, AlternativeHypothesis.TWO_SIDED), 1E-4);
        Assert.assertEquals(0.28, testStatistic.binomialTest(
            3, 3, 0.6, AlternativeHypothesis.TWO_SIDED), 1E-4);
        Assert.assertEquals(0.25, testStatistic.binomialTest(
            3, 3, 0.5, AlternativeHypothesis.TWO_SIDED), 1E-4);
        Assert.assertEquals(0.064, testStatistic.binomialTest(
            3, 3, 0.4, AlternativeHypothesis.TWO_SIDED), 1E-4);
        Assert.assertEquals(0.027, testStatistic.binomialTest(
            3, 3, 0.3, AlternativeHypothesis.TWO_SIDED), 1E-4);
        Assert.assertEquals(0.008, testStatistic.binomialTest(
            3, 3, 0.2, AlternativeHypothesis.TWO_SIDED), 1E-4);
        Assert.assertEquals(0.001, testStatistic.binomialTest(
            3, 3, 0.1, AlternativeHypothesis.TWO_SIDED), 1E-4);
        Assert.assertEquals(0, testStatistic.binomialTest(
            3, 3, 0.0, AlternativeHypothesis.TWO_SIDED), 1E-4);

        Assert.assertEquals(0, testStatistic.binomialTest(
            3, 0, 1, AlternativeHypothesis.TWO_SIDED), 1E-4);
        Assert.assertEquals(0.001, testStatistic.binomialTest(
            3, 0, 0.9, AlternativeHypothesis.TWO_SIDED), 1E-4);
        Assert.assertEquals(0.008, testStatistic.binomialTest(
            3, 0, 0.8, AlternativeHypothesis.TWO_SIDED), 1E-4);
        Assert.assertEquals(0.027, testStatistic.binomialTest(
            3, 0, 0.7, AlternativeHypothesis.TWO_SIDED), 1E-4);
        Assert.assertEquals(0.064, testStatistic.binomialTest(
            3, 0, 0.6, AlternativeHypothesis.TWO_SIDED), 1E-4);
        Assert.assertEquals(0.25, testStatistic.binomialTest(
            3, 0, 0.5, AlternativeHypothesis.TWO_SIDED), 1E-4);
        Assert.assertEquals(0.28, testStatistic.binomialTest(
            3, 0, 0.4, AlternativeHypothesis.TWO_SIDED), 1E-4);
        Assert.assertEquals(0.559, testStatistic.binomialTest(
            3, 0, 0.3, AlternativeHypothesis.TWO_SIDED), 1E-4);
        Assert.assertEquals(1, testStatistic.binomialTest(
            3, 0, 0.2, AlternativeHypothesis.TWO_SIDED), 1E-4);
        Assert.assertEquals(1, testStatistic.binomialTest(
            3, 0, 0.1, AlternativeHypothesis.TWO_SIDED), 1E-4);
        Assert.assertEquals(1, testStatistic.binomialTest(
            3, 0, 0.0, AlternativeHypothesis.TWO_SIDED), 1E-4);

    }

    @Test
    public void testBinomialTestExceptions() {
        try {
            testStatistic.binomialTest(10, -1, 0.5, AlternativeHypothesis.TWO_SIDED);
            Assert.fail("Expected not positive exception");
        } catch (NotPositiveException e) {
            // expected exception;
        }

        try {
            testStatistic.binomialTest(10, 11, 0.5, AlternativeHypothesis.TWO_SIDED);
            Assert.fail("Expected illegal argument exception");
        } catch (MathIllegalArgumentException e) {
            // expected exception;
        }
        try {
            testStatistic.binomialTest(10, 11, 0.5, null);
            Assert.fail("Expected illegal argument exception");
        } catch (MathIllegalArgumentException e) {
            // expected exception;
        }
    }

    @Test
    public void testBinomialTestAcceptReject() {
        double alpha05 = 0.05;
        double alpha01 = 0.01;

        Assert.assertTrue(testStatistic.binomialTest(trials, successes, probability, AlternativeHypothesis.TWO_SIDED, alpha05));
        Assert.assertTrue(testStatistic.binomialTest(trials, successes, probability, AlternativeHypothesis.GREATER_THAN, alpha05));
        Assert.assertFalse(testStatistic.binomialTest(trials, successes, probability, AlternativeHypothesis.LESS_THAN, alpha05));

        Assert.assertFalse(testStatistic.binomialTest(trials, successes, probability, AlternativeHypothesis.TWO_SIDED, alpha01));
        Assert.assertFalse(testStatistic.binomialTest(trials, successes, probability, AlternativeHypothesis.GREATER_THAN, alpha01));
        Assert.assertFalse(testStatistic.binomialTest(trials, successes, probability, AlternativeHypothesis.LESS_THAN, alpha05));
    }

    @Test
    public void testMath1644() {
        final BinomialTest bt = new BinomialTest();
        final double pval = bt.binomialTest(10, 5, 0.5, AlternativeHypothesis.TWO_SIDED);
        Assert.assertTrue("pval=" + pval, pval <= 1);
    }
}
