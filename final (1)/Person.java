class Person{
	private int PersonID;
	private String Name;
	private int MatchesPlayed;
	private int Ties;
	private int Wins;
	
	public int getPersonID(){
		return PersonID;
	}

	public void setPersonID(int id){
		this.PersonID = id;
	}

	public String getName(){
		return Name;
	}
	
	public void setName(String Name){
		this.Name = Name;
	}
	
	public int getMatchesPlayed(){
		return MatchesPlayed;
	}

	public void setMatchesPlayed(int MatchesPlayed){
		this.MatchesPlayed = MatchesPlayed;
	}

	public int getTies(){
		return Ties;
	}

	public void setTies(int Ties){
		this.Ties = Ties;
	}

	public int getWins(){
		return Wins;
	}

	public void setWins(int Wins){
		this.Wins = Wins;
	}
}