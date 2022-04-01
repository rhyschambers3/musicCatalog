/*--------------------------------------------------------------------------
GWU - CS1112 Data Structures and Algorithms - Spring 2022

This class encapsulates the logic to model list functions to add, remove, 
and get songs in a list similar to a playlist on Spotify or album of songs.

authors: <Rhys Chambers>, Charles Peeke
--------------------------------------------------------------------------*/
public class LinkedList implements MusicCatalog {

    /// For a linked-list based list, the head pointer
    private CatalogItem head;
    /// The counter to track the number of elements in the list 
    private int count;

    // Parameterless Constructor
    public LinkedList() {
        count = 0;
        head = null;
    }

    ///  add songs to the linked list by traversing through the linked list 
    //and checking whether or not the data or song at each node is equal to the song 
    //that we want to remove
    //@param song data containing the song that we want to remove from the linked list
    //@return void but move the pointer continually 
   
    public void add(Song song) {
	    CatalogItem tune = new CatalogItem(song);
	    if (head == null){
		    head = tune;
		}
	    //move the pointer to point.next so that the linked list continues
	    //its search of the song we want to remove
	    else
	     {
		     CatalogItem point = head;
		     while (point.next != null)
			{
				point = point.next;
			}
		     point.next = tune;
	     }
	    count++;

    }
   

    /// Removing the first node in the linked list by making the head pointer point to the 
    //node in our linked list. We are decrementing count when we enter this method because 
    //we are removing songs so the count should decrease.
    //@param none because we are removing the first node
    //@return the song that we remove 
    public Song remove() {
	    
      		
	if (head == null){
		return null;
	}
	CatalogItem temp = head;
	//create a new object and set it to the head of the linked list
	//move the head pointer to the next node
	head = head.next;
	count--;	
	return temp.getSong();



		
       
    }

    ///Removing a specific song in the linked list by setting our head pointer to the next
    //head pointer at the node where we want the song to be removed. Decrementing count
    //to indicate that we have fewer items in our linked list.
    //@param song that we want to remove from the linked list
    //@return the name of the song removed
    public Song remove(Song song) {
        // TODO : Implement this function
	count--;
	CatalogItem temp = head;
	//check edge case conditions
	if (temp != null && temp.getSong() == song){
		//move head to the next node
		head = head.next;
		return temp.getSong();

	}
	//move head to the next node to keep traversing through the linked list
	head = head.next;	
	
	return song;
	
        
    }
    
    /// clearing the linked list and setting it back to its original state 
    public void clear() {
       	head = null;
	count = 0;
    }
    
    //method to check if the linked list is empty by checking if the count equals zero or not
    public boolean isEmpty() {
     	if (count ==0){
		return true;
	}
        return false;
    }
    
    /// method to return the count
    public int count() {
       
        return count;
    }
    
    /// when i is less than count and i is greater than zero, and 
    //create a new pointer pointing to head. When count is less than i, set
    //the list pointer equal to the next node. 
    //@param int i to get a song at a specific index
    //@return the song at i or return null if count > i or i >0
    public Song get(int i) {
        // TODO : Implement this function
	if (i >= count || i < 0){
		return null;
	}
	//create a new pointer
	
	
	CatalogItem listPtr = head;
	//move the pointer
	while (count < i){
		listPtr = listPtr.next;
		
	}
	
        return listPtr.getSong();
    }

    /// when head does not equal null, create a new list pointer and set it equal to head
    //When there are nodes to continue pointing to, chekc if the song at each list pointer equals
    //the song that we are checking is in the list
    ////@param song that you want to check if the list contains
    //@return true if the song is contained in the list
     public boolean contains(Song song) {
        // TODO : Implement this function
 	
	if (head == null){
		return false;
	}
	//create new pointer to check if the song is in the list
	CatalogItem listPtr = head;
	while (listPtr != null){
		Song tune = listPtr.getSong();
		//check if the song at the list pointer is the song we are looking for 
		if (tune.equals(song)){
			return true;
		}
		//move the list pointer to keep going through the list
		listPtr = listPtr.next;
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
            System.out.println("1");
            return false;
        }
        if(count == 0 && head == null) {
            return true;
        }
        if(count == 1 && head != null && head.next == null) {
            return true;
        }

        int n = 1;
        CatalogItem it = head;
        while(it.next != null) {
            it = it.next;
            n++;
        }

        if(n != count) {
            System.out.println("2");
            return false;
        }

        return true;
    }

    /// Returns a string that contains information about the list and the 
    /// contents of the list.  This is mostly useful for visual debugging 
    /// @return a string containing information about the contents of the 
    ///         catalog
    public String toString() {
        String s = "";
        s = "LinkedList::count=" + count(); 
        s += ", isEmpty=" + isEmpty(); 
        s += ", ["; 
        CatalogItem it = head;
        while(it != null) {
            if(it != head) {
                s += ", ";
            }
            s += it.getSong().getTitle();
            s += " | ";
            s += it.getSong().getYear();
            it = it.next;
        }
        s += "]";

        return s; 
    }

    /// Returns the earliest and most recent years of all the songs in the
    /// catalog and then clears the catalog of all songs
    /// @return an array of the years of the earliest and most recent songs
    public int[] publish() {
        int[] years = new int[2];
        int oldYear = Integer.MAX_VALUE;
        int newYear = Integer.MIN_VALUE;

        CatalogItem it = head;
        while(it != null) {
            int curYear = it.getSong().getYear();
            if (curYear < oldYear) {
                oldYear = curYear;
                years[0] = oldYear;
            }
            if (curYear > newYear) {
                newYear = curYear;
                years[1] = newYear;
            }
            it = it.next;
        }
        clear();
        return years;
    }

}
