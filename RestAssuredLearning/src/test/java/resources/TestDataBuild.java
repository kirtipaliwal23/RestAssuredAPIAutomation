package resources;

import java.util.ArrayList;
import java.util.List;

import Pojo.AddPlace;
import Pojo.Location;

public class TestDataBuild {

	
	public AddPlace addPlacePayload(String address , String language , String Name, int accuracy , String num) {
		AddPlace gMPS = new AddPlace();
		gMPS.setAccuracy(accuracy);
		gMPS.setAddress(address);
		gMPS.setLanguage(language);
		gMPS.setName(Name);
		gMPS.setPhone_number(num);
		gMPS.setWebsite("http://google.com");
		
		List<String> myList = new ArrayList();
		myList.add("shoe park");
		myList.add("shop");
		gMPS.setTypes(myList);
		
		Location location = new Location();
		location.setLat(-38.383494);
		location.setLng(33.427362);
		gMPS.setLocation(location);
		return gMPS;
	}
	public String UpdatePlace(String placeID ,String newAddress) {
		return "{\n"
				+ "\"place_id\":\""+ placeID +"\",\n"
				+ "\"address\":\""+ newAddress +  "\",\n"
				+"\"key\":\"qaclick123\"\n"
				+ "}";
	}
	public String deletePlace(String placeID) {
        return "{\n"
        		+ "    \"place_id\":\""+ placeID +"\"\n"
        		+ "}\n"
        		+ "";
	}
}
