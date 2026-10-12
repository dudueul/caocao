using System;

[Serializable]
public class tk2dSpriteCollectionPlatform
{
	public string name;

	public tk2dSpriteCollection spriteCollection;

	public bool Valid
	{
		get
		{
			return false;
		}
	}

	public void CopyFrom(tk2dSpriteCollectionPlatform source)
	{
	}
}
