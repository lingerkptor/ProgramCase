package idv.lingerkptor.ProgramCase.ZipAndUnzip;

import java.io.File;

public class Main {
	public static void main(String... args) {
		File source = new File("./testFile/source");
		File destination  = new File("./testFile/destination/test1.zip");
		
		Compress compress = new Compress();
		compress.compress(source, destination);
		
		Decompress decompress = new Decompress();
		File decompressDir = new File("./testFile/decompressDir");
		decompress.decompress(destination, decompressDir);
		
		
	}
}
