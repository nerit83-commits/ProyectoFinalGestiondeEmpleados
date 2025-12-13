package com.rrhh.suites;

import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;


@Suite
@SelectPackages("com.rrhh")
@IncludeTags("smoke")
public class SmokeTestSuite {
}
