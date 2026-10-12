using UnityEngine;

public class tk2dFont : MonoBehaviour
{
	public TextAsset bmFont;

	public Material material;

	public Texture texture;

	public Texture2D gradientTexture;

	public bool dupeCaps;

	public bool flipTextureY;

	[HideInInspector]
	public bool proxyFont;

	[HideInInspector]
	[SerializeField]
	private bool useTk2dCamera;

	[HideInInspector]
	[SerializeField]
	private int targetHeight;

	[HideInInspector]
	[SerializeField]
	private float targetOrthoSize;

	public tk2dSpriteCollectionSize sizeDef;

	public int gradientCount;

	public bool manageMaterial;

	[HideInInspector]
	public bool loadable;

	public int charPadX;

	public tk2dFontData data;

	public static int CURRENT_VERSION;

	public int version;

	public void Upgrade()
	{
	}
}
