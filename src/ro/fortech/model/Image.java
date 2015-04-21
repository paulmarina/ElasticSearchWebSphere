package ro.fortech.model;

public class Image {
	
	private String name;
	private String movieId;
	private String id;
	
	private byte[] data;
	private String mime;
	private long length;

	public Image(){
		
	}
	
	public Image(String name, String movieId) {
		
		this.name = name;
		this.movieId = movieId;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}
	
	public long getLength() {
		return length;
	}

	public void setLength(long length) {
		this.length = length;
	}

	public String getMime() {
		return mime;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		 int dot = name.lastIndexOf('.');
	        if(dot > 0){
	            String extension = name.substring(dot + 1);
	            if("bmp".equals(extension)){
	                this.mime="image/bmp";
	            } else if("jpg".equals(extension)){
	                this.mime="image/jpeg";
	            } else if("gif".equals(extension)){
	                this.mime="image/gif";
	            } else if("png".equals(extension)){
	                this.mime="image/png";
	            } else {
	                this.mime = "image/unknown";
	            }
	        }
	}

	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
