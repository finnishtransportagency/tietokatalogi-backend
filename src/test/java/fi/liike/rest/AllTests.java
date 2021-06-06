package fi.liike.rest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import fi.liike.testutils.TestTestUtil;

@RunWith(Suite.class)
@SuiteClasses({ JarjestelmasalkkuTest.class, PalvelukatalogiTest.class, LooginenTietovarantoTest.class,
		FyysinenTietovarantoTest.class, PaatietoryhmaTest.class, TietoryhmaTest.class, TietolajiTest.class, TestTestUtil.class })
public class AllTests {
}
