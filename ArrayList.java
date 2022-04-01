/*--------------------------------------------------------------------------
GWU - CS1112 Data Structures and Algorithms - Spring 2022

This class encapsulates the logic to model list functions to add, remove, 
and get songs in a list similar to a playlist on Spotify or album of songs.
This class specifically deals with arrays and adding, deleting, or finding specific 
songs in the array.


authors: <Rhys Chambers>, Charles Peeke
--------------------------------------------------------------------------*/
//notes to self: don't forget to document, finish unittest5 and take out print statement for count, parameters, returns

public class ArrayList implements MusicCatalog {
    /// For an array-based list, the array itself
    private CatalogItem[] data;
    /// The counter to track the number of elements in the list
    private int count;

    /// Parameterless Constructor
    public ArrayList() {
        count = 0;
        data = new CatalogItem[2];
    }
   
     //if the arraylist is too small to contain all of the data, double its size. Then, at each 
    //index in the data, add the elements that should be at each index from the old array. Swap the arrays 
    //at the end so that the new array exists as the basic array that is manipulated if too small.
    //@param song that needs to be added to the array
    //@return void
    public void add(Song song) {
        // TODO : Implement this function
	//need to allocate more space in the array
	
	//if count equals the size of the array, increase the size of the array
	//need to do it by size
       
	    if (count >= data.length){
		    CatalogItem[] temp = new CatalogItem [data.length * 2];
   	
			 for (int i = 0; i < data.length; i++){
				    temp[i] = data[i];
		         }
	//make data equal to temp for future		 
	    data = temp;
	   
		    }


	data[count] = new CatalogItem(song);
		
	count++;
   }

    
    

    /// remove the first song in the array by setting a new object at the 0th index and then moving all of the
   //following data into the previous index of the array.
   //@param none, removing the first index in the array
   //@return the song in the first index of the array
   public Song remove() {
        // TODO : Implement this function
	
	if (count <= 0){
		return null;
	}
	//create a new catalog item with the first element of the array
	CatalogItem temp = data[0];
	//move all of the elements in the array to the previous index
	for (int i = 1; i < count; i++){
	
		 data[i-1] = data[i];
		
	
	}	
	count--;
	return temp.getSong();

	}

    
	


    /// remove a specific song from the array by looping through the array and checking if the data at each
    //index equals the song we want to remove.
    //@param song that we want to remove
    //@return the song that is removed
    public Song remove(Song song) {
        // TODO : remove the song that we want to remove. then move all of the values at each index to the 
	// index prior
	
	for (int i = 0; i < count; i++){
		if (data[i].equals(song)){
			//bring 59 down, no else, j= i+ 1
			//same as remove method
			//move all of the data to one index prior 
		CatalogItem y = data[0];
		for (int j = 1; j < count; j++){
		 	data[j-1] = data[j];
		
		}	
		//RHYS YOU TOOK OUT A COUNT-- HERE 
		
		return y.getSong();

	}
	//decrement count 
	count--;
	
	
    }
   return song; 
    }
    
    ///set the current array back to its original state and set the count equal to zero to clear everything
    //@param none
    //@return none
   public void clear() {
        	 
	 data = new CatalogItem[2];
	 count= 0;
	

    }
    
    /// check if the array is empty by checking if the count is greater than zero or not
  public boolean isEmpty() {
        // TODO : Implement this function
	if (count == 0){
		return true;
	}
	
	return false;	
    }

    
    ///check the count by returing count
   public int count() {
        		
	return count;
        
    }
    
    ///check edge case conditions. Find the specific song at i and return it
    //@param index i 
    //@return the song at index i
 public Song get(int i) {
       	if(i < 0 || i>=count){
	   return null;
	 }
  	 if (data[i] == null){
	   return null;
   	}
  	 return data[i].getSong();
      
      
    } 

    ///as long as i is less than count, set a new CatalogItem object equal to the data and check if
    //that catalogItem data equals the song that we are looking to find
    //@param song that we want to check if is contained
    //@return boolean true or false value if it is or isn't in the array
    public boolean contains(Song song) {
   	
	for (int i = 0; i < count; i++){
		//create a new object with the data
		CatalogItem tune = data[i];
		///check if the song at the object equals the song we want to find
		if (tune.getSong().equals(song)){
			return true;
		}
		
	}
	
        return false;
    }

    //--------------------------------------------------------------------
    // Utilities
    //--------------------------------------------------------------------

    /// Returns a truth value indicating whether the catalog's structural
    /// integrity remains valid.  If the integrity is no longer valid,
    /// then the catalog should be invalidated and usage should not be trusted
    /// @return true if the catalog integrity is valid; otherwise, false
    public boolean isIntegrityValid() {
        if(count < 0) {
            return false;
        }
        if(data == null) {
            return false;
        }
        if(count > data.length) {
            return false;
        }
        for(int i = 0; i < count; i++) {
            if(data[i] == null) {
                return false;
            }
        }

        return true;
    }

    /// Returns a string that contains information about the list and the 
    /// contents of the list.  This is mostly useful for visual debugging 
    /// @return a string containing information about the contents of the 
    ///         catalog
    public String toString() {
        String s = "";
        s = "ArrayList::allocated=" + data.length;
        s += ", count=" + count(); 
        s += ", isEmpty=" + isEmpty(); 
        s += ", ["; 
        for(int i = 0; i < count; i++) {
            if(i > 0) {
                s += ", ";
            }
            s += data[i].getSong().getTitle();
            s += " | ";
            s += data[i].getSong().getYear();
        }
        s += "]";
        return s;
    }

    /// Returns the earliest and most recent years of all the songs in the
    /// catalog and then clears the catalog of all songs
    /// @return an array of the years of the earliest and most recent songs
    public int[] publish() {
        int oldYear = 0;
        int newYear = 0;

        for(int i = 0; i < count; i++) {
            int curYear = data[i].getSong().getYear();
            if (curYear < oldYear) oldYear = curYear;
            if (curYear > newYear) newYear = curYear;
        }
        clear();
        return new int[] { oldYear, newYear };
    }

}
