package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {
	
	
	public AddPlace addPlacePayload(String name,String language,String address)
	{
		AddPlace p = new AddPlace();
		p.setAccuracy(50);
		p.setName(name);
		p.setPhone_number("+91) 983 893 3937");
		p.setAddress(address);
		p.setWebsite("https://rahulshettyacademy.com");
		p.setLanguage(language);

		List<String> myList=new ArrayList<String>();
		myList.add("shoe park");
		myList.add("home");
		p.setTypes(myList); // Type is a array list it want List<String> argument will create object and add values one by one,see above code.

		
		Location l = new Location();//object is dummy/empty we use setLat() and setLng methods.
		l.setLat(-37.38349423);
		l.setLng(32.42736223);
		p.setLocation(l);
		return p;
		
	}
	
	public String deletePlacePayload(String placeid)
	{
		return  "{\r\n    \"place_id\":\""+placeid+"\"\r\n}\r\n";
	}

}
