package onlinemarket.readnwrite;

import java.util.TreeSet;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;



public abstract class RnW<T> extends TreeSet<T>{
	private static final long serialVersionUID = 0L;
	
	protected final String filepath;
	
	public RnW(String filepath) {
		this.filepath = filepath;
	}
	
	public synchronized boolean read() {
		clear();
		
		try(ObjectInputStream objectIn = new ObjectInputStream(new FileInputStream(filepath))){
			while(true)
				add((T) objectIn.readObject());
		}catch(EOFException e) {
			return true;
		}catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		catch(IOException e) {
			System.err.println("Filepath problem! " + ((e instanceof FileNotFoundException)? "file not found!\n" : "read failed\n"));
			Error();
			write();
			return false;
		}	
	}
	
	public synchronized boolean write() {
		try(ObjectOutputStream objectOut = new ObjectOutputStream(new FileOutputStream(filepath, false))){
			for(T obj : this)
				objectOut.writeObject(obj);
			
		}catch(IOException e) {
			System.err.println(filepath + ": write failed");
			return false;
		}
		return true;
	}
	
	protected abstract void Error();
}
