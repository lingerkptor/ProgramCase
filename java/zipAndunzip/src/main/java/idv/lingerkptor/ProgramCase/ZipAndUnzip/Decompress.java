package idv.lingerkptor.ProgramCase.ZipAndUnzip;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Decompress {
	/**
	 * 解壓縮
	 * 
	 * @param source 來源的zip檔案
	 * @param destination 目的地的資料夾
	 * @return
	 */
	public boolean decompress(File source, File destination) {

		ZipInputStream in;
		try {
			if (destination.list().length > 0) {
				for (File file : destination.listFiles())
					file.delete();
			}
			in = new ZipInputStream(new FileInputStream(source));
			ZipEntry entry;
			try {
				while ((entry = in.getNextEntry()) != null) {
					if (!entry.isDirectory())
						this.writeFile(entry.getName(), destination, in);
				}
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 解壓縮檔案(寫入檔案)
	 * 
	 * @param FileName
	 * @param parent
	 * @param in
	 * @throws IOException
	 */
	private void writeFile(String FileName, File parent, ZipInputStream in) throws IOException {
		File file = new File(parent, FileName);
		BufferedInputStream input = new BufferedInputStream(in, 1024);
		file.getParentFile().mkdirs();
		file.createNewFile();
		System.out.println("Decompression File=>" + file.getAbsolutePath());
		BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file), 1024);
		int content = -1;
		while ((content = input.read()) != -1) {
			out.write(content);
		}
		out.flush();
		out.close();

	}

}
