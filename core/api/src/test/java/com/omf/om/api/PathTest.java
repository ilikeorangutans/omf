package com.omf.om.api;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PathTest {

	private Path path;

	@Test
	public void testPathCleaning() {
		path = new Path("/");
		assertEquals("/", path.toString());

		path = new Path("//");
		assertEquals("/", path.toString());

		path = new Path("/////");
		assertEquals("/", path.toString());

		path = new Path("/foo///bar/blargh///");
		assertEquals("/foo/bar/blargh", path.toString());

		path = new Path("/a/");
		assertEquals("/a", path.toString());

		path = new Path("  /foo/bar/	");
		assertEquals("/foo/bar", path.toString());
	}

	@Test
	public void testGetParent() throws Exception {
		path = new Path("/");
		assertEquals("/", path.getParent().toString());

		path = new Path("/foo/bar/");
		assertEquals("/foo", path.getParent().toString());
	}

	@Test
	public void testAppend() throws Exception {
		path = new Path("/");
		assertEquals("/", path.append("/").toString());

		path = new Path("/foo");
		assertEquals("/foo/bar", path.append("bar").toString());

		path = new Path("/");
		assertEquals("/foo/bar", path.append("foo/bar").toString());

	}

	@Test
	public void testGetLength() throws Exception {
		path = new Path("/");
		assertEquals(0, path.getLength());

		path = new Path("/foo");
		assertEquals(1, path.getLength());

		path = new Path("/foo/bar");
		assertEquals(2, path.getLength());
	}
}
