/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.zlika.reproducible;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for {@link ManifestStripper}.
 */
public class ManifestStripperTest
{
    /**
     * Tests stripping on a reference Manifest file.
     * @throws IOException 
     */
    @Test
    public void testStripManifest() throws IOException
    {
        try (final InputStream is = this.getClass().getResourceAsStream("MANIFEST.MF");
                final ByteArrayOutputStream os = new ByteArrayOutputStream();
                final InputStream expectedIs = this.getClass().getResourceAsStream("MANIFEST-stripped.MF"))
        {
            new ManifestStripper().strip(is, os);
            final byte[] expected = new byte[expectedIs.available()];
            expectedIs.read(expected);
            Assert.assertArrayEquals(expected, os.toByteArray());
        }
    }
}
