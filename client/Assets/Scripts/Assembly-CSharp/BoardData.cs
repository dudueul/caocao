using LitJson;

public class BoardData
{
	public int id;

	public float star;

	public string myComment;

	public int mySrl;

	public int myRank;

	public int myStar;

	public int myHelpful;

	public int myNotHelpful;

	public bool adminFilter;

	public BoardData(JsonData jsonData)
	{
	}

	public int GetRating()
	{
		return 0;
	}
}
