package org.evoAdmin.upload.image;

import java.io.File;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;


public class FileManager {

	private String dir = null;
	private String rootPath = null;
	private String[] allowFiles = null;
	@SuppressWarnings("unused")
    private int count = 0;
	
	public FileManager ( Map<String, Object> conf ) {

		this.rootPath = (String)conf.get( "rootPath" );
		this.dir = this.rootPath + (String)conf.get( "dir" );
		this.allowFiles = this.getAllowFiles( conf.get("allowFiles") );
		this.count = (Integer)conf.get( "count" );
		
	}
	
	public State listFile ( int index ) {
		
		State state = null;

/*		if ( !dir.exists() ) {
			return new BaseState( false, AppInfo.NOT_EXIST );
		}
		
		if ( !dir.isDirectory() ) {
			return new BaseState( false, AppInfo.NOT_DIRECTORY );
		}*/
		
		//Collection<File> list = FileUtils.listFiles( dir, this.allowFiles, true );
		String cosFolderPath = this.dir.replace(this.rootPath, StringUtils.EMPTY);
		String[] list = QcloudUploadUtils.assembleFileList(cosFolderPath, this.allowFiles);
		
		if ( index < 0 || index > list.length ) {
			state = new MultiState( true );
		} else {
		 
			state = this.getState(list);
		}
		
		state.putInfo( "start", index );
		state.putInfo( "total", list.length );
		
		return state;
		
	}
	
	@SuppressWarnings("unused")
    private State getState (String[] path ) {
		
		MultiState state = new MultiState( true );
		BaseState fileState = null;
		
		File file = null;
		int len = path.length;
		for (int i = 0; i < len; i++) {
			fileState = new BaseState( true );
			fileState.putInfo( "url", path[i]);
			state.addState( fileState );
		}
		
		return state;
		
	}
	
	@SuppressWarnings("unused")
    private String getPath ( File file ) {
		
		String path = file.getAbsolutePath();
		
		return path.replace( this.rootPath, "/" );
		
	}
	
	private String[] getAllowFiles ( Object fileExt ) {
		
		String[] exts = null;
		String ext = null;
		
		if ( fileExt == null ) {
			return new String[ 0 ];
		}
		
		exts = (String[])fileExt;
		
		for ( int i = 0, len = exts.length; i < len; i++ ) {
			
			ext = exts[ i ];
			exts[ i ] = ext.replace( ".", "" );
			
		}
		
		return exts;
	}
	
}
