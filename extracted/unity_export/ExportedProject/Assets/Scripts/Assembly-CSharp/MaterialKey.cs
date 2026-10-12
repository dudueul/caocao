using UnityEngine;

public class MaterialKey
{
	public string m_mainTexturePath;

	public string m_shaderName;

	public int keyRed;

	public int keyGreen;

	public int keyBlue;

	public int red;

	public int green;

	public int blue;

	public MaterialKey(string mainTexturePath, string shaderName, Color32 keyRed, Color32 keyGreen, Color32 keyBlue, Color32 red, Color32 green, Color32 blue)
	{
	}

	public override bool Equals(object obj)
	{
		return false;
	}

	public override int GetHashCode()
	{
		return 0;
	}

	public void SetColors(Color32 red, Color32 green, Color32 blue)
	{
	}
}
