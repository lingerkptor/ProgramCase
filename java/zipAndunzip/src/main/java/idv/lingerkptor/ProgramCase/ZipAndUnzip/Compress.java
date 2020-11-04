package idv.lingerkptor.ProgramCase.ZipAndUnzip;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Compress {

	public boolean compress(File source, File destination) {
		if (!source.exists())
			return false;

		if (destination.exists())
			destination.delete();
		try {
			destination.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		ZipOutputStream out;

		try {
			out = new ZipOutputStream(new FileOutputStream(destination));
			out.setLevel(0);
			this.process("", source, out);
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	/**
	 * 樹狀檔案結構遞迴尋訪
	 * 
	 * @param parent 父路徑
	 * @param source 要處理的檔案或資料夾
	 * @param out    壓縮串流
	 * @throws IOException 
	 */
	private void process(String parent, File source, ZipOutputStream out) throws IOException {
		if (!source.isDirectory()) {
			out.putNextEntry(new ZipEntry(parent + source.getName()));
			System.out.println("process: " + parent + source.getName());
			writeFile(out, source);
		} else {
			for (File file : source.listFiles()) {
				this.process(parent + source.getName() + "/", file, out);
				System.out.println("process: " + parent + source.getName() + "/");
			}
		}
	}

	/**
	 * 寫入檔案
	 * 
	 * @param out
	 * @param source
	 * @throws IOException
	 */
	private void writeFile(ZipOutputStream outStream, File source) throws IOException {
		BufferedOutputStream out = new BufferedOutputStream(outStream, 1024);
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(source), 1024);
		int content = -1;
		while ((content = in.read()) != -1) {
			out.write(content);
		}
		out.flush();
		in.close();
	}

}
