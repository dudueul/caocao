using System.Collections.Generic;
using UnityEngine;

[ExecuteInEditMode]
public class InvDatabase : MonoBehaviour
{
	private static InvDatabase[] mList;

	private static bool mIsDirty;

	public int databaseID;

	public List<InvBaseItem> items;

	public UIAtlas iconAtlas;

	public static InvDatabase[] list
	{
		get
		{
			return null;
		}
	}

	private void OnEnable()
	{
	}

	private void OnDisable()
	{
	}

	private InvBaseItem GetItem(int id16)
	{
		return null;
	}

	private static InvDatabase GetDatabase(int dbID)
	{
		return null;
	}

	public static InvBaseItem FindByID(int id32)
	{
		return null;
	}

	public static InvBaseItem FindByName(string exact)
	{
		return null;
	}

	public static int FindItemID(InvBaseItem item)
	{
		return 0;
	}
}
