package com.javastaff;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

@Mojo(name = "version",
	  defaultPhase=LifecyclePhase.GENERATE_RESOURCES)
public class VersionMojo extends AbstractMojo {

	@Parameter(property = "project.build.directory")
	private String outputDirectory;

    public void execute() throws MojoExecutionException {
    	File f=new File(outputDirectory+"/classes/version.txt");
    	try {
    		String username = System.getProperty("user.name");
    		
    		String osArch = System.getProperty("os.arch");
    		String osName = System.getProperty("os.name");
    		String osVersion = System.getProperty("os.version");
    		
    		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy HH:mm:ssSS");
			PrintWriter pw=new PrintWriter(f);
			pw.print("Build time: ");
			pw.print(sdf.format(new Date()));
			pw.print(" - Build user: ");
			pw.println(username);
			pw.println("Build Os: "+osArch+" "+osName+" "+osVersion);
			pw.flush();
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    }

}