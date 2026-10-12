using System;
using System.Collections.Generic;

[Serializable]
public class __StickerSet
{
	public string name;

	public int id;

	public string thumbnail;

	public List<int> stickerId;

	public void CopyTo(StickerSet dest)
	{
	}

	public void Reset()
	{
	}
}
