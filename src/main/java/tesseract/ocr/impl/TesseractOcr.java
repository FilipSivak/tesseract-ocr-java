package tesseract.ocr.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Expects tesseract ocr binary to be present in %PATH% system variable.
 * @author Filip Sivak <sivakfil@fel.cvut.cz>
 */
public class TesseractOcr {

	/**
	 * Language code of installed language in tesseract
	 */
	protected static final String LANG = "ces";
	
	/**
	 * One of tesseract modes:
	 * pagesegmode values are:
		0 = Orientation and script detection (OSD) only.
		1 = Automatic page segmentation with OSD.
		2 = Automatic page segmentation, but no OSD, or OCR
		3 = Fully automatic page segmentation, but no OSD. (Default)
		4 = Assume a single column of text of variable sizes.
		5 = Assume a single uniform block of vertically aligned text.
		6 = Assume a single uniform block of text.
		7 = Treat the image as a single text line.
		8 = Treat the image as a single word.
		9 = Treat the image as a single word in a circle.
		10 = Treat the image as a single character.
	 */
	protected static final int MODE = 3;
	private static final String OUTPUT_FILE_ENCODING = "utf-8";
	

	/**
	 * @throws IOException 
	 * @param imageFilepath path to image that should be recognized
	 * @param outfile path to file where to store result of recognition without filetype suffix!
	 * @return
	 * @throws
	 */
	public String recognize(String imageFilepath, String outfile) throws IOException {
		// prepare and assemble command
		String command = String.format("tesseract %s %s -l %s -psm %d", imageFilepath, outfile, LANG, MODE);

		// get runtime environment and run prepared command
		Process tesseractProcess = Runtime.getRuntime().exec(command);
		
		// Synchronous wait for process execution
		try {
			tesseractProcess.waitFor();
		} catch (InterruptedException e) {
			throw new IllegalStateException(e);
		}
		
		// read result from output file
		String recognizedText = new String(Files.readAllBytes(Paths.get(outfile + ".txt")), OUTPUT_FILE_ENCODING);
		return recognizedText.trim();
	}

}
