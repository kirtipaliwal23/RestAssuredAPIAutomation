package resources;
// enum is a special class in java which has collection of constants and methods
public enum APIResources {
//These are methods of enum class...separated by comma 
	AddPlaceAPI("/maps/api/place/add/json"),
	GetPlaceAPI("/maps/api/place/get/json"),
	UpdatePlaceAPI("/maps/api/place/update/json"),
	DeletePlaceAPI("/maps/api/place/delete/json");
	
	
	private String r;

	APIResources(String r) {
		this.r = r;
	}
	public String getResourceAPI() {
		return r;
	}
}

