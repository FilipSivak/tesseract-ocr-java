package tesseract.ocr.impl;

import org.testng.Assert;
import org.testng.annotations.Test;

import tesseract.ocr.impl.TesseractOcr;

public class TesseractOcrTest {

	@Test
	public void recognize() throws Exception {
		TesseractOcr tesseractOcr = new TesseractOcr();
		
		String expected = "Ahoj svìte";
		String actual = tesseractOcr.recognize("src/test/resources/ahoj_svete.png", "out");
		Assert.assertEquals(actual, expected);
	}
}
