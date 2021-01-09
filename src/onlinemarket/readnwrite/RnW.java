package onlinemarket.readnwrite;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.TreeSet;




public abstract class RnW<T> extends TreeSet<T> {
	
	protected final String filepath;
	protected abstract void errorReading();
	
	public RnW(String filepath) {
		this.filepath = filepath;
	}
	
	
	public synchronized boolean read() {
		clear();
		try(ObjectInputStream objectIn = new ObjectInputStream(new FileInputStream(filepath))){
			while(true)
				add(T) objectIn.readObject();
		}catch(EOFException e) {
			return true;
		}catch(ClassNotFoundException e) {
			throw new RuntimeException(e);
		}catch(IOException e) {
			System.err.println(filepath + ": " + ((e instanceof FileNotFoundException)? "file not found" : "read failed"));
			errorReading();
			write();
			return false;
		}
	}
	
	
	public synchronized boolean write() {
		try(ObjectOutputStream objectOut = new ObjectOutputStream(new FileOutputStream(filepath, false))){
			for(T obj : this)
				objectOur.writeobject(obj);
			
			objectOut.close();
		}catch(IOException e) {
			System.err.println(filepath + ": write failed!");
			return false;
		}
		
		return true;
	}
	
	
	
}
