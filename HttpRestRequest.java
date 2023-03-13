import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;


public class HttpRestRequest {
	public static void main(String[] args) throws Exception {
	
			        HttpClient client = HttpClient.newHttpClient();
			        
			        int totalPAges=1;
			        int currentPage=1;
			        List<Data> dataList =new ArrayList<>();
			        int cost=140;
			        do {

				        String url="https://jsonmock.hackerrank.com/api/food_outlets?city=Seattle"+"&page="+currentPage;
			
				        
				        HttpRequest request = HttpRequest.newBuilder()
				                .uri(URI.create(url))
				                .build();

				        HttpResponse<String> response = client.send(request,
				                HttpResponse.BodyHandlers.ofString());

//				        System.out.println(response.body());
				        ObjectMapper mapper= new ObjectMapper();
				        Page page= mapper.readValue(response.body(), Page.class);
				        if(page.total_pages>1) {
				        	currentPage=page.page+1;
				        }
				        totalPAges=page.total_pages;
//				        System.out.println(page.data.size());
				        for(Data data:page.data) {
				        	if(data.estimated_cost<cost) {
//				        		System.out.println(data.estimated_cost);
				        		dataList.add(data);
				        	}
				        }
				        
//				        ObjectInputStream input = new ObjectInputStream(new FileInputStream(response.body()));			        
//				        Page page=(Page)input.readObject();
				        
			        }while(currentPage<=totalPAges);

			        double userRating=0.0;
			        int hotelCost=0;
			        Data dataFinal=null;
			        for(Data data: dataList) {
			        	if(data.user_rating.average_rating>userRating) {
			        		userRating=data.user_rating.average_rating;
			        		dataFinal=data;
			        		hotelCost=data.estimated_cost;
			        	}else if(data.user_rating.average_rating==userRating) {
			        		if(hotelCost>data.estimated_cost) {
			        			dataFinal=data;
			        		}
			        	}
			        }
			        
			        System.out.print(dataFinal.city+","+dataFinal.name);
			        
			        
			        
//			        JSONParser parser = new JSONParser();
//			        JSONObject json = new JSONObject(response.body());  
//			        System.out.println(json.toString());
			        
			        
			    	
			    	
//					URL obj = new URL("https://jsonmock.hackerrank.com/api/food_outlets?city=Seattle");
//					HttpURLConnection con = (HttpURLConnection) obj.openConnection();
//					con.setRequestMethod("GET");
//					int responseCode = con.getResponseCode();
//					System.out.println("GET Response Code :: " + responseCode);
//					if (responseCode == HttpURLConnection.HTTP_OK) { // success
//						BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
//						String inputLine;
//						StringBuffer response = new StringBuffer();
		//
//						while ((inputLine = in.readLine()) != null) {
//							response.append(inputLine);
//						}
//						in.close();
		//
//						// print result
//						System.out.println(response.toString());
//					} else {
//						System.out.println("GET request did not work.");
//					}
			    }
}
