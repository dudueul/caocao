using System;
using System.Collections.Generic;
using LitJson;

public class PostData
{
	public ulong srl;

	public int id;

	public int user_id;

	public string nation_name;

	public int rank;

	public string comment;

	public int star5;

	public int star4;

	public int star3;

	public int star2;

	public int star1;

	public int helpfulCount;

	public int nothelpfulCount;

	public DateTime reg_date;

	public DateTime upd_date;

	public bool voted;

	public PostData(JsonData jsonData, List<ulong> voteBoardSrls)
	{
	}
}
