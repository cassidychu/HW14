public class VoterHashTableSeparateChaining {

	public Node<Voter>[] hashTable; // made public for testing purposes only!
	public int tableSize; 		    // made public for testing purposes only!

	public VoterHashTableSeparateChaining(int size) {
		hashTable = (Node<Voter>[]) new Node[size];
		tableSize = size;
	}
	
	public int getHashTableLocation(int voterID) {
		// YOUR CODE HERE
		//take voterID and return the location in hash table where voter will be placed
		//usually a priv method but it's public to allow check in driver program
		//method might be very simple and it's okay!
		return voterID % tableSize;
	}
	//completing extra credit
	public boolean addVoter(Voter voterToAdd) {
		// YOUR CODE HERE
		//add new Voter to hash table and return status od whetehr or not the add was successful
		//return boolean bc of EC, if not complete EC return true
		//EC: make it so hashtable does not accept duplicate voters 
		// add comment saying // completing extra credit
		//write only one addVoter method, no duplicates
		Node<Voter> newVoter = new Node(voterToAdd);
		if(getVoter(voterToAdd.getId()) != null) {
			return false;
		}
		//retrieves the location of voter ID
		int location = getHashTableLocation(voterToAdd.getId());
		boolean checkedAllLocations = false; 
		//if location is empty/null it will allow space for new voter
		if(hashTable[location] == null) {
			hashTable[location] = newVoter;
		}
		else {
			Node<Voter> voterToFill = hashTable[location];
			//if not null then it will 
			while(voterToFill != null) {
				if(voterToFill.getNextNode() == null) {
					voterToFill.setNextNode(newVoter);
					break;
				}
				else {
					//adds in space for next voters
					voterToFill = voterToFill.next;
				}
			}
		}
		return true;
	}

	public Voter getVoter(int voterID) {
		// YOUR CODE HERE
		//take in voterID and return Voter object associated with that id, or null if voter
		//for id isn't on table
		//retrieve location of the voters ID
		int location = getHashTableLocation(voterID);
			Node<Voter>currentVoter = hashTable[location];
			boolean found = false;
			Voter foundVoter = null;
			
			//checks if the location isn't null or found for that voter id
			while(currentVoter != null && !found) {
				if(currentVoter.data.getId() == voterID) {
					//checks if the id matches the data if so returns the data within that location
					return currentVoter.data;
				}
				currentVoter = currentVoter.next;
		}
	    //return null since voter id isn't on the table
		return foundVoter;
	}

	public void printTable() {
		for (int i = 0; i < tableSize; i++) {
			if (hashTable[i] != null) {
				System.out.print("Location " + i + ": ");
				Node<Voter> current = hashTable[i];
				while (current != null && current.next != null) {
					System.out.print(current.data.getName() + " -> ");
					current = current.next;
				}
				System.out.println(current.data.getName());
			}
		}
	}

}
