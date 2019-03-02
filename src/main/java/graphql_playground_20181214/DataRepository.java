package graphql_playground_20181214;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;


public class DataRepository {
  
  public static DataFetcher getDataForCat() {
    DataFetcher aFetcher = new DataFetcher<List<Cat>>() {
      @Override
      public List<Cat> get(DataFetchingEnvironment environment) {
        System.out.println("In get for datafetcher");
        List<Cat> catList = new ArrayList<>();
        String searchName = environment.getArgument("name");
        if (searchName == null) {
          System.out.println("argument is null");
          catList.add(new Cat("Id1","Puffy","Red"));
          catList.add(new Cat("Id2","Buttons","Grey"));
        }
        else {
          System.out.println("argument is NOT null: " + searchName);
          catList.add(new Cat("Id2","Buttons","Pink"));
        }
        return catList;
      }      
    };
    return aFetcher;
  }
  
  
  public static DataFetcher getDataForCat2() {
    return environment -> {
      return getResultsOrThrowException();
    };
  }
  private static List<Map<String, Object>> getResultsOrThrowException() {
    List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
    // Map<String, Object> hashMap = new HashMap<String, >
    Map hm = new HashMap(); 
    hm.put("Puffy", "Red");
    hm.put("Buttons", "Grey");
    results.add(hm);    
    return results;
  }    
}
